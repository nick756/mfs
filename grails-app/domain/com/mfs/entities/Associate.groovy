package com.mfs.entities

class Associate {
    
    String name
    Date birthDate
    Gender gender
    Relationship relation
    boolean guarantor
    
    String icNumber
    
    String address
    String city
    Integer postCode
    
    String employer
    String position
    
    static belongsTo = [
        member: Member
    ]
    
    static constraints = {
        employer nullable: true
        position nullable: true
        address nullable: true
        city nullable: true
        postCode nullable: true
    }
    
    public String toString() {
        name
    }
}
