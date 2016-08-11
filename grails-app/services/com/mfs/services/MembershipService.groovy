package com.mfs.services

import com.mfs.entities.*
import grails.transaction.Transactional
import groovy.transform.Synchronized

@Transactional(readOnly = true)
class MembershipService {

    def verifyEmployment(Member objectInstance) {
        if(!objectInstance?.employment) {
            return false
        }
        else {
            if(objectInstance?.basicSalary > 0 && objectInstance?.joinDate != null) {
                return true
            }
            else return false
        }
    }
    
    def createEmployment(Member member) {
        
        if(!member?.employment) {
            def employment = new Employment(
                employer:       '*** Not Yet Defined ***',
                basicSalary:    0,
                allowance:      0,
                member:         member
            )
            
            if(!employment.save(flush: true)) {
                println "Exception while trying to create Employment for ${member?.name}"
                employment.errors.allErrors.each{
                    println it
                } 
            }
        
            member.employment = employment
            member.save(flush: true)
            
            return true
        }
        else {
            return false
        }
    }
    
    def verifyAssociates(Member objectInstance) {
        if(!objectInstance?.associates) {
            return false
        }
        else {
            return true
        }
    }
    
    /**
     *  Assigning to a given Member instance new serial Number
     */
    def assignNumber(Member member) {
        
        println '====================================='
        println 'Inside MembershipService.assignNumber'
        
        if(!member) return false
        
        println "Member passed: ${member?.name}"
        
        def org = member?.organization
        def generator = org?.numberGenerator
        
        println "Organization: ${org?.name} Generator: ${generator?.serialNumber}"
        
        if(generator && !member?.number) {
            member.number = generator.getNextNumber()
            member.save()
            generator.serialNumber = member.number
            generator.save(flush: true)
            
            println "New Generator value: ${generator?.serialNumber}"
            
            org.numberGenerator = generator
            org.save(flush: true)
            
            println '=====================================\n'
            
            return true
        }
        else {
            return false
        }
    }

    def setStatusPending(Member member) {
        def status = MemberStatus.findByCode(10)
        member?.status = status
        member.save flush: true
        return true        
    }
    
    def setStatusApproved(Member member) {
        def status = MemberStatus.findByCode(20)
        member?.status = status
        member.save flush: true
        return true        
    }
    
    def setStatusRejected(Member member) {
        def status = MemberStatus.findByCode(30)
        member?.status = status
        member.save flush: true
        return true
    }
    
    def setStatusRetired(Member member) {
        def status = MemberStatus.findByCode(40)
        member?.status = status
        member.save flush: true
        return true        
    }
    
    def setStatusDeceased(Member member) {
        def status = MemberStatus.findByCode(50)
        member?.status = status
        member.save flush: true
        return true        
    }
}
