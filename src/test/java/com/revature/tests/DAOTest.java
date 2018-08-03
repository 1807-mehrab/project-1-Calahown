package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.Issue;
import com.revature.Reservation;
import com.revature.Room;
import com.revature.User;
import com.revature.dao.IssueDAO;
import com.revature.dao.ReservationDAO;
import com.revature.dao.RoomDAO;
import com.revature.dao.UserDAO;

public class DAOTest {
	@Test
	public void roomDAOgetAvail() {
		ArrayList<Room> rooms = RoomDAO.rdao.getAvailableRooms();
		boolean check = true;
		for (Room room : rooms) {
			if (room.getRoomstate() != 1) {
				check = false;
			}
		}
		assertTrue(check);
	}

	@Test
	public void issuesGetById () {
		Issue issue = IssueDAO.idao.getIssuesByIId(1);
		assertEquals(1, issue.getI_id());
	}
	
	@Test
	public void resvGetByAID() {
		ArrayList<Reservation> resvs = ReservationDAO.resvDAO.getReservations();
		int resvid = resvs.get(0).getA_id();
		resvs = ReservationDAO.resvDAO.getReservationsByAID(resvid);
		boolean check = true;
		for (Reservation resv : resvs) {
			if (!(resv.getA_id() == resvid)) {
				check = false;
			}
		}
		assertTrue(check);
	}
	
	@Test
	public void accountsGuests() {
		ArrayList<User> users = UserDAO.udao.getGuests();
		boolean check = true;
		for (User user : users) {
			if (user.isIs_host()) {
				check = false;
			}
		}
		assertTrue(check);
		
	}
	
}
