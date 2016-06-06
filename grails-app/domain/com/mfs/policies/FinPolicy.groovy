package com.mfs.policies

/**
 *  Generic Class for defining various Charge & Fee Policies for Financing
 *  Products, automatically propogated to related Contracts/Loans through
 *  configured FPCLink instances.
 *  <p>
 *  Bottom Level for all induced Charges & Fees, must be applied through
 *  instances of Charge and respective *Link.
 */
class FinPolicy {
    /** Unique value through over application, convention over Code */
    Integer code
    String name_EN
    String name_MY
    
    static constraints = {
        code unique: true
        name_EN blank: false
        name_MY: blank: false
    }
}
