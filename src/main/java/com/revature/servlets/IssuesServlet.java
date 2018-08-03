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
import com.revature.Issue;
import com.revature.User;
import com.revature.dao.IssueDAO;

public class IssuesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		ArrayList<Issue> issues = new ArrayList<>();
		if (u.isIs_host()) {
			issues = IssueDAO.idao.getIssues();
		} else {
			issues = IssueDAO.idao.getIssuesByRepId(u.getId());
		}
		PrintWriter pw = resp.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		pw.println(mapper.writeValueAsString(issues));
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		switch (req.getParameter("hidden")) {
		case "insert":
			Issue issue = new Issue(req.getParameter("message"), Integer.parseInt(req.getParameter("room")), u.getId());
			IssueDAO.idao.addIssue(issue);
			break;
		case "update":
			if (u.isIs_host()) {
				Issue i = IssueDAO.idao.getIssuesByIId(Integer.parseInt(req.getParameter("issueid")));
				System.out.println(i);
				if (i.getStatus() != 2) {
					i.setResponder_id(u.getId());
					i.setStatus(2);
					i.setResponderMessage(req.getParameter("message"));
					IssueDAO.idao.updateIssue(i);
					System.out.println(i);
				}
			}
			break;
		}
		resp.sendRedirect("Dashboard.html");
	}
}