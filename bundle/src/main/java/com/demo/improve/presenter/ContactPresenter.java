package com.demo.improve.presenter;

import java.util.List;

import javax.jcr.RepositoryException;

import com.demo.improve.bean.Contact;
import com.demo.improve.service.ContactService;

public class ContactPresenter extends AbstractPresenter {

	@Override
	protected void process() throws RepositoryException {
		putModel("title", properties.get("title", ""));
		putModel("description", properties.get("description", ""));
		putModel("message", properties.get("message", ""));
		putModel("name", properties.get("name", ""));
		putModel("email", properties.get("email", ""));
		putModel("phone", properties.get("phone", ""));
		putModel("labelsTitle", properties.get("labelsTitle", ""));		
		putModel("button", properties.get("button", ""));
		putModel("confirmationMessage", properties.get("confirmationMessage", ""));		
	
		ContactService contactService = sling.getService(ContactService.class);
		List<Contact> contacts = contactService.listContactItems();
		putModel("contacts", contacts);
	}
	
}
