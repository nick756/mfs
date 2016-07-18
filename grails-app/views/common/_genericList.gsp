<%@ page contentType="text/html;charset=UTF-8" %>
<%--
    Template for rendering Tabular Views with Search Pane on the top. Settings
    for Action Column may include pop up Ajax based content dependant Forms.
    Class 'formsService' must provide relevant settings via Map 'tabularView'. 

    Implementation of Search and other functions must be done in respective
    Controller: template is fully domain agnostic.
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table class="${tabularView?.cssClass}">
            <caption><g:message code="${tabularView?.caption}" /></caption>
            <%-- Rendering Search Form --%>
            <tr>
                <td colspan="${tabularView?.colspan}" style="padding: 0;">
                    <g:render template="/common/searchForm" model="[searchForm: searchForm, searchData: searchData]"/>
                </td>
            </tr>
            <%-- Column Headers --%>
            <g:if test="${tabularView?.lineNumber}"><th>No</th></g:if>
            <g:each in="${tabularView?.fields}" var="field" status="i">
                <th><g:message code="${field?.value[3]}"/></th>
            </g:each>
            <%-- Actions Column: always presented, even if empty --%>
            <th></th>
            <%-- Displaying Content --%>
            <g:if test="${instancesList.size() == 0}">
                <tr><td colspan="${tabularView?.colspan}" class="${tabularView?.emptyMessage[0]}"><g:message code="${tabularView?.emptyMessage[1]}"/></td></tr>
            </g:if>
            <g:else>
                <g:each in="${instancesList}" var="currentInstance" status="index">
                    <tr class="${index % 2 == 0 ? 'odd':'even'}">
                        <g:set var="offset" value="${0}"/>
                        <g:if test="${params?.offset}">
                            <g:if test="${new Integer(params?.offset) > 0}"><g:set var="offset" value="${new Integer(params?.offset)}"/></g:if>
                        </g:if>
                        <%-- Handling Column with optional Serial Number, always centered --%>
                        <g:if test="${tabularView?.lineNumber}"><td style="text-align: center;">${index + offset + 1}</td></g:if>
                        <%-- Displaying actual Tabular Content, for each Object Instance --%>
                        <g:each in="${tabularView?.fields}" var="field" status="i">
                            <td class="${field.value[1]}">
                                <%-- Segregating Field Types accordingly --%>
                                <g:if test="${field.value[0] == 'string'}">${currentInstance[field.key].take(field.value[4])}</g:if>
                                <g:if test="${field.value[0] == 'select'}">
                                    <g:if test="${currentInstance[field.key].toString().length() > field.value[4]}">${currentInstance[field.key].toString().take(field.value[4])}...</g:if>
                                    <g:else>${currentInstance[field.key]}</g:else>
                                </g:if>
                                <g:if test="${field.value[0] == 'date'}"><g:formatDate format="${field.value[4]}" date="${currentInstance[field.key]}"/></g:if>
                                <g:if test="${field.value[0] == 'number'}"><g:formatNumber number="${currentInstance[field.key]}" format="${field.value[4]}" /></g:if>
                            </td>
                        </g:each>
                        <%-- Compiling Actions for current Object instance --%>
                        <%-- Updated to trigger Ajax option --%>
                        <td class="${tabularView['actionsClass']}">
                            <g:each in="${tabularView?.actions}" var="actionItem" status="k">
                                <g:if test="${actionItem.value[0] == false}">
                                    <g:link style="text-decoration: none;" controller="${actionItem.value[4]}" action="${actionItem.value[5]}" id="${currentInstance?.id}" params="['params': params]">
                                    <img src="${resource(dir: 'images', file: actionItem.value[1])}" 
                                         title="${message(code: actionItem.value[3]) + ': ' + currentInstance[actionItem.value[6]]}" 
                                         onmouseover='this.src="${resource(dir: 'images', file: actionItem.value[2])}"'
                                         onmouseout='this.src="${resource(dir: 'images', file: actionItem.value[1])}"'/>
                                    </g:link>
                                </g:if>
                                <g:else>
                                    <a style="text-decoration: none; outline: none;" href="#" name="${actionItem.value[7]}" cont="${actionItem.value[4]}" act="${actionItem.value[5]}" caption="${message(code: actionItem.value[8])}" width="${actionItem.value[9]}" height="${actionItem.value[10]}" id="${currentInstance?.id}" params="['params': params]">
                                    <img src="${resource(dir: 'images', file: actionItem.value[1])}" 
                                         title="${message(code: actionItem.value[3]) + ': ' + currentInstance[actionItem.value[6]]}" 
                                         onmouseover='this.src="${resource(dir: 'images', file: actionItem.value[2])}"'
                                         onmouseout='this.src="${resource(dir: 'images', file: actionItem.value[1])}"'/>
                                    </a>
                                </g:else>
                            </g:each>
                        </td>                    
                    </tr>
                </g:each>
            </g:else>
        </table>
        <%-- Pagination Bar: using common Class .pagination --%>
        <div class="pagination"><g:paginate total="${counter ?: 0}" params="[offsetReset: true]"/></div>
        <div class="summary-label"><g:message code="default.application.totalrecords"/>:&nbsp;${counter ?: 0}</div>         
    </body>
</html>
