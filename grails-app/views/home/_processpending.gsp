<g:if test="${failureMessage}">
    <div class="errors centered">${failureMessage}</div>
</g:if>
<div class="form-row">
    <div class="cell-caption"><g:message code="member.branch.label"/></div>
    <div class="cell-data"><g:textField class="data-entry" name="branch.id" precision="day" default="none" noSelection="${['':'']}" value="${objectInstance?.branch?.name}" disabled="true"/></div>
</div>
<div class="form-row">
    <div class="cell-caption"><g:message code="member.name.label"/></div>
    <div class="cell-data"><g:textField class="data-entry" name="name" value="${objectInstance.name}" disabled="true" /></div>
</div>
<div class="form-row">
    <div class="cell-caption"><g:message code="member.icNumber.label"/></div>
    <div class="cell-data"><g:textField class="data-entry" name="icNumber" value="${objectInstance.icNumber}" disabled="true" /></div>
</div>
<div class="form-row">
    <div class="cell-caption"><g:message code="member.registrationDate.label"/></div>
    <div class="cell-data"><g:datePicker name="registrationDate" precision="day" default="none" noSelection="${['':'']}" value="${objectInstance.registrationDate}" disabled="true"/></div>
</div>
<div class="form-row">
    <div class="cell-caption"><g:message code="member.birthDate.label"/></div>
    <div class="cell-data"><g:datePicker name="birthDate" precision="day" default="none" noSelection="${['':'']}" value="${objectInstance.birthDate}" disabled="true"/></div>
</div>      
<g:form name="processpending">
    <g:hiddenField name="id" value="${objectInstance?.id}"/>        
    <div class="form-row">
        <div class="cell-caption"><g:message code="member.approvalDate.label"/></div>
        <div class="cell-data"><g:datePicker name="approvalDate" precision="day" default="none" noSelection="${['':'']}" value="${objectInstance.approvalDate}" relativeYears="[-2..0]"/></div>
    </div>  
    <div class="form-row">
        <div class="cell-caption"><g:message code="member.approvalAuthority.label"/></div>
        <div class="cell-data"><g:textField class="data-entry" name="approvalAuthority" value="${objectInstance.approvalAuthority}" /></div>
    </div>
    <div class="form-row">
        <div class="cell-data" style="text-align: left;">
            <g:submitToRemote url="${[controller: 'home', action: 'updatepending']}" update="dialog" value="${message(code: 'actions.submit.label')}" class="myButton dark"/>
        </div>
        <div class="cell-data" style="text-align: right;">
            <g:if test="${showPendingLink}"><g:link controller="home" action="index" class="myButton dark">Active Members</g:link></g:if>
        </div>
    </div>
</g:form>


