package com.revature;

public class RoomImage {
	int room;
	String image;
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "RoomImage [room=" + room + ", image=" + image + "]";
	}
	public RoomImage(int room, String image) {
		super();
		this.room = room;
		this.image = image;
	}
	
	public RoomImage() {
		super();
	}
}