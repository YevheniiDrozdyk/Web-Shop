<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="list"%>

<!DOCTYPE HTML>
<html>

<div class="special">
	<div class="container">
		<h3>Guitars</h3>
		<div class="filter__menu">
			<form id="" action="showList" method="get">
				<div>
					<label>Categories:</label>
					<c:forEach var="category" items="${categories }">
						<label for="checkbox"> <input type="checkbox"
							name="categories" value="${category.id }"
							<c:forEach var="isChecked" items="${paramValues.categories}">
                                    <c:if test="${isChecked eq category.id}">
                                        checked
                                    </c:if>
                                </c:forEach>>${category.name }
							</input>
						</label>
					</c:forEach>
				</div>
				<div>
					<label>Producer:</label>
					<c:forEach var="producer" items="${producers }">
						<label for="checkbox"> <input type="checkbox"
							name="producers" value="${producer.id }"
							<c:forEach var="isChecked" items="${paramValues.producers}">
                                    <c:if test="${isChecked eq producer.id}">
                                        checked
                                    </c:if>
                                </c:forEach>>${producer.name }
							</input>
						</label>
					</c:forEach>
				</div>
				<div>
					<label>Adapter:</label> <label for="checkbox"> <input
						type="checkbox" name="adapters" value="true"
						<c:forEach var="isChecked" items="${paramValues.adapters }">
                                    <c:if test="${isChecked eq true}">
                                        checked
                                    </c:if>
                                </c:forEach>>There`s
						</input>
					</label> <label for="checkbox"> <input type="checkbox"
						name="adapters" value="false"
						<c:forEach var="isChecked" items="${paramValues.adapters }">
                                    <c:if test="${isChecked eq false}">
                                        checked
                                    </c:if>
                                </c:forEach>>No
						</input>
					</label>
				</div>
				<div>
					<label>Price:</label> <label for="number"> <input
						type="number" name="from_price" value="${param.from_price }">From</input>
					</label> <label for="number"> <input type="number" name="to_price"
						value="${param.to_price }">To</input>
					</label>
				</div>
				<div>
					<label>Sorting:</label> <select name="sorting_column">
						<option value="producer"
							<c:if test="${param.sorting_column eq 'producer' }">
                                selected
                            </c:if>>by
							producer</option>
						<option value="price"
							<c:if test="${param.sorting_column eq 'price' }">
                                selected
                            </c:if>>by
							price</option>
					</select>
				</div>
				<div>
					<label>Order:</label> <select name="sorting_type">
						<option value="asc"
							<c:if test="${param.sorting_type eq 'asc' }">
                                selected
                            </c:if>>ascending
						</option>
						<option value="desc"
							<c:if test="${param.sorting_type eq 'desc' }">
                                selected
                            </c:if>>descending
						</option>
					</select>
				</div>
				<div>
					<label>Limit on page:</label> <select name="limit">
						<option value="4"
							<c:if test="${param.limit eq 4 }">
                                                selected
                                            </c:if>>4
						</option>
						<option value="6"
							<c:if test="${param.limit eq 6 }">
                                                selected
                                            </c:if>>6
						</option>
						<option value="8"
							<c:if test="${param.limit eq 8 }">
                                                selected
                                            </c:if>>8
						</option>
					</select>
				</div>
				<div>
					<input type="hidden" name="page" value="1">
				</div>
				<div>
					<input type="submit" value="Apply">
				</div>
			</form>
		</div>
		<ul class="grid_2">
			<c:forEach var="instrument" items="${instruments }">
				<li><a href="#"> <img
						src="image?code=${instrument.vendorCode }" class="img-responsive"
						alt="">
				</a>
					<div class="special-info grid_1 simpleCart_shelfItem">
						<h5>Guitar category: ${instrument.category }</h5>
						<h5>Producer: ${instrument.producer }</h5>
						<h5>Vendor code: ${instrument.vendorCode }</h5>
						<div class="item_add">
							<span class="item_price"><h6>Price:
									$${instrument.price}</h6></span>
						</div>
						<div class="item_add">
							<span class="item_price"> <a href="#" target="__blank"
								onclick="Cart.addToCart('${instrument.vendorCode }'); return false;">add
									to cart </a>
							</span>
						</div>
					</div></li>
			</c:forEach>
			<div class="clearfix"></div>
		</ul>
		<div>
			<list:pagination />
		</div>
	</div>
</div>

</html>