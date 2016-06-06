package com.mfs.policies

import com.mfs.entities.*

class MemPolicy {
    Integer code
    String name_EN
    String name_MY
    
    static constraints = {
        code unique: true
    }
}
