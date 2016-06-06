package com.mfs.actions

import com.mfs.entities.*
import com.mfs.policies.*
import com.mfs.services.*
import java.text.*
import grails.util.Environment

/** 
 *  Home Page for Organization Admins
 **/
class OrgadminController {

    def index() {
        if(!session.user) {
            redirect controller: 'login'
            return
        }
    }
}
