package com.mfs.utilities

/**
 *
 * @author Nikolay Krasnikov
 */
class AccessMask {
    static final Long MODULE_ACCOUNTS   = 1024  //  Financial Accounting
    static final Long MODULE_MEMBER     = 512   //  Membership Management
    static final Long MODULE_LOAN       = 256   //  Financing and Saving Accounts
    static final Long MODULE_COLLECT    = 128   //  Access to Collection Module
    static final Long MODULE_REPORT     = 64    //  Access to Reporting Module
    static final Long MODULE_STRUCTURE  = 32    //  Managing Organizational Structure
    static final Long MODULE_USERS      = 16    //  Users Management
    static final Long MODULE_SETTINGS   = 8     //  Global Configurations
    static final Long MODULE_CLIENTS    = 4     //  Clients Management
    static final Long MODULE_BILLING    = 2     //  Access to Billing Functions
    static final Long MODULE_MAINTAIN   = 1     //  Access to DB Maintainance
    
    static final Long OPER_ADD          = 1
    static final Long OPER_EDIT         = 2
    static final Long OPER_DELETE       = 4
    static final Long OPER_PROCESS      = 8
    static final Long OPER_DISBURSE     = 16
    static final Long OPER_RECONCILE    = 32
}

