<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<!-- Sidebar ================================================== -->
	<div id="sidebar" class="span3">
		<div class="well well-small">
			<a id="myCart" href="cart.html"><img
				src="themes/images/ico-cart.png" alt="cart">${cartlist.size() }
				Sản phẩm <span class="badge badge-warning pull-right">$</span></a>
		</div>
		<!-- Categories ================================================== -->
		<jsp:include page="categories.jsp"></jsp:include>
		<br />
		<div class="thumbnail" style="background: Gainsboro;">
		<form action="filter" id="filter" method="get">
			<label>Màn hình</label><select name="scsize" onchange="document.getElementById('filter').submit();">
				<option value="">Tất cả</option>
				<option value="4" ${'4'==scsize?'selected':''}>4 inch</option>
				<option value="5"${'5'==scsize?'selected':''}>5 inch</option>
				<option value="6"${'6'==scsize?'selected':''}>6 inch</option>
			</select> <label>Hệ điều hành</label><select name="os" onchange="document.getElementById('filter').submit();">
				<option value="">Tất cả</option>
				<option value="ios" ${'ios'==os?'selected':''}>IOS</option>
				<option value="android" ${'android'==os?'selected':''}>Android</option>
				<option value="windowphone" ${'windowphone'==os?'selected':''}>Window Phone</option>
			</select> <label>Khoảng giá</label><select name="pricelv" onchange="document.getElementById('filter').submit();">
				<option value="0">Tất cả</option>
				<option value="1" ${'1'==pricelv?'selected':''}>Dưới 3 triệu</option>
				<option value="2" ${'2'==pricelv?'selected':''}>3 - 7 triệu</option>
				<option value="3" ${'3'==pricelv?'selected':''}>7 - 12triệu</option>
				<option value="4" ${'4'==pricelv?'selected':''}>Trên 12 triệu</option>
			</select>
			</form>
		</div>
		<br />
	</div>
	<!-- Sidebar end=============================================== -->
	<div class="span9">
		<ul class="breadcrumb">
			<li><a href="index.html">Sản phẩm</a> <span class="divider">/</span></li>
			<li class="active"></li>
		</ul>
		<form class="form-horizontal span6" id="sortby" action="products"
			method="post">
			<div class="control-group">
				<label class="control-label alignL">Sắp xếp theo </label> <select
					name="sortby" onchange="document.getElementById('sortby').submit();">
					<option value="">Mặc định</option>
					<option value="name-asc"${'name-asc'==sortby?'selected':''}>Tên sản phẩm A-Z</option>
					<option value="name-desc"${'name-desc'==sortby?'selected':''}>Tên sản phẩm Z - A</option>
					<option value="price-asc"${'price-asc'==sortby?'selected':''}>Giá thấp đến cao</option>
					<option value="price-desc"${'price-desc'==sortby?'selected':''}>Giá cao đến thấp</option>
				</select>
			</div>
			<c:if test="${supid!=null }">
			<input type="hidden" name="supid" value="${supid }"> 
			</c:if>
		</form>
		<br class="clr" />
		<div class="tab-content">
			<div class="tab-pane  active" id="blockView">
				<ul class="thumbnails">
					<c:forEach items="${products }" var="p">
						<li class="span3">
							<div class="thumbnail" style="width: 250;height: 350px">
								<form action="cart" method="post">
									<a href="products/?prdid=${p.productid }"><img width="200px"
										src="images/${p.picture }" alt="" /></a>
									<div class="caption">
										<h5>${p.productname }</h5>
										<h4 style="text-align: center">
											<a class="btn" href="products/?prdid=${p.productid }"> <i
												class="icon-zoom-in"></i></a>
											<button class="btn" type="submit">
												Thêm<i class="icon-shopping-cart"></i>
											</button>
											<a class="btn btn-primary" href="#">&euro;${p.unitprice}</a>
										</h4>
									</div>
									<input type="hidden" name="productid" value="${p.productid }">
									<input type="hidden" name="productname"
										value="${p.productname }"> <input type="hidden"
										name="unitprice" value="${p.unitprice }"> <input
										type="hidden" name="picture" value="${p.picture }"> <input
										type="hidden" name="add">
								</form>
							</div>
						</li>
					</c:forEach>

				</ul>
				<hr class="soft" />
			</div>
		</div>

		<a href="compair.html" class="btn btn-large pull-right">Compair
			Product</a>
		<div class="pagination">
			<jsp:include page="paging.jsp"></jsp:include>
		</div>
		<br class="clr" />
	</div>
</div>