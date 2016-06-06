<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.mfs.entities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <g:form name="${searchForm.name}" controller="${searchForm.targetMain[0]}">
            <g:hiddenField name="target" value="${searchForm.targetMain[1]}"/>
            <div class="${searchForm.cssClass[0]}">
                <div class="left-search-pane">
                    <g:each in="${searchForm.fields.paneLeft}" var="field" status="index">
                        <div class="${searchForm.cssClass[1]}">
                            <div class="${field.value[1]}"><g:message code="${field.value[3]}"/></div>
                            <div class="${field.value[1]}">
                                <g:if test="${field.value[0] == 'string'}">
                                    <g:textField name="${field.key}" class="${field.value[2]}" value="${searchData[field.key]}" />
                                </g:if>
                                <g:elseif test="${field.value[0] == 'date'}">
                                    <g:datePicker name="${field.key}" precision="day" default="none" noSelection="${['':'']}" years="${Calendar.instance.get(Calendar.YEAR)..(Calendar.instance.get(Calendar.YEAR) + field.value[4])}" value="${searchData[field.key]}" />
                                </g:elseif>
                                <g:elseif test="${field.value[0] == 'select'}">
                                    <g:select name="${field.key}.id" class="${field.value[2]}" from="${grailsApplication.getDomainClass(field.value[4])?.getClazz()?.list()}" default="none" noSelection="${['':'']}" value="${searchData[field.key]?.id}" optionKey="id"/>
                                </g:elseif> 
                                <g:elseif test="${field.value[0] == 'subset'}">
                                    <g:select class="${field.value[2]}" name="${field.key}.id" from="${subsetData[field.key]}" value="${searchData[field.key]?.id}" default="none" noSelection="${['':'']}" optionKey="id"/>
                                </g:elseif>
                            </div>
                        </div>
                    </g:each>
                </div>
                <div class="right-search-pane">
                    <g:each in="${searchForm.fields.paneRight}" var="field" status="index">
                        <div class="${searchForm.cssClass[1]}">
                            <div class="${field.value[1]}"><g:message code="${field.value[3]}"/></div>
                            <div class="${field.value[1]}">
                                <g:if test="${field.value[0] == 'string'}">
                                    <g:textField name="${field.key}" class="${field.value[2]}" value="${searchData[field.key]}" />
                                </g:if>
                                <g:elseif test="${field.value[0] == 'date'}">
                                    <g:datePicker name="${field.key}" precision="day" default="none" noSelection="${['':'']}" years="${Calendar.instance.get(Calendar.YEAR)..(Calendar.instance.get(Calendar.YEAR) + field.value[4])}" value="${searchData[field.key]}" />
                                </g:elseif> 
                                <g:elseif test="${field.value[0] == 'select'}">
                                    <g:select name="${field.key}.id" class="${field.value[2]}" from="${grailsApplication.getDomainClass(field.value[4])?.getClazz()?.list()}" default="none" noSelection="${['':'']}" value="${searchData[field.key]?.id}" optionKey="id"/>
                                </g:elseif>
                                <g:elseif test="${field.value[0] == 'subset'}">
                                    <g:select class="${field.value[2]}" name="${field.key}.id" from="${subsetData[field.key]}" value="${searchData[field.key]?.id}" default="none" noSelection="${['':'']}" optionKey="id"/>
                                </g:elseif>                                
                            </div>
                        </div>
                    </g:each>
                </div>  
                <div class="actions-search-pane">
                    <div style="width: 48%; display: inline-block; vertical-align: top; float: left;">
                        <g:actionSubmitImage value="${searchForm.buttons['search'][1]}" 
                            class="action-image" style="margin-right: 10px;"
                            title="${message(code: searchForm.buttons['search'][1])}" 
                            action="${searchForm.targetMain[1]}" 
                            src="${resource(dir: 'images', file: searchForm.buttons['search'][0])}" />
                    </div>
                    <div style="width: 48%; display: inline-block; vertical-align: top; float: right; margin-left: 2px;">
                        <g:actionSubmitImage class="action-image" style="margin-top: 0px;" value="${searchForm.buttons['reset'][1]}" title="${message(code: searchForm.buttons['reset'][1])}" action="${searchForm.targetReset[1]}" src="${resource(dir: 'images', file: searchForm.buttons['reset'][0])}" />
                    </div>
                </div>
            </div>
        </g:form>
    </body>
</html>
