package com.planner.seats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.planner.domains.Guest;
import com.planner.domains.Table;
import com.planner.utils.PlannerHelper;

public class SeatingPlanner {
		
	public static void main(String args[]) {
		ArrayList<Guest> guestlist = new ArrayList<Guest>();
		ArrayList<Table> tablelist = new ArrayList<Table>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		System.out.print( "Your Selection : " ) ;
		try {
			String input = br.readLine();
			// input: src/main/resources/seatsmap.txt
			PlannerHelper.readFromFile(input, tablelist, guestlist);
			PlannerHelper.start(tablelist, guestlist);
			for(Table str: tablelist){
				System.out.println("Table: "+ str.getName());
				for (Guest guest : str.getSeatedGuests())
					System.out.println(guest.toString());
			}
		} catch (IOException e) {
			System.out.println( "Somehting wrong entered") ;
		}
	}
}
