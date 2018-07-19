<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<!-- Sidebar ================================================== -->
	<div id="sidebar" class="span3">
		<div class="well well-small">
			<a id="myCart" href="cart.html"><img
				src="themes/images/ico-cart.png" alt="cart">${cartlist.size() } Sản phẩm <span
				class="badge badge-warning pull-right">$</span></a>
		</div>
		<!-- Categories ================================================== -->
		<jsp:include page="categories.jsp"></jsp:include>
		<br />
		<br />
	</div>
	<div class="span9">
		<ul class="breadcrumb">
			<li><a href="index.html">Home</a> <span class="divider">/</span></li>
			<li class="active">Payment</li>
		</ul>
		<hr class="soft" />
		<span style="color: green;">${message }</span>
		<span style="color: red;">${message2 }</span>
	</div>