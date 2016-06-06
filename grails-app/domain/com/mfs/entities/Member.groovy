package com.mfs.entities

import com.mfs.policies.*

class Member {
    Date dateCreated
    Date lastUpdated
    Date birthDate
    
    Date approvalDate
    Date registrationDate
    Date rejectionDate
    Date retirementDate
    
    Organization organization
    MemberStatus status
    
    String name
    String icNumber
    Gender gender
    Religion religion
    MaritalStatus maritalStatus
    
    String address
    String city
    State state
    
    String operatorEntry
    String operatorUpdate
    String approvedBy
    String rejectedBy
    
    static belongsTo = [
        branch: Branch
    ]
    
    /**
     *  
     *  @param associates List of Relatives, Guarantors, etc
     *  @param mCharges Link to assigned Membership Fees and Charge
     */  
    static hasMany = [
        associates: Associate,
        mCharges: AMCLink
    ]
    
    static hasOne = [
        employment: Employment
    ]
    
    static constraints = {
        associates      nullable: true
        mCharges        nullable: true
        religion        nullable: true
        address         nullable: true
        city            blank: false, nullable: true
        state           nullable: true
        gender          nullable: true
        operatorEntry   nullable: true
        operatorUpdate  nullable: true
        approvedBy      nullable: true
        rejectedBy      nullable: true
        employment      nullable: true
        maritalStatus   nullable: true
        
        approvalDate        nullable: true
        registrationDate    nullable: false
        rejectionDate       nullable: true
        retirementDate      nullable: true
    }
    
    public String toString() {
        name
    }
}
