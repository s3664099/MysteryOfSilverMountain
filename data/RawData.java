/*
Title: Mystery of Silver Mountain Raw Data
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.10
Date: 29 December 2025
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
		"11half-dug grave1101",					// 1		G		1  (Grave)
		"12goblin graveyared1000",				// 2		GY		2  (Graveyard)
		"11hollow tomb1100",					// 3		G		1  (Grave)
		"23stalactites and stalagmites1100",	// 4		GR		3  (Grotto)
		"11maze of tunnels1100",				// 5		MZ		4  (Maze)
		"11vaulted cavern1000",					// 6		GR		3  (Grotto)
		"23high glass gates1000",				// 7		GT		5  (Gate)
		"12Eentrance hall to the palace1001",	// 8		EH		6  (Entrance)
		"31grarg sentry post1100",				// 9		GH		7  (Guardhouse)
		"12guard room1010",						// 10		GH		7  (Guardhouse)
		"31marshy inlet1011",					// 11		M		8  (Marsh)
		"23rusty gates0111",					// 12		GT		5  (Gate)
		"12gamekeeper's cottage1001",			// 13		H		9  (House)
		"31misty pool1010",						// 14		P		10 (Pool)
		"11high-walled garden1011",				// 15		GD		11 (Garden)
		"14inscribed cavern0110",				// 16		GR		3  (Grotto)
		"34ornate fountain0111",				// 17		FT		12 (Fountain)
		"11dank corridor0111",					// 18		CD		13 (Corridor)
		"12long gallery1001",					// 19		GL		14 (Gallery)
		"12kitchens of the palace0010",			// 20		K		15 (Kitchen)
		"34old kiln0011",						// 21		KL		16 (Kiln)
		"44overgrown track1101",				// 22		R		17 (Road)
		"31disused waterwheel0010",				// 23		WM		18 (Water Mill)
		"33sluice gates0111",					// 24		DM		19 (Dam)
		"11gap between some boulder0100",		// 25		R		17 (Road)
		"41perilous path1100",					// 26		R		17 (Road)
		"31silver bell in the rock1110",		// 27		B		20 (Bell)
		"12dungeons of the palace1011",			// 28		D		21 (Dungeon)
		"11banqueting hall0011",				// 29		BQ      22 (Banquet)
		"42palace battlements0111",				// 30		BT		23 (Battlement)
		"44island shore0001",					// 31		I		24 (Island)
		"31beached ketch1110",					// 32		SW		25 (Shipwreck)
		"13barren countryside0011",				// 33		WL		26 (Wasteland)
		"33sacks on the upper floor1011",		// 34		SK		27 (Sacks)			Down-South
		"46frozen pond0001",					// 35		FP		28 (Frozen Pond)
		"21mountain hut1010",					// 36		H		9  (House)
		"31row of casks1101",					// 37		C		29 (Casks)
		"11wine cellar0110",					// 38		C		29 (Casks)
		"12Hall of Tapestries0011",				// 39		GL		14 (Gallery)
		"11dusty library1011",					// 40		L		30 (Library)
		"13rough water0011",					// 41		W		31 (Water)
		"11ploughed field1101",					// 42		F		32 (Field)
		"55outside a windmill0000",				// 43		WM		33 (Windmill)
		"42lower floor of the mill0110",		// 44		WM		33 (Windmill)		Up-North
		"44icy path0011",						// 45		R		17 (Road)			Up-Down - North-South
		"41scree slope0011",					// 46		SL		34 (Slope)
		"12Silver Chamber1101",					// 47		CB		35 (Chamber)
		"12wizard's lair1010",					// 48		L		30 (Library)
		"11mosaic floor hall0001",				// 49		CD		13 (Corridor)
		"12Silver Throne Room0110",				// 50		TH		36 (Throne)
		"12middle of the lake0101",				// 51		W		31 (Water)
		"42edge of an icy lake1001",			// 52		W		31 (Water)
		"41pitted track0010",					// 53		R		17 (Road)
		"41high pinnacle1101",					// 54		MT		37 (Mountain)
		"55above a glacier0110",				// 55		MT		37 (Mountain)
		"21huge fallen oak1011",				// 56		LG		38 (Log)
		"11turret room with a slot machine1101",// 57		TT		39 (Turret)
		"11cobwebby room0100",					// 58		CB		40 (Cobweb)
		"31safe in Ogban's Chamber0110",		// 59		SF		41 (Safe)
		"31cupboard in the corner1011",			// 60		CP		42 (Cupboard)
		"11narrow passage1001",					// 61		CD		13 (Corridor)
		"16cave1010",							// 62		GR		3  (Grotto)
		"11woodman's hut0001",					// 63		H		9  (House)
		"42side of a  wooded valley1100",		// 64		MT      37 (Mountain)
		"21stream in a valley bottom1010",		// 65		CK		43 (Creek)
		"11deep dark wood0101",					// 66		FS		44 (Forest)
		"11shady hollow1100",					// 67		FS		44 (Forest)
		"34ancient stone circle1000",			// 68		SH		45 (Stonehenge)
		"16stable1010",							// 69		ST		46 (Stable)
		"14attic bedroom0011",					// 70		AT		47 (Attic)			Down - South
		"11damp well bottom1111",				// 71		WL		48 (Well)
		"32top of a deep well1101",				// 72		WL		48 (Well)
		"31burnt-out campfire0100",				// 73		CF		49 (Campfire)
		"16orchard1100",						// 74		OH		50 (Orchard)
		"62end of a bridge0100",				// 75		BD		51 (Bridge)
		"62end of a bridge1100",				// 76		BD		51 (Bridge)
		"61crossroads1100",						// 77		R		17 (Road)
		"41winding road0100",					// 78		R		17 (Road)
		"11village of rustic houses0100",		// 79		V		52 (Village)
		"11white cottage0110"					// 80		H		9  (House)
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
 * 					- Added location exits
 * 29 December 2025 - Completed adding exits to locations
 */