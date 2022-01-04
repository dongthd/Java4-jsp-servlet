package com.java.common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class PageInfo {
	public static Map<PageType, PageInfo> pageRoute = new HashedMap();
	
	static {
		pageRoute.put(PageType.USER_MANAGEMENT_PAGE, new PageInfo("User Management", "/admin/users/users.jsp", null));
		pageRoute.put(PageType.REPORT_MANAGEMENT_PAGE, new PageInfo("Reports", "/admin/reports/reports.jsp", null));
		pageRoute.put(PageType.VIDEO_MANAGEMENT_PAGE,new PageInfo("Video Management", "/admin/videos/videos.jsp", null));
		
		pageRoute.put(PageType.WEB_HOME_PAGE,new PageInfo("Home Page", "/web/home/home.jsp", null));
		pageRoute.put(PageType.WEB_LOGIN_PAGE,new PageInfo("Login Page", "/web/users/login.jsp", null));
		pageRoute.put(PageType.WEB_REGISTRATION_PAGE,new PageInfo("Registration Page", "/web/users/registration.jsp", null));
		pageRoute.put(PageType.WEB_CHANGE_PASSWORD_PAGE,new PageInfo("Change Password", "/web/users/change-password.jsp", null));
		pageRoute.put(PageType.WEB_EDIT_PROFILE_PAGE,new PageInfo("Edit Profile", "/web/users/edit-profile.jsp", null));
		pageRoute.put(PageType.WEB_FORGOT_PASSWORD_PAGE,new PageInfo("Forgot Password", "/web/users/forgot-password.jsp", null));
		
		pageRoute.put(PageType.WEB_FAVORITE_PAGE,new PageInfo("Favorites", "/web/videos/favorite.jsp", null));
		pageRoute.put(PageType.WEB_VIDEO_DETAIL_PAGE,new PageInfo("Video Detail", "/videos/users/detail.jsp", null));
		pageRoute.put(PageType.WEB_SHARE_PAGE,new PageInfo("Share", "/web/videos/share.jsp", null));
	}
	
	public static void prepareAndForward(HttpServletRequest	request,HttpServletResponse	response,PageType pageType ) throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/layout.jsp").forward(request, response);
	}
	
	public static void prepareAndForwardWeb(HttpServletRequest	request,HttpServletResponse	response,PageType pageType ) throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/web/layout.jsp").forward(request, response);
	}	
	
	private String title;
	private String contentUrl;
	private String scriptUrl;
	
	public PageInfo(String title, String contentUrl, String scriptUrl) {
		super();
		this.title = title;
		this.contentUrl = contentUrl;
		this.scriptUrl = scriptUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getScriptUrl() {
		return scriptUrl;
	}
	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
	
	
}
