/*
Title: Mystery of Silver Mountain Game Specific Constant Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 10 December 2025
Source: 

This class is designed to hold the constants. They have been made public since they
do not change, and it makes them easily accessible
*/

package data;

public class GameEntities {

    // Prevent instantiation (private constructor)
    private GameEntities() {
        throw new UnsupportedOperationException("Constants - Utility class");
    }

	// === Rooms ===
	/*
	 * 		"11Half-dug Grave",
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
		"33Sluice Gates",
		"11Gap between some Boulder",
		"41Perilous Path",
		"31Silver Bell in the Rock",
		"12Dungeons of the Palace",
		"11Banqueting Hall",
		"42Palace Battlements",
		"44Island Shore",
		"31Beached Ketch",
		"13Barren Countryside",
		"33Sacks on the Upper Floor",
		"46Frozen Pond",
		"21Mountain Hut",
		"31Row of Casks",
		"11Wine Cellar",
		"12Hall of Tapestries",
		"11Dusty Library",
		"13Rough Water",
		"11Ploughed Field",
		"55Outside a Windmill",
		"42Lower Floor of the Mill",
		"44Icy Path",
		"41Scree Slope",
		"12Silver Chamber",
		"12Wizard's Lair",
		"11Mosaic Floor Hall",
		"12Silver Throne Room",
		"12Middle of the Lake",
		"42Edge of an Icy Lake",
		"41Pitted Track",
		"31High Pinnacle",
		"55Above a Glacier",
		"21Huge Fallen Oak",
		"11Turret Room with a Slot Machine",
		"11Cobwebby Room",
		"31Safe in Ogban's Chamber",
		"31Cupboard in the Corner",
		"11Narrow Passage",
		"16Cave",
		"11Woodman's Hut",
		"42Side of a  Wooded Valley",
		"21Stream in a Valley Bottom",
		"11Deep Dark Wood",
		"11Shady Hollow",
		"34Ancient Stone Circle",
		"16Stable",
		"14Attic Bedroom",
		"11Damp Well Bottom",
		"32Top of a Deep Well",
		"31Burnt-Out Campfire",
		"16Orchard",
		"62End of a Bridge",
		"62End of a Bridge",
		"61Crossroads",
		"41Winding Road",
		"11Village of Rustic Houses",
		"11White Cottage"
	 */
	// === Items ===

	// === Commands ===
	public static final int MOVE_BOTTOM = 0;
	public static final int MOVE_NOT_DIRECTION = 0;
	public static final int MOVE_TOP = 6;

	public static final int CMD_NORTH = 1;
	public static final int CMD_SOUTH = 2;
	public static final int CMD_EAST = 3;
	public static final int CMD_WEST =4;
	public static final int CMD_GO = 5;
	public static final int CMD_DROP = 9;
	public static final int CMD_TAKE = 6;
	public static final int CMD_LOAD = 40;
	public static final int CMD_SAVE = 41;
	public static final int CMD_QUIT = 42;
	public static final int CMD_RESTART = 43;
	
	public static final String SPACE = " ";
    
	// === Codes ===
	
	// === Directions ===
	public static final int NORTH = 1;
	public static final int SOUTH = 2;
	public static final int EAST = 3;
	public static final int WEST = 4;

}

/* 3 December 2025 - Created File
 * 6 December 2025 - Cleared contents
 * 8 December 2025 - Added back required codes
 * 				   - Increased version number
 * 10 December 2025 - Added title
 */