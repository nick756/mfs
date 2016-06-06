package com.mfs.entities

class Employment {

    String              employer
    Date                joinDate
    Date                retirementDate
    EmploymentSector    sector
    EmploymentType      type
    String              position
    BigDecimal          basicSalary
    BigDecimal          allowance
    
    String  address
    String  city
    State   state
    Integer postCode
    
    String contactNumber
    String email
    
    static belongsTo = [
        member: Member
    ]
    
    static constraints = {
        employer        nullable: false
        joinDate        nullable: false
        retirementDate  nullable: true
        type            nullable: true
        sector          nullable: true
        position        nullable: true
        basicSalary     nullable: true
        allowance       nullable: true
        address         nullable: true
        city            nullable: true
        state           nullable: true
        postCode        nullable: true
        contactNumber   nullable: true
        email           nullable: true, email: true
    }
    
    public String toString() {
        employer
    }
}
