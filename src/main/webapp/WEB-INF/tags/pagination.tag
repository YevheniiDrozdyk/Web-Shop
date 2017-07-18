<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<ul style="white-space: nowrap;">
		<c:forEach begin="1" end="${pageCount }" varStatus="loop">
			<c:choose>
				<c:when test="${pageCount eq 1 }">
					<li class="active"><a href="#">${loop.index }</a></li>
				</c:when>
				<c:otherwise>
					<c:url var="pageUrl" value='showList'>
						<c:forEach var="category" items="${instrumentFilter.categories }">
							<c:param name="categories" value="${category }" />
						</c:forEach>

						<c:forEach var="producer" items="${instrumentFilter.producers }">
							<c:param name="producers" value="${producer }" />
						</c:forEach>

						<c:forEach var="adapter" items="${instrumentFilter.adapters }">
							<c:param name="adapters" value="${adapter }" />
						</c:forEach>

						<c:param name="from_price" value="${instrumentFilter.fromPrice }" />
						<c:param name="to_price" value="${instrumentFilter.toPrice }" />

						<c:param name="sorting_column"
							value="${instrumentFilter.sortingColumn }" />
						<c:param name="sorting_type"
							value="${instrumentFilter.sortingType }" />
						<c:param name="limit" value="${instrumentFilter.itemsLimit }" />
						<c:param name="page" value="${loop.index }" />
					</c:url>
					<li><a href="${pageUrl }">${loop.index}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</div>