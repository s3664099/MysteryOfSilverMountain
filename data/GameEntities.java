/*
Title: Mystery of Silver Mountain Game Specific Constant Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 18 December 2025
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
    
    public static final int ROOM_GRAVE = 1;
    public static final int ROOM_GRAVEYARD = 2;
    public static final int ROOM_TOMB = 3;
    public static final int ROOM_STALACTITES = 4;
    public static final int ROOM_TUNNELS = 5;
    public static final int ROOM_VAULTED_CAVERN = 6;
    public static final int ROOM_GLASS_GATES = 7;
    public static final int ROOM_ENTRANCE_HALL = 8;
    public static final int ROOM_SENTRY_POST = 9;
    public static final int ROOM_GUARD_ROOM = 10;
    public static final int ROOM_INLET = 11;
    public static final int ROOM_RUSTY_GATES = 12;
    public static final int ROOM_COTTAGE = 13;
    public static final int ROOM_POOL = 14;
    public static final int ROOM_GARDEN = 15;
    public static final int ROOM_INSCRIBED_CAVERN = 16;
    public static final int ROOM_FOUNTAIN = 17;
    public static final int ROOM_CORRIDOR = 18;
    public static final int ROOM_GALLERY = 19;
    public static final int ROOM_KITCHEN = 20;
    public static final int ROOM_KILN = 21;
    public static final int ROOM_TRACK = 22;
    public static final int ROOM_WATERWHEEL = 23;
    public static final int ROOM_SLUICE_GATES = 24;
    public static final int ROOM_BOULDER = 25;
    public static final int ROOM_PATH = 26;
    public static final int ROOM_BELL = 27;
    public static final int ROOM_DUNGEONS = 28;
    public static final int ROOM_BANQUET_HALL = 29;
    public static final int ROOM_BATTLEMENTS = 30;
    public static final int ROOM_SHORE = 31;
    public static final int ROOM_KETCH = 32;
    public static final int ROOM_COUNTRYSIDE = 33;
    public static final int ROOM_SACKS = 34;
    public static final int ROOM_POND = 35;
    public static final int ROOM_HUT = 36;
    public static final int ROOM_CASKS = 37;
    public static final int ROOM_WINE_CELLAR = 38;
    public static final int ROOM_TAPESTRIES = 39;
    public static final int ROOM_LIBRARY = 40;
    public static final int ROOM_ROUGH_WATER = 41;
    public static final int ROOM_PLOUGHED_FIELD = 42;
    public static final int ROOM_OUTSIDE_WINDMILL = 43;
    public static final int ROOM_LOWER_MILL = 44;
    public static final int ROOM_ICY_PATH = 45;
    public static final int ROOM_SCREE_SLOPE = 46;
    public static final int ROOM_SILVER_CHAMBER = 47;
    public static final int ROOM_WIZARD_LAIR = 48;
    public static final int ROOM_MOSAIC_HALL = 49;
    public static final int ROOM_THRONE_ROOM = 50;
    public static final int ROOM_MIDDLE_LAKE = 51;
    public static final int ROOM_EDGE_LAKE = 52;
    public static final int ROOM_PITTED_TRACK = 53;
    public static final int ROOM_PINNACLE = 54;
    public static final int ROOM_GLACIER = 55;
    public static final int ROOM_FALLEN_OAK = 56;
    public static final int ROOM_TURRET = 57;
    public static final int	ROOM_COBWEB = 58;
    public static final int ROOM_OGBAN_CHAMBER = 59;
    public static final int ROOM_CUPBOARD = 60;
    
	/*
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
 * 18 December 2025 - Started adding the room constants
 */