package com.revature;

public class Room {
	int r_id;
	String roomtype = null;
	int a_id = -1;
	int roomstate = -1;
	String imageurl = null;
		
	public Room(int r_id, String roomtype, int a_id, int roomstate, String imageurl) {
		super();
		this.r_id = r_id;
		this.roomtype = roomtype;
		this.a_id = a_id;
		this.roomstate = roomstate;
		this.imageurl = imageurl;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public int getRoomstate() {
		return roomstate;
	}
	public void setRoomstate(int roomstate) {
		this.roomstate = roomstate;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	@Override
	public String toString() {
		return "Room [r_id=" + r_id + ", roomtype=" + roomtype + ", a_id=" + a_id + ", roomstate=" + roomstate
				+ ", imageurl=" + imageurl + "]";
	}
}