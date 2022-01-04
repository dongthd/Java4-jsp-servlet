package com.java.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.java.common.CookieUtils;
import com.java.common.PageInfo;
import com.java.common.PageType;
import com.java.common.SessionUtils;
import com.java.dao.UserDao;
import com.java.domain.LoginForm;
import com.java.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = CookieUtils.get("username", request);
		if (username == null) {
			PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_LOGIN_PAGE);
			return;
		}
		SessionUtils.add(request, "username", username);
		request.getRequestDispatcher("/HomePage").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			LoginForm form = new LoginForm();
			BeanUtils.populate(form, request.getParameterMap());

			UserDao dao = new UserDao();
			User user = dao.findById(form.getUsername());

			if (user != null && user.getPassword().equals(form.getPassword())) {
				SessionUtils.add(request, "username", user.getUsername());

				if (form.isRemember()) {
					CookieUtils.add("username", form.getUsername(), 24, response);
				} else {
					CookieUtils.add("username", form.getUsername(), 0, response);
				}
				request.setAttribute("isLogin", true);
				request.setAttribute("name", user.getFullname());
				if(user.getAdmin()) {
					PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
					return;
				}
				request.getRequestDispatcher("/HomePage").forward(request, response);
				return;
			}
			request.setAttribute("error", "invalid user or password");
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_LOGIN_PAGE);
	}

}
