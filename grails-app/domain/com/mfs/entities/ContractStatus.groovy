package com.mfs.entities

/**
 *  10  Pending Processing
 *  20  Pending Documentation
 *  30  Pending Approval
 *  40  Pending Acceptance
 *  50  Pending Disbursement
 *  60  Active
 *  70  Under Redemption
 *  80  Early Settled
 *  90  Fully Settled
 *  500 Delinquant
 *  990 Rejected
 */
class ContractStatus {
    
    Integer code
    String value

    static constraints = {
    }
}
