package com.lexis;

import org.junit.*;

import lexis.models.User;

public class UserTest {
	
	private User user1;
	private User user2;

	@Before
	public void setUp() throws Exception {
		user1 = new User("admin", "admin", "admin@admin");
		user1.setId(121313);
		user1.setVersion(1234);
		
		user2 = new User("user", "1234", "bubu@admin");
		user2.setId(414141);
		user2.setVersion(1234);
	}

//	@Test
//	public void testGetRoot() {
//		fail("Not yet implemented");
//	}


	
	@Test
	public void testConstructor() throws Exception {
		user1 = new User("rafael", "Klynger", "rafael.klynger@gmail.com");
		
		Assert.assertEquals("rafael", user1.getLogin());
		Assert.assertEquals("Klynger", user1.getPassword());
		Assert.assertEquals("rafael.klynger@gmail.com", user1.getEmail());
	}
	
	@Test
	public void testSetPassword() throws Exception {
		user1.setPassword("87654321");
		user2.setPassword("12345678");
		
		Assert.assertEquals("87654321", user1.getPassword());
		Assert.assertEquals("12345678", user2.getPassword());
		
		
		try {
			user1.setPassword(null);
			Assert.fail("Should get a NullPointerException");
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			Assert.fail("Shouldn't be here");
		}
		
	}

	@Test
	public void testSetEmail() throws Exception {
		
		Assert.assertEquals(user1.getEmail(), "admin@admin");
		Assert.assertEquals(user2.getEmail(), "bubu@admin");
		
		user1.setEmail("adu@admin");
		user2.setEmail("ded@admin");
		
		Assert.assertEquals("adu@admin", user1.getEmail());
		Assert.assertEquals("ded@admin", user2.getEmail());
		
		try {
			user2.setEmail(null);
			Assert.fail("Should get a NullPointerException");
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			Assert.fail("Shouldn't be here");
		}
	}

//	@Test
//	public void testToString() {
//		assertTrue(user1.toString() =="id: 121313 login: admin email: admin@admin password: admin");
//	}

}
