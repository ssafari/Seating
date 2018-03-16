# Seating
------------

Main class calls static helper functions from class util: PlannerHelper: 

-- readFromFile() to read a file and parse it to construct data structures of Guests and Tables.
-- start():  This function implement the main logic for seating the guests at the tables.

Algorithm:
-----------
Save guests in an arraylist and sort the list based on each guest party numbers in descending format.
Save tables in an arraylist and sort the list based on the number of seats in ascending format.

Start seating first higher guest from start of the list at available first free table. 
Continue next higher guest and seat them in first availabe free seats.

We can seat guest with higher number first and start to add more to the tables available seats by checking the dislike relation.
If during adding guest any guest can find a table or we run out of seat the program terminates by displaying a message.

For testing:
------------
From the source code:
Compile project: mvn clean install
Run program:     java -jar target/seating-planner-0.0.1-SNAPSHOT.jar

Program will ask for the filename full path for testing you can enter: src/main/resources/seatsmap.txt
Or any other file with correct path.

Testing:
--------
No need to create JUnit test. 
