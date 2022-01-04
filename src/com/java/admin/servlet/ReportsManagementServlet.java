package com.java.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.common.PageInfo;
import com.java.common.PageType;
import com.java.dao.FavoriteDao;
import com.java.dao.VideoDao;
import com.java.domain.FavoriteReport;
import com.java.domain.FavoriteUserReport;
import com.java.model.Video;

/**
 * Servlet implementation class ReportsManagementServlet
 */
@WebServlet("/ReportsManagementServlet")
public class ReportsManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reportFavoritesByVideos(request, response);
		reportFavoriteUsersByVideos(request, response);
		
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void reportFavoriteUsersByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");

			VideoDao vdao = new VideoDao();
			List<Video> vList = vdao.findAll();

			if (videoUserId == null && vList.size() > 0) {
				videoUserId = vList.get(0).getVideoId();
			}

			FavoriteDao dao = new FavoriteDao();
			List<FavoriteUserReport> list = dao.reportFavoriteUsersByVideo(videoUserId);

			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vList);
			request.setAttribute("favUsers", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void reportFavoritesByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FavoriteDao dao = new FavoriteDao();
			List<FavoriteReport> list = dao.reportFavoritesByVideos();

			request.setAttribute("favList", list);
		} catch (Exception e) {
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

}
