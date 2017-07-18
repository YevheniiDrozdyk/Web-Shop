<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://drozdyk.epam.com/webshop/tags" prefix="customTag"%>

<!DOCTYPE HTML>
<html>

<!-- content -->
<div class="container">
	<div class="main">
		<!-- start registration -->
		<div class="registration">
			<div class="registration_left">
				<h2>
					new user? <span> create an account </span>
					<c:if test="${regErrors.existing_user != null}">
						<div class="error-message">
							<span class="error-message">${regErrors.existing_user}</span>
						</div>
					</c:if>
				</h2>
				<script>
					(function() {

						// Create input element for testing
						var inputs = document.createElement('input');

						// Create the supports object
						var supports = {};

						supports.autofocus = 'autofocus' in inputs;
						supports.required = 'required' in inputs;
						supports.placeholder = 'placeholder' in inputs;

						// Fallback for autofocus attribute
						if (!supports.autofocus) {

						}

						// Fallback for required attribute
						if (!supports.required) {

						}

						// Fallback for placeholder attribute
						if (!supports.placeholder) {

						}

						// Change text inside send button on submit
						var send = document.getElementById('register-submit');
						if (send) {
							send.onclick = function() {
								this.innerHTML = '...Sending';
							}
						}

					})();
				</script>

				<div class="registration_form">
					<!-- Form -->
					<form id="registration_form" action="registration" method="post"
						enctype="multipart/form-data">
						<div>
							<label> <input name="first_name"
								value="${lastRegForm.firstName }" placeholder="First name:"
								type="text" tabindex="1"> <c:if
									test="${regErrors.first_name != null}">
									<div class="error-message">
										<span class="error-message">${regErrors.first_name}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<label> <input name="last_name"
								value="${lastRegForm.lastName }" placeholder="Last name:"
								type="text" tabindex="2"> <c:if
									test="${regErrors.last_name != null}">
									<div class="error-message">
										<span class="error-message">${regErrors.last_name}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<label> <input name="email_address"
								value="${lastRegForm.email }" placeholder="Email address:"
								type="email" tabindex="3"> <c:if
									test="${regErrors.email_address != null}">
									<div class="error-message">
										<span class="error-message">${regErrors.email_address}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<label> <input name="user_password"
								placeholder="Password" type="password" tabindex="4"> <c:if
									test="${regErrors.user_password != null}">
									<div class="error-message">
										<span class="error-message">${regErrors.user_password}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<label> <input name="retype_password"
								placeholder="Retype password" type="password" tabindex="5">
								<c:if test="${regErrors.retype_password != null}">
									<div class="error-message">
										<span class="error-message">${regErrors.retype_password}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<label> <input type="file" name="avatar" />
							</label>
						</div>
						<div>
							<!-- Drawing captcha here... -->
							<customTag:captcha captchaId="${captchaId }" />
							<!-- ...end drawing -->
							<label> <input name="captcha" placeholder="Captcha:"
								type="text" tabindex="6"> <c:if
									test="${regErrors.captcha != null}">
									<div class="error-message">
										<span class="error-message">${regErrors.captcha}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div class="sky-form">
							<label class="checkbox"> <input name="agreement"
								type="checkbox"><i></i>i agree to shoppe.com &nbsp;<a
								class="terms" href="#"> terms of service</a> <c:if
									test="${regErrors.agreement != null}">
									<div class="error-message">
										<span class="error-message">${regErrors.agreement}</span>
									</div>
								</c:if>
							</label>
						</div>
						<div>
							<input type="submit" value="create an account"
								id="register-submit">
						</div>
					</form>
					<!-- /Form -->
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<!-- end registration -->
	</div>
</div>
<script type='text/javascript' src="static/js/validatorJquery.js"></script>

</html>