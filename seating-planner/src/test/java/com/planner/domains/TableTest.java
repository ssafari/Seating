package com.planner.domains;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;


public class TableTest {

	public static Table table;
	
	@BeforeClass
	public static void initialize() {
		table = new Table("A", 5);
		
		Guest guest = new Guest("Toms", 3);
		table.addGuest(guest);
		
		List<String> dislikedParties = new ArrayList<>();
		dislikedParties.add("Keans".toLowerCase());
		dislikedParties.add("Morgans".toLowerCase());
		
		table.setDislikedParties(dislikedParties);
	}
	
	@Test
	public void testAddGuest() {
		Guest guest = new Guest("Sams", 3);
		assertFalse(table.addGuest(guest));
	}

	@Test
	public void testGetSeatedGuests() {
		assertEquals(1, table.getSeatedGuests().size());
	}
	
	@Test
	public void testIsDislike() {
		assertTrue("Dislike check fails",table.isDislike("Keans"));
	}

	@Test
	public void testGetDislikedParties() {
		String[] expectedOutput = {"keans", "morgans"};
		Object[] actualOutput = table.getDislikedParties().toArray();
		assertArrayEquals("Not equal dislike lists", expectedOutput, actualOutput);
	}

}
