package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.revature.ConnectionUtil;
import com.revature.User;

public class UserDAO {
	public static UserDAO udao = new UserDAO();
	
	public ArrayList<User> getGuests() {
		ArrayList<User> guests = new ArrayList<>();
		PreparedStatement ps = null;
		User u = null;
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "Select * from Accounts where is_host = 0";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User(rs.getInt("a_id"), rs.getString("email"), rs.getString("username"), rs.getString("pass"), rs.getInt("is_host"));
				guests.add(u);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();;
		}
		return guests;
	}
	
	public void insertUser (User u) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "Insert Into Accounts (email, pass, is_host, username) Values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.isIs_host() ? 1:0);
			ps.setString(4, u.getUsername());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public User userAuth(User u) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Accounts Where username = ? and pass = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { u = new User(rs.getInt("a_id"), rs.getString("email"), rs.getString("username"), rs.getString("pass"), rs.getInt("is_host")); }
			else {u = null; }
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}
	
	public User getUserById(int id) {
		PreparedStatement ps = null;
		User u = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Accounts where a_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { u = new User(rs.getInt("a_id"), rs.getString("email"), rs.getString("username"), rs.getString("pass"), rs.getInt("is_host"));}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}
	
	public void changePasswordAtID(String newpassword, int id) {
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Update Accounts Set pass = ? where a_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, newpassword);
			ps.setInt(2, id);
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
}