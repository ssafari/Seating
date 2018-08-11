package com.planner.domains;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class GuestTest {
	

	@Test
	public void testIsDislike() {
		Guest guest1 = new Guest("Jones", 5);
		guest1.addDislike("Daltons");
		guest1.addDislike("Davids");
		
		Guest guest2 = new Guest("Owens", 5);
		
		List<Guest> list = new ArrayList<Guest>();
		list.add(guest2);
		
		assertFalse(guest1.isDislike(list));
		
	}

}
