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
				<th>Đơn hàng</th>
			</tr>
			<tr>
				<th>#</th>
				<th></th>
				<th>Sản phẩm</th>
				<th>Số lượng</th>
				<th>Đơn giá</th>
				<th>Thành tiền</th>
			</tr>
			<c:forEach items="${cartlist }" var="c" varStatus="i">
			<tr>
			<td width="10%">${i.index+1}</td>
			<td><img alt="" src="images/${c.product.picture }" width="50"/></td>
			<td>${c.product.productname }</td>
			<td>${c.getQuantity()}</td>
			<td>${c.product.unitprice}</td>
			<td>${c.getQuantity()*c.product.unitprice}</td>
			</tr>
			<c:set var="tong"
						value="${tong + c.product.unitprice*c.getQuantity() }"></c:set>
			</c:forEach>
			<tr>
			<th colspan="5" style="text-align:center ;">Tổng</th>
			<td>${tong }</td>
			</tr>
		</table>
		<br/>
		<table class="table table-bordered">
			<tr>
				<th>Thông tin giao hàng</th>
			</tr>
			<tr>
				<td>
					<form class="form-horizontal" action="payment" method="post">
					<c:if test="${user==null }">
					<div class="control-group">
							<label class="control-label">Họ và tên</label>
							<div class="controls">
								<input type="text" name="cusname">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >Số điện thoại</label>
							<div class="controls">
								<input type="text" name="phone" >
							</div>
						</div>
					</c:if>
					
						<div class="control-group">
							<label class="control-label" for="inputPost">Địa chỉ</label>
							<div class="controls">
								<input type="text" name="address" id="inputPost" value="${user.getAddress() }">
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
							<input type="hidden" name="payment_method" value="${payment_method }"/>
								<button name="accept" type="submit" class="btn">Xác nhận</button>
							</div>
						</div>
					</form>
				</td>
			</tr>
		</table>
	</div>