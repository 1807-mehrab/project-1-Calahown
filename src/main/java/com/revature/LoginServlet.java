package com.revature;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ObjectMapper mapper = new ObjectMapper();
		//User u = mapper.readValue(req.getInputStream(), User.class);
		User u = new User();
		u.setUsername(req.getParameter("username"));
		u.setPassword(req.getParameter("password"));
		u = UserDAO.udao.userAuth(u);
		if (u != null) {
			HttpSession session = req.getSession();
			//removepassword from setattribute
			u.setPassword("");
			session.setAttribute("user", u);
			resp.sendRedirect("Dashboard.html");
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("Dashboard");
		rd.forward(req,resp);
	}
}
