<%@tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${pageContext.request.locale }"/>
<fmt:setBundle basename="resources"/>

<select class="dropdown" onchange="location = this.value;">
    <option value="${requestScope['javax.servlet.forward.request_uri']}?lang=en" ${pageContext.request.locale eq 'en' ? 'selected' : ''}><fmt:message key="english"/></option>
    <option value="${requestScope['javax.servlet.forward.request_uri']}?lang=ru" ${pageContext.request.locale eq 'ru' ? 'selected' : ''}><fmt:message key="russian"/></option>
    <option value="${requestScope['javax.servlet.forward.request_uri']}?lang=uk" ${pageContext.request.locale eq 'uk' ? 'selected' : ''}><fmt:message key="ukrainian"/></option>
</select>