package com.mfs.entities

class State {
    Integer code
    String name
    
    static constraints = {
        code nullable: false, unique: true
        name nullable: false
    }
    
   
    public String toString() {
        "${String.format('%1$2s', code).replace(' ', '0')}: ${name}"
    }
}
