package com.demo.improve.service.impl;

import java.util.List;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import com.demo.improve.bean.Contact;
import com.demo.improve.dao.ContactDao;
import com.demo.improve.service.ContactService;

@Component(label = "Contact Service", metatype = true, description = "Contact Service")
@Service
@Properties({
        @Property(name = "service.vendor", value = "Digitas"),
        @Property(name = "service.description", value = "Contact Service")
})
public class ContactServiceImpl implements ContactService{

	@Reference
	private ContactDao contactDao;
	
	public boolean save(String name, String email, String phone, String title,
			String message) throws Exception{
		return contactDao.save(name, email, phone, title, message);
	}
	
	public List<Contact> listContactItems() {
		return contactDao.listContactItems();
	}
}
