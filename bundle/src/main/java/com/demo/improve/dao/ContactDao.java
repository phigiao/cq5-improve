package com.demo.improve.dao;

import java.util.List;

import com.demo.improve.bean.Contact;


public interface ContactDao {
	public boolean save(String name, String email, String phone, String title,
			String message) throws Exception;
	
	public List<Contact> listContactItems();
}
