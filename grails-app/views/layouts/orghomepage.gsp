<%-- ***************************************************************************
    Common Layout for all categories of Users: from Data Entry Operators up to
    Global Admin Users
*************************************************************************** --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mfs.utilities.AccessMask" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MFS</title>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>    
    <link href='http://fonts.googleapis.com/css?family=Poiret+One|Muli|Economica|Quicksand|Jura|Orbitron' rel='stylesheet' type='text/css'>
    <g:layoutHead/>
    <r:layoutResources />    
    <% 
        mask = new AccessMask() 
    %>
    
<g:javascript>
  $(document).ready(function($) {
    $('#accordion').find('.accordion-toggle').click(function(){

      //Expand or collapse this panel
      $(this).next().slideToggle('fast');

      //Hide the other panels
      $(".accordion-content").not($(this).next()).slideUp('fast');

    });
  });
</g:javascript>    
</head>
<body>
<center>
    <div style="width: 1345px; padding: 0; margin: 0; text-align: left;">
        <div class="top-menu-container">
            <div class="left-menu-pane">
                <img style="display: inline-block; margin-bottom: -5px; border: none;" src="${resource(dir: 'images', file: 'user_login.png')}"/>
                <g:if test="${session?.user}">${session?.user?.name}&nbsp;
                    <%-- May need to be revised for more dynamism (more langugaes can be added) --%>
                    (<g:if test="${session?.locale.toString() == 'en'}">${session?.user?.role.name_EN}</g:if>
                    <g:else>${session?.user?.role.name_MS}</g:else>)
                </g:if>
                <g:else>No User was identified</g:else>
            </div>
            <%--
                Conditionally displaying Horizontal (Top) Menu, subject to Access Masks
                assigned for User Roles
            --%>
            <div class="right-menu-pane">
                <g:if test="${session?.user}">
                    <g:if test="${controllerName == 'home'}"><span class="active"><g:message code="application.module.membership"/></span></g:if>
                    <g:else><g:if test="${session?.user?.role.maskMenu & mask.MODULE_MEMBER}"><g:link class="menu-item righted" controller="home" action="index" params="['offset': 0]"><g:message code="application.module.membership"/></g:link></g:if></g:else>
                    <g:if test="${controllerName == 'financing'}"><span class="active"><g:message code="application.module.financing"/></span></g:if>
                    <g:else><g:if test="${session?.user?.role.maskMenu & mask.MODULE_LOAN}"><g:link class="menu-item righted" controller="financing" action="index"><g:message code="application.module.financing"/></g:link></g:if></g:else>
                    <g:if test="${controllerName == 'collections'}"><span class="active"><g:message code="application.module.collections"/></span></g:if>         
                    <g:else><g:if test="${session?.user?.role.maskMenu & mask.MODULE_COLLECT}"><g:link class="menu-item righted" controller="collections" action="index"><g:message code="application.module.collections"/></g:link></g:if></g:else>
                    <g:if test="${controllerName == 'report'}"><span class="active"><g:message code="application.module.report"/></span></g:if>         
                    <g:else><g:if test="${session?.user?.role.maskMenu & mask.MODULE_REPORT}"><g:link class="menu-item righted" controller="report" action="index"><g:message code="application.module.report"/></g:link></g:if></g:else>
                    <g:if test="${controllerName == 'structure'}"><span class="active"><g:message code="application.module.structure"/></span></g:if>         
                    <g:else><g:if test="${session?.user?.role.maskMenu & mask.MODULE_STRUCTURE}"><g:link class="menu-item righted" controller="structure" action="index"><g:message code="application.module.structure"/></g:link></g:if></g:else>                        
                    <g:if test="${controllerName == 'users'}"><span class="active"><g:message code="application.module.users"/></span></g:if>         
                    <g:else><g:if test="${session?.user?.role.maskMenu & mask.MODULE_USERS}"><g:link class="menu-item righted" controller="users" action="index"><g:message code="application.module.users"/></g:link></g:if></g:else>                        
                </g:if>
                <g:else>
                    <g:javascript>
                        window.location.href = "<g:createLink controller='login'/>";
                    </g:javascript>
                </g:else>
            </div>
        </div>
        <div class="application-header">
            <%-- Admin Users are set: session.organization = null --%>
            <g:if test="${session?.organization}">${session?.organization.name}:&nbsp;${session?.branch?.name}</g:if>
            <g:else><g:message code="application.caption"/></g:else>
        </div>
        <g:layoutBody/>
    </div>
</center>
</body>
</html>
