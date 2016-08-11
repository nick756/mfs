<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mfs.entities.*" %>
<%--
    Parameters to pass:
    -   formData
    -   objectInstance
    -   parentInstance
    -   subsetData
    -   winWidth, winHeight
--%>

<h1 class="view-form-header">${parentInstance[formData?.header]}</h1>
<%-- Rendering Horizontal Actions Panel, if applicable  --%>
<g:if test="${formData?.source}">
    <div class="form-action-panel">
        <g:remoteLink update="dialog" controller="${formData?.source[0]}" action="${formData?.source[1]}" id="${parentInstance?.id}">
            <img class="action-image" style="float: ${formData?.source[3]}" src="${resource(dir: 'images', file: formData?.source[2])}"/>
        </g:remoteLink>
    </div>
</g:if>   
<g:hasErrors bean="${objectInstance}">
    <div class="errors">
        <ul>
            <g:eachError bean="${objectInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </div>
</g:hasErrors>
<g:if test="${successMessage}">
    <div class="success-message">${successMessage}</div>
</g:if>
<g:set var="objectName" value="${formData?.object}"/>
<g:form name="${formData.name}">
    <g:hiddenField name="parentID" value="${parentInstance?.id}"/>
    <g:hiddenField name="id" value="${objectInstance?.id}"/>
    <div class="form-holder">
        <g:each in="${formData?.fields}" var="field" status="i">
            <div class="form-row">
                <g:if test="${field.value[1] == true}">
                    <div class="cell-caption red"><g:message code="${objectName.toLowerCase()}.${field.key}.label"/></div>
                </g:if>
                <g:else>
                    <div class="cell-caption"><g:message code="${objectName.toLowerCase()}.${field.key}.label"/></div>
                </g:else>
                <div class="cell-data">
                    <g:if test="${field.value[2] == 'string'}"><g:textField class="data-entry" name="${field.key}" value="${objectInstance[field.key]}"/></g:if>
                    <g:if test="${field.value[2] == 'select'}"><g:select class="selectable" name="${field.key}.id" from="${grailsApplication.getDomainClass(field.value[3])?.getClazz()?.list()}" value="${objectInstance[field.key]?.id}" optionKey="id" default="none" noSelection="${['':'']}"/></g:if>
                    <g:if test="${field.value[2] == 'date'}"><g:datePicker name="${field.key}" precision="day" default="none" noSelection="${['':'']}" relativeYears="${[field.value[3]..0]}" value="${objectInstance[field.key]}"/></g:if>
                    <g:if test="${field.value[2] == 'text'}"><g:textArea name="${field.key}" class="form-text" value="${objectInstance[field.key]}"/></g:if>
                    <g:if test="${field.value[2] == 'subset'}"><g:select class="selectable" name="${field.key}.id" from="${subsetData[field.key]}" value="${objectInstance[field.key]?.id}" default="none" noSelection="${['':'']}" optionKey="id"/></g:if>
                    <g:if test="${field.value[2] == 'boolean'}"><g:checkBox style="transform: scale(1.5); margin-left: 5px;" name="${field.key}" value="${objectInstance[field.key]}" /></g:if>                    
                </div>
            </div>
        </g:each>
    </div>
    <g:submitToRemote url="${['controller': formData.target[0], 'action': formData.target[1]]}" update="dialog" value="${message(code: 'actions.submit.label')}" class="myButton dark"/>
</g:form>


<%-- Processing Popup Window after re-loading content: resizing and scrolling to top --%>
<g:javascript>
    var winHeight = "${winHeight}";
    var winWidth  = "${winWidth}";

    $("#dialog").dialog("option", "title", "${message(code: formData?.caption)}");
    $("#dialog").dialog( "option", "height", winHeight );
    $("#dialog").dialog( "option", "width", winWidth );
    $("#dialog").dialog("option", "position", {my: "center", at: "center", of: window});
    $('#dialog').animate({scrollTop: 0}, 300);
</g:javascript>
