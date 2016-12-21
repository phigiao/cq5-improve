package com.demo.improve.service;

import java.util.List;

import com.demo.improve.bean.Contact;


public interface ContactService {
	public boolean save(String name, String email, String phone, String title,
			String message) throws Exception;
	
	public List<Contact> listContactItems() ;
}
