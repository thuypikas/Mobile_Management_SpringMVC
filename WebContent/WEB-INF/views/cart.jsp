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
			<li class="active">SHOPPING CART</li>
		</ul>
		<h3>
			SHOPPING CART [ <small>${cartlist.size() } Sản phẩm </small>]<a href="products.html"
				class="btn btn-large pull-right"><i class="icon-arrow-left"></i>
				Continue Shopping </a>
		</h3>
		<hr class="soft" />

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Product</th>
					<th>Description</th>
					<th>Quantity/Update</th>
					<th>Price</th>
					<th>Discount</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cartlist }" var="c">
					<tr>
						<form class="cart" action="cart" method="post">
							<td><img width="60" src="images/${c.product.picture }"
								alt="" /></td>
							<td>${c.product.productname }<br />Color : black, Material
								: metal
							</td>
							<td>
								<div class="input-append">
									<input name="Quantity" class="quantity" style="width: 30px"
										type="number" min="1" max="10" value="${c.getQuantity() }">
									<button name="remove" class="btn btn-danger" type="submit">
										<i class="icon-remove icon-white"></i>
									</button>
								</div>
							</td>
							<td>${c.product.unitprice }</td>
							<td>$0.00</td>
							<td>${c.product.unitprice*c.getQuantity() }</td> 
							<input
								type="hidden" name="product.productid"
								value="${c.product.productid }"> 
							<input type="hidden"
								name="customer.CustomerID"
								value="${c.customer.getCustomerID() }">
						</form>
					</tr>
					<c:set var="tong"
						value="${tong + c.product.unitprice*c.getQuantity() }"></c:set>
				</c:forEach>

				<tr>
					<td colspan="5" style="text-align: right"><strong>TOTAL
							=</strong></td>
					<td class="label label-important" style="display: block"><strong>
							${tong } </strong></td>
				</tr>
			</tbody>
		</table>

		<script>
			$('.quantity').on('click', function() {
				<!--
				//js client
				var sum = 0;
				var quantity = $(this).val();
				var divprice = $(this).parent().parent().find('.unitprice');
				var subtotal = parseInt(divprice.text(), 10) * quantity;
				var divsubtotal = divprice.parent().find('.subtotal');
				divsubtotal.text(subtotal);
				$('.subtotal').each(function() {
					sum += parseInt($(this).text(), 10);
				});
				$('#price').text(sum);
				-->
				var x = $(this).parent().parent().parent().find('.cart');
				x.submit();
			});
		</script>
		
		<a href="products.html" class="btn btn-large"><i
			class="icon-arrow-left"></i> Continue Shopping </a> <a href="payment.html"
			class="btn btn-large pull-right">Thanh toán <i class="icon-arrow-right"></i></a>

	</div>
	</div>