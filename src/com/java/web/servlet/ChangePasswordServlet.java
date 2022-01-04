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
import com.java.domain.ChangePasswordForm;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);
		if (username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		request.setAttribute("username", username);
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_CHANGE_PASSWORD_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = SessionUtils.getLoginedUsername(request);

			ChangePasswordForm form = new ChangePasswordForm();
			BeanUtils.populate(form, request.getParameterMap());

			request.setAttribute("username", username);

			if (!form.getConfirmPassword().equals(form.getPassword())) {
				request.setAttribute("error", "new password and new confirm password are not identical");
			} else {
				UserDao dao = new UserDao();
				dao.changePassword(form.getUsername(), form.getCurrentPassword(), form.getPassword());
				request.setAttribute("message", "Password has been changed !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardWeb(request, response, PageType.WEB_CHANGE_PASSWORD_PAGE);
	}

}
