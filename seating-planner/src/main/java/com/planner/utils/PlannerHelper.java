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
public class PlannerHelper {
	/**
	 * Starts seating guests at the tables.
	 * 
	 * @param tables
	 * @param guests
	 */
	public static void start(List<Table> tables, List<Guest> guests) {
		Collections.sort(guests);
		Collections.sort(tables);
		 
		for (Guest guest : guests) {
			boolean gotseated = false;
			for (Table table : tables) {
				if (table.getAvailable() >= guest.getPartyOf()) {
					if (table.getSeatedGuests().isEmpty()) { //first guest at the table !
						table.addGuest(guest);
						gotseated = true;
						break;
					} else {  // Table already has guest seated.
						boolean add = true;
						for (Guest seated : table.getSeatedGuests()) {
							if (!seated.getDislike().isEmpty()) {  // Check for each seated guest dislike list
								for (String dislike : seated.getDislike()) {
									if (guest.getName().toLowerCase().compareTo(dislike) == 0) {
										add = false;   //Sorry they don't like you :(
									}
								}
							}
						}
						//Seated guests don't have dislike 
						if (add == true) {	
							if (guest.getDislike().isEmpty()) {  //No dislike
								table.addGuest(guest);
								gotseated = true;
								break;
							} else {  //Maybe guest doesn't like this table check
								boolean addGuest = true;
								for (Guest seated : table.getSeatedGuests()) {
									for (String dislike : guest.getDislike()) {
										if (seated.getName().toLowerCase().compareTo(dislike) == 0) {
											addGuest = false;
										}
									}
								}
								if (addGuest == true) {
									table.addGuest(guest);
									gotseated = true;
									break;
								}
							}
						}
					}
				}
			}
			if (gotseated == false) {
				System.out.println("Exit: Guest "+guest.getName()+" couldn't be seated at any table");
				System.exit(0);
			}
		}
	}
	
	/**
	 * Parses the text file of the tables and guests based on a fixed file format.
	 * 
	 * @param fileName
	 * @param tablelist
	 * @param guests
	 */
	public static void readFromFile(String fileName, List<Table> tablelist, List<Guest> guestlist) {
        String line = null;
        Pattern p1 = Pattern.compile("[a-zA-Z]+");
        Pattern p2 = Pattern.compile("[0-9]+");
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            		if (line.isEmpty())
            			continue;
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
                } else {
                		Matcher m1 = p1.matcher(line);  //match all words
                		Matcher m2 = p2.matcher(line);  //match number
                		boolean dislike =line.matches(".*\\bdislikes\\b.*");
                		m1.find();
            			String name = m1.group();
            			m2.find();
            			int partyOf = Integer.parseInt(m2.group());
            			Guest guest = new Guest(name, partyOf);
                		if (dislike == true) {
                			Matcher m = p1.matcher((line.split("dislikes"))[1]);
                			while (m.find()) {
                				guest.addDislike(m.group().toLowerCase());
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
