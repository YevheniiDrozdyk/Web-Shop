<!-- Template for all JSP -->
<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="template"%>

<!DOCTYPE HTML>
<html>

<head>
<title>Gretong web shop</title>
<link href="static/css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Gretong Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!-- start menu -->
<link href="static/css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
</head>

<body>
	<header>
		<!-- header_top -->
		<div class="top_bg">
			<div class="container">
				<div class="header_top">
					<div class="top_right">
						<ul>
							<li><a href="contact.html">Contact</a></li>
						</ul>
					</div>
					<div class="top_left">
						<h2>
							<span></span> Call us : +380 (68) 34-35-909
						</h2>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!-- header -->
		<div class="header_bg">
			<div class="container">
				<div class="header">
					<div class="head-t">
						<div class="logo">
							<a href="index.html"><img src="static/images/logo.png"
								class="img-responsive" alt="" /> </a>
						</div>
						<!-- start header_right -->
						<c:if test="${currentUser != null}">
							<div class="header_right profileLog">
						</c:if>
						<c:if test="${currentUser == null}">
							<div class="header_right">
						</c:if>
						<div class="rgt-bottom">
							<div class="log">
								<div class="login">
									<div id="loginContainer">
										<template:profile />
									</div>
								</div>
							</div>
							<div class="reg">
								<a href="registration">REGISTER</a>
							</div>
							<div class="cart box_1">
								<a href="checkout">
									<h3>
										<span class="simpleCart_total">$0.00</span> (<span
											id="simpleCart_quantity" class="simpleCart_quantity">0</span>
										items)<img src="static/images/bag.png" alt="">
									</h3>
								</a>
								<p>
									<a href="#" target="__blank" class="simpleCart_empty"
										onclick="Cart.clearCart(); return false;">(empty card)</a>
								</p>
								<div class="clearfix"></div>
							</div>
							<div class="create_btn">
								<a href="checkout">CHECKOUT</a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="search">
							<form>
								<input type="text" value="" placeholder="search..."> <input
									type="submit" value="">
							</form>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
				<!-- start header menu -->
				<ul class="megamenu skyblue">
					<li class="active grid"><a class="color1" href="index.html">Home</a></li>
					<li class="grid"><a class="color2"
						href="showList?limit=4&page=1">shop now</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>Guitars</h4>
										<ul>
											<li><a href="women.html">Acoustic</a></li>
											<li><a href="#">Bass</a></li>
											<li><a href="#">Classic</a></li>
											<li><a href="#">Electric</a></li>
										</ul>
									</div>
								</div>
								<div class="col1">
									<div class="h_nav">
										<h4>Violins</h4>
										<ul>
											<li><a href="#">Artisans</a></li>
											<li><a href="#">Factory</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col2"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
								<div class="col1"></div>
							</div>
						</div></li>
					<li><a class="color4" href="#">ACOUSTIC</a></li>
					<li><a class="color5" href="#">BASS</a></li>
					<li><a class="color6" href="#">CLASSIC</a></li>
					<li><a class="color7" href="#">ELECTRIC</a></li>
					<li><a class="color8" href="#">ARTISANS</a></li>
					<li><a class="color9" href="#">FACTORY</a></li>
				</ul>
			</div>
		</div>
		</div>
	</header>

	<div>
		<jsp:include page="${currentPage }" />
	</div>

	<footer>
		<div class="foot-top">
			<div class="container">
				<div class="col-md-6 s-c">
					<li>
						<div class="fooll">
							<h5>follow us on</h5>
						</div>
					</li>
					<li>
						<div class="social-ic">
							<ul>
								<li><a href="#"><i class="facebok"> </i></a></li>
								<li><a href="#"><i class="twiter"> </i></a></li>
								<li><a href="#"><i class="goog"> </i></a></li>
								<li><a href="#"><i class="be"> </i></a></li>
								<li><a href="#"><i class="pp"> </i></a></li>
								<div class="clearfix"></div>
							</ul>
						</div>
					</li>
					<div class="clearfix"></div>
				</div>
				<div class="col-md-6 s-c">
					<div class="stay">
						<div class="stay-left">
							<form>
								<input type="text"
									placeholder="Enter your email to join our newsletter"
									required="">
							</form>
						</div>
						<div class="btn-1">
							<form>
								<input type="submit" value="join">
							</form>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

		<div class="footer">
			<div class="container">
				<div class="col-md-3 cust">
					<h4>CUSTOMER CARE</h4>
					<li><a href="#">Help Center</a></li>
					<li><a href="#">FAQ</a></li>
					<li><a href="buy.html">How To Buy</a></li>
					<li><a href="#">Delivery</a></li>
				</div>
				<div class="col-md-2 abt">
					<h4>ABOUT US</h4>
					<li><a href="#">Our Stories</a></li>
					<li><a href="#">Press</a></li>
					<li><a href="#">Career</a></li>
					<li><a href="contact.html">Contact</a></li>
				</div>
				<div class="col-md-2 myac">
					<h4>MY ACCOUNT</h4>
					<li><a href="registration">Register</a></li>
					<li><a href="#">My Cart</a></li>
					<li><a href="#">Order History</a></li>
					<li><a href="buy.html">Payment</a></li>
				</div>
				<div class="col-md-5 our-st">
					<div class="our-left">
						<h4>OUR STORES</h4>
					</div>
					<div class="clearfix"></div>
					<li><i class="add"> </i>Ukraine, Kharkiv, Kolomenskaya str.,
						63</li>
					<li><i class="phone"> </i>+380 (68) 34-35-909</li>
					<li><a href="mailto:info@example.com"><i class="mail">
						</i>Yevhenii_Drozdyk@epam.com </a></li>
				</div>
				<div class="clearfix"></div>
				<p>
					Copyrights © 2015 Gretong. All rights reserved | Template by <a
						href="http://w3layouts.com/">W3layouts</a>
				</p>
			</div>
		</div>
	</footer>

	<!-- jQuery (necessary JavaScript plugins) -->
	<script type='text/javascript' src="static/js/jquery-1.11.1.min.js"></script>
	<script type='text/javascript' src="static/js/cart.js"></script>
</body>

</html>