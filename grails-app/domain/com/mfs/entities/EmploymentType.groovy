package com.mfs.entities

class EmploymentType {

    Integer code
    String value_EN
    String value_MS
    
    static constraints = {
        code unique: true
    }
    
    public String toString() {
        value_EN
    }
}
