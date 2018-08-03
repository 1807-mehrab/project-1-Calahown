package com.revature;

public class Issue {
	int i_id;
	String reportMessage;
	int r_id;
	int reporter_id; 
	int responder_id;
	int status;
	String responderMessage;
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public String getReportMessage() {
		return reportMessage;
	}
	public void setReportMessage(String reportMessage) {
		this.reportMessage = reportMessage;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public int getReporter_id() {
		return reporter_id;
	}
	public void setReporter_id(int reporter_id) {
		this.reporter_id = reporter_id;
	}
	public int getResponder_id() {
		return responder_id;
	}
	public void setResponder_id(int responder_id) {
		this.responder_id = responder_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResponderMessage() {
		return responderMessage;
	}
	public void setResponderMessage(String responderMessage) {
		this.responderMessage = responderMessage;
	}
	@Override
	public String toString() {
		return "Issue [i_id=" + i_id + ", reportMessage=" + reportMessage + ", r_id=" + r_id + ", reporter_id="
				+ reporter_id + ", responder_id=" + responder_id + ", status=" + status + ", responderMessage="
				+ responderMessage + "]";
	}
	public Issue(int i_id, String reportMessage, int r_id, int reporter_id, int responder_id, int status,
			String responderMessage) {
		super();
		this.i_id = i_id;
		this.reportMessage = reportMessage;
		this.r_id = r_id;
		this.reporter_id = reporter_id;
		this.responder_id = responder_id;
		this.status = status;
		this.responderMessage = responderMessage;
	}
	public Issue(String reportMessage, int r_id, int reporter_id) {
		super();
		this.reportMessage = reportMessage;
		this.r_id = r_id;
		this.reporter_id = reporter_id;
	}
}
