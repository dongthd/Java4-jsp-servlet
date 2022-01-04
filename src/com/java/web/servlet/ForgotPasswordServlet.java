package com.java.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.common.EmailUtils;
import com.java.common.PageInfo;
import com.java.common.PageType;
import com.java.dao.UserDao;
import com.java.domain.Email;
import com.java.model.User;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_FORGOT_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String username = request.getParameter("username");

			UserDao dao = new UserDao();
			User user = dao.findBbUsernameAndEmail(username, emailAddress);

			if (user == null) {
				request.setAttribute("error", "Username or email are incorrect");

			} else {
				Email email = new Email();
				email.setFrom("dongthdpd03555@fpt.edu.vn");
				email.setFromPassword("donghai1");
				email.setTo(emailAddress);
				email.setSubject("Forgot password Function");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear ").append(username).append("<br>");
				sb.append("You are used the forgot password function. <br> ");
				sb.append("You password is <br< ").append(user.getPassword()).append("<br>");
				sb.append("Regards<br>");
				sb.append("Administrator");

				email.setContent(sb.toString());
				EmailUtils.send(email);

				request.setAttribute("message",
						"Email send to the email Address. " + "Please check and get your password");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_FORGOT_PASSWORD_PAGE);
	}

}
