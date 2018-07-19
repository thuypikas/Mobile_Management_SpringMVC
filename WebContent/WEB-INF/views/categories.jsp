<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul id="sideManu" class="nav nav-tabs nav-stacked">
	<c:forEach items="${categories}" var="cat">
		<li class="subMenu open"><a> ${cat.categoryname }</a>
			<ul>
			<c:forEach items="${cat.supplier }" var="sup" >
			<li><a class="active" href="products?supid=${sup.supplierid }"><i
						class="icon-chevron-right"></i>${sup.companyname }</a></li>
			</c:forEach>
			</ul></li>
	</c:forEach>
</ul>