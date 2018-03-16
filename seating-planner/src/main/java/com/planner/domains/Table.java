package com.planner.domains;

import java.util.ArrayList;
import java.util.List;


public class Table implements Comparable<Table>{

	private final String name;
	private final int capacity;
	private int available;
	private List<Guest> seatedGuests;
	
	public Table(String name, int capacity) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.seatedGuests = new ArrayList<Guest>();
		this.available = capacity;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public List<Guest> getSeatedGuests() {
		return seatedGuests;
	}

	public void addGuest(Guest guest) {
		this.seatedGuests.add(guest);
		this.available -= guest.getPartyOf();
	}

	public void removeGuest(Guest guest) {
		this.seatedGuests.remove(guest);
		this.available += guest.getPartyOf();
	}
	
	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public int compareTo(Table ob) {
		return this.getCapacity()-ob.getCapacity();
	}
}
