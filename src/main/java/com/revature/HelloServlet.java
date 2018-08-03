package com.revature;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String hello = "Hello, world";
		PrintWriter pw = resp.getWriter();
		pw.println(hello);
		ArrayList<Room> rooms = RoomDAO.rdao.getRooms();
		for (Room room : rooms) {
			pw.println(room.toString());
		}
		pw.close();
	}
}
