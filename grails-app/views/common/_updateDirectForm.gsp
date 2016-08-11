<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mfs.entities.*" %>
<%--
    Template for rendering Update Form for master instance
    of an object (not associated). Template DOES NOT render 'hasMany' fields.
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MFS</title>
    </head>
    <body>
        <h1 class="view-form-header">${objectInstance[formData.headerField]}</h1>
        <%-- Rendering Horizontal Actions Panel, if applicable  --%>
        <g:if test="${formData?.source}">
            <div class="form-action-panel">
                <g:remoteLink update="dialog" controller="${formData?.source[0]}" action="${formData?.source[1]}" id="${objectInstance?.id}">
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
        <%-- Rendering Success Message --%>
        <g:if test="${successMessage}">
            <div class="success-message">${successMessage}</div>
        </g:if>
        <g:set var="objectName" value="${formData.object}"/>
        <g:form name="${formData.name}">
            <g:hiddenField name="id" value="${objectInstance?.id}"/>
            <div class="form-holder">
                <g:each in="${formData.fields}" var="field" status="i">
                    <div class="form-row">
                        <g:if test="${field.value[1] == true}">
                            <div class="cell-caption red"><g:message code="${objectName.toLowerCase()}.${field.key}.label"/></div>
                        </g:if>
                        <g:else>
                            <div class="cell-caption"><g:message code="${objectName.toLowerCase()}.${field.key}.label"/></div>
                        </g:else>
                        <%-- Segregating between editable and non-editable fields --%>
                        <g:if test="${field.value[0] == true}">
                            <div class="cell-data">
                                <g:if test="${field.value[2] == 'string'}"><g:textField class="data-entry" name="${field.key}" value="${objectInstance[field.key]}"/></g:if>
                                <g:if test="${field.value[2] == 'select'}"><g:select class="selectable" name="${field.key}.id" from="${grailsApplication.getDomainClass(field.value[3])?.getClazz()?.list()}" value="${objectInstance[field.key]?.id}" optionKey="id" default="none" noSelection="${['':'']}"/></g:if>
                                <g:if test="${field.value[2] == 'date'}"><g:datePicker name="${field.key}" precision="day" default="none" noSelection="${['':'']}" relativeYears="${[field.value[3]..0]}" value="${objectInstance[field.key]}"/></g:if>
                                <g:if test="${field.value[2] == 'text'}"><g:textArea name="${field.key}" class="form-text" value="${objectInstance[field.key]}"/></g:if>
                                <g:if test="${field.value[2] == 'subset'}"><g:select class="selectable" name="${field.key}.id" from="${subsetData[field.key]}" value="${objectInstance[field.key]?.id}" default="none" noSelection="${['':'']}" optionKey="id"/></g:if>
                            </div>
                        </g:if>
                        <g:else>
                            <div class="cell-data">
                                <g:if test="${field.value[2] == 'string' || field.value[2] == 'select' || field.value[2] == 'subset'}"><g:textField class="data-entry" name="_${field.key}_" value="${objectInstance[field.key]}" disabled="true"/></g:if>
                                <g:if test="${field.value[2] == 'date'}"><g:formatDate name="_${field.key}_" date="${objectInstance[field.key]}" format="${field.value[3]}"/></g:if>
                                <g:if test="${field.value[2] == 'boolean'}"><g:if test="${objectInstance[field.key] == true}">&#10004;</g:if><g:else>&minus;</g:else></g:if>
                                <g:if test="${field.value[2] == 'integer'}"><g:formatNumber name="_${field.key}_" number="${objectInstance[field.key]}" minIntegerDigits="${field.value[3]}"/></g:if>
                            </div>
                        </g:else>
                    </div>
                </g:each>
            </div>
            <%-- Tag 'formRemote' is deprecated in Grails 2.5: not functioning; replaced by below construct --%>
            <g:submitToRemote url="${['controller': formData.target[0], 'action': formData.target[1]]}" update="dialog" value="${message(code: 'actions.submit.label')}" class="myButton dark"/>
            
            <g:if test="${triggered && formData.trigger}"><g:link controller="${formData.triggerAction[0]}" action="${formData.triggerAction[1]}" class="myButton dark"><g:message code="${formData.triggerAction[2]}"/></g:link></g:if>
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
    </body>
</html>

