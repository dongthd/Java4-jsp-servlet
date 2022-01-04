<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="offset-3 col-6 mt-4">
	<form action="ChangePassword" method="post" style="margin-top: 0%">
		<div class="card">
			<div class="card-header">Change Password</div>
			<div class="card-body">
				<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label for="username">User Name</label> <input type="text"
								class="form-control" name="username" id="username" value="${username }"
								aria-describedby="helpId" placeholder="Username"> <small
								id="usernameHid" class="form-text text-muted">User Name
								is required</small>
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control" name="password" id="password"
								placeholder="Password">
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label for="currentpassword">Current Password</label> <input
								type="password" class="form-control" name="currentPassword"
								id="currentPassword" placeholder="Current Password">
						</div>
						<br>
						<div class="form-group">
							<label for="confirmpassword">Confirm Password</label> <input
								type="password" class="form-control" name="confirmPassword"
								id="confirmPassword" placeholder="Confirm Password">
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Change Password</button>
			</div>
		</div>
	</form>
</div>