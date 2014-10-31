package tests;

import static org.junit.Assert.*;
import models.DatabaseSingleton;

import org.junit.Test;

public class TestDatabase {

	@Test
	public void testConnection() {
		
		DatabaseSingleton db = new DatabaseSingleton();
		
		assertEquals(db, db);
	}

}
