<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MFS</title>
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>    
        <link href='http://fonts.googleapis.com/css?family=Poiret+One|Muli|Economica|Quicksand|Jura|Orbitron' rel='stylesheet' type='text/css'>
        <g:layoutHead/>
        <r:layoutResources /> 
    </head>
    <body>
        <center>
            <div style="width: 1350px; padding: 0; margin: 0; text-align: left;">
                <div class="login-header">
                    <g:if test="${session?.user}">
                        <div class="user-details">Logged User: ${session.user?.name}</div>
                    </g:if>
                    <g:else>
                        <g:link action="index" controller="login" params="[lang: 'ms']">
                            <img class="lang-selector" style="margin-left: 10px;" src="${resource(dir: 'images', file: 'my.png')}">
                        </g:link>    
                        <g:link action="index" controller="login" params="[lang: 'en_US']">
                            <img class="lang-selector" src="${resource(dir: 'images', file: 'gb.png')}">
                        </g:link>
                    </g:else>
                </div>
                <div class="login-title-box">
                    <center>
                    <div style="width: 70%; display: table; margin-top: 15px;">
                        <div style="text-align: center; vertical-align: middle; display: table-cell;">
                            <h1 class="login-title" style="display: inline-block; margin-top: -30px;"><g:message code="application.caption"/></h1>
                        </div>
                    </div>
                    </center>
                </div>   
                <g:layoutBody/>
            </div>
        </center>
    </body>
</html>
