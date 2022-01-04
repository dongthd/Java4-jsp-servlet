<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-8 offset-2">
	<form action="" method="post">
	${Routes.WEB_REGISTRATION_SHOW }
		<div class="card">
			<div class="card-header">
				<b>Registration</b>
			</div>
			<div class="card-body">
			<jsp:include page = "/common/inform.jsp"></jsp:include>			
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label for="username">User Name</label>
							<input type="text" 
							class="form-control" name="username" id="username" value="${user.username}"
							aria-describedby="helpId" placeholder="Username">
							<small id="usernameHid" class="form-text text-muted">User Name is required</small>
						</div>
						<div class="form-group">
							<label for="fullname">Full Name</label> 
							<input type="text"
								class="form-control" name="fullname" id="fullname" value="${user.fullname}"
								placeholder="Fullname" aria-describedby="helpId">
								<small id="fullnameHid" class="text-muted">Full Name is required</small>
						</div>
					</div>

					<div class="col">
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control" name="password" id="password"
								placeholder=""> <br>
						</div>
						<div class="form-group">
							<label for="">Email</label>
							<input type="text" name="email" id="email" class="form-control" placeholder="Email" value="${user.email}"
								aria-describedby="helpId">
								<small id="emailHid" class="text-muted">Email is required</small>
						</div>
					</div>
				</div>

			</div>
			<div class="card-footer text-muted">
				<button class="btn btn-success">Sign Up</button>
			</div>
		</div>
	</form>
</div>