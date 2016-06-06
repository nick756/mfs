package com.mfs.actions

import com.mfs.entities.*
import com.mfs.policies.*
import org.springframework.web.servlet.support.RequestContextUtils as RCU

/**
 *  Login for all User Groups.
 **/
class LoginController {

    def index() { 
        if(params.lang) {
            def newLocale = new Locale(params.lang)
            RCU.getLocaleResolver(request).setLocale(request, response, newLocale)
            session.locale = newLocale
            println "Locale set: " + newLocale
        }
        else {
            //println "Locale remains unchanged"
        }
        
        println "Current Locale: ${session.locale}"    
    }
    
    def process = {LoginCommand cmd ->
        Locale locale
        
        println "Login: ${cmd.login} Passw: ${cmd.passw}"
        println "User: ${User.findByLogin(cmd.login)?.name}"
        
        if(session?.user) {
            render(view: 'index')
        }
        else {
            if(request.method == 'POST') {
                if(!cmd.hasErrors()) {
                    def user = cmd.getUser()
                    session.user = user
                    session.branch = user.branch
                    session.organization = user.organization

                    if(session?.user) {
                        session.setMaxInactiveInterval(-1)
                        
                        def currentLocale = RCU.getLocale(request)
                        
                        println "Login process: Locale = ${currentLocale.toString()}"
                        
                        if(currentLocale.toString() == 'ms') {
                            locale = new Locale('ms')
                        }
                        else {
                            locale = new Locale('en')
                        }
                        
                        session.locale = locale
                        
                        switch (session?.user.role.code) {
                            case 40:    redirect(controller: 'home')
                                        break
                            default:    redirect action: 'index'

                        }
                    }
                }
                else {
                    render(view: '/login/index', model: [loginCmd: cmd])
                }
            }
            else {
                render(view: '/login/index')
            }
        }        
    } 
    
    def logout = {
        def curLocale = RCU.getLocale(request)
        session.invalidate()
        RCU.getLocaleResolver(request).setLocale(request, response, curLocale)
        redirect(action: 'index')        
    }    
}

class LoginCommand {

    String login
    String passw
    private u

    User getUser() {
        if(!u && login) {
            u = User.findByLogin(login)
        }

        return u
    }

    static constraints = {
        login(blank: false, validator: {val, cmd ->
                if(!cmd.user) {
                    return "user.not.found"
                }
            })
        passw(blank: false, validator: {val, cmd ->
                if(cmd.user && cmd.user.passw != val) {
                    return "user.password.invalid"
                }
            })
    }
}
