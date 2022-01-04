package com.java.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.java.common.PageInfo;
import com.java.common.PageType;
import com.java.common.SessionUtils;
import com.java.dao.UserDao;
import com.java.model.User;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);
		if (username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}

		try {
			UserDao dao = new UserDao();
			User user = dao.findById(username);

			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_EDIT_PROFILE_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			String username = SessionUtils.getLoginedUsername(request);
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);
			user.setAdmin(oldUser.getAdmin());
			dao.update(user);
			request.setAttribute("message", "User profile updated !");
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_EDIT_PROFILE_PAGE);
	}

}
