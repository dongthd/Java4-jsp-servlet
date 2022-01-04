<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-9">
	<div class="row p-2">
		<c:forEach var="item" items="${videos }">
		<div class="col-3 mt-2">
			<div class="card text-center">
				<div class="card-body">
					<img src="${empty item.poster? 'images/logo_ys.png': item.poster }" width="90%" alt="" class="img-fluid">
					<div class="row border-top mt-2">
						<b>${item.title }</b>
					</div>
				</div>
				<div class="card-footer">
					<a href="LikeVideo?videoId=${item.videoId }" class="btn btn-primary">Like</a>
					<a href="ShareVideo?videoId=${item.videoId }" class="btn btn-warning">Share</a>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	<div class="row">
		<div class="col" style="text-align: center;">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>
<div class="col-3">
	<div class="row mt-3 mb-3">
		<div class="col">
			<div class="card poly-card">
				<div class="card-header">
					<span class="glyphicon glyphicon-th-list"></span> Favorites
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Computer</li>
					<li class="list-group-item">Máy tính xách tay</li>
					<li class="list-group-item">Máy tính để bàn</li>
					<li class="list-group-item">Laptop1</li>
					<li class="list-group-item">Laptop2</li>
					<li class="list-group-item">Laptop3</li>
				</ul>
			</div>
		</div>
	</div>
</div>