package com.mfs.actions

import com.mfs.entities.*
import com.mfs.policies.*
import com.mfs.services.*
import com.mfs.utilities.*
import java.text.*
import grails.util.Environment

/**
 *  Home Page for Organizations' Operators, implements Dashboard with data
 *  related to User Role (Branches, etc)
 **/
class HomeController {
    def messageSource
    def menuService
    def formsService
    def grailsApplication
    
    /**
     *  Default action, shows list of Approved Members
     */
    def index() { 
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        params.offset = params.offset ?: 0
        params.max = params.max ?: 3         
        
        //  session.preserveMemberPaging
        //  session.memberPaging::PagingData
        //  session.memberSearchActive
        
        Organization org = Organization.get(session?.organization?.id)
        Branch branch = Branch.get(session?.branch?.id)
        def membersList
        def statusApproved = MemberStatus.findByCode(20)
        
        //  Compiling Search Data List, for dynamic Search Form
        //  Parameters:
        //  -   searchName
        //  -   searchIcNumber
        //  -   searchDateFrom  ~ Approval Date
        //  -   searchDateTill  ~ Approval Date
        //  -   searchState
        
        def memberSearchActive = [:]
        def subsetData = [:]
        Date searchDateFrom = null
        Date searchDateTill = null
        
        //  Compiling Subset Data for Search Panel; HQ Users are shown all
        //  Branches
        
        if(branch.code == 0) {
            subsetData['searchBranch'] = Branch.findAllByOrganization(org).toList()
        }
        else {
            subsetData['searchBranch'] = []
            subsetData['searchBranch'] << branch
        }
        
        if(session.memberSearchActive) {
            memberSearchActive = session.memberSearchActive
        }

        //  Override Session Search Data
        
        if(params.searchName) {
            memberSearchActive['searchName'] = params.searchName
        }
        
        if(params.searchIcNumber) {
            memberSearchActive['searchIcNumber'] = params.searchIcNumber
        }
        
        if(params.searchDateFrom) {
            searchDateFrom = params?.searchDateFrom?.clearTime()
            memberSearchActive['searchDateFrom'] = searchDateFrom
        }
        
        if(params.searchDateTill) {
            searchDateTill = params?.searchDateTill?.clearTime()
            memberSearchActive['searchDateTill'] = searchDateTill
        } 
        
        if(params.searchState?.id) {
            def state = State.get(params.searchState?.id)
            memberSearchActive['searchState'] = state
        }
        
        session.memberSearchActive = memberSearchActive
        
        membersList = Member.createCriteria().list(params) {
            eq('organization', org)
            eq('status', statusApproved)
            
            //  Filter by Branch only if a User is not from Head Quarters
            
            if(branch.code > 0) {
                eq('branch', branch)
            }
            
            if(memberSearchActive.size() > 0) {
                if(memberSearchActive.searchName) {
                    ilike('name', "%${memberSearchActive.searchName}%")
                }
                if(memberSearchActive.searchState) {
                    eq('state', memberSearchActive.searchState)
                }
            }
            
            order 'name', 'asc'            
        }

        //        println ''
        //        println "Action: ${controllerName}/${actionName} ${new Date()}"
        //        println "Hybernate: ${org}/${branch}"
        //        println "Total count: ${Member.list().size()}"
        //        println "Search Date Filter: ${memberSearchActive['searchDateFrom']}"
        
        render view: 'list', model: [
            instancesList:  membersList,
            counter:        membersList.totalCount,
            menuList:       menuService.generateSideMenu('home', 'index'),
            searchForm:     formsService.getSearchForm('searchActiveMember'),
            fragment:       '01',
            searchData:     memberSearchActive,
            tabularView:    formsService.getTabularView('activeMembers'),
            subsetData:     subsetData
        ]
    }
    
    def viewmember(Member objectInstance) {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        def actionActive = actionName
        
        println ''
        println "${new Date()} Location: ${controllerName}/${actionName}"
        
        //render view: '/common/show', model: [
        render template: '/common/viewForm', model: [
            objectInstance: objectInstance,
            menuList: menuService.generateSideMenu('home', actionActive),
            //searchForm: formsService.getSearchForm('searchActiveMember'),
            fragment: '01',
            //searchData: searchData,
            viewForm: formsService.getViewForm('memberPersonalDetails')
        ]        
    }
    
    def resetSearch() {
        def target = params?.target
        
        switch(target) {
        case 'index': 
            session.memberSearchActive = null
            break
        case 'pending':
            session.memberSearchData = null
            break
        case 'retired':
            session.memberSearchRetired = null
            break
        case 'rejected':
            session.memberSearchRejected = null
            break
        case 'shares':
            session.memberSearchShares = null
            break
        }
        
        redirect action: "${target}"
    }
    
    def pending() {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        if(params.offsetReset) {
            session.offsetHomePending = params.offset
        }
        
        if(session?.offsetHomePending) {
            params.offset = session.offsetHomePending ?: 0
        }
        
        params.max = params.max ?: 3
        params.offset = params.offset ?: 0  
        
        Organization org = Organization.get(session?.organization?.id)
        Branch branch = Branch.get(session?.branch?.id) 
        MemberStatus statusPending = MemberStatus.findByCode(10)
        
        //  Compiling Search Data List, for dynamic Search Form
        
        def memberSearchData = [:]
        def membersList
        
        if(session.memberSearchData) {
            memberSearchData = session.memberSearchData
            
            //            memberSearchData.each{key, value ->
            //                params."${key}" = value
            //            }
        }        
        
        Date searchDateFrom = null
        Date searchDateTill = null
        
        if(params.searchName) {
            memberSearchData['searchName'] = params.searchName
        }
        
        if(params.searchIcNumber) {
            memberSearchData['searchIcNumber'] = params.searchIcNumber
        }
        
        if(params.searchDateFrom) {
            searchDateFrom = params?.searchDateFrom
            searchDateFrom = searchDateFrom.clearTime()
            memberSearchData['searchDateFrom'] = searchDateFrom
        }
        
        if(params.searchDateTill) {
            searchDateTill = params?.searchDateTill?.clearTime()
            memberSearchData['searchDateTill'] = searchDateTill
        }
        
        if(params.searchState?.id) {
            def searchState = State.get(new Integer(params.searchState?.id))
            memberSearchData['searchState'] = searchState
        }        
        
        session.memberSearchData = memberSearchData
        
        membersList = Member.createCriteria().list(params) {
            eq('organization', org)
            eq('status', statusPending)
            
            if(branch.code > 0) {
                eq('branch', branch)
            }
            
            if(memberSearchData.searchName) {
                ilike('name', "%${memberSearchData.searchName}%")
            }
            
            if(memberSearchData.searchDateFrom) {
                ge('registrationDate', memberSearchData.searchDateFrom)
            }
            
            if(memberSearchData.searchDateTill) {
                le('registrationDate', memberSearchData.searchDateTill)
            }
            
            if(memberSearchData.searchState) {
                eq('state', memberSearchData.searchState)
            }            
            
            order 'name', 'asc'
        }
        
        println ''
        println "Action: ${controllerName}/${actionName} ${new Date()}"
        println "Hybernate: ${org}/${branch}"
        println "Total count: ${Member.list().size()}"
        println "Search Date Filter: ${memberSearchData['searchDateFrom']}"        
        
        render view: 'list', model: [
            instancesList:  membersList,
            counter:        membersList.totalCount,
            menuList:       menuService.generateSideMenu('home', 'pending'),
            searchForm:     formsService.getSearchForm('searchPendingMember'),
            fragment:       '01',
            searchData:     memberSearchData,
            tabularView:    formsService.getTabularView('pendingMembers')
        ]        
    }
    
    /**
     *  Adding new Member (navigation to the Form)
     */
    def newmember(Member member) {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        if(!member) {
            member = new Member()
        }
        
        Organization org = Organization.get(session?.organization?.id)        
        def subsetData = [:]
        
        subsetData['branch'] = Branch.findAllByOrganization(org).toList()
        
        //subsetData['branch'] = branchList
        
        render template: '/common/genericForm', model: [
            //render view: 'newinstance', model: [
            formData: formsService.getEntryForm('memberentry'),
            menuList: menuService.generateSideMenu('home', 'newmember'),
            fragment: '01',
            objectInstance: member,
            subsetData: subsetData
        ]
    }
    
    /**
     *  Saving new Member instance
     */
    def savemember() {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        def memberInstance
        def organization = Organization.get(session?.organization?.id)
        def branch = Branch.get(session?.branch?.id)
        def statusPending = MemberStatus.findByCode(10) 
        
        Organization org = Organization.get(session?.organization?.id)        
        def subsetData = [:]
        
        def branchList = Branch.findAllByOrganization(org).toList()
        
        subsetData['branch'] = branchList        
        
        try {
            memberInstance = new Member(params)
        }
        catch(AssertionError e) {
            println 'Method: savemember'
            println params
            
            //  Excluding all Dates
            params.registrationDate = null
            params.birthDate = null
            memberInstance = new Member(params)
            
            memberInstance.organization     = organization
            //memberInstance.branch           = branch
            memberInstance.dateCreated      = new Date()
            memberInstance.lastUpdated      = new Date()
            memberInstance.status           = statusPending
            memberInstance.operatorEntry    = session?.user?.name
        
            memberInstance.validate()
            
            render template: '/common/genericForm', model: [
                //render view: 'newinstance', model: [
                formData: formsService.getEntryForm('memberentry'),
                menuList: menuService.generateSideMenu('home', 'newmember'),
                fragment: '01',
                objectInstance: memberInstance,
                errorsList: memberInstance.errors,
                subsetData: subsetData
            ]
            
            return            
        }
        
        println '\nMethod: savemember'
        println "User: ${session?.user?.name}"
        println "Identified: ${organization?.name} Branch: ${branch?.name}"
        println "Params: ${params}"
        
        memberInstance.organization     = organization
        //memberInstance.branch           = branch
        memberInstance.dateCreated      = new Date()
        memberInstance.lastUpdated      = new Date()
        memberInstance.status           = statusPending
        memberInstance.operatorEntry    = session?.user?.name
        
        if(!memberInstance.validate()) {
            render template: '/common/genericForm', model: [
                //render view: 'newinstance', model: [
                formData: formsService.getEntryForm('memberentry'),
                menuList: menuService.generateSideMenu('home', 'newmember'),
                fragment: '01',
                objectInstance: memberInstance,
                errorsList: memberInstance.errors,
                subsetData: subsetData
            ]
            
            return
        }
        else {
            memberInstance.save(flush: true)
        }
        
        //redirect action: 'pending'
        render "Membership Record created: $memberInstance?.name"
    }
    
    /**
     *  Employment View Form
     */
    def employmentview(Member parentInstance) {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        def objectInstances = []
        def employmentID = parentInstance.employment?.id
        def employmentData
        def currentInstance = null
        
        if(employmentID) {
            employmentData = Employment.get(employmentID)
        }
        
        println "Action: employmentview ${new Date()}"
        println "Parent: ${parentInstance?.name} employmentID: ${employmentID}"
        println "Data found: ${employmentData}"
        
        //render view: '/common/showAssociated', model: [
        render template: '/common/viewFormAssociated', model: [
            //menuList:           menuService.generateSideMenu('home', actionName),
            //fragment:           '01',
            viewForm:           formsService.getViewFormAssociated('employmentDetails'),
            parentInstance:     parentInstance,
            instancesList:      objectInstances,
            currentInstance:     employmentData
        ]
    }
    
    def associatesview(Member parentInstance) {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        def instancesList = []
        def currentInstance = null
        
        instancesList = parentInstance?.associates
        
        //render view: '/common/showAssociated', model: [
        render template: '/common/viewFormAssociated', model: [
            menuList:           menuService.generateSideMenu('home', actionName),
            viewForm:           formsService.getViewFormAssociated('associatesDetails'),
            parentInstance:     parentInstance,
            instancesList:      instancesList,
            currentInstance:    currentInstance
        ]
    }
    
    /**
     *  Listing Retired Members for a given Organization (Cooperative)
     */
    def retired() {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        Organization org = Organization.get(session?.organization?.id)
        Branch branch = Branch.get(session?.branch?.id) 
        MemberStatus statusRetired = MemberStatus.findByCode(40)
        
        //  Compiling Search Data for:
        //  searchName
        //  searchIcNumber
        //  searchApprDateFrom
        //  searchApprDateTill
        //  searchRetireDateFrom
        //  searchRetireDateTill
        //  searchState
        
        def memberSearchRetired = [:]
        def membersList
        
        if(session.memberSearchRetired) {
            memberSearchRetired = session.memberSearchRetired
        }
        
        if(params.searchName) {
            memberSearchRetired['searchName'] = params.searchName
        }
        
        if(params.searchIcNumber) {
            memberSearchRetired['searchIcNumber'] = params.searchIcNumber
        }
        
        if(params.searchState?.id) {
            def searchState = State.get(new Integer(params.searchState?.id))
            memberSearchRetired['searchState'] = searchState
        }
        
        if(params.searchApprDateFrom) {
            def searchApprDateFrom = params?.searchApprDateFrom?.clearTime()
            memberSearchRetired['searchApprDateFrom'] = searchApprDateFrom
        } 
        
        if(params.searchApprDateTill) {
            def searchApprDateTill = params?.searchApprDateTill?.clearTime()
            memberSearchRetired['searchApprDateTill'] = searchApprDateTill
        } 
        
        if(params.searchRetireDateFrom) {
            def searchRetireDateFrom = params?.searchRetireDateFrom?.clearTime()
            memberSearchRetired['searchRetireDateFrom'] = searchRetireDateFrom
        }  
        
        if(params.searchRetireDateTill) {
            def searchRetireDateTill = params?.searchRetireDateTill?.clearTime()
            memberSearchRetired['searchRetireDateTill'] = searchRetireDateTill
        }         
        
        session.memberSearchRetired = memberSearchRetired
        
        membersList = Member.createCriteria().list(params) {
            eq('organization', org)
            eq('status', statusRetired)
            
            if(branch.code > 0) {   //  Only for Not HQ
                eq('branch', branch)
            }
            
            if(memberSearchRetired.size() > 0) {
                if(memberSearchRetired.searchName) {
                    ilike('name', "%${memberSearchRetired.searchName}%")
                }
                if(memberSearchRetired.searchIcNumber) {
                    ilike('icNumber', memberSearchRetired.searchIcNumber)
                }
                if(memberSearchRetired.searchApprDateFrom) {
                    ge('approvalDate', memberSearchRetired.searchApprDateFrom)
                }
                if(memberSearchRetired.searchApprDateTill) {
                    le('approvalDate', memberSearchRetired.searchApprDateTill)
                }
                if(memberSearchRetired.searchState) {
                    eq('state', memberSearchRetired.searchState)
                }
            }
            
            order 'name', 'asc'
        }
        
        render view: 'list', model: [
            instancesList:  membersList,
            menuList:       menuService.generateSideMenu('home', 'retired'),
            searchForm:     formsService.getSearchForm('searchRetiredMember'),
            fragment:       '01',
            searchData:     memberSearchRetired,
            tabularView:    formsService.getTabularView('retiredMembers')
        ]        
    }
    
    def rejected() {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        Organization org = Organization.get(session?.organization?.id)
        Branch branch = Branch.get(session?.branch?.id) 
        MemberStatus statusRejected = MemberStatus.findByCode(30)
        
        //  Handling Search Filter
        
        def memberSearchRejected = [:]
        
        if(session.memberSearchRejected) {
            memberSearchRejected = session.memberSearchRejected
        }
        
        if(params.searchName) {
            memberSearchRejected['searchName'] = params.searchName
        }
        
        if(params.searchIcNumber) {
            memberSearchRejected['searchIcNumber'] = params.searchIcNumber
        }
        
        if(params.searchRegDateFrom) {
            def searchRegDateFrom = params?.searchRegDateFrom?.clearTime()
            memberSearchRejected['searchRegDateFrom'] = searchRegDateFrom
        }
        
        if(params.searchRegDateTill) {
            def searchRegDateTill = params?.searchRegDateTill?.clearTime()
            memberSearchRejected['searchRegDateTill'] = params.searchRegDateTill
        }
        
        if(params.searchRejDateFrom) {
            def searchRejDateFrom = params?.searchRejDateFrom?.clearTime()
            memberSearchRejected['searchRejDateFrom'] = searchRejDateFrom
        }
        
        if(params.searchRejDateTill) {
            def searchRejDateTill = params?.searchRejDateTill?.clearTime()
            memberSearchRejected['searchRejDateTill'] = searchRejDateTill
        }
        
        if(params.searchState?.id) {
            def searchState = State.get(new Integer(params.searchState?.id))
            memberSearchRejected['searchState'] = searchState
        }        
        
        session.memberSearchRejected = memberSearchRejected
        
        //  Fetching list of Rejected Members, subject to set Filter
        
        def rejectedList = Member.createCriteria().list(params) {
            eq('organization', org)
            eq('status', statusRejected) 
            
            if(branch.code > 0) {
                eq('branch', branch)
            }
            
            //  Dynamic Filters
            if(memberSearchRejected.size() > 0) {
                if(memberSearchRejected.searchName) {
                    ilike('name', "%${memberSerchRejected.searchName}%")
                }
                if(memberSearchRejected.searchIcNumber) {
                    ilike('icNumber', "%${memberSearchIcNumber}%")
                }
                if(memberSearchRejected.searchState) {
                    eq('state', memberSearchRejected.searchState)
                }                  
            }
            
            order 'name', 'desc'
        }
        
        render view: 'list', model: [
            instancesList:  rejectedList,
            menuList:       menuService.generateSideMenu('home', 'rejected'),
            searchForm:     formsService.getSearchForm('searchRejectedMember'),
            fragment:       '01',
            searchData:     memberSearchRejected,
            tabularView:    formsService.getTabularView('rejectedMembers')
        ]         
    }
    
    /**
     *  Listing Membership Records (except ones with status REJECTED) for
     *  Shares Management: action 'Process' must navigate to List of share
     *  payment records for selected Member,
     */
    def shares() {
        if(!session?.user) {
            redirect controller: 'login'
            return
        }  
        
        if(params.offsetReset) {
            session.offsetHomeShares = params.offset
        }
        
        if(session?.offsetHomeShares) {
            params.offset = session.offsetHomeShares ?: 0
        }
        
        params.max = params.max ?: 3
        params.offset = params.offset ?: 0

        Organization org = Organization.get(session?.organization?.id)
        Branch branch = Branch.get(session?.branch?.id) 
        MemberStatus statusRejected = MemberStatus.findByCode(30)
        
        println 'Action: shares'
        println "Paging Reset: ${params?.offsetReset}"
        println "Offset: ${params.offset}"
        
        def subsetData = [:]
        
        if(branch.code > 0) {
            subsetData['searchBranch'] = []
            sebsetData['searchBranch'] << branch
        }
        else {
            subsetData['searchBranch'] = Branch.findAllByOrganization(org).toList()
        }
        
        //  Handling Search Filter for Membership List (Shares Management)
        
        def memberSearchShares = [:]
        
        if(session.memberSearchShares) {
            memberSearchShares = session.memberSearchShares
        }
        
        if(params.searchName) {
            memberSearchShares['searchName'] = params.searchName
        }
        
        if(params.searchIcNumber) {
            memberSearchShares['searchIcNumber'] = params.searchIcNumber
        }
        
        if(params.searchState?.id) {
            def searchState = State.get(new Integer(params.searchState?.id))
            memberSearchShares['searchState'] = searchState
        } 
        
        if(params.searchBranch?.id) {
            memberSearchShares['searchBranch'] = Branch.get(params.searchBranch?.id)
        }
        
        if(params.searchDateFrom) {
            def searchDateFrom = params?.searchDateFrom?.clearTime()
            memberSearchData['searchDateFrom'] = searchDateFrom
        }
        
        if(params.searchDateTill) {
            def searchDateTill = params?.searchDateTill?.clearTime()
            memberSearchData['searchDateTill'] = searchDateTill
        }        
        
        session.memberSearchShares = memberSearchShares
        
        //  List of Members for Shares Management, subject to set Filtering options
        
        def membersList = Member.createCriteria().list(params) {
            eq('organization', org)
            ne('status', statusRejected) 
            
            if(branch.code > 0) {
                eq('branch', branch)
            }
            
            if(memberSearchShares.size() > 0) {
                if(memberSearchShares.searchName) {
                    ilike('name', "%${memberSearchShares.searchName}%")
                }
                if(memberSearchShares.searchIcNumber) {
                    ilike('icNumber', "%${memberSearchShares.searchIcNumber}%")
                }
                if(memberSearchShares.searchDateFrom) {
                    ge('registrationDate', memberSearchShares.searchDateFrom)
                }
                if(memberSearchShares.searchDateTill) {
                    le('registrationDate', memberSearchShares.searchDateTill)
                }
                if(memberSearchShares.searchState) {
                    eq('state', memberSearchShares.searchState)
                }
                if(branch.code == 0 && memberSearchShares.searchBranch) {
                    eq('branch', memberSearchShares.searchBranch)
                }
            }
            
            order 'name', 'asc'
        }
        
        render view: 'list', model: [
            instancesList:  membersList,
            counter:        membersList.totalCount,
            menuList:       menuService.generateSideMenu('home', 'shares'),
            searchForm:     formsService.getSearchForm('searchSharesMember'),
            fragment:       '01',
            searchData:     memberSearchShares,
            tabularView:    formsService.getTabularView('sharesMembers'),
            subsetData:     subsetData
        ]        
    }
    
    def processpending(Member objectInstance) {
        
        render template: 'processpending', model: [objectInstance: objectInstance]
    }
}
