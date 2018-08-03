package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Room;
import com.revature.User;
import com.revature.dao.RoomDAO;

public class RoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		ArrayList<Room> rooms = new ArrayList<>();
		if (u.isIs_host()) {
			rooms = RoomDAO.rdao.getRooms();
		} else {
			rooms = RoomDAO.rdao.getAvailableRooms();
		}
		PrintWriter pw = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		pw.println(mapper.writeValueAsString(rooms));
	}
}
