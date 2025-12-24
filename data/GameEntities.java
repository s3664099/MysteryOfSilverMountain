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
    public static final int ROOM_PASSAGE = 61;
    public static final int ROOM_CAVE = 62;
    public static final int ROOM_WOODMAN_HUT = 63;
    public static final int ROOM_VALLEY = 64;
    public static final int ROOM_VALLEY_BOTTOM = 65;
    public static final int ROOM_DARK_WOOD = 66;
    public static final int ROOM_SHADY_HOLLOW = 67;
    public static final int ROOM_STONE_CIRCLE = 68;
    public static final int ROOM_STABLE = 69;
    public static final int ROOM_ATTIC = 70;
    public static final int ROOM_WELL_BOTTOM = 71;
    public static final int ROOM_WELL = 72;
    public static final int ROOM_CAMPFIRE = 73;
    public static final int ROOM_ORCHARD = 74;
    public static final int ROOM_BRIDGE_WEST = 75;
    public static final int ROOM_BRIDGE_EAST = 76;
    public static final int ROOM_CROSSROADS = 77;
    public static final int ROOM_WINDING_ROAD = 78;
    public static final int ROOM_VILLAGE = 79;
    public static final int ROOM_WHITE_COTTAGE = 80;
    
	// === Items ===
    public static final int ITEM_COINS = 1;
    public static final int ITEM_SHEET = 2;
    
    
    /*
		"boots",								// 3	- 3
		"horseshoe",							// 4	- 1
		"apples",								// 5	- 2
		"bucket",								// 6	- 1
		"axe",									// 7	- 4
		"boat",									// 8	- 1
		"phial",								// 9	- 1
		"reeds",								// 10	- 3
		"bone",									// 11	- 1
		"shield",								// 12	- 1
		"planks",								// 13	- 3
		"rope",									// 14	- 1
		"rings",								// 15	- 1
		"jug",									// 16	- 1
		"net",									// 17	- 1
		"sword",								// 18	- 1
		"silver plate",							// 19	- 1
		"uniform",								// 20	- 1
		"key",									// 21	- 1
		"seeds",								// 22	- 3
		"lamp",									// 23	- 1
		"bread",								// 24	- 3
		"brooch",								// 25	- 1
		"matches",								// 26	- 3
		"stone of destiny",						// 27	- 2
		"apple",								// 28	- 4
		"cupboard",								// 29
		"bridge",								// 30
		"trees",								// 31
		"sail",									// 32
		"kiln",									// 34
		"ketch",								// 35
		"bricks",								// 36
		"windmill",								// 37
		"sacks",								// 38
		"Ogban's boar",							// 39
		"wheel",								// 40
		"pony",									// 41
		"gravestones",							// 42
		"pool",									// 43
		"gates",								// 44
		"handle",			"coins",								// 1	- 3
		"sheet",								// 2	- 1
		"boots",								// 3	- 3
		"horseshoe",							// 4	- 1
		"apples",								// 5	- 2
		"bucket",								// 6	- 1
		"axe",									// 7	- 4
		"boat",									// 8	- 1
		"phial",								// 9	- 1
		"reeds",								// 10	- 3
		"bone",									// 11	- 1
		"shield",								// 12	- 1
		"planks",								// 13	- 3
		"rope",									// 14	- 1
		"rings",								// 15	- 1
		"jug",									// 16	- 1
		"net",									// 17	- 1
		"sword",								// 18	- 1
		"silver plate",							// 19	- 1
		"uniform",								// 20	- 1
		"key",									// 21	- 1
		"seeds",								// 22	- 3
		"lamp",									// 23	- 1
		"bread",								// 24	- 3
		"brooch",								// 25	- 1
		"matches",								// 26	- 3
		"stone of destiny",						// 27	- 2
		"apple",								// 28	- 4
		"cupboard",								// 29
		"bridge",								// 30
		"trees",								// 31
		"sail",									// 32
		"kiln",									// 34
		"ketch",								// 35
		"bricks",								// 36
		"windmill",								// 37
		"sacks",								// 38
		"Ogban's boar",							// 39
		"wheel",								// 40
		"pony",									// 41
		"gravestones",							// 42
		"pool",									// 43
		"gates",								// 44
		"handle",								// 45
		"hut",									// 46
		"vine",									// 47
		"inscriptions",							// 48
		"troll",								// 49
		"rubble",								// 50
		"hound",								// 51
		"fountain",								// 52
		"circle",								// 53
		"mosaics",								// 54
		"books",								// 55
		"casks",								// 56
		"well",									// 57
		"walls",								// 58
		"rats",									// 59
		"safe",									// 60
		"cobwebs",								// 61
		"coin",									// 62
		"bell",									// 63
		"up silver plate",						// 64
		"stones",								// 65
		"kitchens",								// 66
		"goblet",								// 67
		"wine",									// 68
		"grargs",								// 69
		"door",									// 70
		"awake",								// 71
		"guide",								// 72
		"protect",								// 73
		"lead",									// 74
		"help",									// 75
		"chest",								// 76
		"water",								// 77
		"stables",								// 78
		"sluice gates",							// 79
		"pot",									// 80
		"statue",								// 81
		"pinnacle",								// 82
		"music",								// 83
		"magic words",							// 84
		"misty pool",							// 85
		"well bottom",							// 86
		"old kiln",								// 87
		"mountain hut"							// 88							// 45
		"hut",									// 46
		"vine",									// 47
		"inscriptions",							// 48
		"troll",								// 49
		"rubble",								// 50
		"hound",								// 51
		"fountain",								// 52
		"circle",								// 53
		"mosaics",								// 54
		"books",								// 55
		"casks",								// 56
		"well",									// 57
		"walls",								// 58
		"rats",									// 59
		"safe",									// 60
		"cobwebs",								// 61
		"coin",									// 62
		"bell",									// 63
		"up silver plate",						// 64
		"stones",								// 65
		"kitchens",								// 66
		"goblet",								// 67
		"wine",									// 68
		"grargs",								// 69
		"door",									// 70
		"awake",								// 71
		"guide",								// 72
		"protect",								// 73
		"lead",									// 74
		"help",									// 75
		"chest",								// 76
		"water",								// 77
		"stables",								// 78
		"sluice gates",							// 79
		"pot",									// 80
		"statue",								// 81
		"pinnacle",								// 82
		"music",								// 83
		"magic words",							// 84
		"misty pool",							// 85
		"well bottom",							// 86
		"old kiln",								// 87
		"mountain hut"							// 88
     */
    
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
 * 18 December 2025 - Added the room constants
 */