package com.demo.improve.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.demo.improve.bean.Contact;
import com.demo.improve.dao.ContactDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class ContactTest {
	
	private static ContactDao contactDao;
	private static Mongo mongo;
	private static DB db;
	private static DBCollection col;
	
	@BeforeClass
	public static void setupBefore() {
		try {
			mongo = new Mongo("localhost",27017);
			db = mongo.getDB("test");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
		
	@Before
	public void setup() {
		try {			
			col = db.getCollection("myCollection");
			DBCursor cursor = col.find();
			while (cursor.hasNext()) {
				col.remove(cursor.next());
			}
			contactDao = new ContactDaoImpl(mongo,db,col);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	@Ignore
	public void persistAndCountContact() throws Exception {
		String name = "Test1";
		String email = "test1@gmail.com";
		String phone = "test1";
		String title = "Hello1";
		String message = "Hello1 test, how are you?";
		boolean result = contactDao.save(name, email, phone, title, message);
		
		name = "Test2";
		email = "test2@gmail.com";
		phone = "test2";
		title = "Hello2";
		message = "Hello2 test, how are you?";
		result = contactDao.save(name, email, phone, title, message);
		
		assertTrue(col.count() == 2);
	}
	
	@Test
	public void testFindObject() throws Exception {
		String name = "Test1";
		String email = "test1@gmail.com";
		String phone = "test1";
		String title = "Hello1";
		String message = "Hello1 test, how are you?";
		boolean result = contactDao.save(name, email, phone, title, message);
		
		BasicDBObject query = new BasicDBObject();
		query.put("name", "Test1");
		query.put("email", "test1@gmail.com");
		query.put("phone", "test1");
		query.put("title", "Hello1");
		query.put("message", "Hello1 test, how are you?");		
		DBObject found = col.findOne(query);
		
		assertEquals(found.get("name").toString(),name);
		assertEquals(found.get("email").toString(),email);
		assertEquals(found.get("phone").toString(),phone);
		assertEquals(found.get("title").toString(),title);
		assertEquals(found.get("message").toString(),message);
	}

	@Test
	@Ignore
	public void saveContact() throws Exception {
		System.out.println("start saveContact");
		String name = "Test";
		String email = "test@gmail.com";
		String phone = "test";
		String title = "Hello";
		String message = "Hello test, how are you?";
		boolean result = contactDao.save(name, email, phone, title, message);
		System.out.println("end saveContact : " + result);
	}
	
	@Test
	@Ignore
	public void listContactItems(){
		System.out.println("start listContactItems");
		List<Contact> contacts = contactDao.listContactItems();
		for (Contact contact : contacts) {
			System.out.println("Name : " + contact.getName() + "," + 
								"Email : " + contact.getEmail() + "," + 
								"Phone : " + contact.getPhone()  + "," + 
								"Title : " + contact.getTitle() + "," +
								"Message : " + contact.getMessage());
		}
		System.out.println("end listContactItems : " + contacts);
	}
	
	@After
	public void tearDown(){
		contactDao = null;		
	}
	
	
	@AfterClass
	public static void testCleanUp(){
		mongo.close();
	}	
}
