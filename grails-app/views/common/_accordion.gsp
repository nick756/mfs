<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mfs.utilities.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
		<ul class="accordion">
			
                    <g:each in="${menuList}" var="menuItem" index="k">
                        <li id="${menuItem.fragmentName}">
                            <a href="#${menuItem.fragmentName}"><g:message code="${menuItem.caption}"/></a>
                            <ul class="sub-menu">
                                <g:each in="${menuItem.lines}" var="menuLine" index="i">
                                    <g:if test="${menuLine.active}">
                                        <li><g:link action="${menuLine.actName}"><g:message code="${menuLine.caption}"/></g:link></li>
                                    </g:if>
                                    <g:else>
                                    <li><a href="#${menuItem.fragmentName}" style="color: #20b2aa;"><g:message code="${menuLine.caption}"/></a></li>
                                    </g:else>
                                </g:each>
                            </ul>
                        </li>
                    </g:each>
                    <%-- Fixed Menu Item --%>
                    <li id="999">
                        <a href="#999"><g:message code="menus.logout.label"/></a>
                            <ul class="sub-menu">
                                <li><g:link controller="login" action="logout"><g:message code="menus.logout.exit.label"/></g:link></li>
                            </ul>
                    </li>
                    <%--
			<li id="one">

				<a href="#one">My Files</a>

				<ul class="sub-menu">
					
					<li><a href="#one">Dropbox</a></li>
					
					<li><a href="#one">Skydrive</a></li>

					<li><a href="#one">FTP Server</a></li>

				</ul>

			</li>
			
			<li id="two">

				<a href="#two">Mail</a>

				<ul class="sub-menu">
					
					<li><a href="#two"><em>01</em>Hotmail</a></li>
					
					<li><a href="#two"><em>02</em>Yahoo</a></li>

					<li><a href="#two"><em>03</em>Gmail</a></li>

				</ul>

			</li>
			
			<li id="three">

				<a href="#three">Cloud</a>

				<ul class="sub-menu">
					
					<li><a href="#three"><em>01</em>Connect</a></li>
					
					<li><a href="#three"><em>02</em>Profiles</a></li>

					<li><a href="#three"><em>03</em>Options</a></li>

				</ul>

			</li>
			
			<li id="four">

				<a href="#four">Sign Out</a>

				<ul class="sub-menu">
					
					<li><a href="#four"><em>01</em>Log Out</a></li>
					
					<li><a href="#four"><em>02</em>Delete Account</a></li>

					<li><a href="#four"><em>03</em>Freeze Account</a></li>

				</ul>

			</li>
                        --%>
		
		</ul>

    </body>
</html>
