/*
Title: Mystery of Silver Mountain Raw Data
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.8
Date: 26 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/


package data;

public class RawData {
	
	
	private static final Integer[] LOCATION_TYPES = {1,2,1,3,4,3,5,6,7,7,
													8,5,9,10,11,3,12,13,14,15,
													16,17,18,19,17,17,20,21,22,23,
													24,25,26,27,28,9,29,29,14,30,
													31,32,33,33,17,34,35,30,13,36,
													31,31,17,37,37,38,39,40,41,42,
													13,3,9,37,43,44,44,45,46,47,
													48,48,49,50,51,51,17,17,52,9};
	
    private static final String[] LOCATION_IMAGE = {"grave","graveyard","grotto","maze","gate","entrance",
    												"guardhouse","marsh","house","pool","garden","fountain",
    												"corridor","gallery","kitchen","kiln","road","watermill",
    												"dam","bell","dungeon","banquet","battlement","island",
    												"shipwreck","wasteland","sacks","frozenpond","casks",
    												"library","water","field","windmill","slope","chamber",
    												"throne" ,"mountain","log","turret","cobweb","safe",
    												"cupboard","creek","forest","stonehenge","stable","attic",
    												"well","campfire","orchard","bridge","village"};
	    
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
		"34Ornate Fountain",                  // 17		FT		12 (Fountain)
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
		"44Island Shore",                     // 31		I		24 (Island)
		"31Beached Ketch",                    // 32		SW		25 (Shipwreck)
		"13Barren Countryside",               // 33		WL		26 (Wasteland)
		"33Sacks on the Upper Floor",         // 34		SK		27 (Sacks)
		"46Frozen Pond",                      // 35		FP		28 (Frozen Pond)
		"21Mountain Hut",                     // 36		H		9  (House)
		"31Row of Casks",                     // 37		C		29 (Casks)
		"11Wine Cellar",                      // 38		C		29 (Casks)
		"12Hall of Tapestries",               // 39		GL		14 (Gallery)
		"11Dusty Library",                    // 40		L		30 (Library)
		"13Rough Water",                      // 41		W		31 (Water)
		"11Ploughed Field",                   // 42		F		32 (Field)
		"55Outside a Windmill",               // 43		WM		33 (Windmill)
		"42Lower Floor of the Mill",          // 44		WM		33 (Windmill)
		"44Icy Path",                         // 45		R		17 (Road)
		"41Scree Slope",                      // 46		SL		34 (Slope)
		"12Silver Chamber",                   // 47		CB		35 (Chamber)
		"12Wizard's Lair",                    // 48		L		30 (Library)
		"11Mosaic Floor Hall",                // 49		CD		13 (Corridor)
		"12Silver Throne Room",               // 50 	TH		36 (Throne)
		"12Middle of the Lake",               // 51		W		31 (Water)
		"42Edge of an Icy Lake",              // 52		W		31 (Water)
		"41Pitted Track",                     // 53		R		17 (Road)
		"31High Pinnacle",                    // 54		MT		37 (Mountain)
		"55Above a Glacier",                  // 55		MT		37 (Mountain)
		"21Huge Fallen Oak",                  // 56		LG		38 (Log)
		"11Turret Room with a Slot Machine",  // 57		TT		39 (Turret)
		"11Cobwebby Room",                    // 58		CB		40 (Cobweb)
		"31Safe in Ogban's Chamber",          // 59		SF		41 (Safe)
		"31Cupboard in the Corner",           // 60		CP		42 (Cupboard)
		"11Narrow Passage",                   // 61		CD		13 (Corridor)
		"16Cave",                             // 62		GR		3  (Grotto)
		"11Woodman's Hut",                    // 63		H		9  (House)
		"42Side of a  Wooded Valley",         // 64		MT      37 (Mountain)
		"21Stream in a Valley Bottom",        // 65		CK		43 (Creek)
		"11Deep Dark Wood",                   // 66		FS		44 (Forest)
		"11Shady Hollow",                     // 67		FS		44 (Forest)
		"34Ancient Stone Circle",             // 68		SH		45 (Stonehenge)
		"16Stable",                           // 69		ST		46 (Stable)
		"14Attic Bedroom",                    // 70		AT		47 (Attic)
		"11Damp Well Bottom",                 // 71		WL		48 (Well)
		"32Top of a Deep Well",               // 72		WL		48 (Well)
		"31Burnt-Out Campfire",               // 73		CF		49 (Campfire)
		"16Orchard",                          // 74     OH		50 (Orchard)
		"62End of a Bridge",                  // 75		BD		51 (Bridge)
		"62End of a Bridge",                  // 76		BD		51 (Bridge)
		"61Crossroads",                       // 77		R		17 (Road)
		"41Winding Road",                     // 78		R		17 (Road)
		"11Village of Rustic Houses",         // 79		V		52 (Village)
		"11White Cottage"                     // 80		H		9  (House)
	};
		
	private static final String[] OBJECTS = {
		"coins",								// 1	- 3
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
		"bed",									// 30
		"bridge",								// 31
		"trees",								// 32
		"sail",									// 33
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
		"mountain hut"							// 88
	};

	private static final String[] VERBS = {
		"n","e","s","w","u","d","inventory","get","take","examine","read","give","say","pick",
		"wear","tie","climb","rig","use","open","light","fill","plant","water","swing","empty",
		"enter","cross","remove","feed","turn","dive","bail","leave","throw","insert","blow",
		"drop","eat","move","into","ring","cut","hold","burn","poison","show","unlock","with",
		"drink","count","pay","make","break","steal","gather","reflect"
	};
	
	private static final String[] NOUNS = {};
			
	
	//4230 DATA 80,70,60,69,74,72,63,52,20,11,1,14,36,54,61,21,32,10,50
	//4240 DATA 29,59,34,13,80,30,81,47,74
	
	private static final int[] ITEM_LOCATION = {};
	
	//4250 DATA 1,2,3,4,5,9,12,13,16,17,20,21,22
	//4470 FOR I=1 TO 13:READ A: F(A)=1:NEXT I
	//4480 F(41)=INT(RND(1)*900)+100: F(42)=INT(RND(1)*3)+2
	//4490 F(44)=4: F(57)=68: F(58)=54: F(59)=15: F(52)=INT(RND(1)*3)
	private static final int[] ITEM_FLAG = {};
	
	private static final String[] PREPOSITIONS_ONE = {
		"","in ","near ","by ","on ","","at "
	};
	
	private static final String[] PREPOSITIONS_TWO = {
		"","a","the","some","an","","a small"
	};
	
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

	public static String[] getPrepositionsOne() {
		return PREPOSITIONS_ONE;
	}
	
	public static String[] getPrepositionsTwo() {
		return PREPOSITIONS_TWO;
	}
		
	public static int getItemLocation(int number) {
		
		if (number<0 || number >= ITEM_LOCATION.length) {
			throw new IllegalArgumentException("Raw Data - Invalid object location number: "+number);
		}
		
		return ITEM_LOCATION[number-1];
	}
	
	public static int getItemFlag(int number) {
		
		if (number<0 || number >= ITEM_FLAG.length) {
			throw new IllegalArgumentException("Raw Data - Invalid object flag number: "+number);
		}		
		
		return ITEM_FLAG[number-1];
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
 * 19 December 2025 - Added icons to first 20 rooms
 * 21 December 2025 - Added icons to next 20 rooms
 * 22 December 2025 - Added icons to final 40 rooms
 * 					- Added first 16 items (and noted numbers at front)
 * 24 December 2025 - Added items
 * 25 December 2025 - Added prepositions & nouns
 * 					- Updated for ints as item flags and locations
 * 26 December 2025 - Updated data and split prepositions into two arrays
 */