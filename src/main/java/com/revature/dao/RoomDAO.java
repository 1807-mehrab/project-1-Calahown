package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.revature.ConnectionUtil;
import com.revature.Room;

public class RoomDAO {
	public static RoomDAO rdao = new RoomDAO();
	
	public ArrayList<Room> getRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		PreparedStatement ps = null;
		Room room = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * From Rooms";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				try {
					room = new Room(rs.getInt("r_id"), rs.getString("roomtype"), rs.getInt("a_id"), rs.getInt("roomstate"), rs.getString("image"));
					rooms.add(room);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rooms;
	}
	
	public void changeRoomStateAtRID(int r_id, int state) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Update Rooms Set roomstate = ? Where r_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, r_id);
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void changeRoomAIDatRID(int r_id, int a_id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Update Rooms Set a_id = ? Where r_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, a_id);
			ps.setInt(2, r_id);
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Room> getAvailableRooms() {
		PreparedStatement ps = null;
		ArrayList<Room> rooms = new ArrayList<>();
		Room room = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Rooms Where roomstate = 1";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				room = new Room(rs.getInt("r_id"),rs.getString("roomtype"), rs.getInt("a_id"), rs.getInt("roomstate"), rs.getString("image"));
				rooms.add(room);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rooms;
	}
	
	public void uploadImageToRID(String b, int r_id) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Update Rooms Set image = ? where r_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, b);
			ps.setInt(2, r_id);
			ps.executeQuery();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}	