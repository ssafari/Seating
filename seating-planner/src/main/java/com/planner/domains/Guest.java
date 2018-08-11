package com.planner.domains;

import java.util.ArrayList;
import java.util.List;


/**
 * Class representing party information
 * It contains name of the party number of the party.
 * And list of disliked parties by this party.
 * It implements comparable interface for sorting.
 * 
 * @author Sepehr Safari
 *
 */
public class Guest implements Comparable<Guest> {
	
	private final String name;
	private final int partyOf;
	private List<String> dislike = null;
	
	
	public Guest(String name, int partyOf) {
		super();
		this.name = name;
		this.partyOf = partyOf;
	}
	
	public List<String> getDislike() {
		return dislike;
	}

	public void addDislike(String dislike) {
		if (this.dislike == null)
			this.dislike = new ArrayList<String>();
		this.dislike.add(dislike.toLowerCase());
	}

	public String getName() {
		return name;
	}

	public int getPartyOf() {
		return partyOf;
	}

	/**
	 * Method to check if this party dislike any
	 * other party in a list.
	 * 
	 * @param list of parties at the table
	 * @return
	 */
	public boolean isDislike(List<Guest> list) {
		if ((this.dislike != null) && (list != null)) {
		   for (Guest guest : list) {
			   if (this.dislike.contains(guest.getName().toLowerCase()))
				   return true;
		   }
		}
		return false;
	}
	/**
	 * Method to use for sorting the parties list
	 * base on their numbers.
	 * It gives priority to the party with dislike
	 * list in case of equality in numbers.
	 */
	public int compareTo(Guest ob) {
		if (ob.getPartyOf() == this.getPartyOf()) {
			if (this.dislike != null)
				return -1;
			else
				return 0;
		}
		return ob.getPartyOf() - this.getPartyOf();
	}

	@Override
	public String toString() {
		return "Guest [name=" + name + ", partyOf=" + partyOf + ", dislike=" + dislike + "]";
	}
}
