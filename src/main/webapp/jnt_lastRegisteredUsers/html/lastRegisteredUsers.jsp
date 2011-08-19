<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="org.jahia.modules.lastregisteredusers.LastRegisteredUsers"%>
<template:addResources type="css" resources="lastregistered.css"/>

<div id="lastRegisteredUsers Container">
	<p class="lastusersheader">
		<span><fmt:message key='jnt_lastRegisteredUsers.title'/></span>
	</p>
	<div id="latestUsers">
		<jsp:useBean id="lastRegisteredUsers" class="org.jahia.modules.lastregisteredusers.LastRegisteredUsers"  scope="request"/>
		<jsp:setProperty name="lastRegisteredUsers" property="numberUsers" value="${currentNode.properties['numberUsers'].string}"/>
		<jsp:getProperty name="lastRegisteredUsers" property="userNames"/>
	</div>
</div>



