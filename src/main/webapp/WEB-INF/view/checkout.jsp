<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<div class="container">
	<div class="check">
		<div class="col-md-3 cart-total">
			<div class="price-details">
				<h3>Price Details</h3>
				<span>Total</span> <span class="total1">${userCart.getTotalCost() }</span>
				<div class="clearfix"></div>
			</div>
			<ul class="total_price">
				<li class="last_price">
					<h4>TOTAL</h4>
				</li>
				<li class="last_price"><span id="total__price">${userCart.getTotalCost() }</span></li>
				<div class="clearfix"></div>
			</ul>
			<div class="clearfix"></div>
			<a class="order" href="placeOrder">Place Order</a>
		</div>
		<div class="col-md-9 cart-items">
			<h1>My Shopping Bag (2)</h1>
			<div class="cart-header">
				<c:forEach items="${userCart.getCart() }" var="entry">
					<div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							<img src="image?code=${entry.key.getVendorCode() }"
								class="img-responsive" alt="" />
						</div>
						<div class="cart-item-info">
							<h3>
								<a href="#">${entry.key.getCategory()} Guitar:
									${entry.key.getProducer()}</a><span>Vendor code:
									${entry.key.getVendorCode()}</span>
							</h3>
							<ul class="qty">
								<li><p id="item_qty">Quantity : ${entry.value}</p></li>
							</ul>
							<div class="delivery">
								<p>Service Charges : Rs.100.00</p>
								<span>Delivered in 2-3 bussiness days</span>
								<div class="clearfix"></div>
							</div>
						</div>
						<div>
							<a href="#"
								onclick="Cart.increaseAmount('${entry.key.getVendorCode()}');return false;"
								target="__blank">add one more</a>
						</div>
						<div>
							<a href="#"
								onclick="Cart.reduceAmount('${entry.key.getVendorCode()}');return false;"
								target="__blank">remove one</a>
						</div>
						<div>
							<a href="#"
								onclick="Cart.removeItem('${entry.key.getVendorCode()}');return false;"
								target="__blank">remove item</a>
						</div>
						<div class="clearfix"></div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
</html>