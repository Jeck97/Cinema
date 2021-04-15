package model;

import java.util.Vector;

public class BookingDetail {

	private String mvName;
	private double price;
	private String date;
	private String time;
	private Vector<String> seats;

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setMvName(String mvName) {
		this.mvName = mvName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setSeats(Vector<String> seats) {
		this.seats = seats;
	}

	public String getMvName() {
		return mvName;
	}

	public double getPrice() {
		return price;
	}
	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public Vector<String> getSeats() {
		return seats;
	}

}
