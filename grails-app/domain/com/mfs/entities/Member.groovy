package com.mfs.entities

import com.mfs.policies.*

class Member {
    Date dateCreated
    Date lastUpdated
    Date birthDate
    
    Date approvalDate
    Date registrationDate   //  ~ Application Date
    Date rejectionDate
    Date retirementDate
    
    //  Retirement related
    Date retirementRequestDate
    boolean retired
    
    
    Organization    organization
    MemberStatus    status
    MembershipType  type
    
    String name
    Integer number
    String icNumber
    Gender gender
    Religion religion
    MaritalStatus maritalStatus
    
    String address
    String city
    State state
    
    String approvalAuthority
    String remarksGeneral
    String remarksApproval
    String remarksRejection
    
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
        number          nullable: true
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
        type            nullable: false
        
        approvalDate        nullable: true
        registrationDate    nullable: false
        rejectionDate       nullable: true
        retirementDate      nullable: true
        
        approvalAuthority   nullable: true
        remarksGeneral      maxSize: 10240, nullable: true
        remarksApproval     maxSize: 10240, nullable: true
        remarksRejection    maxSize: 10240, nullable: true
        
        retirementRequestDate   nullable: true
        retired                 nullable: true
    }
    
    public String toString() {
        name
    }
}
