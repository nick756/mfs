package com.mfs.actions

import com.mfs.entities.*
import com.mfs.services.*

class AssociateController {

    def messageSource
    def formsService
    
    def index() { }
    
    def create() {
        Member parentInstance
        def objectInstance
        def subsetData = [:]
        
        if(!objectInstance) {
            objectInstance = new Associate()
        }
        
        //  Suppress any errors on this stage: all further calls are directed
        //  to 'save' action
        objectInstance.errors = null
        
        if(params?.parentID) {
            parentInstance = Member.get(new Integer(params?.parentID))
        }
        
        if(!parentInstance) {
            println "\nAssociate.create: parentInstance is not instantiated"
        }
        
        render template: '/common/updateAssociatedForm', model: [
            formData:   formsService.getUpdateAssociatedForm('associate'),
            objectInstance: objectInstance,
            parentInstance: parentInstance,
            subsetData:     subsetData,
            winWidth:       800,
            winHeight:      600
        ]
    }
    
    def save(Associate objectInstance) {
        Member parentInstance = Member.get(new Integer(params?.parentID))
        def successMessage = null
        def subsetData = [:]
       
        objectInstance.member = parentInstance
        objectInstance.save flush: true
        
        if(!objectInstance.hasErrors()) {
            successMessage = message(code: 'actions.associate.create.success')
        }
        
        render template: '/common/updateAssociatedForm', model: [
            formData:   formsService.getUpdateAssociatedForm('associate'),
            objectInstance: objectInstance,
            parentInstance: parentInstance,
            subsetData:     subsetData,
            successMessage: successMessage,
            winWidth:       800,
            winHeight:      600
        ]        
    }    
    
    /**
     *  Display Form for Associate edit: instance cannot be passed as parameter,
     *  new empty record is created by default (invalidated) - edit button can
     *  be clicked with empty list and null instance.
     */
    def edit() {
        Member parentInstance = Member.get(new Integer(params?.parentID))
        def objectInstance = null
        
        if(params.id) {
            objectInstance = Associate.get(params.id)
        }
        
        if(objectInstance == null) {
            redirect controller: 'home', action: 'associatesview', params: [
                'id': parentInstance?.id
            ]
            
            return
        }

        render template: '/common/updateAssociatedForm', model: [
            formData:   formsService.getUpdateAssociatedForm('associateEdit'),
            objectInstance: objectInstance,
            parentInstance: parentInstance,
            subsetData:     null,
            successMessage: null,
            winWidth:       800,
            winHeight:      600
        ]         
    }

    /**
     *  Actual update of Associate Record
     */
    def update(Associate objectInstance) {
        def successMessage = null
        def parentInstance = objectInstance?.member
        
        if(objectInstance.hasErrors()) {
            render template: '/common/updateAssociatedForm', model: [
                formData:   formsService.getUpdateAssociatedForm('associateEdit'),
                objectInstance: objectInstance,
                parentInstance: parentInstance,
                subsetData:     null,
                successMessage: null,
                winWidth:       800,
                winHeight:      600
            ] 
        
            return
        }
        
        objectInstance.save flush: true
        successMessage = message(code: 'actions.associate.update.success')
        
        render template: '/common/updateAssociatedForm', model: [
            formData:   formsService.getUpdateAssociatedForm('associateEdit'),
            objectInstance: objectInstance,
            parentInstance: parentInstance,
            subsetData:     null,
            successMessage: successMessage,
            winWidth:       800,
            winHeight:      600
        ]         
    }
}
