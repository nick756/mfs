package com.mfs.entities

class User implements Serializable {
    
    static transients = ['passwOld', 'passwNew', 'passwRep']
    
    String name
    String login
    String contactNumber
    String email
    String position
    
    String passw
    String passwNew
    String passwOld
    String passwRep
    
    UserRole role
    Organization organization
    Branch branch
    
    static constraints = {
        login           unique: true
        passw           password: true, blank: false, size: 4..10
        email           email: true
        contactNumber   nullable: true
        organization    nullable: true
        branch          nullable: true
        email           nullable: true
        position        nullable: true
        passwOld        nullable: true
        passwRep        nullable: true
    }
    
    static mapping = {
        sort 'name'
    }
    
    public String toString() {
        "${login}: ${name}"
    }
}
