<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="span9">
<c:forEach items="${list }" var="i">
<p>${i}</p>
</c:forEach>
</div>