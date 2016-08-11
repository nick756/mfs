package com.mfs.services

import grails.transaction.Transactional
import com.mfs.entities.*

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
                type:               [true, 'select', 'com.mfs.entities.MembershipType'],
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
        ]
    ]
    
    /**
     *  Data Entry forms for multiple Associated Enttities, such as Associates 
     *  and others. To be displayed in pop up Window with pre-prepared instance,
     *  must maintain back link for canceling operation.
     *  
     *   Default classes will be applied to rendered Form, relative to main class
     *   provided.
     */
    final private entryAssociated = [
        newAssociate: [
            name:       'newAssociate',
            object:     'Associate',
            parent:     'Member',
            caption:    'forms.member.add.associate.label', // Content of Window Header
            titleField: 'name',                             //  Attribute of 'parent'
            target:     ['home', 'saveassociate'],          //  Controller, action
            cssClass:   'entry-form',
            fields: [
                name:           [true, 'string'],
                birthDate:      [true, 'date', -50],
                gender:         [true, 'select', 'com.mfs.entities.Gender'],
                relationship:   [true, 'select', 'com.mfs.entities.Relationship'],
                guarantor:      [true, 'boolean'],
                icNumber:       [true, 'string'],
                employer:       [true, 'string'],
                position:       [false, 'string'],
                address:        [true, 'text'],
                city:           [true, 'string'],
                postCode:       [true, 'integer', 5]
            ]
        ],
        updateEmployment: [
            name:       'updateEmpoyment',
            object:     'Employment',
            parent:     'Member',
            caption:    'forms.member.employment.label',    // Content of Window Header
            titleField: 'name',                             //  Attribute of 'parent'
            target:     ['home', 'updateEmployment'],       //  Controller, action
            cssClass:   'entry-form',
            fields: [
                employer:       [true, 'string'],
                joinDate:       [true, 'date', -50],
                sector:         [true, 'select', 'com.mfs.entities.EmploymentSector'],
                type:           [true, 'select', 'com.mfs.entities.EmploymentType'],
                position:       [false, 'string'],
                basicSalary:    [true, 'string'],
                allowance:      [true, 'string'],
                address:        [false, 'text'],
                city:           [true, 'string'],
                postCode:       [false, 'integer', 5],
                contactNumber:  [false, 'string'],
                email:          [false, 'string']
            ]            
        ]
    ]
    
    /**
     *  Forms definitions for direct update of Object instance (not associated).
     *  Forms are Ajax enabled, generated in pop up window. All links must be
     *  rendered as Remote Links.
     *  
     *  Must be supplied:
     *  -   formData        ~ element of this Map
     *  -   objectInstance  ~ master object
     *  -   subsetData      ~ Map with attributes as keys
     *  -   winWidth
     *  -   winHeight
     */
    final private updateDirectForms = [
        editMember: [
            name:           'editmember',
            object:         'Member',            
            caption:        'forms.member.edit.biodata.label',   //  Window's Title
            headerField:    'name',                     //  Content Caption
            target:         ['home', 'updatemember'],
            source:         ['home', 'viewmember', 'arrow_left.png', 'left'],   
            trigger:        false,
            triggerAction:  [],
            fields: [
                number:             [false, false, 'integer', 6],
                branch:             [true, true, 'subset', 'com.mfs.entities.Branch'],
                type:               [true, true, 'select', 'com.mfs.entities.MembershipType'],
                registrationDate:   [true, true, 'date', -2],
                name:               [true, true, 'string'],
                birthDate:          [true, true, 'date', -50],
                icNumber:           [true, true, 'string'],
                gender:             [true, true, 'select', 'com.mfs.entities.Gender'],
                religion:           [true, false, 'select', 'com.mfs.entities.Religion'],
                maritalStatus:      [true, true, 'select', 'com.mfs.entities.MaritalStatus'],
                address:            [true, false, 'text'],
                city:               [true, true, 'string'],
                state:              [true, false, 'select', 'com.mfs.entities.State']                
            ]
        ],
        
        retirement: [
            name:           'retirement',
            object:         'Member',            
            caption:        'forms.member.retirement.label',   //  Window's Title
            headerField:    'name',                     //  Content Caption
            target:         ['membership', 'processRetirement'],
            source:         [],     
            trigger:        false,
            fields: [
                number:             [false, false, 'integer', 6],
                branch:             [false, false, 'subset', 'com.mfs.entities.Branch'],
                type:               [false, false, 'select', 'com.mfs.entities.MembershipType'],
                registrationDate:   [false, false, 'date', 'dd/MM/yyyy'],
                approvalDate:       [false, false, 'date', 'dd/MM/yyyy'],
                icNumber:           [true, true, 'string'],
                gender:             [true, true, 'select', 'com.mfs.entities.Gender'],
                religion:           [true, false, 'select', 'com.mfs.entities.Religion'],
                maritalStatus:      [true, true, 'select', 'com.mfs.entities.MaritalStatus'],
                address:            [true, false, 'text'],
                city:               [true, true, 'string'],
                state:              [true, false, 'select', 'com.mfs.entities.State']                
            ]            
        ],
        
        rejection: [
            name:           'rejection',
            object:         'Member',            
            caption:        'forms.member.reject.label',   //  Window's Title
            headerField:    'name',                     //  Content Caption
            target:         ['membership', 'processRejection'],
            source:         [],         
            trigger:        true,   //  ~ ${triggered} in GSP
            triggerAction:  ['home', 'rejected', 'actions.goto.rejected.label'], //  Conditional Action (Button/Link)
            fields: [
                number:             [false, false, 'integer', 6],
                branch:             [false, false, 'subset', 'com.mfs.entities.Branch'],
                type:               [false, false, 'select', 'com.mfs.entities.MembershipType'],
                registrationDate:   [false, false, 'date', 'dd/MM/yyyy'],
                icNumber:           [false, false, 'string'],
                gender:             [false, false, 'select', 'com.mfs.entities.Gender'],
                religion:           [false, false, 'select', 'com.mfs.entities.Religion'],
                maritalStatus:      [false, false, 'select', 'com.mfs.entities.MaritalStatus'],
                rejectionDate:      [true, true, 'date', -2],
                remarksRejection:   [true, true, 'text']
            ]            
        ],        
        
        pendingProcess: [
            name:           'updatestatus',
            caption:        'forms.statusUpdate.label',
            target:         ['home', 'updatepending'],
            source:         [],
            object:         'Member',
            headerField:    'name',
            fields:     [
                //                  ------------------------------------
                //                  1       2          3       4
                //                  edit    mandatory  type    extra data
                //                  ------------------------------------
                name:               [false, false, 'string'],
                branch:             [false, false, 'string'],
                type:               [false, false, 'string'],
                registrationDate:   [false, false, 'date'],
                city:               [false, false, 'string'],
                state:              [false, false, 'string'],
                approvalDate:       [true, false, 'date', -2],
                approvalAuthority:  [true, true, 'string'],
                remarksApproval:    [true, false, 'text'],
                rejectionDate:      [true, false, 'date', -2],
                remarksRejection:   [true, false, 'text']
            ]
        ]
    ]
    
    /**
     *  To be passed to GSP:
     *  -   formData            element of this Map
     *  -   objectInstance      Associated
     *  -   parentInstance
     *  -   subsetData          If required for Fields
     *  -   winWidth, winHeight Size of Ajax Window
     */
    final private updateAssociated = [
        employment: [
            caption:    'forms.member.employment.edit.label',
            name:       'updateEmployment',
            object:     'Employment',
            parent:     'Member',
            header:     'name',         //  Attribute of Parent Object
            target:     ['home', 'updateEmployment'],
            source:     ['home', 'employmentview', 'arrow_left.png', 'left'],
            fields: [
                //  0           1       2       3       4
                //  Editable    Mand    Type    Class   Option
                employer:       [true, true, 'string', 'view-string-data'],
                sector:         [true, false, 'select', 'com.mfs.entities.EmploymentSector'],
                type:           [true, false, 'select', 'com.mfs.entities.EmploymentType'],
                position:       [true, true, 'string', 'view-string-data'],
                joinDate:       [true, false, 'date', -20],
                retirementDate: [true, false, 'date', -5],
                basicSalary:    [true, true, 'string', 'view-string-data', '#,##0.00'],
//                allowance:      [true, true, 'number', 'view-string-data', '#,##0.00'],
                contactNumber:  [true, false, 'string', 'view-string-data'],
                email:          [true, false, 'string', 'view-string-data'],
                address:        [true, false, 'text', 'view-text-data'],
                city:           [true, false, 'string', 'view-string-data'],
                state:          [true, false, 'select', 'com.mfs.entities.State'],
                postCode:       [true, false, 'string', 'view-string-data', "#####"]                
            ]
        ],
        
        //  Used for create action
        associate: [
            caption:    'forms.member.associate.new.label',
            name:       'createAssociate',
            object:     'Associate',
            parent:     'Member',
            header:     'name',         //  Attribute of Parent Object
            target:     ['associate', 'save'],
            source:     ['home', 'associatesview', 'arrow_left.png', 'left'], 
            fields: [
                name:           [true, true, 'string'],
                relation:       [true, true, 'select', 'com.mfs.entities.Relationship'],
                guarantor:      [true, true, 'boolean'],
                gender:         [true, true, 'select', 'com.mfs.entities.Gender'],
                birthDate:      [true, true, 'date', -50],
                icNumber:       [true, true, 'string'],
                address:        [true, false, 'text'],
                city:           [true, false, 'string'],
                postCode:       [true, false, 'number', '#####'],
                employer:       [true, false, 'string'],
                position:       [true, false, 'string']
            ]
        ],
        
        //  Used for edit action
        associateEdit: [
            caption:    'forms.member.associate.edit.label',
            name:       'createAssociate',
            object:     'Associate',
            parent:     'Member',
            header:     'name',         //  Attribute of Parent Object
            target:     ['associate', 'update'],
            source:     ['home', 'associatesview', 'arrow_left.png', 'left'], 
            fields: [
                name:           [true, true, 'string'],
                relation:       [true, true, 'select', 'com.mfs.entities.Relationship'],
                guarantor:      [true, true, 'boolean'],
                gender:         [true, true, 'select', 'com.mfs.entities.Gender'],
                birthDate:      [true, true, 'date', -50],
                icNumber:       [true, true, 'string'],
                address:        [true, false, 'text'],
                city:           [true, false, 'string'],
                postCode:       [true, false, 'number', '#####'],
                employer:       [true, false, 'string'],
                position:       [true, false, 'string']
            ]
        ],        
    ]
    
    /**
     *  Controllers processing search requests must be attributes aware, as per
     *  specific Search Options - attributes are keys if panel maps panelLeft
     *  and panelRight
     */
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
                    searchIcNumber: ['string', 'search-cell', 'data-entry', 'member.icNumber.label'],
                    searchState:    ['select', 'search-cell', 'data-entry', 'member.state.label', 'com.mfs.entities.State']                    
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
        ]        
    ]
    
    final private tabularViews = [
        activeMembers:  [
            caption:      'lists.members.approved.label',
            cssClass:     'main-content',
            actionsClass: 'centered',
            lineNumber:   true,
            colspan:      7,
            emptyMessage: ['empty-list', 'messages.emptyList.label'],
            actions: [    //  With ID of current instance
                details:    [true, 'icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name', 'view_profile', 'forms.member.profile.label', 1200, 600],
                retire:    [true, 'icons/retirement_small.png', 'icons/retirement_small_over.png', 'actions.icon.retirement.label', 'membership', 'retirement', 'name', 'view_profile', 'forms.member.retirement.label', 800, 600]
            ],
            toolTip:  [   //  Applied to each instance, first column
                ['member.name.label', 'name'],
                ['member.icNumber.label', 'icNumber'],
                ['member.approvalDate.label', 'approvalDate'],
                ['member.state.label', 'state']
            ],
            fields: [
                //              Type     Position   Cell Class    Message Code
                number:       ['integer', 'centered', 'data-input', 'member.number.label', 6],
                name:         ['string', 'lefted', 'data-input', 'member.name.label', 40],
                icNumber:     ['string', 'centered', 'data-input-view', 'member.icNumber.label', 16],
                approvalDate: ['date', 'centered', 'data-input-view', 'member.approvalDate.label', 'dd/MM/yyyy'],
                state:        ['select', 'lefted', 'data-input-view', 'member.state.label', 25]
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
                //          0           1               2           3               4           5           6                   7           8           9     10
                //          ajax flag, image_normal, image_hover, general title, controller, action name, attribute for title, ajax name,   form title width height
                details:    [true, 'icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name', 'view_profile', 'forms.member.profile.label', 1200, 600],
                // update:     [false, 'icons/edit_small.png', 'icons/edit_small_over.png', 'actions.icon.edit.label', 'home', 'updatemember', 'name'],
                process:    [true, 'icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process.label', 'home', 'processpending', 'name', 'view_profile', 'forms.member.approve.label', 800, 500],
                reject:     [true, 'icons/discard_small.png', 'icons/discard_small_over.png', 'actions.icon.reject.label', 'membership', 'reject', 'name', 'view_profile', 'forms.member.reject.label', 800, 620]
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
                details:    [true, 'icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name', 'view_profile', 'forms.member.profile.label', 1200, 600],
                print:      [false, 'icons/printer_small.png', 'icons/printer_small_over.png', 'actions.icon.print.label', 'reportmembership', 'profile', 'name', true]
                //process:    [false, 'icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process.label', 'home', 'processrejected', 'name']
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
                rejectionDate:    ['date', 'centered', 'data-input-view', 'member.rejectionDate.label', 'dd/MM/yyyy'],
                state:            ['select', 'lefted', 'data-input-view', 'member.state.label', 10]
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
                details:    [false, 'icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name'],
                update:     [false, 'icons/edit_small.png', 'icons/edit_small_over.png', 'actions.icon.edit.label', 'home', 'updatemember', 'name'],
                process:    [false, 'icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process', 'home', 'processretired', 'name']
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
                details:    [true, 'icons/details_small.png', 'icons/details_small_over.png', 'actions.icon.details.label', 'home', 'viewmember', 'name', 'view_profile', 'forms.member.profile.label', 1200, 600],
                process:    [false, 'icons/process_small.png', 'icons/process_small_over.png', 'actions.icon.process.shares.label', 'home', 'processshares', 'name', false]
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
            caption:      'forms.member.profile.label',
            cssClass:     ['view-form-container', 'view-row', 'view-cell-caption', 'view-cell-content', 'view-form-footer'],
            tabClass:     ['tabs-container', 'tabs-sec'],
            headerField:  'name',   //  Related to provided objectInstance
            headerClass:  'view-form-header',
            footerClass:  ['footer-caption', 'footer-data'],
            addMenu:      true,
            tabs: [ //  Based on <ul>
                personaldata:       [true, 'selected-item', 'tabs.personaldata.label'],
                employmentdate:     [false, 'unselected-item', 'tabs.employment.label', 'home', 'employmentview'],
                associates:         [false, 'unselected-item', 'tabs.associates.label', 'home', 'associatesview']
            ],
            actionsPane: [
                button1:    ['right', 'data_edit.png', 'home', 'editmember', 'actions.icon.edit.label']                
            ],
            fields: [
                branch:             ['string', 'view-string-data', 'member.branch.label'],
                type:               ['string', 'view-string-data', 'member.type.label'],
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
            caption:            'forms.member.profile.label',
            objectCode:         'employment',
            parentObjectCode:   'member',
            headerField:        'name',
            tabbed:             true,
            tabular:            false,
            clickableRows:      false,
            addMenu:            true,
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
            actionsPane:    [
                button1:    ['right', 'data_edit.png', 'home', 'editEmployment', 'actions.icon.edit.label']                
            ], 
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
            caption:            'forms.member.profile.label',
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
                button1:    ['right', 'data_add.png', 'associate', 'create', 'actions.icon.add.associate.label'],
                button2:    ['right', 'data_edit.png', 'associate', 'edit', 'actions.icon.edit.associate.label']
            ],
            tableFields:        [
                name:       ['string', 'lefted', 40],   //  Clickable Column
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
    
    def getEntryAssociatedForm(String name) {
        this.entryAssociated[name]
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
    
    def getUpdateDirectForm(String name) {
        this.updateDirectForms[name]
    }
    
    def getUpdateAssociatedForm(String name) {
        this.updateAssociated[name]
    }
}
