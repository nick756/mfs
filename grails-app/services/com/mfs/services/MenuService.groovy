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
                'new':      [true, 'newmember', 'menus.membership.add.label', 800, 620],
                'active':   [false, 'index', 'menus.membership.active.label', 0, 0],
                'pending':  [false, 'pending', 'menus.membership.pending.label', 0, 0],
                'retired':  [false, 'retired', 'menus.membership.retired.label', 0, 0],
                'rejected': [false, 'rejected', 'menus.membership.rejected.label', 0, 0],
                'shares':   [false, 'shares', 'menus.membership.shares.label', 0, 0]
            ]
        ],
        'financing': [  //  Managing Loan Records
            'fragment': '02',
            'caption':  'menus.financing.label',
            'items': [
                'new':          [false, 'new', 'menus.financing.add.label', 0, 0],
                'active':       [false, 'index', 'menus.financing.active.label', 0, 0],
                'pending':      [false, 'pending', 'menus.financing.pending.label', 0, 0],
                'rejected':     [false, 'rejected', 'menus.financing.rejected.label', 0, 0],
                'redemption':   [false, 'redemption', 'menus.financing.redemption.label', 0, 0],
                'settled':      [false, 'settled', 'menus.financing.settled.label', 0, 0],
                'delinquent':   [false, 'delinquency', 'menus.financing.delinquency.label', 0, 0]
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
                ajax:       value[0],
                caption:    value[2],
                contName:   sourceName,
                actName:    value[1],
                active:     value[1] != actName? true:false,
                width:      value[3],
                height:     value[4]
            )
        }

        return menuList << item
    }
}
