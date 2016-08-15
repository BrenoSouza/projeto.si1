package com.lexis;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import lexis.models.File;
import lexis.models.Folder;

public class FileTest {
	
	File file1;
	File file2;
	Folder parent;
	@Before
	public void setUp() throws Exception {
		file1 = new File("notas");
		file2  = new File("work");
		
	}

	@Test
	public void testFile() {
		assertTrue(file1 != null);
		assertTrue(file2 != null);
	}

	@Test
	public void testGetData() {
		equals(file1.getData() == file2.getData());
	}

//	@Test
//	public void testSetData() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetName() {
		assertTrue("notas" == file1.getName());
		assertTrue("work" == file2.getName());
	}

	@Test
	public void testSetName() {
		file1.setName("doc");
		file2.setName("misc");
		assertTrue(file1.getName() == "doc");
		assertTrue(file2.getName() == "misc");
	}

//	@Test
//	public void testGetDateCreation() {
//		assertTrue(file1.getDateCreation() == file2.getDateCreation());
//	}

//	@Test
//	public void testGetDateEdition() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetDateEdition() {
//		fail("Not yet implemented");
//	}

}
