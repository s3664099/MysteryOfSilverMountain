/*
Title: Mystery of Silver Mountain Raw Data
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.8
Date: 26 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

change the caps
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
		"11half-dug grave1101",					// 1		G		1  (Grave)			E
		"12goblin graveyared1000",				// 2		GY		2  (Graveyard)		ESW
		"11hollow tomb1100",					// 3		G		1  (Grave)			WE
		"23stalactites and stalagmites1100",	// 4		GR		3  (Grotto)			WE
		"11maze of tunnels1101",				// 5		MZ		4  (Maze)			ESW
		"11vaulted cavern1000",					// 6		GR		3  (Grotto)			ESW
		"23high glass gates1100",				// 7		GT		5  (Gate)			ES
		"12Eentrance hall to the palace1100",	// 8		EH		6  (Entrance)		EW
		"31grarg sentry post1100",				// 9		GH		7  (Guardhouse)		SW
		"12guard room1000",						// 10		GH		7  (Guardhouse)		N
		"31marshy inlet1000",					// 11		M		8  (Marsh)			ES
		"23rusty gates1001",					// 12		GT		5  (Gate)			SW
		"12gamekeeper's cottage1100",			// 13		H		9  (House)			S
		"31misty pool1010",						// 14		P		10 (Pool)			NW
		"11high-walled garden1011",				// 15		GD		11 (Garden)			N
		"14inscribed cavern0111",				// 16		GR		3  (Grotto)			N
		"34ornate fountain",					// 17		FT		12 (Fountain)		ES
		"11dank corridor",						// 18		CD		13 (Corridor)		NSW
		"12long gallery",						// 19		GL		14 (Gallery)}		N
		"12kitchens of the palace",				// 20		K		15 (Kitchen)		ES
		"34old kiln",							// 21		KL		16 (Kiln)			SW
		"44overgrown track",					// 22		R		17 (Road)			S
		"31disused waterwheel",					// 23		WM		18 (Water Mill)		NW
		"33sluice gates",						// 24		DM		19 (Dam)			N
		"11gap between some boulder",			// 25		R		17 (Road)			N
		"41perilous path",						// 26		R		17 (Road)			ES
		"31silver bell in the rock",			// 27		B		20 (Bell)			NSW
		"12dungeons of the palace",				// 28		D		21 (Dungeon)		NS
		"11banqueting hall",					// 29		BQ      22 (Banquet)		E
		"42palace battlements",					// 30		BT		23 (Battlement)		NSW
		"44island shore",						// 31		I		24 (Island)			N
		"31beached ketch",						// 32		SW		25 (Shipwreck)		NEW
		"13barren countryside",					// 33		WL		26 (Wasteland)		EW
		"33sacks on the upper floor",			// 34		SK		27 (Sacks)			W
		"46frozen pond",						// 35		FP		28 (Frozen Pond)	S
		"21mountain hut",						// 36		H		9  (House)			NS
		"31row of casks",						// 37		C		29 (Casks)			N
		"11wine cellar",						// 38		C		29 (Casks)			NES
		"12Hall of Tapestries",					// 39		GL		14 (Gallery)		W
		"11dusty library",						// 40		L		30 (Library)		NS
		"13rough water",						// 41		W		31 (Water)			D
		"11ploughed field",						// 42		F		32 (Field)			NES
		"55outside a windmill",					// 43		WM		33 (Windmill)		SW
		"42lower floor of the mill",			// 44		WM		33 (Windmill)		E
		"44icy path",							// 45		R		17 (Road)			NW
		"41scree slope",						// 46		SL		34 (Slope)			NS
		"12Silver Chamber",						// 47		CB		35 (Chamber)		S
		"12wizard's lair",						// 48		L		30 (Library)		NS
		"11mosaic floor hall",					// 49		CD		13 (Corridor)		E
		"12Silver Throne Room",					// 50 		TH		36 (Throne)			NSEW	
		"12middle of the lake",					// 51		W		31 (Water)			WU
		"42edge of an icy lake",				// 52		W		31 (Water)			UD
		"41pitted track",						// 53		R		17 (Road)			NS			
		"41high pinnacle",						// 54		MT		37 (Mountain)		E
		"55above a glacier",					// 55		MT		37 (Mountain)		SW
		"21huge fallen oak",					// 56		LG		38 (Log)			NSE
		"11turret room with a slot machine",	// 57		TT		39 (Turret)			NW
	    //4200 DATA NE,EW,NSW,E,WN,S,E,NEW,NW,S
	    //4210 DATA ES,SW,NES,EW,SW,NE,EW,ESW,SW,ND
	    //4220 DATA " ",E,NEW,EW,NEW,EW,EW,NEW,NEW,WU
		"11cobwebby room",                    // 58		CB		40 (Cobweb)
		"31safe in Ogban's Chamber",          // 59		SF		41 (Safe)
		"31cupboard in the corner",           // 60		CP		42 (Cupboard)
		"11narrow passage",                   // 61		CD		13 (Corridor)
		"16cave",                             // 62		GR		3  (Grotto)
		"11woodman's hut",                    // 63		H		9  (House)
		"42side of a  wooded valley",         // 64		MT      37 (Mountain)
		"21stream in a valley bottom",        // 65		CK		43 (Creek)
		"11deep dark wood",                   // 66		FS		44 (Forest)
		"11shady hollow",                     // 67		FS		44 (Forest)
		"34ancient stone circle",             // 68		SH		45 (Stonehenge)
		"16stable",                           // 69		ST		46 (Stable)
		"14attic bedroom",                    // 70		AT		47 (Attic)
		"11damp well bottom",                 // 71		WL		48 (Well)
		"32top of a deep well",               // 72		WL		48 (Well)
		"31burnt-out campfire",               // 73		CF		49 (Campfire)
		"16orchard",                          // 74     OH		50 (Orchard)
		"62end of a bridge",                  // 75		BD		51 (Bridge)
		"62end of a bridge",                  // 76		BD		51 (Bridge)
		"61crossroads",                       // 77		R		17 (Road)
		"41winding road",                     // 78		R		17 (Road)
		"11village of rustic houses",         // 79		V		52 (Village)
		"11white cottage"                     // 80		H		9  (House)
	};
		
	private static final String[] OBJECTS = {
		"3coins",								// 1
		"1sheet",								// 2
		"3boots",								// 3
		"1horseshoe",							// 4
		"2apples",								// 5
		"1bucket",								// 6
		"4axe",									// 7
		"1boat",								// 8
		"1phial",								// 9
		"3reeds",								// 10
		"1bone",								// 11
		"1shield",								// 12
		"3planks",								// 13
		"1rope",								// 14
		"1rings",								// 15
		"1jug",									// 16
		"1net",									// 17
		"1sword",								// 18
		"1silver plate",						// 19
		"1uniform",								// 20
		"1key",									// 21
		"3seeds",								// 22
		"1lamp",								// 23
		"3bread",								// 24
		"1brooch",								// 25
		"3matches",								// 26
		"2stone of destiny",					// 27
		"4apple",								// 28
		"0cupboard",							// 29
		"0bed",									// 30
		"0bridge",								// 31
		"0trees",								// 32
		"0sail",								// 33
		"0kiln",								// 34
		"0ketch",								// 35
		"0bricks",								// 36
		"0windmill",							// 37
		"0sacks",								// 38
		"0Ogban's boar",						// 39
		"0wheel",								// 40
		"0pony",								// 41
		"0gravestones",							// 42
		"0pool",								// 43
		"0gates",								// 44
		"0handle",								// 45
		"0hut",									// 46
		"0vine",								// 47
		"0inscriptions",						// 48
		"0troll",								// 49
		"0rubble",								// 50
		"0hound",								// 51
		"0fountain",							// 52
		"0circle",								// 53
		"0mosaics",								// 54
		"0books",								// 55
		"0casks",								// 56
		"0well",								// 57
		"0walls",								// 58
		"0rats",								// 59
		"0safe",								// 60
		"0cobwebs",								// 61
		"0coin",								// 62
		"0bell",								// 63
		"0up silver plate",						// 64
		"0stones",								// 65
		"0kitchens",							// 66
		"0goblet",								// 67
		"0wine",								// 68
		"0grargs",								// 69
		"0door",								// 70
		"0awake",								// 71
		"0guide",								// 72
		"0protect",								// 73
		"0lead",								// 74
		"0help",								// 75
		"0chest",								// 76
		"0water",								// 77
		"0stables",								// 78
		"0sluice gates",						// 79
		"0pot",									// 80
		"0statue",								// 81
		"0pinnacle",							// 82
		"0music",								// 83
		"0magic words",							// 84
		"0misty pool",							// 85
		"0well bottom",							// 86
		"0old kiln",							// 87
		"0mountain hut"							// 88
	};

	private static final String[] VERBS = {
		"n","e","s","w","u","d","inventory","get","take","examine","read","give","say","pick",
		"wear","tie","climb","rig","use","open","light","fill","plant","water","swing","empty",
		"enter","cross","remove","feed","turn","dive","bail","leave","throw","insert","blow",
		"drop","eat","move","into","ring","cut","hold","burn","poison","show","unlock","with",
		"drink","count","pay","make","break","steal","gather","reflect"
	};
	
	private static final String[] NOUNS = {};
	
	private static final int[] ITEM_LOCATION = {80,70,60,69,74,72,63,52,20,11,
												1,14,36,54,61,21,32,10,50,29,
												59,34,13,80,30,81,47,74,-1,-1,
												-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
												-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
												-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
												-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
												-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
												-1,-1,-1,-1,-1,-1,-1,-1};
	
	private static final int[] ITEM_FLAG = {1,1,1,1,1,0,0,0,1,0,
											0,1,1,0,0,1,1,0,0,1,
											1,1,0,0,0,0,0,0,0,0,
											0,0,0,0,0,0,0,0,0,0,
											0,0,0,4,0,0,0,0,0,0,
											0,0,0,0,0,0,68,54,15,0,
											0,0,0,0,0,0,0,0,0,0,
											0,0,0,0,0,0,0,0,0,0,
											0,0,0,0,0,0,0,0};
	
	private static final String[] PREPOSITIONS_ONE = {
		"","in ","near ","by ","on ","","at "
	};
	
	private static final String[] PREPOSITIONS_TWO = {
		"","a ","the ","some ","an ","","a small "
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
 * 28 December 2025 - Added item locations, prepositions, and flags
 * 					- Added Started added notes for location exits
 */