package com.mfs.services

import grails.transaction.Transactional
import grails.mfs.entities.*

/**
 *  <h1>Dynamic Forms Service</h1>
 *  <p>
 *  Class contains Maps with Forms definition for cases:
 *  <p>
 *  1. Data Entry Froms (replacement of standard Scaff-folding options)<br/>
 *  2. Tabular View for displaying lists of various Domain Objects<br/>
 *  3. Search Froms to accomplish Tabular Views
 *  <p>
 *  Supported Control Types (Field Types) for Data Entry Forms:<br>
 *  <pre>{@code string} : Mapped into {@code <g:textField>} Tag</pre>
 *  <pre>{@code date}   : Mapped into {@code <g:datePicker>} Widget (precision = 'day')</pre>
 *  <pre>{@code select} : Mapped into {@code <g:select>} Tag</pre>
 *  <pre>{@code text}   : Mapped into {@code <g:textArea>} Tag</pre>
 *  <pre>{@code number} : Mapped into {@code <g:formatNumber>} Tag</pre>
 *  
 *  @version    1.0.0
 *  @since      March 2016
 */
@Transactional (readOnly = true)
class FormsService {
    
    /**
     *  Conventions for Fields in Data Entry Forms:
     *  1. Mandatory (true|false)
     *  2. Type
     *  3. Either, subject to Data Type:
     *      - Lookup object for 'select'
     *      - Offset in years for 'date'
     *      - Object for 'subset' (Instances are passed through subsetData Map)
     *        All instances of a Doman cannot be used here: must be defined in
     *        Controller.
     *      - Null for other Types
     */
    final private entryForms = [
        memberentry : [   //  Entering new Member instance
            name:     'newmember',
            caption:  'forms.addmember.caption',
            target:   ['home', 'savemember'],
            object:   'Member',
            fields:  [
                branch:             [true, 'subset', 'com.mfs.entities.Branch'],
                registrationDate:   [true, 'date', -2],
                name:               [true, 'string'],
                birthDate:          [true, 'date', -50],
                icNumber:           [true, 'string'],
                gender:             [true, 'select', 'com.mfs.entities.Gender'],
                religion:           [false, 'select', 'com.mfs.entities.Religion'],
                maritalStatus:      [true, 'select', 'com.mfs.entities.MaritalStatus'],
                address:            [false, 'text'],
                city:               [true, 'string'],
                state:              [false, 'select', 'com.mfs.entities.State']
            ]
        ],
        employmentdetails: [  //  Entering Employment Details (Second Step)
            name:     'employment',
            caption:  'forms.epmloyment.label',
            target:   ['home', 'saveemployment'],
            object:   'Employment',
            fields:   [
                
            ]
        ]
    ]
    
    final private searchForms = [
        searchActiveMember: [
            name:         'searchmember',
            cssClass:     ['search-pane', 'search-row', 'search-footer'],
            targetMain:   ['home', 'index'],
            targetReset:  ['home', 'resetSearch'],
            buttons:  [   //  Image and message code of each button
                search:   ['icons/search_round.png', 'actions.search.label', 'icons/data_find.png'],
                reset:    ['icons/reset_round.png', 'actions.reset.label', 'icons/reset.png']
            ],
            fields:   [
                paneLeft: [
                    searchName:     ['string', 'search-cell', 'data-entry', 'member.name.label'],
                    searchIcNumber:     ['string', 'search-cell', 'data-entry', 'member.icNumber.label'],
                    searchState:        ['select', 'search-cell', 'data-entry', 'member.state.label', 'com.mfs.entities.State']                    
                ],
                paneRight: [
                    searchBranch:   ['subset', 'search-cell', 'data-entry', 'member.branch.label'],
                    searchDateFrom: ['date', 'search-cell', 'data-entry', 'member.approvalDate.from.label', -10],
                    searchDateTill: ['date', 'search-cell', 'data-entry', 'member.approvalDate.till.label', -10]
                ]
            ]
        ],
        
        'searchPendingMember': [
            'name':         'searchmember',
            'cssClass':     ['search-pane', 'search-row', 'search-footer'],
            'targetMain':   ['home', 'pending'],
            'targetReset':  ['home', 'resetSearch'],
            'buttons':  [   //  Image and message code of each button
                'search':   ['icons/search_round.png', 'actions.search.label', 'icons/data_find.png'],
                'reset':    ['icons/reset_round.png', 'actions.reset.label', 'icons/reset.png']
            ],
            'fields':   [
                'paneLeft': [
                    'searchName':     ['string', 'search-cell', 'data-entry', 'member.name.label'],
                    'searchIcNumber':     ['string', 'search-cell', 'data-entry', 'member.icNumber.label'],
                    'searchState':        ['select', 'search-cell', 'data-entry', 'member.state.label', 'com.mfs.entities.State']                    
                ],
                'paneRight': [
                    'searchDateFrom': ['date', 'search-cell', 'data-entry', 'member.registrationDate.from.label', -10],
                    'searchDateTill': ['date', 'search-cell', 'data-entry', 'member.registrationDate.till.label', -10]
                ]
            ]
        ],
        
        searchRetiredMember: [
            name:         'searchmember',
            cssClass:     ['search-pane', 'search-row', 'search-footer'],
            targetMain:   ['home', 'retired'],
            targetReset:  ['home', 'resetSearch'],
            buttons:  [   //  Image and message code of each button
                search:   ['icons/search_round.png', 'actions.search.label', 'icons/search_round_over/png'],
                reset:    ['icons/reset_round.png', 'actions.reset.label', 'icons/reset_round_over.png']
            ],
            fields:   [
                paneLeft: [
                    searchName:         ['string', 'search-cell', 'data-entry', 'member.name.label'],
                    searchIcNumber:     ['string', 'search-cell', 'data-entry', 'member.icNumber.label'],
                    searchState:        ['select', 'search-cell', 'data-entry', 'member.state.label', 'com.mfs.entities.State']                    
                ],
                paneRight: [
                    searchApprDateFrom:   ['date', 'search-cell', 'data-entry', 'member.approvalDate.from.label', -10],
                    searchApprDateTill:   ['date', 'search-cell', 'data-entry', 'member.approvalDate.till.label', -10],
                    searchRetireDateFrom:   ['date', 'search-cell', 'data-entry', 'member.retirementDate.from.label', -10],
                    searchretireDateTill:   ['date', 'search-cell', 'data-entry', 'member.retirementDate.till.label', -10]
                ]
            ]            
        ],
        
        searchRejectedMember: [
            name:         'searchmember',
            cssClass:     ['search-pane', 'search-row', 'search-footer'],
            targetMain:   ['home', 'rejected'],
            targetReset:  ['home', 'resetSearch'],
            buttons:  [   //  Image and message code of each button
                search:   ['icons/search_round.png', 'actions.search.label', 'icons/search_round_over.png'],
                reset:    ['icons/reset_round.png', 'actions.reset.label', 'icons/reset_round_over.png']
            ],
            fields:   [
                paneLeft: [
                    searchName:         ['string', 'search-cell', 'data-entry', 'member.name.label'],
                    searchIcNumber:     ['string', 'search-cell', 'data-entry', 'member.icNumber.label'],
                    searchState:        ['select', 'search-cell', 'data-entry', 'member.state.label', 'com.mfs.entities.State']                    
                ],
                paneRight: [
                    searchRegDateFrom:  ['date', 'search-cell', 'data-entry', 'member.registrationDate.from.label', -10],
                    searchRegDateTill:  ['date', 'search-cell', 'data-entry', 'member.registrationDate.till.label', -10],
                    searchRejDateFrom:  ['date', 'search-cell', 'data-entry', 'member.rejectionDate.from.label', -10],
                    searchRejDateTill:  ['date', 'search-cell', 'data-entry', 'member.rejectionDate.till.label', -10]
                ]
            ]             
        ],
        
        searchSharesMember: [
            name:         'searchmember',
            cssClass:     ['search-pane', 'search-row', 'search-footer'],
            targetMain:   ['home', 'shares'],
            targetReset:  ['home', 'resetSearch'],
            buttons:  [   //  Image and message code of each button
                search:   ['icons/search_round.png', 'actions.search.label', 'icons/data_find.png'],
                reset:    ['icons/reset_round.png', 'actions.reset.label', 'icons/reset.png']
            ],
            fields:   [
                paneLeft: [
                    searchName:     ['string', 'search-cell', 'data-entry', 'member.name.label'],
                    searchIcNumber: ['string', 'search-cell', 'data-entry', 'member.icNumber.label'],
                    searchState:    ['select', 'search-cell', 'data-entry', 'member.state.label', 'com.mfs.entities.State']                    
                ],
                paneRight: [
                    searchBranch:   ['subset', 'search-cell', 'data-entry', 'member.branch.label'],
                    searchDateFrom: ['date', 'search-cell', 'data-entry', 'member.registrationDate.from.label', -10],
                    searchDateTill: ['date', 'search-cell', 'data-entry', 'member.registrationDate.till.label', -10]
                ]
            ]
        ],        
    ]
    
    final private tabularViews = [
        activeMembers:  [
            caption:      'lists.members.approved.label',
            cssClass:     'main-content',
            actionsClass: 'centered',
            lineNumber:   true,
            colspan:      6,
            emptyMessage: ['empty-list', 'messages.emptyList.label'],
            actions: [    //  With ID of current instance
                details:  ['icons/edit_small.png', 'actions.icon.details.label', 'controller', 'action', '']
            ],
            toolTip:  [   //  Applied to each instance, first column
                ['member.name.label', 'name'],
                ['member.icNumber.label', 'icNumber'],
                ['member.approvalDate.label', 'approvalDate'],
                ['member.state.label', 'state']
            ],
            fields: [
                //              Type     Position   Cell Class    Message Code
                name:         ['string', 'lefted', 'data-input', 'member.name.label', 40],
                icNumber:     ['string', 'centered', 'data-input-view', 'member.icNumber.label', 16],
                approvalDate: ['date', 'centered', 'data-input-view', 'member.approvalDate.label', 'dd/MM/yyyy'],
                state:        ['string', 'lefted', 'data-input-view', 'member.state.label', 10]
            ]
        ],
        
        pendingMembers: [
            caption:      'lists.members.pending.label',
            cssClass:     'main-content',
            actionsClass: 'centered',
            lineNumber:   true,
            colspan:      6,  //  Must include line number, if set to true
            emptyMessage: ['empty-list', 'messages.emptyList.label'],
            actions: [    //  With ID of current instance
                details:    ['icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name'],
                update:     ['icons/edit_small.png', 'icons/edit_small_over.png', 'actions.icon.edit.label', 'home', 'updatemember', 'name'],
                process:    ['icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process.label', 'home', 'processpending', 'name']
            ],
            toolTip:  [   //  Applied to each instance, first column
                ['member.name.label', 'name'],
                ['member.icNumber.label', 'icNumber'],
                ['member.approvalDate.label', 'approvalDate'],
                ['member.state.label', 'state']
            ],
            fields: [
                name:             ['string', 'lefted', 'data-input', 'member.name.label', 40],
                icNumber:         ['string', 'centered', 'data-input-view', 'member.icNumber.label', 16],
                registrationDate: ['date', 'centered', 'data-input-view', 'member.registrationDate.label', 'dd/MM/yyyy'],
                state:            ['select', 'lefted', 'data-input-view', 'member.state.label', 25]
            ]            
        ],
        
        rejectedMembers: [
            caption:      'lists.members.rejected.label',
            cssClass:     'main-content',
            actionsClass: 'centered',
            lineNumber:   true,
            colspan:      6,  //  Must include line number, if set to true
            emptyMessage: ['empty-list', 'messages.emptyList.label'],
            actions: [    //  With ID of current instance
                details:    ['icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name'],
                update:     ['icons/edit_small.png', 'icons/edit_small_over.png', 'actions.icon.edit.label', 'home', 'updatemember', 'name'],
                process:    ['icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process.label', 'home', 'processrejected', 'name']
            ],
            toolTip:  [   //  Applied to each instance, first column
                ['member.name.label', 'name'],
                ['member.icNumber.label', 'icNumber'],
                ['member.rejectionDate.label', 'rejectionDate'],
                ['member.state.label', 'state']
            ],
            fields: [
                name:             ['string', 'lefted', 'data-input', 'member.name.label', 40],
                icNumber:         ['string', 'centered', 'data-input-view', 'member.icNumber.label', 16],
                rejectedDate:     ['date', 'centered', 'data-input-view', 'member.rejectionDate.label', 'dd/MM/yyyy'],
                state:            ['string', 'lefted', 'data-input-view', 'member.state.label', 10]
            ]             
        ],
        
        retiredMembers: [
            caption:      'lists.members.retired.label',
            cssClass:     'main-content',
            actionsClass: 'centered',
            lineNumber:   true,
            colspan:      6,  //  Must include line number, if set to true
            emptyMessage: ['empty-list', 'messages.emptyList.label'],
            actions: [    //  With ID of current instance
                details:    ['icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name'],
                update:     ['icons/edit_small.png', 'icons/edit_small_over.png', 'actions.icon.edit.label', 'home', 'updatemember', 'name'],
                process:    ['icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process', 'home', 'processretired', 'name']
            ],
            toolTip:  [   //  Applied to each instance, first column
                ['member.name.label', 'name'],
                ['member.icNumber.label', 'icNumber'],
                ['member.approvalDate.label', 'approvalDate'],
                ['member.state.label', 'state']
            ],
            fields: [
                name:             ['string', 'lefted', 'data-input', 'member.name.label', 40],
                icNumber:         ['string', 'centered', 'data-input-view', 'member.icNumber.label', 16],
                retirementDate:   ['date', 'centered', 'data-input-view', 'member.retirementDate.label', 'dd/MM/yyyy'],
                state:            ['string', 'lefted', 'data-input-view', 'member.state.label', 10]
            ]              
        ],
        
        sharesMembers: [
            caption:      'lists.members.shares.label',
            cssClass:     'main-content',
            actionsClass: 'centered',
            lineNumber:   true,
            colspan:      7,  //  Must include line number, if set to true
            emptyMessage: ['empty-list', 'messages.emptyList.label'],
            actions: [    //  With ID of current instance
                details:    ['icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name'],
                process:    ['icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process.shares.label', 'home', 'processshares', 'name']
            ],
            toolTip:  [   //  Applied to each instance, first column
                ['member.name.label', 'name'],
                ['member.icNumber.label', 'icNumber'],
                ['member.approvalDate.label', 'approvalDate'],
                ['member.state.label', 'state']
            ],
            fields: [
                name:       ['string', 'lefted', 'data-input', 'member.name.label', 40],
                icNumber:   ['string', 'centered', 'data-input-view', 'member.icNumber.label', 16],
                status:     ['select', 'centered', 'data-input-view', 'member.status.label', 20],
                branch:     ['select', 'lefted', 'data-input-view', 'member.branch.label', 10],
                state:      ['select', 'lefted', 'data-input-view', 'member.state.label', 25]
            ]              
        ],
        
        associates: [
            
        ],
        activeLoans: [
            
        ],
        pendingLoans: [
            
        ],
        rejectedLoans: [
            
        ],
        settledLoans: [
            
        ],
        delinquentLoans: [
            
        ]
    ]
    
    final private viewForms = [
        memberPersonalDetails: [
            objectCode:   'member',
            tabbed:       true,
            cssClass:     ['view-form-container', 'view-row', 'view-cell-caption', 'view-cell-content', 'view-form-footer'],
            tabClass:     ['tabs-container', 'tabs-sec'],
            headerField:  'name',   //  Related to provided objectInstance
            headerClass:  'view-form-header',
            footerClass:  ['footer-caption', 'footer-data'],
            tabs: [ //  Based on <ul>
                personaldata:       [true, 'selected-item', 'tabs.personaldata.label'],
                employmentdate:     [false, 'unselected-item', 'tabs.employment.label', 'home', 'employmentview'],
                associates:         [false, 'unselected-item', 'tabs.associates.label', 'home', 'associatesview']
            ],
            fields: [
                branch:             ['string', 'view-string-data', 'member.branch.label'],
                status:             ['string', 'view-string-data', 'member.status.label'],
                registrationDate:   ['date', 'view-string-data', 'member.registrationDate.label', 'dd/MM/yyyy'],
                approvalDate:       ['date', 'view-string-data', 'member.approvalDate.label', 'dd/MM/yyyy'],
                rejectionDate:      ['date', 'view-string-data', 'member.rejectionDate.label', 'dd/MM/yyyy'],
                retirementDate:     ['date', 'view-string-data', 'member.retirementDate.label', 'dd/MM/yyyy'],
                blankLine1:         ['blank', 'view-blank-row'],
                birthDate:          ['date', 'view-string-data', 'member.birthDate.label', 'dd/MM/yyyy'],
                gender:             ['string', 'view-string-data', 'member.gender.label'],
                religion:           ['string', 'view-string-data', 'member.religion.label'],
                address:            ['text', 'view-text-data', 'member.address.label'],
                city:               ['string', 'view-string-data', 'member.city.label'],
                state:              ['string', 'view-string-data', 'member.state.label']
            ],
            footer: [
                dateCreated:        ['date', 'view-string-data', 'member.dateCreated.label', 'dd/MM/yyyy HH:mm:ss'],
                operatorEntry:      ['string', 'view-string-data', 'member.operatorEntry.label']
            ]
        ]
    ]
    
    /**
     *  Parameters to be passed to respective View for rendering Form:
     *  <ol>
     *  <li>Instance of Parent Object</li>
     *  <li>List of instances for Associated Object</li>
     *  </ol>
     */
    final private viewFormsAssociated = [
        //  Employment Details, single association
        employmentDetails: [
            objectCode:         'employment',
            parentObjectCode:   'member',
            headerField:        'name',
            tabbed:             true,
            tabular:            false,
            clickableRows:      false,
            addMenu:            false,
            styles: [
                main:   ['view-form-container', 'view-row', 'view-cell-caption', 'view-cell-content'],
                header: 'view-form-header',
                tab:    ['tabs-container', 'tabs-sec'],
                table:  [],
                click:  [],
                footer: ['footer-caption', 'footer-data'],
                topPanel:  []
            ],
            tabs: [
                personaldata:       [false, 'unselected-item',  'tabs.personaldata.label',  'home', 'viewmember'],
                employmentdate:     [true,  'selected-item',    'tabs.employment.label'],
                associates:         [false, 'unselected-item',  'tabs.associates.label',    'home', 'associatesview']                
            ],
            inlineActions:  [],     //  Inline actions for instances of associated Object
            actionsPane:    [],     //  Actions Panel definitions
            tableFields:    [],
            //  Keys must exactly follow names of respective Attributes
            detailFields:   [
                employer:       ['string', 'view-string-data'],
                sector:         ['string', 'view-string-data'],
                type:           ['string', 'view-string-data'],
                position:       ['string', 'view-string-data'],
                joinDate:       ['date', 'view-string-data'],
                retirementDate: ['date', 'view-string-data'],
                basicSalary:    ['number', 'view-string-data', '#,##0.00'],
                allowance:      ['number', 'view-string-data', '#,##0.00'],
                contactNumber:  ['string', 'view-string-data'],
                email:          ['string', 'view-string-data'],
                address:        ['text', 'view-text-data'],
                city:           ['string', 'view-string-data'],
                state:          ['string', 'view-string-data'],
                postCode:       ['number', 'view-string-data', "#####"]
            ],
            footerFields: [ //  Related to associated Object
                dateCreated:        ['date', 'view-string-data', 'member.dateCreated.label', 'dd/MM/yyyy HH:mm:ss'],
                operatorEntry:      ['string', 'view-string-data', 'member.operatorEntry.label']                
            ]
        ],
        
        //  Multiple Instances of Associate, must include Tabular View
        associatesDetails: [
            objectCode:         'associate',
            parentObjectCode:   'member',
            headerField:        'name',     //  Of parentObject
            emptyMessage:       ['messages.emptyList.label'],
            colspan:            5,
            tabbed:             true,
            tabular:            true,
            clickableRows:      true,
            addMenu:            true,       //  Actions Panel, depends on actionsPane Map
            styles:             [
                main:   ['view-form-container', 'view-row', 'view-cell-caption', 'view-cell-content'],
                header: 'view-form-header',
                tab:    ['tabs-container', 'tabs-sec'],
                table:  ['main-content'],
                click:  [],
                footer: ['footer-caption', 'footer-data']
            ],
            tabs:               [
                personaldata:       [false, 'unselected-item',  'tabs.personaldata.label',  'home', 'viewmember'],
                employmentdate:     [false,  'unselected-item',    'tabs.employment.label', 'home', 'employmentview'],
                associates:         [true, 'selected-item',  'tabs.associates.label',    'home', 'associatesview']                
            ],
            inlineActions:      [
                
            ],
            actionsPane:        [
                button1:    ['right', 'home', 'addassociate', 'actions.icon.add.associate.label']
            ],
            tableFields:        [
                name:       ['string', 'lefted', 40],
                gender:     ['select', 'centered', 25],
                relation:   ['select', 'lefted', 30],
                icNumber:   ['string', 'centered', 50]
            ],
            detailFields:       [
                name:       ['string', 'view-string-data'],
                icNumber:   ['string', 'view-string-data'],
                gender:     ['string', 'view-string-data'],
                relation:   ['string', 'view-string-data'],
                guarantor:  ['boolean', 'view-string-data'],
                employer:   ['string', 'view-string-data'],
                position:   ['string', 'view-string-data'],
                address:    ['text', 'view-text-data'],
                city:       ['string', 'view-string-data'],
                postCode:   ['number', 'view-string-data', '#####']
            ],
            footerFields:       [
                
            ]
        ]
    ]

    /**
     * Fetching definition of generic Data Entry Form.
     *  
     * @param formName Name of a Data Entry Form the definition of which is requested
     * @return Map with Form definition in format:<br>
     * <pre>
     * 'formName': [
     *      'name':     'string',
     *      'caption':  'Message Code',
     *      'cssClass': 'cssClass for entire form',
     *      'target':   ['Controller', 'Action'],
     *      'fields':   [
     *          'field1': [true|false, 'cssClass', 'Type', 'Message Code', {'full.path.Object'}],
     *          ...
     *          'fieldN': [true|false, 'cssClass', 'Type', 'message code', {'full.path.Object'}]
     *      ]
     * ]
     * </pre>
     */
    def getEntryForm(String formName) {
        this.entryForms[formName]
    }
    
    def getTabularView(String viewName) {
        this.tabularViews[viewName]
    }
    
    def getSearchForm(String formName) {
        this.searchForms[formName]
    }
    
    def getViewForm(String name) {
        this.viewForms[name]
    }
    
    def getViewFormAssociated(String name) {
        this.viewFormsAssociated[name]
    }
}
