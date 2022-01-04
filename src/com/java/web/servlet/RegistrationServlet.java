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
import com.java.dao.UserDao;
import com.java.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_REGISTRATION_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			
			BeanUtils.populate(user, request.getParameterMap());

			UserDao dao = new UserDao();
			dao.insert(user);

			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.setAttribute("user", user);
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_REGISTRATION_PAGE);
	}

}
