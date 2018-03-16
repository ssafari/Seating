package com.planner.domains;

import java.util.ArrayList;
import java.util.List;

public class Guest implements Comparable<Guest> {
	
	private final String name;
	private final int partyOf;
	private List<String> dislike;
	private String assigned;
	
	public Guest(String name, int partyOf) {
		super();
		this.name = name;
		this.partyOf = partyOf;
		this.dislike = new ArrayList<String>();
	}
	
	

	public List<String> getDislike() {
		return dislike;
	}

	public void addDislike(String dislike) {
		this.dislike.add(dislike.toLowerCase());
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public String getName() {
		return name;
	}

	public int getPartyOf() {
		return partyOf;
	}

	@Override
	public String toString() {
		return "Guest [name=" + name + ", partyOf=" + partyOf + " ]";
	}

	public int compareTo(Guest ob) {
		return ob.getPartyOf()-this.getPartyOf();
	}
}
