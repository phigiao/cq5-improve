package com.demo.improve.bean;


public class Contact {
	private String name;
	private String email;
	private String phone;
	private String title;
	private String message;

	public Contact() {
	}

	public Contact(String name, String email, String phone,
			String title, String message) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.title = title;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
