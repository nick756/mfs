package com.mfs.entities

/**
 *  Class to define a User Group. Access Rights are pre-configured (hard wired) 
 *  for each UserRole instance.
 *  
 *  @version 1.0
 */
class UserRole {
    /** Unique Code through over entire Application */
    Integer code
    String name_EN
    String name_MS
    
    /** Bit Map for defining Menu Display rules */
    Long maskMenu
    /** Bit Map for specifiying Access Rights to particular Operations */
    Long maskOper

    static constraints = {
        code    unique: true
        name_EN    blank: false
        name_MS    blank: false
        maskMenu nullable: true
        maskOper nullable: true
    }
    
    static mapping = {
        sort 'code'
    }
    
    /**
     *  Overridden for auto-detection of current Locale, only 2 languages are 
     *  supported as on February 2016.
     *  
     *  @param None
     *  @since 2016-02-10
     */
    public String toString() {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        def currentLocale = 'en'
        
        if(session) {
            currentLocale = session.locale.toString();
        }
        
        if(currentLocale == 'en') {
            return "${String.format('%1$2s', code).replace(' ', '0')} ${name_EN}"
        }
        else {
            return "${String.format('%1$2s', code).replace(' ', '0')} ${name_MS}"
        }
    }
}
