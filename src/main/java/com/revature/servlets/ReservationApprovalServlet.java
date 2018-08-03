package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.Reservation;
import com.revature.User;
import com.revature.dao.ReservationDAO;

public class ReservationApprovalServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		if (u.isIs_host()) {
			Reservation r = ReservationDAO.resvDAO.getReservationByResvId(Integer.parseInt(req.getParameter("reservation")));
			if (r != null) {
				ReservationDAO.resvDAO.updateReservation(r, Integer.parseInt(req.getParameter("permission")));
			}
		}
		resp.sendRedirect("Dashboard.html");
	}
}
