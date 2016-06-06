package com.mfs.entities

class Organization implements Serializable {
    
    Integer code
    String name
    String registrationNo
    Date incorporationDate
    
    static hasMany = [
        branches: Branch
    ]

    static constraints = {
        branches nullable: true
        incorporationDate nullable: true
    }
    
    public String toString() {
        "${String.format('%1$4s', this.code).replaceAll(' ', '0')} $name"
    }
}
