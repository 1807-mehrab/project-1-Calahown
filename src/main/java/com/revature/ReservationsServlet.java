package com.revature;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReservationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		ArrayList<Reservation> resvs = new ArrayList<>();
		if (u.isIs_host()) {
			resvs = ReservationDAO.resvDAO.getReservations();
		} else {
			resvs = ReservationDAO.resvDAO.getReservationsByAID(u.getId());
		}
		PrintWriter pw = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		pw.println(mapper.writeValueAsString(resvs));
	}
	
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reservation resv = new Reservation();
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		resv.setA_id(u.getId());
		resv.setR_id(Integer.parseInt(req.getParameter("room")));
		resv.setCheckin(Date.valueOf(req.getParameter("checkin")));
		resv.setCheckout(Date.valueOf(req.getParameter("checkout")));
		resv.setApprove(0);
		ReservationDAO.resvDAO.addReservation(resv);
		resp.sendRedirect("Dashboard.html");
	}
}