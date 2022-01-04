<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="offset-4 col-4">
	<form action="" method="POST" style="margin-top: 5%">
		<div class="card">
			<div class="card-header">
				<b>Login to System</b>
			</div>
			<div class="card-body">
				<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="form-group">
					<label for="username">Username</label>
					<input type="text"
						class="form-control" name="username" id="username"
						aria-describedby="UsernamehelpId" placeholder="Username">
					<small id="UsernamehelpId" class="form-text text-muted">Username is Required</small>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password"
						name="password" id="password" class="form-control"
						placeholder="Password" aria-describedby="PasswordhelpId">
					<small id="PasswordhelpId" class="text-muted">Password is Required</small>
				</div>
				<div class="form-check form-check-inline">
					<label><input type="checkbox" class="form-check-input"
						name="remember">Remember me?</label>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Login</button>
			</div>
		</div>
	</form>
</div>