package com.java.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.common.PageInfo;
import com.java.common.PageType;
import com.java.common.SessionUtils;
import com.java.dao.FavoriteDao;
import com.java.model.Favorite;
import com.java.model.User;
import com.java.model.Video;

/**
 * Servlet implementation class LikeVideoServlet
 */
@WebServlet("/LikeVideo")
public class LikeVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String page = request.getParameter("page");
		String videoId = request.getParameter("videoId");
		
		if(videoId == null) {
			response.sendRedirect("/HomePage");
			return;
		}
		
		try {
			FavoriteDao dao = new FavoriteDao();
			Favorite favorite = new Favorite();
			Video video = new Video();
			video.setVideoId(videoId);
			favorite.setVideo(video);
			
			String username = SessionUtils.getLoginedUsername(request);
			User user = new User();
			user.setUsername(username);
			favorite.setUser(user);
			
			favorite.setLikedDate(new Date());
			
			dao.insert(favorite);
			
			request.setAttribute("message", "Video is added to Favorite");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		if (page == null) {
			page = "/HomePage";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
