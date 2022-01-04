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
			aria-selected="true">Video Editing</a></li>
		<li class="nav-item" role="presentation"><a class="nav-link"
			id="videolist-tab" data-toggle="tab" href="#videolist" role="tab"
			aria-controls="videolist" aria-selected="false">Video List</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoediting"
			role="tabpanel" aria-labelledby="videoediting-tab">
			<form action="" method="post" enctype="multipart/form-data">
				<div class="card">

					<div class="card-body">
						<div class="row">
							<div class="col-3">
								<div class="border p-3">
									<img src="${video.poster != null ? video.poster : 'images/logo_ys.png' }" alt="" class="img-fluid">
									<div class="input-group mb-3 mt-3">
										<div class="input-group-prepend"></div>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="cover"
												name="cover" /> <label for="cover"> <span
												class="input-group-text"> Upload </span></label>
										</div>
									</div>
								</div>
							</div>
							<div class="col-9">
								<div class="form-group">
									<label for="youtubeid">Youtube id</label> <input type="text"
										class="form-control" name="videoId" id="videoId" value="${video.videoId }"
										aria-describedby="youtubeIHid" placeholder="Video ID">
									<small id="youtubeIHid" class="form-text text-muted"></small>
								</div>
								<div class="form-group">
									<label for="videotitle">Video Title</label> <input type="text" value="${video.title }"
										class="form-control" name="title" id="title"
										aria-describedby="videotHid" placeholder="Video Title">
									<small id="videotHid" class="form-text text-muted"></small>
								</div>
								<div class="form-group">
									<label for="viewcount">View Count</label> <input type="text" value="${video.views }"
										class="form-control" name="views" id="views"
										aria-describedby="viewcountHid" placeholder="Views">
									<small id="viewcountHid" class="form-text text-muted"></small>
								</div>
								<div class="form-check form-check-inline">
									<label for=""><input type="radio" name="active" ${video.active?'checked':'' }
										id="status" class="form-check-input" value="true">Active</label>
									<label for=""><input type="radio" name="active" ${video.active?'':'checked' }
										id="status" class="form-check-input" value="false">Inactive</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label for="description">Description</label>
								<textarea name="description" id="description" cols="30" rows="4"
									class="form-control">${video.description }</textarea>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-primary" formaction="Admin/VideosManagement/create">Create</button>
						<button class="btn btn-warning" formaction="Admin/VideosManagement/update">Update</button>
						<button class="btn btn-danger" formaction="Admin/VideosManagement/delete">Delete</button>
						<button class="btn btn-info" formaction="Admin/VideosManagement/resert">Resert</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="videolist" role="tabpanel"
			aria-labelledby="videolist-tab">
			<table class="table table-stripe">
				<tr>
					<td>Youtube ID</td>
					<td>Video Title</td>
					<td>View Count</td>
					<td>Status</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${videos}">
				<tr>
					<td>${item.videoId }</td>
					<td>${item.title }</td>
					<td>${item.views }</td>
					<td>${item.active?'Active':'Deactive' }</td>
					<td>
					<a href="Admin/VideosManagement/edit?videoId=${item.videoId }"><i class="fa fa-edit" aria-hidden="true"></i>Edit</a>
					<a href="Admin/VideosManagement/delete?videoId=${item.videoId }"><i class="fa fa-trash" aria-hidden="true">Delete</i></a>
					</td>
				</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>
</div>