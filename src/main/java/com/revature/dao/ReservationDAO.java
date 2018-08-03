package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.revature.ConnectionUtil;
import com.revature.Reservation;

public class ReservationDAO {
	public static ReservationDAO resvDAO = new ReservationDAO();
	public ArrayList<Reservation> getReservations() {
		ArrayList<Reservation> resvs = new ArrayList<>();
		PreparedStatement ps = null;
		
		Reservation res = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Reservations";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = new Reservation(rs.getInt("resv_id"), rs.getInt("a_id"), rs.getInt("r_id"), rs.getDate("checkin"), rs.getDate("checkout"), rs.getInt("approve"));
				resvs.add(res);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return resvs;
	}
	
	public ArrayList<Reservation> getReservationsByAID(int a_id) {
		ArrayList<Reservation> resvs = new ArrayList<>();
		PreparedStatement ps = null;
		
		Reservation res = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Reservations Where a_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, a_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = new Reservation(rs.getInt("resv_id"), rs.getInt("a_id"), rs.getInt("r_id"), rs.getDate("checkin"), rs.getDate("checkout"), rs.getInt("approve"));
				resvs.add(res);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return resvs;
	}
	
	public Reservation getReservationByResvId(int resv_id) {
		PreparedStatement ps = null;
		Reservation res = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Reservations where resv_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, resv_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { res = new Reservation(rs.getInt("resv_id"), rs.getInt("a_id"), rs.getInt("r_id"), rs.getDate("checkin"), rs.getDate("checkout"), rs.getInt("approve")); }
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	public void addReservation(Reservation r) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Insert Into Reservations (a_id, r_id, checkin, checkout, approve) Values (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getA_id());
			ps.setInt(2, r.getR_id());
			ps.setDate(3, r.getCheckin());
			ps.setDate(4, r.getCheckout());
			ps.setInt(5, r.getApprove());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void updateReservation(Reservation r, int perm) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Update Reservations Set approve = ? Where resv_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, perm);
			ps.setInt(2, r.getResv_id());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
