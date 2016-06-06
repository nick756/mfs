<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="orghomepage"/>
        <title>MFS</title>
    </head>
    <body>
        <div class="content-holder">
            <div class="col-2">
                <g:render template="/common/sidemenu" model ="[menuList: menuList]"/>
            </div>
            <div class="col-10">
                 <g:render template="/common/viewFormAssociated" model="[viewForm: viewForm, parentInstance: parentInstance, instancesList: instancesList, currentInstance: currentInstance]"/>
            </div>
        </div>
    </body>
</html>
