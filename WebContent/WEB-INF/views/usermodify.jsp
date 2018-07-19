<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="span9">
	<ul class="breadcrumb">
		<li><a href="index.html">Home</a> <span class="divider">/</span></li>
		<li class="active">Registration</li>
	</ul>
	<h3>Infomation</h3>
	<div class="well">
		
			<form:form class="form-horizontal" action="usermodify/save"
				method="post" modelAttribute="customers">

				<form:input type="hidden" path="CustomerID"
							value="${cus.getCustomerID()}" />
				<h4>Your personal information</h4>
				<div class="control-group">
					<label class="control-label" for="FirstName">First name <sup>*</sup></label>
					<div class="controls">
						<form:input placeholder="First Name" path="FirstName"
							value="${cus.getFirstName()}" />
						<form:errors path="FirstName"></form:errors>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="Last">Last name <sup>*</sup></label>
					<div class="controls">
						<form:input type="text" placeholder="Last Name" path="LastName"
							value="${cus.getLastName()}" />
						<form:errors path="LastName"></form:errors>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="Address">Address <sup></sup></label>
					<div class="controls">
						<form:input type="text" placeholder="Address" path="Address"
							value="${cus.getAddress()}" />
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="phone">phone <sup></sup></label>
					<div class="controls">
						<form:input type="text" path="phone" placeholder="phone"
							value="${cus.getPhone()}" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="email">Email<sup>*</sup></label>
					<div class="controls">
						<form:input type="email" path="Email" placeholder="Email"
							value="${cus.getEmail()}" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="picture">picture<sup></sup></label>
					<div class="controls">
						<form:input type="text" path="picture" placeholder="picture"
							value="${cus.getPicture()}" />
					</div>
				</div>




				<div class="control-group">
					<div class="controls">
						<input class="btn btn-large btn-success" type="submit"
							value="Save" />
						<h3>${message }</h3>
					</div>
				</div>

			</form:form>
		
	</div>

</div>