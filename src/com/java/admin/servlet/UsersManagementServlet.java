package com.java.admin.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class UsersManagementServlet
 */
@WebServlet({ "/Admin/UsersManagement", "/Admin/UsersManagement/create", "/Admin/UsersManagement/update",
		"/Admin/UsersManagement/delete", "/Admin/UsersManagement/resert", "/Admin/UsersManagement/edit", })
public class UsersManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("edit")) {
			edit(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("resert")) {
			resert(request, response);
			return;
		}

		FindAll(request, response);

		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

	private void FindAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserDao dao = new UserDao();

			List<User> list = dao.findAll();
			request.setAttribute("users", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("username");
		if (id == null) {
			request.setAttribute("error", "Gap loi khi thuc hien");
			PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
			return;
		}
		try {
			UserDao dao = new UserDao();

			User user = dao.findById(id);

			request.setAttribute("user", user);
			FindAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();

		if (url.contains("create")) {
			create(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("update")) {
			update(request, response);
			return;
		}
		if (url.contains("resert")) {
			resert(request, response);
			return;
		}
	}

	private void resert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());

			UserDao dao = new UserDao();

			dao.update(user);

			request.setAttribute("User", user);
			request.setAttribute("message", "User is updated !");
			FindAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("username");

		if (id == null) {
			request.setAttribute("error", "userID is Required");
			PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
			return;
		}
		try {
			UserDao dao = new UserDao();
			User User = dao.findById(id);
			if (User == null) {
				request.setAttribute("error", "UserID khong tim thay");
				PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
				return;
			}

			dao.delete(id);
			request.setAttribute("message", "Delete is usered !");
			request.setAttribute("user", new User());

			FindAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("error", e.getMessage());
		}

		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());

			UserDao dao = new UserDao();
			dao.insert(user);

			request.setAttribute("user", user);
			request.setAttribute("message", "User is inserted !");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error:	" + e.getMessage());
		}
		FindAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

}
