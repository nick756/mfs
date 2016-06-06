package com.mfs.services

import grails.transaction.Transactional
import com.mfs.entities.*
import com.mfs.policies.*
import com.mfs.utilities.*
import java.text.*
import grails.util.Environment

@Transactional (readOnly = true)
class MenuService {
    
    private menuList = [
        'home': [   //  Managing Membership Records
            'fragment': '01',
            'caption':  'menus.membership.label',
            'items': [
                'new':      ['newmember', 'menus.membership.add.label'],
                'active':   ['index', 'menus.membership.active.label'],
                'pending':  ['pending', 'menus.membership.pending.label'],
                'retired':  ['retired', 'menus.membership.retired.label'],
                'rejected': ['rejected', 'menus.membership.rejected.label'],
                'shares':   ['shares', 'menus.membership.shares.label']
            ]
        ],
        'financing': [  //  Managing Loan Records
            'fragment': '02',
            'caption':  'menus.financing.label',
            'items': [
                'new':          ['new', 'menus.financing.add.label'],
                'active':       ['index', 'menus.financing.active.label'],
                'pending':      ['pending', 'menus.financing.pending.label'],
                'rejected':     ['rejected', 'menus.financing.rejected.label'],
                'redemption':   ['redemption', 'menus.financing.redemption.label'],
                'settled':      ['settled', 'menus.financing.settled.label'],
                'delinquent':   ['delinquency', 'menus.financing.delinquency.label']
            ]
        ]
    ]

    /**
     *  @param sourceName Name of Controller requesting Menu
     *  @param actName Name of specific Action the call is made from
     *  
     *  IMPORTANT!!!
     *  ------------
     *  messageSource does NOT work in service classes: message codes passed to
     *  Controller as they are, for displaying in GSP files via <g:message> tags
     */
    def generateSideMenu(String sourceName, String actName) {

        def sideMenu  = this.menuList[sourceName]
        def menuList = []
        
        def item = new MenuItem(
            caption:        sideMenu.caption,
            fragmentName:   sideMenu.fragment,
            lines:          []
        )

        sideMenu.items.each {key, value ->
            item.lines << new MenuLine(
                caption: value[1],
                actName: value[0],
                active:  value[0] != actName? true:false
            )
        }

        return menuList << item
    }
}
