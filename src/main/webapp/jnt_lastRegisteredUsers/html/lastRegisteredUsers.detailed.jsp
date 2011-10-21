<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="org.jahia.modules.lastregisteredusers.DateTranslation"%>
<template:addResources type="css" resources="lastregistered.css"/>

<c:choose>
	<c:when test="${currentUser.name != 'guest'}" >
	<div id="lastRegisteredUsers Container">
		<p class="lastusersheader">
			<span>${currentNode.propertiesAsString['jcr:title']}</span><br/>
		</p>	
		<jsp:useBean id="sinceProcessing" class="org.jahia.modules.lastregisteredusers.DateTranslation"  scope="request"/>
		<jsp:setProperty name="sinceProcessing" property="startDate" value="${currentNode.properties['startDate'].string}"/>
		<jsp:setProperty name="sinceProcessing" property="endDate" value="${currentNode.properties['endDate'].string}"/>	
		<div id="latestUsers">
			<jcr:sql var="lastRegistered" sql="select * from [jnt:user] as u ${sinceProcessing.jcrConstraint} order by u.[jcr:created] desc" limit="${currentNode.properties['numberUsers'].string}"/>
			 <c:forEach items="${lastRegistered.nodes}" var="user">
			 	<c:if test="${user.name != 'guest'}" >
				 	<strong><fmt:message key='jnt_lastRegisteredUsers.userNameLabel'/>:</strong> ${user.name}<br/>
				 	<strong><fmt:message key='jnt_lastRegisteredUsers.creationDateLabel'/>:</strong>
				 	<fmt:parseDate var="dateObj" value="${user.propertiesAsString['jcr:created']}" type="DATE" pattern="yyyy-MM-dd"/>		 	        
					<fmt:formatDate value="${dateObj}" pattern="MMM dd, yyyy"/><br/>
					<strong><fmt:message key='jnt_lastRegisteredUsers.firstNameLabel'/>:</strong> ${user.propertiesAsString['j:firtsName']}<br/>
					<strong><fmt:message key='jnt_lastRegisteredUsers.lastNameLabel'/>:</strong> ${user.propertiesAsString['j:lastName']}<br/>
					<strong><fmt:message key='jnt_lastRegisteredUsers.emailLabel'/>:</strong> ${user.propertiesAsString['email']}<br/>
					<strong><fmt:message key='jnt_lastRegisteredUsers.primaryTypeLabel'/>:</strong> ${user.propertiesAsString['jcr:primaryType']}
					<br/><br/>
				</c:if>
			 </c:forEach>
			 <c:if test="${lastRegistered.nodes.size == 0}">
				<strong><fmt:message key='jnt_lastRegisteredUsers.noResults'/></strong>
			</c:if>
		</div>
	</div>
	</c:when>
	<c:otherwise>
		<div><strong><fmt:message key='jnt_lastRegisteredUsers.errorMessage'/></strong></div>
	</c:otherwise>
</c:choose>	