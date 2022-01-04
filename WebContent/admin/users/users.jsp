<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<jsp:include page="/common/inform.jsp"></jsp:include>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation"><a
			class="nav-link active" id="videoediting-tab" data-toggle="tab"
			href="#videoediting" role="tab" aria-controls="videoediting"
			aria-selected="true">User Editing</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="videolist-tab" data-toggle="tab" href="#videolist" role="tab"
			aria-controls="videolist" aria-selected="false">User List</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoediting"
			role="tabpanel" aria-labelledby="videoediting-tab">
			<form action="" method="post">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label for="username">Username</label> <input type="text"
										class="form-control" name="username" id="username" value="${user.username }"
										aria-describedby="usernamehelpId" placeholder="username">
									<small id="usernamehelpId" class="form-text text-muted">Username
										is required</small>
								</div>
								<div class="form-group">
									<label for="fullname">Fullname</label> <input type="text"
										class="form-control" name="fullname" id="fullname" value="${user.fullname }"
										aria-describedby="fullnamehelpId" placeholder="fullname">
									<small id="fullnamehelpId" class="form-text text-muted">Fullname
										is required</small>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label for="password">Password</label> <input type="password"
										class="form-control" name="password" id="password"
										aria-describedby="passwordhelpId" placeholder="password">
									<small id="passwordhelpId" class="form-text text-muted">Password
										is required</small>
								</div>
								<div class="form-group">
									<label for="email">Email</label> <input type="text"
										class="form-control" name="email" id="email" value="${user.email }"
										aria-describedby="emailhelpId" placeholder="email"> <small
										id="emailhelpId" class="form-text text-muted">Email is
										required</small>
								</div>
								<div class="form-check form-check-inline">
									<label for=""><input type="radio" name="admin" ${user.admin?'checked':'' }
										id="status" class="form-check-input" value="true">Admin</label>
									<label for=""><input type="radio" name="admin" ${user.admin?'':'checked' }
										id="status" class="form-check-input" value="false">User</label>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-primary" formaction="Admin/UsersManagement/create">Create</button>
						<button class="btn btn-warning" formaction="Admin/UsersManagement/update">Update</button>
						<button class="btn btn-danger" formaction="Admin/UsersManagement/update">Delete</button>
						<button class="btn btn-info" formaction="Admin/UsersManagement/resert">Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="videolist" role="tabpanel"
			aria-labelledby="videolist-tab">
			<table class="table table-stripe">
				<tr>
					<td>Username</td>
					<td>Fullname</td>
					<td>Email</td>
					<td>Role</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${users}" >
					<tr>
						<td>${item.username }</td>
						<td>${item.fullname }</td>
						<td>${item.email }</td>
						
						<%-- <td>${item.admin?'Admin' : 'User'}</td>--%>
						<td>${item.admin?'Admin':'User' }</td>
						<td>
							<a href="Admin/UsersManagement/edit?username=${item.username }"><i class="fa fa-edit" aria-hidden="true"></i>Edit | </a>
							<a href="Admin/UsersManagement/delete?username=${item.username }"><i class="fa fa-trash" aria-hidden="true"></i>Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
