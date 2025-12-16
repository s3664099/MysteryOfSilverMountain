/*
Title: Mystery of Silver Mountain Raw Data
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 10 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package data;

public class RawData {
	
	
	private static final Integer[] LOCATION_TYPES = {};
	
    private static final String[] LOCATION_IMAGE = {};
		    
	private static final String[] LOCATIONS = {
		"11Half-dug Grave",
		"12Goblin Graveyared",
		"11Hollow Tomb",
		"23Stalactites and Stalagmites",
		"11Maze of Tunnels",
		"11Vaulted Cavern",
		"23High Glass Gates",
		"12Entrance Hall to the Palace",
		"31Grarg Sentry Post",
		"12Guard Room",
		"31Marshy In",
		"23Rusty Gates",
		"12Gamekeeper's Cottage",
		"31Misty Pool",
		"11High-Walled Garden",
		"14Inscribed Cavern",
		"34Ornate Fountain",
		"11Dank Corridor",
		"12Long Gallery",
		"12Kitchens of the Palace",
		"34Old Kiln",
		"44Overgrown Track",
		"31Disused Waterwheel",
		"33Sluice Gates"
	};
		
	private static final String[] OBJECTS = {};
		
	private static final String[] VERBS = {};
	
	private static final String[] NOUNS = {};
			
	private static final String ITEM_LOCATION = "";
	private static final String ITEM_FLAG = "";
	private static final String[] PREPOSITIONS = {};
	
	public static String getLocation(int number) {
		
		if (number<0 || number >= LOCATIONS.length) {
			throw new IllegalArgumentException("Raw Data - Invalid location number: "+number);
		}
		
		return LOCATIONS[number];
	}
	
	public static String getImage(int number) {
		
		if (number<0 || number >= LOCATION_TYPES.length) {
			throw new IllegalArgumentException("Raw Data - Invalid location type number: "+number);
		}
		
		return LOCATION_IMAGE[LOCATION_TYPES[number]-1];
	}
	
	public static String getObjects(int number) {
				
		if (number<0 || number >= OBJECTS.length+1) {
			throw new IllegalArgumentException("Raw Data - Invalid object number: "+number);
		}
		
		return OBJECTS[number-1];
	}

	public static String[] getPrepositions() {
		return PREPOSITIONS;
	}
		
	public static char getItemLocation(int number) {
		
		if (number<0 || number >= ITEM_LOCATION.length()) {
			throw new IllegalArgumentException("Raw Data - Invalid object location number: "+number);
		}
		
		return ITEM_LOCATION.charAt(number-1);
	}
	
	public static char getItemFlag(int number) {
		
		if (number<0 || number >= ITEM_FLAG.length()) {
			throw new IllegalArgumentException("Raw Data - Invalid object flag number: "+number);
		}		
		
		return ITEM_FLAG.charAt(number-1);
	}
	
	public static String[] getVerbs() {
		return VERBS;
	}
	
	public static String[] getNouns() {
		return NOUNS;
	}
}
/* 3 December 2025 - Created File
 * 6 December 2025 - Removed game specific code
 * 8 December 2025 - Increased version number
 * 10 December 2025 - Added title
 */