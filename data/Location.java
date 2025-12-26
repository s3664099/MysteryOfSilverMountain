/*
Title: Mystery of Silver Mountain Location Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 26 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package data;

import java.io.Serializable;
import java.util.Arrays;

public class Location implements Serializable {
	
	private static final long serialVersionUID = 7421397108414613755L;
	
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
				
		int preposition_1 = Integer.parseInt(name.substring(0, 1));
		int preposition_2 = Integer.parseInt(name.substring(1,2));
				
		//Parse the name
		this.name = prepositionsOne[preposition_1]+prepositionsTwo[preposition_2]+
					name.substring(2);
		
		System.out.println(this.name);
		
		//Parse the exits
		
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
 */