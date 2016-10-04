package com.lexis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lexis.Application;
import lexis.models.User;
import lexis.repositories.UserRepository;
import lexis.services.UserServiceDAO;
import lexis.services.impl.UserServiceDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest {
	
/*	@Autowired
	private UserService userService; //objeto responsavel por manipular User
	private UserRepository userRepository;
	
		
	@Before
	public void criaUserERepositorio() {
		userService = new UserServiceImpl(); 
		
	}
		
	
	

	@Test
	public void testListAllUsers() {
		
		User userTemp = new User();
		userTemp.setId(10);
		userService.saveUser(userTemp);
		
		assertTrue(userService.listAllUsers() != null);
		userService.deleteUser(10);
		assertTrue(userService.listAllUsers() == null);
	}

	@Test
	public void testGetUserById() {
		User userTest = userService.getUserByLogin("admin");
		assertEquals(userTest.getLogin(), "admin");
		assertEquals(userTest.getEmail(), "admin@admin");
	}

	@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {

	}

	@Test
	public void testGetUserByLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserByEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testExistsByLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testExistsByEmail() {
		fail("Not yet implemented");
	}*/

}
