/*
Title: Mystery of Silver Mountain Raw Data
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.5
Date: 19 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/


package data;

public class RawData {
	
	
	private static final Integer[] LOCATION_TYPES = {1,2,1,3,4,3,5,6,7,7,8,5,9,10,11,3,12,13,14,15,
													 16,17,18,19,17,17,20,21,22,23};
	
    private static final String[] LOCATION_IMAGE = {"grave","graveyard","grotto","maze","gate","entrance",
    												"guardhouse","marsh","house","pool","garden","fountain",
    												"corridor","gallery","kitchen","kiln","road","watermill",
    												"dam","bell","dungeon","banquet","battlement"};
		    
	private static final String[] LOCATIONS = {
		"11Half-dug Grave",                   // 1		G		1  (Grave)
		"12Goblin Graveyared",                // 2		GY		2  (Graveyard)
		"11Hollow Tomb",                      // 3		G		1  (Grave)
		"23Stalactites and Stalagmites",      // 4		GR		3  (Grotto)
		"11Maze of Tunnels",                  // 5		MZ		4  (Maze)
		"11Vaulted Cavern",                   // 6		GR		3  (Grotto)
		"23High Glass Gates",                 // 7		GT		5  (Gate)
		"12Entrance Hall to the Palace",      // 8		EH		6  (Entrance)
		"31Grarg Sentry Post",                // 9		GH		7  (Guardhouse)
		"12Guard Room",                       // 10		GH		7  (Guardhouse)
		"31Marshy Inlet",                     // 11		M		8  (Marsh)
		"23Rusty Gates",                      // 12		GT		5  (Gate)
		"12Gamekeeper's Cottage",             // 13		H		9  (House)
		"31Misty Pool",                       // 14		P		10 (Pool)
		"11High-Walled Garden",               // 15		GD		11 (Garden)
		"14Inscribed Cavern",                 // 16		GR		3  (Grotto)
		"34Ornate Fountain",                  // 17		F		12 (Fountain)
		"11Dank Corridor",                    // 18		CD		13 (Corridor)
		"12Long Gallery",                     // 19		GL		14 (Gallery)}
		"12Kitchens of the Palace",           // 20		K		15 (Kitchen)
		"34Old Kiln",                         // 21		KL		16 (Kiln)
		"44Overgrown Track",                  // 22		R		17 (Road)
		"31Disused Waterwheel",               // 23		WM		18 (Water Mill)
		"33Sluice Gates",                     // 24		DM		19 (Dam)
		"11Gap between some Boulder",         // 25		R		17 (Road)
		"41Perilous Path",                    // 26		R		17 (Road)
		"31Silver Bell in the Rock",          // 27		B		20 (Bell)
		"12Dungeons of the Palace",           // 28		D		21 (Dungeon)
		"11Banqueting Hall",                  // 29		BQ      22 (Banquet)
		"42Palace Battlements",               // 30		BT		23 (Battlement)
		"44Island Shore",                     // 31
		"31Beached Ketch",                    // 32
		"13Barren Countryside",               // 33
		"33Sacks on the Upper Floor",         // 34
		"46Frozen Pond",                      // 35
		"21Mountain Hut",                     // 36
		"31Row of Casks",                     // 37
		"11Wine Cellar",                      // 38
		"12Hall of Tapestries",               // 39
		"11Dusty Library",                    // 40
		"13Rough Water",                      // 41
		"11Ploughed Field",                   // 42
		"55Outside a Windmill",               // 43
		"42Lower Floor of the Mill",          // 44
		"44Icy Path",                         // 45
		"41Scree Slope",                      // 46
		"12Silver Chamber",                   // 47
		"12Wizard's Lair",                    // 48
		"11Mosaic Floor Hall",                // 49
		"12Silver Throne Room",               // 50
		"12Middle of the Lake",               // 51
		"42Edge of an Icy Lake",              // 52
		"41Pitted Track",                     // 53
		"31High Pinnacle",                    // 54
		"55Above a Glacier",                  // 55
		"21Huge Fallen Oak",                  // 56
		"11Turret Room with a Slot Machine",  // 57
		"11Cobwebby Room",                    // 58
		"31Safe in Ogban's Chamber",          // 59
		"31Cupboard in the Corner",           // 60
		"11Narrow Passage",                   // 61
		"16Cave",                             // 62
		"11Woodman's Hut",                    // 63
		"42Side of a  Wooded Valley",         // 64
		"21Stream in a Valley Bottom",        // 65
		"11Deep Dark Wood",                   // 66
		"11Shady Hollow",                     // 67
		"34Ancient Stone Circle",             // 68
		"16Stable",                           // 69
		"14Attic Bedroom",                    // 70
		"11Damp Well Bottom",                 // 71
		"32Top of a Deep Well",               // 72
		"31Burnt-Out Campfire",               // 73
		"16Orchard",                          // 74
		"62End of a Bridge",                  // 75
		"62End of a Bridge",                  // 76
		"61Crossroads",                       // 77
		"41Winding Road",                     // 78
		"11Village of Rustic Houses",         // 79
		"11White Cottage"                     // 80
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
 * 16 December 2025 - Started Adding locations
 * 17 December 2025 - Finished adding locations
 * 18 December 2025 - Started Adding icon notes for locations
 * 19 December 2025 - Added first 20 icons for rooms
 */