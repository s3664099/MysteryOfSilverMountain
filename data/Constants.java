/*
Title: Mystery of Silver Mountain Constant Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.4
Date: 1 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

This class is designed to hold the constants. They have been made public since they
do not change, and it makes them easily accessible
*/

package data;

public class Constants {

    // Prevent instantiation (private constructor)
    private Constants() {
        throw new UnsupportedOperationException("Constants - Utility class");
    }
	
	//Game related constants
	public static final int NUMBER_OF_ROOMS = 80;
	public static final int NUMBER_OF_ITEMS = 88;
	public static final int NUMBER_OF_VERBS = 57;
	public static final int NUMBER_OF_NOUNS = 88;
	public static final int NUMBER_EXITS = 4;
	public static final int FLAG_HIDDEN = 9;
	public static final int INITIAL_SAVE_COUNT = 2;
	
	//Panel Related constants
	public static final int MESSAGE_LENGTH = 100;
	
	//Threshold for item categories in the item list
	public static final int MAX_CARRIABLE_ITEMS = 28; // Items with IDs <= 28 are carriable
	public static final int FOOD_THRESHOLD = 0; // Items with IDs >16 are food
	public static final int DRINK_THRESHOLD = 0; // Items with IDs > 21 are drinks
	public static final int LINE_LENGTH = 90;
	
	//Constants for the Game Class
	public static final int START_LOCATION = GameEntities.ROOM_CROSSROADS;
	//public static final int START_LOCATION = 28;
	
	//Constants for the player starting values
	public static final float STARTING_STRENGTH = 0;
	public static final int STARTING_WISDOM = 0;
	public static final int STARTING_TIME = 0;
	
	public static final String NORTH = "North";
	public static final String SOUTH = "South";
	public static final String EAST = "East";
	public static final String WEST = "West";
	public static final String[] DIRECTIONS = {NORTH, SOUTH, EAST,WEST};

	public static final String STAT_STRENGTH = "strength";
	public static final String STAT_WISDOM = "wisdom";
	public static final String STAT_TIME = "timeRemaining";
	
	public static final String STARTING_MESSAGE = "Good luck on your quest";
	public static final String TITLE = "The Mystery of Silver Mountain";
	
}

/* 3 December 2025 - Created File
 * 6 December 2025 - Cleared values
 * 7 December 2025 - Made direction names public
 * 8 December 2025 - Increased version number
 * 10 December 2025 - Added title
 * 17 December 2025 - Added number of rooms
 * 24 December 2025 - Added number of items
 * 31 December 2025 - Added starting message
 * 1 January 2026 - Added title as a constant
 */