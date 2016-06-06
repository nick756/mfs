package com.mfs.policies

import com.mfs.entities.*

/**
 *  Default Member Policy setup: differs from Policies setup for Products.
 */
class DMCLink {
    Organization organization
    String name
    
    static hasMany = [
        policies: MemPolicy
    ]
    
    static constraints = {
    }
}
