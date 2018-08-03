package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.RoomImage;
import com.revature.User;
import com.revature.dao.RoomDAO;

public class RoomImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedReader sis = req.getReader();
		String line = "lol";
		while ((line = sis.readLine()) != null) {
			System.out.println(line);
		}
		// session.setAttribute("room", mapper.readValue(req.getInputStream(),
		// Integer.class));
		// System.out.println(session.getAttribute("room"));
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		if (u.isIs_host()) {
			ObjectMapper mapper = new ObjectMapper();
			RoomImage ri = mapper.readValue(req.getInputStream(), RoomImage.class);
			System.out.println(ri.getImage());
			RoomDAO.rdao.uploadImageToRID(ri.getImage(), ri.getRoom());
			// RoomDAO.rdao.uploadImageToRID(req.getParameter("imagefile").getBytes(),
			// Integer.parseInt(req.getParameter("room")));
		}
	}
}
