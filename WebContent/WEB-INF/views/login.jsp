<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="span9">
	<ul class="breadcrumb">
		<li><a href="index.html">Home</a> <span class="divider">/</span></li>
		<li class="active">Đăng nhập</li>
	</ul>
	<div class="well">
		<!--
	<div class="alert alert-info fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	<div class="alert fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	 <div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div> -->
		<form class="form-horizontal" action="login" method="post">

			<h4>Đăng nhập</h4>
			${message }
			<div class="control-group">
				<label class="control-label" for="inputFname">Username <sup>*</sup></label>
				<div class="controls">
					<input type="text" id="inputFname" name="username"
						placeholder="Username">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputLname">Password <sup>*</sup></label>
				<div class="controls">
					<input type="password" id="inputLname" name="password"
						placeholder="Password" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<a href="" style="color: orange;">Forgot Password ?</a>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					 <input
						class="btn btn-large btn-success" type="submit" value="Login" />
				</div>
			</div>
		</form>
	</div>
	<div class="well">
		<h5>CREATE YOUR ACCOUNT</h5>
		<br> Enter your e-mail address to create an account.<br><br/>
			<div class="controls">
				<a href="register"><span class="btn btn-large btn-success">Create
						your Account</span></a>
			</div>
	</div>
</div>