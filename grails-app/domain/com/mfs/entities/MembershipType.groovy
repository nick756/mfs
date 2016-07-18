package com.mfs.entities

class MembershipType {

    Integer code
    String value_EN
    String value_MY
    
    static constraints = {
        code        nullable: false
        value_EN    blank: false
        value_MY    blank: false
    }
    
    static mapping = {
        sort 'code'
    }
    
    public String toString() {
        value_EN
    }
}
