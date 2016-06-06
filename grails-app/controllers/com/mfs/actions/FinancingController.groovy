package com.mfs.actions

import com.mfs.utilitiess.*
import com.mfs.policies.*
import com.mfs.products.*
import com.mfs.entities.*

class FinancingController {
    
    def menuService
    def policiesService
    def contractService

    /**
     *  Displaying list of active Loans
     */
    def index() { 
        if(!session?.user) {
            redirect controller: 'login'
            return
        }
        
        def organization
        def branch = Branch.get(session?.branch?.id)
        def contractsList = []
        
        println "Branch: ${session?.branch.name} ${session?.branch?.code}"
        println "Hybernate Session: ${branch}"
        
        [
            contractsList:  contractsList,
            menuList:       menuService.generateSideMenu('financing', 'index'),
            fragment:       '02'
        ]
    }
}
