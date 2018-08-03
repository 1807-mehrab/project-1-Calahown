package com.revature;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RegistrationServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User u = mapper.readValue(req.getInputStream(), User.class);
		u.setIs_host(false);
		UserDAO.udao.insertUser(u);
		HttpSession session = req.getSession();
		//removepassword from set attribute
		u.setPassword("");
		session.setAttribute("user", u);
		RequestDispatcher rd = req.getRequestDispatcher("Dashboard.html");
		rd.forward(req, resp);
	}
}