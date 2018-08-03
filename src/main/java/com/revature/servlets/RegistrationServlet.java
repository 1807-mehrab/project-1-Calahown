package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.User;
import com.revature.dao.UserDAO;

public class RegistrationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = new User();
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));
		u.setUsername(req.getParameter("username"));
		u.setIs_host(false);
		UserDAO.udao.insertUser(u);
		HttpSession session = req.getSession();
		//remove password from set attribute
		u.setPassword("");
		session.setAttribute("user", u);
		RequestDispatcher rd = req.getRequestDispatcher("Dashboard.html");
		rd.forward(req, resp);
	}
}