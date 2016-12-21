package com.demo.improve.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.improve.DBConstants;
import com.demo.improve.bean.Contact;
import com.demo.improve.dao.ContactDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@Component
@Service
@Properties({
		@Property(name = "service.vendor", value = "Digitas"),
		@Property(name = "service.description", value = "Contact Dao") })
public class ContactDaoImpl implements ContactDao {
	
	private final Logger logger = LoggerFactory.getLogger(ContactDaoImpl.class);

//	@Reference
	private Mongo mongo;
	private DB db;
	private DBCollection col;
	
	public ContactDaoImpl() {
	}
	
	public ContactDaoImpl(Mongo mongo,DB db,DBCollection col) {
		this.mongo = mongo;
		this.db = db;
		this.col = col; 
	}

	@Activate
	protected void activate(ComponentContext context) {
		try {
			mongo = new Mongo(DBConstants.NISSAN_LOCALHOST,DBConstants.NISSAN_PORT);
			db = mongo.getDB(DBConstants.NISSAN_DB);
			col = db.getCollection(DBConstants.NISSAN_COLLECTION); 
		} catch (Exception e) {
			logger.info("Could not connect database", e);
		}
	}

	@Deactivate
	protected void deactivate(ComponentContext componentContext) {
		mongo.close();
	}

	public boolean save(String name, String email, String phone, String title,
			String message) throws Exception {
		
		//insert a form data into collections
		BasicDBObject document = new BasicDBObject();
		document.put("name", name);
		document.append("email", email);
		document.append("phone", phone);
		document.append("title",title);
		document.append("message", message);			 
		col.insert(document);
			
		return true;
	}
	
	public List<Contact> listContactItems() {
		List<Contact> result = new ArrayList<Contact>();
		try {
			DBCursor cur = col.find();
			 while(cur.hasNext()) {
				 DBObject obj = cur.next();
				 
				 String email = (String)obj.get("email");
				 String message = (String)obj.get("message");
				 String name = (String)obj.get("name");
				 String phone = (String)obj.get("phone");
				 String title = (String)obj.get("title");
				 
				 Contact contact = new Contact();
				 contact.setEmail(email);
				 contact.setMessage(message);
				 contact.setName(name);
				 contact.setPhone(phone);
				 contact.setTitle(title);
				 result.add(contact);
			 }
		} catch (Exception ex) {
			logger.error("Fail when loading list : " + ex.toString());
		}
		return result;
	}
}
