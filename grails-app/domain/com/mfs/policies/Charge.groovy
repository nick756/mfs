package com.mfs.policies

import com.mfs.entities.*

class Charge {
    String name
    
    static belongsTo = [
        member: Member
    ]
    
    static hasMany = [
        memberPolicies: AMCLink
    ]
    
    static constraints = {
        memberPolicies nullable: true
    }
}
