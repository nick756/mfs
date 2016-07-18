<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mfs.entities.*" %>
<%--
    Template for rendering generic Data Entry (/ Update (?)) Form for master instance
    of an object (not associated). Template DOES NOT render 'hasMany' fields.
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MFS</title>
    </head>
    <body>
        <h1 class="form-header"><g:message code="${formData.caption}"/></h1>
        <g:hasErrors bean="${objectInstance}">
            <div class="errors">
                <ul>
                    <g:eachError bean="${objectInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </div>
        </g:hasErrors>        
        <g:set var="objectName" value="${formData.object}"/>
        <g:form name="${formData.name}">
            <div class="form-holder">
                <g:each in="${formData.fields}" var="field" status="i">
                    <div class="form-row">
                        <g:if test="${field.value[0] == true}">
                            <div class="cell-caption red"><g:message code="${objectName.toLowerCase()}.${field.key}.label"/></div>
                        </g:if>
                        <g:else>
                            <div class="cell-caption"><g:message code="${objectName.toLowerCase()}.${field.key}.label"/></div>
                        </g:else>
                        <div class="cell-data">
                            <g:if test="${field.value[1] == 'string'}"><g:textField class="data-entry" name="${field.key}" value="${objectInstance[field.key]}"/></g:if>
                            <g:if test="${field.value[1] == 'select'}"><g:select class="selectable" name="${field.key}.id" from="${grailsApplication.getDomainClass(field.value[2])?.getClazz()?.list()}" value="${objectInstance[field.key]?.id}" optionKey="id" default="none" noSelection="${['':'']}"/></g:if>
                            <g:if test="${field.value[1] == 'date'}"><g:datePicker name="${field.key}" precision="day" default="none" noSelection="${['':'']}" relativeYears="${[field.value[2]..0]}" value="${objectInstance[field.key]}"/></g:if>
                            <g:if test="${field.value[1] == 'text'}"><g:textArea name="${field.key}" class="form-text" value="${objectInstance[field.key]}"/></g:if>
                            <g:if test="${field.value[1] == 'subset'}"><g:select class="selectable" name="${field.key}.id" from="${subsetData[field.key]}" value="${objectInstance[field.key]?.id}" default="none" noSelection="${['':'']}" optionKey="id"/></g:if>
                        </div>
                    </div>
                </g:each>
            </div>
            <%-- Tag 'formRemote' is deprecated in Grails 2.5: not functioning; replaced by below construct --%>
            <g:submitToRemote url="${['controller': formData.target[0], 'action': formData.target[1]]}" update="dialog" value="${message(code: 'actions.submit.label')}" class="myButton dark"/>
        </g:form>
    </body>
</html>
