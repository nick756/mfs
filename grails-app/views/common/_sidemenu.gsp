<div id="accordion" class="menu-container">
    <g:each in="${menuList}" var="item" status="index">
        <h4 class="accordion-toggle"><g:message code="${item.caption}"/></h4>
        <div class="accordion-content default">
                <g:each in="${item.lines}" var="line" status="k">
                    <g:if test="${line.active}">
                        <div class="line-item"><g:link action="${line.actName}"><span style="color: #FFF;">&#9658;&nbsp;</span><g:message code="${line.caption}"/></g:link></div>
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
<%--
<div id="accordion">
  <h4 class="accordion-toggle">Accordion 1</h4>
  <div class="accordion-content default">
    <p>Cras malesuada ultrices augue molestie risus.</p>
  </div>
  <h4 class="accordion-toggle">Accordion 2</h4>
  <div class="accordion-content">
    <p>Lorem ipsum dolor sit amet mauris eu turpis.</p>
  </div>
  <h4 class="accordion-toggle">Accordion 3</h4>
  <div class="accordion-content">
    <p>Vivamus facilisisnibh scelerisque laoreet.</p>
  </div>
</div>
--%>
