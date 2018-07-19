<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="product-block">
	<div class="pro-head">
		<h2>Products</h2>
	</div>
	<div style="margin: auto; height: 600px">
		<form:form action="admin/productmgt" modelAttribute="product"
			enctype="multipart/form-data" method="post">
			<div class="col-md-5 single-right-left animated wow slideInUp"
				data-wow-delay=".5s">
				<input type="file" name="upfile" />
				<div class="flexslider">
					<ul class="slides">
						<li data-thumb="images/si.jpg"><c:if
								test='${product.picture!=null }'>
								<div class="thumb-image">
									<img src="images/${product.picture }" data-imagezoom="true"
										class="img-responsive">
								</div>
							</c:if></li>
					</ul>
				</div>
			</div>
			<div
				class="col-md-7 single-right-left simpleCart_shelfItem animated wow slideInRight"
				data-wow-delay=".5s" style="padding-left: 70px">
				${message }
				<table border="0"
					style="text-align: left; border-collapse: separate; border-spacing: 10px; color: Chocolate">
					<tr>
						<td class="tblabel">Product name</td>
						<td><form:errors path="productname"></form:errors> <form:input
								path="productname" /><form:input path="productid"
								type="hidden" /></td>
					</tr>
					<tr>
						<td class="tblabel">Categories</td>
						<td><form:select path="category.categoryid">
								<form:options items="${cats }" itemValue="categoryid"
									itemLabel="categoryname"></form:options>
							</form:select></td>
					</tr>
					<tr>
						<td class="tblabel">Supplier</td>
						<td><form:select path="supplier.supplierid">
								<form:options items="${suppliers }" itemValue="supplierid"
									itemLabel="companyname"></form:options>
							</form:select></td>
					</tr>
					<tr>
						<td class="tblabel">Unit price($)</td>
						<td><form:input path="unitprice" />
							<form:errors path="unitprice"></form:errors></td>
					</tr>
					<%-- 
					<tr>
						<td class="tblabel">Operating System</td>
						<td><form:input path="operatingsystem" /></td>
					</tr>
					<tr>
						<td class="tblabel">Screen Size</td>
						<td><form:input path="screensize" /></td>
					</tr>
					--%>
					<tr>
						<td class="tblabel">Description</td>
						<td><form:textarea path="description" cols="20" /> <form:input
								path="picture" type="hidden" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="control" style="margin-left: 100px; margin-top: 10px">
								<button name="update" type="submit" class="btn btn-default">Cập
									nhật</button>
								<button type="reset" class="btn btn-default">Làm lại</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form:form>
	</div>
	<div class="clearfix"></div>
</div>