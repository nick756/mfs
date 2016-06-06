<h1 class="${viewForm.headerClass}">${objectInstance[viewForm.headerField]}</h1>
<div class="${viewForm.cssClass[0]}">
    <%-- Analyzing whether the Form is tabbed or not, displaying Tabs if required --%>
    <g:if test="${viewForm['tabbed']}">
        <div class="${viewForm.tabClass[0]}">
            <ul class="${viewForm.tabClass[1]}">
                <g:each in="${viewForm.tabs}" var="tab" status="i">
                    <li class="${tab.value[1]}">
                        <g:if test="${tab.value[0]}"><g:message code="${tab.value[2]}"/></g:if>
                        <g:else><g:link  controller="${tab.value[3]}" action="${tab.value[4]}" id="${objectInstance?.id}"><g:message code="${tab.value[2]}"/></g:link></g:else>
                    </li>
                </g:each>
            </ul>
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
