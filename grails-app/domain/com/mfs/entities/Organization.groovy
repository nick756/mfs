package com.mfs.entities

class Organization implements Serializable {
    
    Integer code
    String name
    String registrationNo
    Date incorporationDate
    
    String address
    String city
    State  state
    Integer postCode
    String email
    String contactNumber
    String contactPerson
    
    MemberNumberGenerator numberGenerator
    
    static hasMany = [
        branches: Branch
    ]

    static constraints = {
        branches nullable: true
        incorporationDate nullable: true
        
        address     nullable: true
        city        nullable: true
        state       nullable: true
        postCode    nullable: true
        email       nullable: true, email: true
        
        contactNumber   nullable: true
        contactPerson   nullable: true
        numberGenerator nullable: true
    }
    
    public String toString() {
        "${String.format('%1$4s', this.code).replaceAll(' ', '0')} ${name}"
    }
}
