/*
Title: Mystery of Silver Mountain Location Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.3
Date: 29 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package data;

import java.io.Serializable;
import java.util.Arrays;

public class Location implements Serializable {
	
	private static final long serialVersionUID = 7421397108414613755L;
	
	private static final int PREPOSITION_ONE_INDEX = 0;
	private static final int PREPOSITION_TWO_INDEX = 1;
	private static final int NAME_START_INDEX = 2;
	private static final int EXIT_START_INDEX = 4;
	
	private final String name;
	private final boolean[] exits = new boolean[4];
	private boolean visited = false;
	private boolean viewed = false;
	private String roomType;
	
    /**
     * Constructs a location with the specified name, prepositions, and room type.
     *
     * @param name         The name of the location (e.g., "4the furthest depth of the forest1001").
     * @param prepositions The array of prepositions used to format the name.
     * @param roomType     The type of the room (e.g., "forest", "cave").
     * @throws IllegalArgumentException If the input parameters are invalid.
     */
	public Location(String name, String[] prepositionsOne, String[] prepositionsTwo, String roomType) {		

		//Validate inputs
		if (name == null||name.length()<5) {
			throw new IllegalArgumentException("Invalid name format");
		}
				
		if (roomType == null||roomType.isEmpty()) {
			throw new IllegalArgumentException("Room type cannot be null or empty");
		}
				
		//Parse the name
		int prep_1 = Integer.parseInt(name.substring(PREPOSITION_ONE_INDEX,PREPOSITION_TWO_INDEX));
		int prep_2 = Integer.parseInt(name.substring(PREPOSITION_TWO_INDEX,NAME_START_INDEX));
		this.name = String.format("%s%s%s",prepositionsOne[prep_1],prepositionsTwo[prep_2],
										   name.substring(NAME_START_INDEX,name.length()-EXIT_START_INDEX)); 
		
		//Parse the exits
		String exitString = name.substring(name.length()-EXIT_START_INDEX);
		for (int x=0;x<4;x++) {
			exits[x] = exitString.charAt(x) == '0';
			System.out.println(exits[x]+" "+exitString.charAt(x));
		}
		
		this.roomType = roomType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean[] getExits() {
		return this.exits;
	}
	
	public void setVisited() {
		this.visited = true;
	}
	
	public boolean getVisited() {
		return this.visited;
	}
	
	public void setViewed() {
		this.viewed = true;
	}
	
	public boolean getViewed() {
		return this.viewed;
	}
	
	public String getRoomType() {
		return this.roomType;
	}
	
	@Override
	public String toString() {
	    return "Location{" +
	            "name='" + name + '\'' +
	            ", exits=" + Arrays.toString(exits) +
	            ", visited=" + visited +
	            ", viewed=" + viewed +
	            ", roomType='" + roomType + '\'' +
	            '}';
	}
}
/* 3 December 2025 - Created File
 * 6 December 2025 - Removed game specific code
 * 8 December 2025 - Increased version number
 * 10 December 2025 - Added title
 * 26 December 2025 - Built location name
 * 29 December 2025 - Added exits and updated name
 */