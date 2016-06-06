package com.mfs.entities

class Relationship {
    Integer code
    String value_EN
    String value_MS
    
    static constraints = {
        code unique: true
        value_EN nullable: false, blank: false
        value_MS nullable: false, blank: false
    }
}
