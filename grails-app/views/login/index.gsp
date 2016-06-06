<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="loginpage"/>
        <title>MFS</title>
    </head>
    <body>
    <center>
        <div class="login-form">
            <g:if test="${!session?.user}">
                <g:form name="loginForm" controller="login" action="process">
                    <label><g:message code="actions.login.name"/></label>
                    <g:textField name="login" class="text-input" style="width: 250px;"/>
                    <br/>
                    <label><g:message code="actions.login.password"/></label>
                    <g:passwordField name="passw" class="text-input" style="width: 250px;"/>
                    <br/>
                    <label>&nbsp;</label>
                    <input type="submit" value="<g:message code='actions.login.submit'/>" class="myButton" style="color: #fff;"/>
                </g:form>
                <g:renderErrors bean="${loginCmd}"></g:renderErrors>
            </g:if>
            <g:else>
                <h3 style="font-weight: normal;"><g:message code="actions.login.logged" /> ${session?.user?.name}</h3>
                <h3 style="font-weight: normal;"><g:message code="actions.login.suggest"/></h3>
                <g:link class="log-out-link" controller="login" action="logout">
                    <span class="log-out-link">
                        <g:message code="actions.login.logout" />
                    </span>
                </g:link>
            </g:else>
        </div>
    </center>
    </body>
</html>
