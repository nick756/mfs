package com.mfs.actions

import com.mfs.entities.*
import com.mfs.policies.*

class MembershipController {

    /**
     *  Listing all Membership Records, identified by User instance. Listing is
     *  created for respective Branch, subject to exact User Role.
     *  
     *  Org. Admins are shown full listing.
     **/
    def index() { 
        if(!session.user) {
            redirect controller: 'login'
            return
        }
        
        def organization    //  Must always be presented
        def branch          //  Is optional for Admin Role
        
        //  Filtering Options
        def filterName      = params?.filterName ?: null
        def filterICNumber  = params?.filterICNumber ?: null
        def filterICType    = params?.filterICNumber == null ? null : ICType.get(new Integer(params?.filterICType.id))
        def filterState     = params?.filterState == null ? null : State.get(new Integer(params?.filterState.id))
        def filterCity      = params?.filterCity ?: null
        def filterGender    = null
        def filterReligion  = null
        
        
        def listing = Member.createCriteria().list(params) {
            eq('organization', organization)
            
            if(branch) {
                eq('branch', branch)
            }
            
            //  Handling Filtering Options
            
            order('name')
        }
        
        [
            count: result.totalCount,
            memberInstanceListing: listing,
            params: params
        ]
    }
}
