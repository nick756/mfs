package com.mfs.entities

class Branch {
    
    Integer code
    String name
    String address
    String city
    
    static belongsTo = [
        organization: Organization
    ]
    
    static hasMany = [
        members: Member
    ]

    static constraints = {
        members nullable: true
        address nullable: true
    }
    
    static mapping = {
        sort 'code'
    }
    
    public String toString() {
        name
    }
}
