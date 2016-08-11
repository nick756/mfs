<%--
    Passed Parameters:
    ----------------------------------------------------------------------------
    1   parentInstance      - Parent Object for Associated Form
    2   currentInstance     - Instance of associated Object (for details)
    3   instancesList       - List for Tabular View (Domain is the same as for currentInstance)
    4   viewForm            - Form Definition, from formsService

    Table paging is based on standard Parameters, with id for selected instance
    of associated object.

    The Form must include Actions Pane for Buttons (Add, Print, etc.).

    IMPORTANT!!!
    ------------
    1.  All Instances of related Objects are not interpreted here, must be
        managed on Controller side.
    2.  Form Definition only contains message codes and Objects' Attributes as
        Map Keys.
--%>

<h1 class="${viewForm.styles.header}">${parentInstance[viewForm.headerField]}</h1>
<div class="${viewForm.styles.main[0]}">
    <%-- Analyzing whether the Form is tabbed or not, displaying Tabs if required --%>
    <g:if test="${viewForm['tabbed']}">
        <div class="${viewForm.styles.tab[0]}">
            <ul class="${viewForm.styles.tab[1]}">
                <g:each in="${viewForm.tabs}" var="tab" status="i">
                    <li class="${tab.value[1]}">
                        <g:if test="${tab.value[0]}"><g:message code="${tab.value[2]}"/></g:if>
                        <g:else><g:remoteLink  update="dialog" controller="${tab.value[3]}" action="${tab.value[4]}" id="${parentInstance?.id}"><g:message code="${tab.value[2]}"/></g:remoteLink></g:else>
                            </li>
                </g:each>
            </ul>
        </div>
    </g:if>
    <%-- Rendering Horizontal Actions Panel, if applicable  --%>
    <g:if test="${viewForm?.addMenu}">
        <div class="form-action-panel">
            <g:each in="${viewForm?.actionsPane}" var="actionItem" status="k">
                <g:remoteLink update="dialog" controller="${actionItem.value[2]}" action="${actionItem.value[3]}" id="${currentInstance?.id}" params="['parentID': parentInstance?.id]">
                    <img class="action-image" style="float: ${actionItem.value[0]}" src="${resource(dir: 'images', file: actionItem.value[1])}"/>
                </g:remoteLink>
            </g:each>
        </div>
    </g:if>
    <%-- 
        Rendering Tabular Section, if any defined: Details View is rendered for a 
         selected Record (currentInstance?.id) 
    --%>    
    <g:if test="${viewForm.tabular}">
        <table class="${viewForm.styles.table[0]} larger">
            <th>No</th>
            <g:each in="${viewForm?.tableFields}" var="field" status="i">
            <th><g:message code="${viewForm?.objectCode + '.' + field.key + '.label'}"/></th>
            </g:each>
            <g:if test="${instancesList.size() == 0}">
            <tr><td colspan="${viewForm?.colspan}" class="empty-list"><g:message code="${viewForm?.emptyMessage[0]}"/></td></tr>
            </g:if>
            <g:else>
                <g:each in="${instancesList}" var="element" status="k">
                    <tr <g:if test="${currentInstance?.id == element.id}">class="selected"</g:if>>
                    
                        <td class="centered">${new Integer(k) + 1}</td>
                        <g:each in="${viewForm?.tableFields}" var="column" status="j">
                            <td class="${column.value[1]}">
                                <g:if test="${viewForm?.clickableRows && new Integer(j) == 0}">
                                    <g:if test="${column.value[0] == 'string'}"><g:remoteLink update="dialog" controller="home" action="associatesview" params="['selectedID': element.id, 'id': parentInstance.id]">${element[column.key]}</g:remoteLink></g:if>
                                    <g:elseif test="${column.value[0] == 'select'}">${element[column.key]}</g:elseif>
                                </g:if>
                                <g:else>
                                    <g:if test="${column.value[0] == 'string'}">${element[column.key]}</g:if>
                                    <g:elseif test="${column.value[0] == 'select'}">${element[column.key]}</g:elseif>                                    
                                </g:else>
                            </td>
                        </g:each>
                    </tr>
                </g:each>
            </g:else>
        </table>
        <hr width="100%" size="3" color="#536878"/>
    </g:if>
    <%-- Rendering Details View for Current Instance (passed from Controller) --%>
    <div class="${viewForm.styles.main[0]}">
        <g:each in="${viewForm.detailFields}" var="field" status="index">
            <div class="${viewForm.styles.main[1]}">
                <g:if test="${field.value[0] == 'blank'}"><div class="${field.value[1]}"></div></g:if>
                <g:else>
                    <div class="${viewForm.styles.main[2]}"><g:message code="${viewForm.objectCode + '.' + field.key + '.label'}"/></div>
                    <div class="${viewForm.styles.main[3]}">
                        <g:if test="${field.value[0] == 'string'}"><g:textField name="${field.key}" class="${field.value[1]}" value="${currentInstance == null ? '': currentInstance[field.key]}" disabled="true"/></g:if>
                        <g:elseif test="${field.value[0] == 'date'}"><g:datePicker name="${field.key}" precision="day" value="${currentInstance == null ? null : currentInstance[field.key]}" default="none" noSelection="${['':'']}" disabled="true"/></g:elseif>
                        <g:elseif test="${field.value[0] == 'text'}"><g:textArea name="${field.key}" class="${field.value[1]}" value="${currentInstance == null ? '': currentInstance[field.key]}" disabled="true"/></g:elseif>
                        <g:elseif test="${field.value[0] == 'number'}"><g:textField  name="${field.key}" class="${field.value[1]}" value="${formatNumber(format: field.value[2], number: currentInstance == null ? 0:currentInstance[field.key])}" disabled="true"/></g:elseif>
                        <%--g:elseif test="${field.value[0] == 'boolean'}"><g:textField name="${field.key}" value="${currentInstance == null ? '': currentInstance[field.key] ? '&#1004;' : '&#1006;'}" disabled="true"/></g:elseif--%>
                        <g:elseif test="${field.value[0] == 'boolean'}"><g:if test="${currentInstance == null || currentInstance[field.key] == false}">&minus;</g:if><g:else>&#10004;</g:else></g:elseif>
                        </div>
                </g:else>
            </div>
        </g:each>
        <%-- Conditionally rendering Form Footer for Current Instance --%>
        <g:if test="${viewForm['footerFields'].size() > 0}">

    </g:if>
</div>
</div>
<g:javascript>
    var winHeight = "${winHeight}";
    var winWidth  = "${winWidth}";

    $("#dialog").dialog("option", "title", "${message(code: viewForm?.caption)}");
    $("#dialog").dialog( "option", "height", winHeight );
    $("#dialog").dialog( "option", "width", winWidth );
    $("#dialog").dialog("option", "position", {my: "center", at: "center", of: window});
    $('#dialog').animate({scrollTop: 0}, 300);
</g:javascript>
