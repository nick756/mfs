package com.mfs.entities

class MemberNumberGenerator {

    Integer serialNumber
    Date effectiveDate
    
    static belongsTo = [
        organization: Organization
    ]
    
    static constraints = {
    }
    
    public synchronized Integer getNextNumber() {
        ++this.serialNumber
        this.effectiveDate = new Date()
        
        return this.serialNumber
    }
}
