<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${pageContext.request.locale }"/>
<fmt:setBundle basename="resources"/>
<%@ taglib tagdir="/WEB-INF/tags" prefix="login"%>

<!DOCTYPE HTML>
<html>

<div class="container">
	<div class="main">
        <login:localization />
		<!-- start login  -->
		<div class="registration">
			<div class="registration_left">
				<h2>
					<fmt:message key="login"/><span>&nbsp;<fmt:message key="credential"/></span>
				</h2>
				<div class="registration_form">
					<form id="login_form" action="login" method="post">
						<div>
							<label><fmt:message key="email"/></label> <label> <input
								name="email_address" value="${lastLoginForm.email }"
								type="email"> <c:if
									test="${loginErrors.email_address != null}">
									<div class="error-message">
										<span class="error-message">${loginErrors.email_address}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<label><fmt:message key="password"/></label> <label> <input
								name="user_password" type="password"> <c:if
									test="${loginErrors.user_password != null}">
									<div class="error-message">
										<span class="error-message">${loginErrors.user_password}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<label for="checkbox"><input type="checkbox"
								id="checkbox"> <i><fmt:message key="remember"/></i></label>
						</div>
						<div>
							<input type="submit" value="<fmt:message key="sign_in"/>" id="login">
						</div>
					</form>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>

</html>