<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="login-page">
    <div class="login-main">  	
    	 <div class="login-head">
				<h1>Login</h1>
			</div>
			<div class="login-block">
				<form action="admin/login" method="post">
				${message }
					<input type="text" name="username" placeholder="Username" required="">
					<input type="password" name="password" class="lock" placeholder="Password">
					<div class="forgot-top-grids">
						<div class="forgot-grid">
							<ul>
								<li>
									<input type="checkbox" id="brand1" value="">
								</li>
							</ul>
						</div>
						<div class="clearfix"> </div>
					</div>
					<input type="submit" name="Sign In" value="Login">	
				</form>
				<h5><a href="index.html">Go Back to Home</a></h5>
			</div>
      </div>
</div>