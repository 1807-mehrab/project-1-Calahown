package com.revature.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

import com.revature.User;
import com.revature.dao.UserDAO;

public class HostRegistration extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost (HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		User u = new User();
		u.setEmail(req.getParameter("email"));
		Random rand = new Random();
		u.setPassword(Integer.toString(rand.nextInt(1000000)));
		u.setUsername(req.getParameter("username"));
		u.setIs_host(false);
		UserDAO.udao.insertUser(u);
		sendMail(u);
	}
	
	private void sendMail(User u) {
		try {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("C:/Users/David/Documents/workspace-sts-3.9.5.RELEASE/Hotel/mail.properties");
		prop.load(in);
		String host = prop.getProperty("host");
		String port = prop.getProperty("port");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		Properties props = System.getProperties();
		props.setProperty("mail.stmp.host", host);
		props.put("mail.stmp.port", port);
		props.put("mail.stmp.auth", true);
		props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");  
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}});
		
		MimeMessage message = new MimeMessage(session); 
	     
	    // header field of the header.
	    message.setFrom(new InternetAddress("notifications@roast.gg"));
	     
	    message.addRecipient(Message.RecipientType.TO, 
	                          new InternetAddress(u.getEmail()));
	    message.setSubject("subject");
	    message.setText("Hello, as is sending email ");
	 
	    // Send message
	    Transport.send(message);
	    System.out.println("Yo it has been sent..");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}