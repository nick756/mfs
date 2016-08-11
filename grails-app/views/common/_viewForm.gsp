<h1 class="${viewForm.headerClass}">${objectInstance[viewForm.headerField]}</h1>
<div class="${viewForm.cssClass[0]}">
    <%-- Analyzing whether the Form is tabbed or not, displaying Tabs if required --%>
    <g:if test="${viewForm['tabbed']}">
        <div class="${viewForm.tabClass[0]}">
            <ul class="${viewForm.tabClass[1]}">
                <g:each in="${viewForm.tabs}" var="tab" status="i">
                    <li class="${tab.value[1]}">
                        <g:if test="${tab.value[0]}"><g:message code="${tab.value[2]}"/></g:if>
                        <g:else><g:remoteLink  update="dialog" controller="${tab.value[3]}" action="${tab.value[4]}" id="${objectInstance?.id}"><g:message code="${tab.value[2]}"/></g:remoteLink></g:else>
                    </li>
                </g:each>
            </ul>
        </div>
    </g:if>
    <%-- Rendering Horizontal Actions Panel, if applicable  --%>
    <g:if test="${viewForm?.addMenu}">
        <div class="form-action-panel">
            <%--g:link><img class="action-image" src="${resource(dir: 'images', file: 'arrow_left.png')}"></g:link--%>
            <g:each in="${viewForm?.actionsPane}" var="actionItem" status="k">
                <g:remoteLink update="dialog" controller="${actionItem.value[2]}" action="${actionItem.value[3]}" id="${objectInstance?.id}">
                    <img class="action-image" style="float: ${actionItem.value[0]}" src="${resource(dir: 'images', file: actionItem.value[1])}"/>
                </g:remoteLink>
            </g:each>
        </div>
    </g:if>    
    <%-- Displaying Content (for provided objectInstance) --%>
    <div class="${viewForm.cssClass[0]}">
        <g:each in="${viewForm.fields}" var="field" status="index">
            <div class="${viewForm.cssClass[1]}">
                <g:if test="${field.value[0] == 'blank'}"><div class="${field.value[1]}"></div></g:if>
                <g:else>
                    <div class="${viewForm.cssClass[2]}"><g:message code="${viewForm.objectCode + '.' + field.key + '.label'}"/></div>
                    <div class="${viewForm.cssClass[3]}">
                        <g:if test="${field.value[0] == 'string'}">
                            <g:textField name="${field.key}" class="${field.value[1]}" value="${objectInstance[field.key]}" disabled="true"/>
                        </g:if>
                        <g:elseif test="${field.value[0] == 'date'}">
                            <g:datePicker name="${field.key}" precision="day" value="${objectInstance[field.key]}" default="none" noSelection="${['':'']}" disabled="true"/>
                        </g:elseif>
                        <g:elseif test="${field.value[0] == 'text'}">
                            <g:textArea name="${field.key}" class="${field.value[1]}" value="${objectInstance[field.key]}" disabled="true"/>
                        </g:elseif>
                    </div>
                </g:else>
            </div>
        </g:each>
        <%-- Rendering Footer Items, if any specified --%>
        <g:if test="${viewForm['footer'].size() > 0}">
            <g:each in="${viewForm.footer}" var="footerItem" status="index">
                <div class="${viewForm.cssClass[4]}" style="border: 1px solid #555;">
                    <div class="${viewForm.footerClass[0]}"><g:message code="${footerItem.value[2]}"/></div>
                    <div class="${viewForm.footerClass[1]}">
                        <g:if test="${footerItem.value[0] == 'string'}">${objectInstance[footerItem.key]}</g:if>
                        <g:elseif test="${footerItem.value[0] == 'date'}"><g:formatDate format="${footerItem.value[3]}" date="${objectInstance[footerItem.key]}"/></g:elseif>
                    </div>
                </div>
            </g:each>
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
