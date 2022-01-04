package com.java.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.common.EmailUtils;
import com.java.common.PageInfo;
import com.java.common.PageType;
import com.java.common.SessionUtils;
import com.java.dao.ShareDao;
import com.java.dao.UserDao;
import com.java.domain.Email;
import com.java.model.Share;
import com.java.model.User;
import com.java.model.Video;

/**
 * Servlet implementation class ShareVideoServlet
 */
@WebServlet("/ShareVideo")
public class ShareVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String videoId = request.getParameter("videoId");
		
		if(videoId == null) {
			response.sendRedirect("/HomePage");
			return;
		}
		request.setAttribute("videoId", videoId);
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_SHARE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");

			if (videoId == null) {
				request.setAttribute("error", "VideoId is null");

			} else {
				Email email = new Email();
				email.setFrom("dongthdpd03555@fpt.edu.vn");
				email.setFromPassword("donghai1");
				email.setTo(emailAddress);
				email.setSubject("Share Favorite Video");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear Ms/Mrs. <br>");
				sb.append("The video is more interesting anh I want to share with you <br> ");
				sb.append("Please click the link").append(String.format("<a href=''>View Video</a><br>",
						videoId));
				sb.append("Regards<br>");
				sb.append("Administrator");

				email.setContent(sb.toString());
				EmailUtils.send(email);
				
				ShareDao dao= new ShareDao();
				Share share = new Share();
				share.setEmails(emailAddress);
				share.setShareDate(new Date());
				
				String username = SessionUtils.getLoginedUsername(request);
				User user = new User();
				user.setUsername(username);
				
				share.setUser(user);
				Video video = new Video();
				video.setVideoId(videoId);
				share.setVideo(video);
				
				dao.insert(share);
				request.setAttribute("message","Video link has been sent");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_SHARE_PAGE);
	}

}
