package com.revature;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		User upuser = UserDAO.udao.getUserById(u.getId());
		if (req.getParameter("oldpassword").equals(upuser.getPassword()) && upuser.getUsername().equals(u.getUsername())) {
			UserDAO.udao.changePasswordAtID(req.getParameter("newpassword"), upuser.getId());
		}
		resp.sendRedirect("Dashboard.html");
	}
}