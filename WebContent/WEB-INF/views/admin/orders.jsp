<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="work-progres">
	<div class="chit-chat-heading">Hóa đơn</div>
	<div class="table-responsive" style="overflow: scroll;min-height: 500px;">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th width="12%">Khách Hàng</th>
					<th>Ngày đặt</th>
					<th>Ngày giao</th>
					<th width="30%">Địa chỉ</th>
					<th>Tình trạng</th>
					<th>Ghi chú</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${orders }" var="order" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${order.getOrderID() }</td>
					<td>${order.customer.getLastName() } ${order.customer.getFirstName() }</td>
					<td>${order.getOrderDate() }</td>
					<td>${order.getRequiredDate() }</td>
					<td>${order.getShipAddress() }</td>
					<td>${true==order.getOrderStatus()?'<span class="label label-success">Đã thanh toán</span>':'<span class="label label-danger">Chưa thanh toán</span>'}</td>
					<td>${order.getNote()}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>