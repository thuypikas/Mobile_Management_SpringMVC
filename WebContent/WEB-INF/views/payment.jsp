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

		

		<table class="table table-bordered">
			<tr>
				<th>Phương thức thanh toán</th>
			</tr>
			<tr>
				<td>
					<form class="form-horizontal" action="payment" method="get">
					<input id="payment_method1" type="radio" name="payment_method" value="1">
							<label for="payment_method1">Thanh toán khi nhận hàng </label><br/>
							<input id="payment_method2" type="radio" name="payment_method" value="2">
							<label for="payment_method2">Thanh toán qua ngân hàng </label>
							
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn">Tiếp tục</button>
							</div>
						</div>
					</form>
				</td>
			</tr>
		</table>
	</div>