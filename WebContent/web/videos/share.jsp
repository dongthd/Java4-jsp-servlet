<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="offset-3 col-6">
	<form action="ShareVideo" method="post" style="margin-top: 3%">
		<input type="hidden" name="videoId" value="${videoId }">
		<div class="card">
			<div class="card-hearder">Send Video to your Friends</div>
			<div class="card-body">
			<jsp:include page="/common/inform.jsp"></jsp:include>
			<div class="row">
				<div class="col">
					<div class="form-group">
					<label for="email">Your Friend's emails:</label>
						<input type="text"
						class="form-control" name="email" id="email"
						aria-describedby="helpId" placeholder="Email"> <small
						id="helpId" class="form-text text-muted">Email is Required</small>
				</div>
				</div>
			</div>
				
			</div>
			<div class="card-footer">
				<button class="btn btn-success">Send</button>
			</div>
		</div>
	</form>
</div>