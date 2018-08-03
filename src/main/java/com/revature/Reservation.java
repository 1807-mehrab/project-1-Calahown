package com.revature;

import java.sql.Date;

public class Reservation {
	int resv_id;
	int a_id;
	int r_id;
	Date checkin;
	Date checkout;
	//0 for neutral, 1 for false, 2 for true
	int approve;
	
	public int getResv_id() {
		return resv_id;
	}
	public void setResv_id(int resv_id) {
		this.resv_id = resv_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public Date getCheckin() {
		return checkin;
	}
	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}
	public Date getCheckout() {
		return checkout;
	}
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	public int getApprove() {
		return approve;
	}
	public void setApprove(int approve) {
		this.approve = approve;
	}
	public Reservation(int resv_id, int a_id, int r_id, Date checkin, Date checkout, int approve) {
		super();
		this.resv_id = resv_id;
		this.a_id = a_id;
		this.r_id = r_id;
		this.checkin = checkin;
		this.checkout = checkout;
		this.approve = approve;
	}
	
	public Reservation() { super();}
	
	@Override
	public String toString() {
		return "Reservation [resv_id=" + resv_id + ", a_id=" + a_id + ", r_id=" + r_id + ", checkin=" + checkin
				+ ", checkout=" + checkout + ", approve=" + approve + "]";
	}
}
