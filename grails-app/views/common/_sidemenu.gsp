<div id="accordion" class="menu-container">
    <g:each in="${menuList}" var="item" status="index">
        <h4 class="accordion-toggle"><g:message code="${item.caption}"/></h4>
        <div class="accordion-content default">
            <g:each in="${item.lines}" var="line" status="k">
                <g:if test="${line.active}">
                    <div class="line-item">
                        <g:if test="${line.ajax}">
                            <a href="#" name="view_profile" act="${line.actName}" cont="${line.contName}" caption="${message(code: line.caption)}" width="${line.width}" height="${line.height}">
                                <span style="color: #FFF;">&#9658;&nbsp;</span><g:message code="${line.caption}"/>
                            </a>
                        </g:if>
                        <g:else>
                            <g:link action="${line.actName}"><span style="color: #FFF;">&#9658;&nbsp;</span><g:message code="${line.caption}"/></g:link>
                        </g:else>
                    </div>
                </g:if>
                <g:else>
                    <div class="line-item"><a href="#${item.fragmentName}" style="color: #20b2aa; font-weight: bold;">&#9658;&nbsp;<g:message code="${line.caption}"/></a></div>
                    <%--div class="line-item"><a href="#${item.fragmentName}" class="current">&#9658;&nbsp;<g:message code="${line.caption}"/></a></div--%>
                </g:else>                
            </g:each>
        </div>
    </g:each>
    <h4 class="accordion-toggle"><g:message code="menus.logout.label"/></h4>
    <div class="accordion-content">
        <div class="line-item"><g:link controller="login" action="logout"><g:message code="menus.logout.exit.label"/></g:link></div>
        </div>
    </div>
