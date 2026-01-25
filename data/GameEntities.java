/*
Title: Mystery of Silver Mountain Game Specific Constant Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.8
Date: 14 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

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
    
    public static final int ROOM_CARRYING = 0;
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
    public static final int ROOM_DESTROYED = 81;
    
	// === Items ===
    public static final int ITEM_COINS = 1;
    public static final int ITEM_SHEET = 2;
    public static final int ITEM_BOOTS = 3;
    public static final int ITEM_HORSESHOE = 4;
    public static final int ITEM_APPLES = 5;
    public static final int ITEM_BUCKET = 6;
    public static final int ITEM_AXE = 7;
    public static final int ITEM_BOAT = 8;
    public static final int ITEM_PHIAL = 9;
    public static final int ITEM_REEDS = 10;
    public static final int ITEM_BONE = 11;
    public static final int ITEM_SHIELD = 12;
    public static final int ITEM_PLANKS = 13;
    public static final int ITEM_ROPE = 14;
    public static final int ITEM_RINGS = 15;
    public static final int ITEM_JUG = 16;
    public static final int ITEM_NET = 17;
    public static final int ITEM_SWORD = 18;
    public static final int ITEM_PLATE = 19;
    public static final int ITEM_UNIFORM = 20;
    public static final int ITEM_KEY = 21;
    public static final int ITEM_SEEDS = 22;
    public static final int ITEM_LAMP = 23;
    public static final int ITEM_BREAD = 24;
    public static final int ITEM_BROOCH = 25;
    public static final int ITEM_MATCHES = 26;
    public static final int ITEM_STONE_DESTINY = 27;
    public static final int ITEM_APPLE = 28;
    public static final int ITEM_CUPBOARD = 29;
    public static final int ITEM_BED = 30;
    public static final int ITEM_BRIDGE = 31;
    public static final int ITEM_TREES = 32;
    public static final int ITEM_SAIL = 33;
    public static final int ITEM_KILN = 34;
    public static final int ITEM_KETCH = 35;
    public static final int ITEM_BRICKS = 36;
    public static final int ITEM_WINDMILL = 37;
    public static final int ITEM_SACK = 38;
    public static final int ITEM_BOAR = 39;
    public static final int ITEM_WHEEL = 40;
    public static final int ITEM_PONY = 41;
    public static final int ITEM_GRAVESTONE = 42;
    public static final int ITEM_POOL = 43;
    public static final int ITEM_GATE = 44;
    public static final int ITEM_HANDLE = 45;
    public static final int ITEM_HUT = 46;
    public static final int ITEM_VINE = 47;
    public static final int ITEM_INSCRIPTION = 48;
    public static final int ITEM_TROLL = 49;
    public static final int ITEM_RUBBLE = 50;
    public static final int ITEM_HOUND = 51;
    public static final int ITEM_FOUNTAIN = 52;
    public static final int ITEM_CIRCLE = 53;
    public static final int ITEM_MOSAICS = 54;
    public static final int ITEM_BOOKS = 55;
    public static final int ITEM_CASKS = 56;
    public static final int ITEM_WELL = 57;
    public static final int ITEM_WALL = 58;
    public static final int ITEM_RAT = 59;
    public static final int ITEM_SAFE = 60;
    public static final int ITEM_COBWEBS = 61;
    public static final int ITEM_COIN = 62;
    public static final int ITEM_BELL = 63;
    public static final int ITEM_SILVER_PLATE = 64;
    public static final int ITEM_STONES = 65;
    public static final int ITEM_KITCHEN = 66;
    public static final int ITEM_GOBLET = 67;
    public static final int ITEM_WINE = 68;
    public static final int ITEM_GRARG = 69;
    public static final int ITEM_DOOR = 70;
    public static final int ITEM_AWAKE = 71;
    public static final int ITEM_GUIDE = 72;
    public static final int ITEM_PROTECT = 73;
    public static final int ITEM_LEAD = 74;
    public static final int ITEM_HELP = 75;
    public static final int ITEM_CHEST = 76;
    public static final int ITEM_WATER = 77;
    public static final int ITEM_STABLES = 78;
    public static final int ITEM_SLUICE = 79;
    public static final int ITEM_POT = 80;
    public static final int ITEM_STATUE = 81;
    public static final int ITEM_PINNACLE = 82;
    public static final int ITEM_MUSIC = 83;
    public static final int ITEM_MAGIC_WORDS = 84;
    public static final int ITEM_MISTY_POOL = 85;
    public static final int ITEM_WELL_BOTTOM = 86;
    public static final int ITEM_OLD_KILN = 87;
    public static final int ITEM_MOUNTAIN_HUT = 88;
    
    // === Flags ===
    public static final int FLAG_ONE = 1;
    public static final int FLAG_TWO = 2;
    public static final int FLAG_THREE = 3;
    public static final int FLAG_FOUR = 4;
    public static final int FLAG_FIVE = 5;
    public static final int FLAG_SIX = 6;
    public static final int FLAG_SEVEN = 7;
    public static final int FLAG_EIGHT = 8;
    public static final int FLAG_NINE = 9;
    public static final int FLAG_TEN = 10;
    public static final int FLAG_ELEVEN = 11;
    public static final int FLAG_TWELVE = 12;
    public static final int FLAG_THIRTEEN = 13;
    public static final int FLAG_FOURTEEN = 14;
    public static final int FLAG_FIFTEEN = 15;
    public static final int FLAG_SIXTEEN = 16;
    public static final int FLAG_SEVENTEEN = 17;
    public static final int FLAG_EIGHTEEN = 18;
    public static final int FLAG_NINETEEN = 19;
    public static final int FLAG_TWENTY = 20;
    public static final int FLAG_TWENTY_ONE = 21;
    public static final int FLAG_TWENTY_TWO = 22;
    public static final int FLAG_TWENTY_THREE = 23;
    public static final int FLAG_TWENTY_FOUR = 24;
    public static final int FLAG_TWENTY_FIVE = 25;
    public static final int FLAG_TWENTY_SIX = 26;
    public static final int FLAG_TWENTY_SEVEN = 27;
    public static final int FLAG_TWENTY_EIGHT = 28;
    public static final int FLAG_WEARING_BOOTS = 29;
    public static final int FLAG_BOAT_POWER = 30;
    public static final int FLAG_BOAT_FLAG = 31;
    public static final int FLAG_OGBANS_BOAR = 32;
    public static final int FLAG_THIRTY_THREE = 33;
    public static final int FLAG_THIRTY_FOUR = 34;
    public static final int FLAG_THIRTY_FIVE = 35;
    public static final int FLAG_THIRTY_SIX = 36;
    public static final int FLAG_THIRTY_SEVEN = 37;
    public static final int FLAG_THIRTY_EIGHT = 38;
    public static final int FLAG_BOOT_WEAR_STATUS = 39;
    public static final int FLAG_FORTY = 40;
    public static final int FLAG_FORTY_ONE = 41;
    public static final int FLAG_FORTY_TWO = 42;
    public static final int FLAG_HORSESHOE_NAILED_ON = 43;
    public static final int FLAG_COIN_NUMBERS = 44;
    public static final int FLAG_RUBBLE_BLOCKING = 45;
    public static final int FLAG_HOUND = 46;
    public static final int FLAG_FORTY_SEVEN = 47;
    public static final int FLAG_FORTY_EIGHT = 48;
    public static final int FLAG_CARRYING_ITEMS = 49;
    public static final int FLAG_IS_DARK= 50;
    public static final int FLAG_FIFTY_ONE = 51;
    public static final int FLAG_FIFTY_TWO = 52;
    public static final int FLAG_FIFTY_THREE = 53;
    public static final int FLAG_FORCES = 54;
    public static final int FLAG_PLAYER_SPOTTED = 55;
    public static final int FLAG_PLAYER_FAILED = 56;
    public static final int FLAG_FIFTY_SEVEN = 57;
    public static final int FLAG_FIFTY_EIGHT = 58;
    public static final int FLAG_FIFTY_NINE = 59;
    public static final int FLAG_SIXTY = 60;
    public static final int FLAG_SIXTY_ONE = 61;
    public static final int FLAG_SIXTY_TWO = 62;
    public static final int FLAG_SIXTY_THREE = 63;
    public static final int FLAG_TROLL = 64;
    public static final int FLAG_RATS = 65;
    public static final int FLAG_COBWEBS = 66;
    public static final int FLAG_SIXTY_SEVEN = 67;
    public static final int FLAG_OBGAN = 68;
    public static final int FLAG_SIXTY_NINE = 69;
    public static final int FLAG_DOOR = 70;
    public static final int FLAG_SEVENTY_ONE = 71;
    public static final int FLAG_SEVENTY_TWO = 72;
    public static final int FLAG_SEVENTY_THREE = 73;
    public static final int FLAG_SEVENTY_FOUR = 74;
    public static final int FLAG_SEVENTY_FIVE = 75;
    public static final int FLAG_SEVENTY_SIX = 76;
    public static final int FLAG_SEVENTY_SEVEN = 77;
    public static final int FLAG_SEVENTY_EIGHT = 78;
    public static final int FLAG_SEVENTY_NINE = 79;
    public static final int FLAG_EIGHTY = 80;
    public static final int FLAG_EIGHTY_ONE = 81;
    public static final int FLAG_EIGHTY_TWO = 82;
    public static final int FLAG_EIGHTY_THREE = 83;
    public static final int FLAG_EIGHTY_FOUR = 84;
    public static final int FLAG_EIGHTY_FIVE = 85;
    public static final int FLAG_EIGHTY_SIX = 86;
    public static final int FLAG_EIGHTY_SEVEN = 87;
    public static final int FLAG_EIGHTY_EIGHT = 88;
    
	// === Commands ===
	public static final int MOVE_BOTTOM = 0;
	public static final int MOVE_NOT_DIRECTION = 6;
	public static final int MOVE_TOP = 7;

	public static final int CMD_NORTH = 1;
	public static final int CMD_EAST = 2;
	public static final int CMD_SOUTH = 3;
	public static final int CMD_WEST = 4;
	public static final int CMD_UP = 5;
	public static final int CMD_DOWN = 6;
	public static final int CMD_INVENTORY = 7;
	public static final int CMD_GET = 8;
	public static final int CMD_TAKE = 9;
	public static final int CMD_EXAMINE = 10;
	public static final int CMD_READ = 11;
	public static final int CMD_GIVE = 12;
	public static final int CMD_SAY = 13;
	public static final int CMD_PICK = 14;	
	public static final int CMD_WEAR = 15;
	public static final int CMD_TIE = 16;
	public static final int CMD_CLIMB = 17;
	public static final int CMD_RIG = 18;
	public static final int CMD_USE = 19;
	public static final int CMD_OPEN = 20;
	public static final int CMD_LIGHT = 21;
	public static final int CMD_FILL = 22;
	public static final int CMD_PLANT = 23;
	public static final int CMD_WATER = 24;
	public static final int CMD_SWING = 25;
	public static final int CMD_EMPTY = 26;
    public static final int CMD_ENTER = 27;
	public static final int CMD_CROSS = 28;
	public static final int CMD_REMOVE = 29;
	public static final int CMD_FEED = 30;
	public static final int CMD_TURN = 31;
	public static final int CMD_DIVE = 32;
	public static final int CMD_BAIL = 33;
	public static final int CMD_LEAVE = 34;
	public static final int CMD_THROW = 35;
	public static final int CMD_INSERT = 36;
	public static final int CMD_BLOW = 37;
	public static final int CMD_DROP = 38;
	public static final int CMD_EAT = 39;
	public static final int CMD_MOVE = 40;					
	public static final int CMD_INTO = 41;
	public static final int CMD_RING = 42;
	public static final int CMD_CUT = 43;
	public static final int CMD_HOLD = 44;
	public static final int CMD_BURN = 45;
	public static final int CMD_POISON = 46;
	public static final int CMD_SHOW = 47;
	public static final int CMD_UNLOCK = 48;
	public static final int CMD_WITH = 49;
	public static final int CMD_DRINK = 50;
	public static final int CMD_COUNT = 51;
	public static final int CMD_PAY = 52;
	public static final int CMD_MAKE = 53;
	public static final int CMD_BREAK = 54;
	public static final int CMD_STEAL = 55;
	public static final int CMD_GATHER = 56;
	public static final int CMD_RELFECT = 57;
	
	public static final int CMD_VALID_NORTH = 1;
	public static final int CMD_VALID_SOUTH = 2;
	public static final int CMD_VALID_EAST = 3;
	public static final int CMD_VALID_WEST = 4;
	
	public static final int NOUN_NIL = 0;
		
	public static final String SPACE = " ";
	public static final String CLEAR = "";
    
	// === Codes ===

	// === Directions ===
	public static final int NORTH = 1;
	public static final int SOUTH = 2;
	public static final int EAST = 3;
	public static final int WEST = 4;
	
	public static final int CARRYING = 0;
	
	// === Special Items ===
	public static final int SPECIAL_ITEM_GRARG_FEASTING = 1;
	public static final int SPECIAL_ITEM_GRARG_SLEEPING = 2;
	public static final int SPECIAL_ITEM_PONY_PRESENT = 3;
	public static final int SPECIAL_ITEM_HERMIT_PRESENT = 4;
	public static final int SPECIAL_ITEM_DOOR_OPEN = 5;
	public static final int SPECIAL_ITEM_OGBAN_DEAD = 6;
	public static final int SPECIAL_ITEM_OGBAN = 7;
	public static final int SPECIAL_ITEM_TROLL = 8;
	public static final int SPECIAL_ITEM_BOAR = 9;
	public static final int SPECIAL_ITEM_RUBBLE = 10;
	public static final int SPECIAL_ITEM_HOUND = 11;
	public static final int SPECIAL_ITEM_RATS = 12;

}

/* 3 December 2025 - Created File
 * 6 December 2025 - Cleared contents
 * 8 December 2025 - Added back required codes
 * 				   - Increased version number
 * 10 December 2025 - Added title
 * 18 December 2025 - Added the room constants
 * 24 December 2025 - Added first 60 item constants
 * 25 December 2025 - Completed adding item constants
 * 					- Added nouns
 * 5 January 2026 - Added a zero value for movement
 * 6 January 2026 - Added flags
 * 9 January 2026 - Added validation for directions (they differ from the previous game).
 * 12 January 2026 - Updated flag names
 * 13 January 2026 - Updated flag names and added destroyed location for items.
 * 14 January 2026 - Added further special items
 */