package com.lexis;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import lexis.models.User;

public class UserTest {
	
	private User user1;
	private User user2;

	@Before
	public void setUp() throws Exception {
		user1 = new User();
		user1.setId(121313);
		user1.setEmail("admin@admin");
		user1.setLogin("admin");
		user1.setPassword("admin");
		user1.setVersion(1234);
		
		user2 = new User();
		user2.setId(414141);
		user2.setEmail("bubu@admin");
		user2.setLogin("user");
		user2.setPassword("1234");
		user2.setVersion(1234);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetVersion() {
		assertTrue(user1.getVersion().equals(1234));
	}

	@Test
	public void testSetVersion() {
		user1.setVersion(121212);
		assertTrue(user1.getVersion() == 121212);
	}

//	@Test
//	public void testGetRoot() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetId() {
		assertTrue(user1.getId() == 121313);
	}

	@Test
	public void testSetId() {
		user1.setId(123);
		assertTrue(user1.getId() == 123);
	}

	@Test
	public void testGetLogin() {
		assertTrue(user1.getLogin() == "admin");
		assertTrue(user2.getLogin() == "user");
	}

	@Test
	public void testSetLogin() {
		user1.setLogin("test");
		user2.setLogin("bubu");
		assertTrue(user1.getLogin() == "test");
		assertTrue(user2.getLogin() == "bubu");
	}

	@Test
	public void testGetPassword() {
		assertTrue(user1.getPassword() == "admin");
		assertTrue(user2.getPassword() == "1234");
	}

	@Test
	public void testSetPassword() {
		user1.setPassword("54321");
		user2.setPassword("12345");
		assertTrue(user1.getPassword() == "54321");
		assertTrue(user2.getPassword() == "12345");
	}

	@Test
	public void testGetEmail() {
		assertTrue(user1.getEmail() == "admin@admin");
		assertTrue(user2.getEmail() == "bubu@admin");
	}

	@Test
	public void testSetEmail() {
		user1.setEmail("adu@admin");
		user2.setEmail("ded@admin");
		assertTrue(user1.getEmail() == "adu@admin");
		assertTrue(user2.getEmail() == "ded@admin");
	}

//	@Test
//	public void testToString() {
//		assertTrue(user1.toString() =="id: 121313 login: admin email: admin@admin password: admin");
//	}

}
