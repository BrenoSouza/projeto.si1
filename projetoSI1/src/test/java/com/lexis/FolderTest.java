package com.lexis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lexis.models.Folder;
import lexis.models.Permission;

public class FolderTest {

	private Folder folder;

	@Before
	public void criaFolder() {
		folder = new Folder("home", Permission.PRIVATE);
	}

	@Test
	public void testAddFolder() {
		folder.addFolder("pasta1", Permission.PRIVATE);
		assertEquals(folder.getFolder("pasta1").getName(), "pasta1");

	}

	@Test
	public void testRemoveFolder() {
		folder.addFolder("pasta1", Permission.PRIVATE);
		assertEquals(folder.getFolder("pasta1").getName(), "pasta1");

		folder.removeFolder("pasta1");
		assertFalse(folder.getFolder("pasta1").getName(), false);
	}

	@Test
	public void testGetFolder() {
		folder.addFolder("pasta1", Permission.PRIVATE);
		assertEquals(folder.getFolder("pasta1").getName(), "pasta1");
	}

	@Test
	public void testGetDateCreation() {
		Folder folder2 = new Folder("home2", Permission.PRIVATE);
		assertEquals(folder.getDateEdition(), folder2.getDateEdition());
	}

	@Test
	public void testGetDateEdition() {
		Folder folder2 = new Folder("home2", Permission.PRIVATE);
		assertEquals(folder.getDateCreation(), folder2.getDateCreation());
	}

	@Test
	public void testGetName() {
		folder.addFolder("pasta2", Permission.PRIVATE);
		assertEquals(folder.getFolder("pasta2").getName(), "pasta2");
		assertFalse(folder.getFolder("pasta2").getName() == "aiaiaiai");
	}

	@Test
	public void testSetName() {
		folder.addFolder("pasta1", Permission.PRIVATE);
		assertEquals(folder.getFolder("pasta1").getName(), "pasta1");

		folder.setName("home2");
		assertEquals(folder.getName(), "home2");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
