package com.mfs.entities

import com.mfs.products.*

/**
 *  Individual Loans only
 */ 
class Contract {
    
    Date dateUpdated
    Date dateCreated
    Date dateApplied
    Date dateProcessed
    Date dateApproved
    Date dateDisbursed      //  Single Tranche only
    Date dateRejected
    
    String referenceNo
    Integer contractNo
    
    Organization organization
    Branch branch
    ContractStatus status
    
    /** Exclusive with savProduct and depProduct */
    FinProduct finProduct
    /** Exclusive with finProduct and depProduct */
    SavProduct savProduct
    /** Exclusive with savProduct and finProduct */
    DepProduct depProduct
    
    boolean processed
    boolean approved
    boolean disbursed
    boolean redeemed
    boolean settled
    boolean rejected
    boolean delinquent
    
    static belongsTo = [
        member: Member
    ]
    static constraints = {
    }
}
