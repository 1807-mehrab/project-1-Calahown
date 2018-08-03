package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.revature.ConnectionUtil;
import com.revature.Issue;

public class IssueDAO {
	public static IssueDAO idao = new IssueDAO();
	public ArrayList<Issue> getIssues() {
		ArrayList<Issue> issues = new ArrayList<>();
		PreparedStatement ps = null;
		
		Issue issue = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Issues";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				issue = new Issue(rs.getInt("i_id"), rs.getString("reportmessage"), rs.getInt("r_id"), rs.getInt("reporter_id"), rs.getInt("responder_id"), rs.getInt("status"), rs.getString("responsemessage"));
				issues.add(issue);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return issues;
	}
	
	public ArrayList<Issue> getIssuesByRID(int r_id) {
		ArrayList<Issue> issues = new ArrayList<>();
		PreparedStatement ps = null;
		Issue issue = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Issues where r_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, r_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				issue = new Issue(rs.getInt("i_id"), rs.getString("reportmessage"), rs.getInt("r_id"), rs.getInt("reporter_id"), rs.getInt("responder_id"), rs.getInt("status"), rs.getString("respondermessage"));
				issues.add(issue);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return issues;
	}
	
	public ArrayList<Issue> getIssuesByRepId(int rep) {
		ArrayList<Issue> issues = new ArrayList<>();
		PreparedStatement ps = null;
		Issue issue = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Issues where reporter_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rep);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				issue = new Issue(rs.getInt("i_id"), rs.getString("reportmessage"), rs.getInt("r_id"), rs.getInt("reporter_id"), rs.getInt("responder_id"), rs.getInt("status"), rs.getString("respondermessage"));
				issues.add(issue);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return issues;
		
	}
	
	public Issue getIssuesByIId(int i_id) {
		Issue issue = null;
		PreparedStatement ps = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select * from Issues where i_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				issue = new Issue(rs.getInt("i_id"), rs.getString("reportmessage"), rs.getInt("r_id"), rs.getInt("reporter_id"), rs.getInt("responder_id"), rs.getInt("status"), rs.getString("responsemessage"));
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return issue;
	}
	
	public void addIssue(Issue i) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Insert Into Issues (reportmessage, r_id, reporter_id, status) Values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, i.getReportMessage());
			ps.setInt(2, i.getR_id());
			ps.setInt(3, i.getReporter_id());
			ps.setInt(4, 1);
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void updateIssue(Issue i) {
		PreparedStatement ps = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Update Issues Set responder_id = ?, status = ?, responsemessage = ? Where i_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i.getResponder_id());
			ps.setInt(2, i.getStatus());
			ps.setString(3, i.getResponderMessage());
			ps.setInt(4, i.getI_id());
			ps.executeQuery();
			conn.close();
			ps.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}