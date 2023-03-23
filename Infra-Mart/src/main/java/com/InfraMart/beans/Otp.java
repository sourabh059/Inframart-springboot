package com.InfraMart.beans;

public class Otp {

	private int newotp;
	private String email;


	public Otp(int newotp, String email) {
		super();
		this.newotp = newotp;
		this.email = email;
	}

	public int getNewotp() {
		return newotp;
	}

	public void setNewotp(int newotp) {
		this.newotp = newotp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Otp [newotp=" + newotp + ", email=" + email + "]";
	}




}
