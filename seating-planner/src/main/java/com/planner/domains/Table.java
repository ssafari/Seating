package com.planner.domains;

import java.util.ArrayList;
import java.util.List;


/**
 * Class representing each table information.
 * Table name, Capacity and list of parties assigned to table.
 * 
 * @author sepehrsafari
 *
 */
public class Table implements Comparable<Table>{

	private final String name;
	private final int capacity;
	private int available;
	private List<Guest> seatedGuests;
	private List<String> dislikedParties;
	
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

	/**
	 * Add party to the table if there is
	 * seats available and keep list of parties
	 * disliked by new added one.
	 * 
	 * @param guest -- new party to be seated.
	 */
	public boolean addGuest(Guest guest) {
		if (this.getAvailable() >= guest.getPartyOf()) {
			this.seatedGuests.add(guest);
			this.available -= guest.getPartyOf();
			if (guest.getDislike() != null) {
				for (String name : guest.getDislike()) {
					addDislikeParty(name);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Checks if this party dislike another party.
	 * 
	 * @param name -- the name of a party.
	 * @return true if dislike, false otherwise.
	 */
	public boolean isDislike(String name) {
		if ((this.dislikedParties != null) && 
				this.dislikedParties.contains(name.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Name of the table.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<String> getDislikedParties() {
		return dislikedParties;
	}

	private void addDislikeParty(String name) {
		if (this.dislikedParties == null)
			this.dislikedParties = new ArrayList<String>();
		this.dislikedParties.add(name.toLowerCase());
	}
	public void setDislikedParties(List<String> dislikedParties) {
		this.dislikedParties = dislikedParties;
	}

	/**
	 * Use for sorting table list base on their capacity in
	 * ascending format.
	 */
	public int compareTo(Table ob) {
		return ob.getCapacity()-this.getCapacity();
	}

	@Override
	public String toString() {
		return "Table [name=" + name + ", capacity=" + capacity + ", dislike=" + dislikedParties +"]";
	}
}
