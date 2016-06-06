package com.mfs.policies

import com.mfs.entities.*

/**
 *  Actual Members' Policies, derived from DMCLink configured for an 
 *  Organization.
 *  
 *  @author Nikolay Krasnikov
 *  @version 1.0
 */
class AMCLink {
    /** Descriptive Name of Policy */
    String name
    /** Value, interpretation depends on type of Charge */
    BigDecimal value
    
    Date dateEffective
    Date dateValid
    boolean active
    
    static belongsTo = [
        policy: MemPolicy,
        member: Member
    ]
    static constraints = {
        dateEffective nullable: true
        dateValid nullable: true
    }
}
