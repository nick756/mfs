package com.mfs.actions

import com.mfs.entities.*
import com.mfs.policies.*
import com.mfs.services.*

class MembershipController {

    def formsService
    def membershipService
    def messageSource
    
    /**
     *  Listing all Membership Records, identified by User instance. Listing is
     *  created for respective Branch, subject to exact User Role.
     *  
     *  Org. Admins are shown full listing.
     **/
    def index() { 
        if(!session.user) {
            redirect controller: 'login'
            return
        }
        
        def organization    //  Must always be presented
        def branch          //  Is optional for Admin Role
        
        //  Filtering Options
        def filterName      = params?.filterName ?: null
        def filterICNumber  = params?.filterICNumber ?: null
        def filterICType    = params?.filterICNumber == null ? null : ICType.get(new Integer(params?.filterICType.id))
        def filterState     = params?.filterState == null ? null : State.get(new Integer(params?.filterState.id))
        def filterCity      = params?.filterCity ?: null
        def filterGender    = null
        def filterReligion  = null
        
        
        def listing = Member.createCriteria().list(params) {
            eq('organization', organization)
            
            if(branch) {
                eq('branch', branch)
            }
            
            //  Handling Filtering Options
            
            order('name')
        }
        
        [
            count: result.totalCount,
            memberInstanceListing: listing,
            params: params
        ]
    }
    
    /**
     *  Displaying Form for capturing Retirement Request in pop up Ajax based
     *  Window: initial Step of the process, then navigation to action
     *  'processRetirement'
     */
    def retirement(Member objectInstance) {
        def organization = objectInstance?.organization
        
        render template: 'retirement', model: [
            
        ]
    }
    
    /**
     *  Displaying Form for managing Membership Rejection manually
     */
    def reject(Member objectInstance) {
        render template: '/common/updateDirectForm', model: [
            formData:       formsService.getUpdateDirectForm('rejection'),
            objectInstance: objectInstance,
            subsetData:     null,
            triggered:      false,
            winWidth:       800,
            winHeight:      620
        ]
    }
    
    def processRejection(Member objectInstance) {
        def successMessage = null
        def failureMessage = null
        
        if(!objectInstance.rejectionDate || !objectInstance?.remarksRejection) {
            failureMessage = message(code: 'actions.member.reject.failure')
        }
        else {
            successMessage = message(code: 'actions.member.reject.success')
        }
        
        membershipService.setStatusRejected(objectInstance)
        
        render template: '/common/updateDirectForm', model: [
            formData:       formsService.getUpdateDirectForm('rejection'),
            objectInstance: objectInstance,
            subsetData:     null,
            triggered:      true,
            successMessage: successMessage,
            winWidth:       800,
            winHeight:      620
        ]        
    }
}
