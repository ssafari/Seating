package com.planner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.planner.domains.Guest;
import com.planner.domains.Table;
import com.planner.utils.SeatingHelper;

/**
 * Main class containing main method.
 * 
 * @author sepehr safari
 *
 */
public class SeatingPlanner {
		
	/**
	 * Main method displays small menu for reading the file name.
	 * Calls SeatingHelper class methods to start planning.
	 * 
	 * @param args  No input arguments
	 */
	public static void main(String args[]) {
		ArrayList<Guest> guestlist = new ArrayList<Guest>();
		ArrayList<Table> tablelist = new ArrayList<Table>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
		System.out.println( "Enter file name (full path): " ) ;
		String input;
		try {
			input = br.readLine();
			SeatingHelper.readFromFile(input, tablelist, guestlist);
			SeatingHelper.seating(tablelist, guestlist);
			for(Table table: tablelist){
				System.out.println(String.format("--Table (%s) with capacity of %d", 
						table.getName(), table.getCapacity()));
				for (Guest guest : table.getSeatedGuests())
					System.out.println("  "+guest.toString());
				System.out.println("------------------------");;
			}
		} catch (IOException e) {
			System.out.println("Error -- Couldn't find the file !");
		}
	}
}
