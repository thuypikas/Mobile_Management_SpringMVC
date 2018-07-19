<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="product-block">
	<div class="pro-head">
		<h2>Products</h2>
	</div>
	<div
		style="margin: 10px 0 5px 0; height: 30px; padding-right: 50px; margin: auto;">
		<form id="paging" action="admin/productmgt" method="get">
			<input type="hidden" name="supid" value="${supid }"> <select
				name="page" onchange="document.getElementById('paging').submit();"
				style="float: right;">
				<c:forEach begin="1" end="${soluong/9+1 }" varStatus="i">
					<c:if test="${paging==i.index }">
						<option value="${i.index }" selected="selected">${i.index}</option>
					</c:if>
					<c:if test="${paging!=i.index }">
						<option value="${i.index }">${i.index}</option>
					</c:if>
				</c:forEach>
			</select>
		</form>
		<form id="supid" action="admin/productmgt" method="get">
			<select name="supid"
				onchange="document.getElementById('supid').submit();"
				style="float: right;">
				<option value="0">Tất cả</option>
				<c:forEach items="${suppliers }" var="s">
					<c:if test="${supid==s.supplierid }">
						<option value="${s.supplierid }" selected="selected">${s.companyname }</option>
					</c:if>
					<c:if test="${supid!=s.supplierid }">
						<option value="${s.supplierid }">${s.companyname }</option>
					</c:if>
				</c:forEach>
			</select>
		</form>
	</div>
	<div style="overflow: scroll; height: 800px; margin: auto">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>Tên sản phẩm</th>
					<th>Nhà cung cấp</th>
					<th>Giá bán</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5"></td>
					<td><button class="btn btn-default" style="border: none">
							<a href="admin/productmgt/?new"> <span
								class="glyphicon glyphicon-plus" style="font-size: 18px"></span>
							</a>
						</button></td>
				</tr>
				<c:forEach var="p" items="${products }" begin="0" varStatus="i">
					<form action="admin/productmgt" method="post">
						<tr>
							<td>${i.index+1 }</td>
							<td>${p.productid }</td>
							<td>${p.productname }</td>
							<td>${p.supplier.companyname }</td>
							<td>${p.unitprice }</td>
							<td><button name="edit" type="submit"
									class="btn btn-default" style="border: none">
									<a href="#"> <span class="glyphicon glyphicon-edit"
										style="font-size: 18px"></span>
									</a>
								</button>
								<button name="remove3" type="submit" class="btn btn-default"
									style="border: none" onclick="return confirm('Bạn muốn loại bỏ ${p.productname } ?')">
									<a href="#"> <span
										class="glyphicon glyphicon-remove-circle"
										style="font-size: 18px"></span>
									</a>
								</button>
								</td>
						</tr>
						<input type="hidden" name="productid" value="${p.productid }" />
						<input type="hidden" name="productname" value="${p.productname }" />
						<input type="hidden" name="unitprice" value="${p.unitprice }" />
						<input type="hidden" name="description" value="${p.description }" />
						<input type="hidden" name="picture" value="${p.picture }" /> <input
							type="hidden" name="soluong" value="${p.soluong }" /> <input
							type="hidden" name="category.categoryid"
							value="${p.category.categoryid}" /> <input type="hidden"
							name="category.categoryname" value="${p.category.categoryname}" />
						<input type="hidden" name="supplier.supplierid"
							value="${p.supplier.supplierid}" /> <input type="hidden"
							name="supplier.companyname" value="${p.supplier.companyname}" />
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="clearfix"></div>
</div>