<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<div class="container">
	<div class="main">
		<div class="registration">
			<div class="registration_left">
				<h2>
					Payment<span> Enter your card</span>
				</h2>
				<div class="registration_form">
					<form id="payment_form" action="confirmOrder" method="post">
						<div>
							<label>Bankcard number:</label> <label> <input
								name="bank_card" value="" type="number">
							</label>
						</div>
						<div>
							<label>Delivery address:</label> <label> <input
								name="delivery_address" type="text">
							</label>
						</div>
						<div>
							<input type="submit" value="Confirm" id="login">
						</div>
					</form>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
</html>