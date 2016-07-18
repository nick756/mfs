
    <g:form name="processpending" controller="home" action="updatepending">
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
        <div class="form-row">
            <div class="cell-caption"><g:message code="member.approvalDate.label"/></div>
            <div class="cell-data"><g:datePicker name="approvalDate" precision="day" default="none" noSelection="${['':'']}" value="${objectInstance.approvalDate}" relativeYears="[-2..0]"/></div>
        </div>  
    </g:form>
</div>
