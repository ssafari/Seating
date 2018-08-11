package com.planner.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.planner.domains.Guest;
import com.planner.domains.Table;

/**
 * Provides methods for performing seating planning.
 * 
 * @author sepehr safari
 *
 */
public class SeatingHelper {
	
	public static void seating(List<Table> tables, List<Guest> guests) {
		Collections.sort(guests);
		Collections.sort(tables);

		boolean seated;
		/*
		 *  Start seating from larger parties for available table seats.
		 *  If the table doesn't have enough available seats or there is
		 *  a party already they don't like each other then go to next table.
		 */
		for (Guest guest : guests) {
			seated = false;
			for (Table table : tables) {
				//Sit the first party at first empty table
				if (table.getSeatedGuests().isEmpty()) {
					table.addGuest(guest);
					seated = true;
					break;
				} else { //No empty table check if parties can sit together
					if (table.isDislike(guest.getName()) || 
							guest.isDislike(table.getSeatedGuests())) {
						continue; // go to next table
					} else {
						if (table.addGuest(guest) == true) {
							seated = true;
							break;  // found the table go to next guest
						}
					}
				}
			}
			if (seated == false) {
				System.out.println("Guest "+guest.getName()+" couldn't be seated at any table");
				System.exit(0);
			}
		}
	}
	

	/**
	 * Parses the text file and extracts each table's name and capacity
	 * and each guest's name and numbers. And it creates lists of tables 
	 * and guests.
	 * 
	 * @param fileName    file name to open and read
	 * @param tablelist   Empty list of tables for adding new each new table.
	 * @param guests      Empty list of quests for adding each new guest.
	 */
	public static void readFromFile(String fileName, List<Table> tablelist, List<Guest> guestlist) {
		String line = null;
		Pattern p1 = Pattern.compile("[a-zA-Z]+");
		Pattern p2 = Pattern.compile("[0-9]+");
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader =  new BufferedReader(fileReader);

			// Read each line 
			while((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty())
					continue;
				/*
				 * Extract tables information if the line has table keyword
				 */
				if (line.matches(".*\\bTables\\b.*")) {
					String[] str = line.split(":");
					String word = str[0].toLowerCase();
					if (word.compareTo("tables") == 0) {
						String[] tables = str[1].trim().split(" ");
						for (int i = 0; i < tables.length; i++) {
							String[] table = tables[i].split("-");
							tablelist.add(new Table(table[0], Integer.parseInt(table[1])));
						}
					}
					/*
					 * Otherwise check for guess names and numbers
					 */
				} else {
					Matcher m1 = p1.matcher(line);  //match all words
					Matcher m2 = p2.matcher(line);  //match number
					boolean dislike =line.matches(".*\\bdislikes\\b.*");
					m1.find();
					String name = m1.group();
					m2.find();
					int partyOf = Integer.parseInt(m2.group());
					Guest guest = new Guest(name.trim(), partyOf);
					if (dislike == true) {
						Matcher m = p1.matcher((line.split("dislikes"))[1]);
						while (m.find()) {
							guest.addDislike(m.group().trim().toLowerCase());
						}
					}
					guestlist.add(guest);
				}
			}   
			bufferedReader.close();         
		} catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
		} catch(IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");                  
		}
	}

}
