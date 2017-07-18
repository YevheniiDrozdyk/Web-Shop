<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
    <c:when test="${not empty sessionScope.currentUser}">
       <div class="userGreeting">Welcome to web-shop, ${sessionScope.currentUser.getFirstName()}</div>
       <div id="avatarContainer">
           <img src="avatar" height="100%" width="100%">
       </div>
        <a href="logout" id="loginButton"><span>Logout</span></a>
    </c:when>
    <c:otherwise>
        <a href="login" id="loginButton"><span>Login</span></a>
    </c:otherwise>
</c:choose>