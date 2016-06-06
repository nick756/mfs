package com.mfs.entities

class MemberStatus {
    Integer code
    String value_EN
    String value_MS
    
    static constraints = {
        code(nullable: false, unique: true)
        value_EN(nullable: false)
        value_MS (nullable: false)
    }
    
    public String toString() {
        value_EN
    }
}
