package com.lexis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lexis.Application;
import lexis.models.User;
import lexis.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceTests {
	@Autowired
	private UserService userService;//objeto responsavel por manipular User
	
	
	@Test
	public void getUserByLoginTest() {
		User userTest = userService.getUserByLogin("admin");
		assertEquals(userTest.getLogin(), "admin");
		assertEquals(userTest.getEmail(), "admin@admin");
		//façam 
	}

	@Test
	public void contextLoads() {
	}

}
