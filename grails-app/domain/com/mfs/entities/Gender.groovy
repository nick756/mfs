package com.mfs.entities

class Gender {
    Integer code
    String value_EN
    String value_MS
      
    static constraints = {
        code       nullable: false
        value_EN   nullable: false, blank: false
        value_MS   nullable: false, blank: false
    }
    
    public String toString() {
        value_EN 
    }
}
