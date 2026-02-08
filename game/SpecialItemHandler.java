/*
Title: <Game Name> Special Item Handler Class
Author: 
Translator: David Sarkies
Version: 1.4
Date: 8 February 2026
Source: 
*/

package game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import data.GameEntities;
import data.Item;
import data.Location;

/**
 * Computes special item descriptions for specific rooms based on game state.
 * <br>
 * This handler returns contextual descriptions (e.g., items mounted, hidden, or revealed)
 * depending on item flags/locations and whether a room has been viewed.
 * If no special description should be shown, an empty string is returned.
 */
public class SpecialItemHandler implements Serializable {

	private static final long serialVersionUID = -3392796825592359959L;

    /** Static mapping of room number to its base special description. */
	private static final Map<Integer, String> itemDescriptions = new HashMap<>();
	
    /**
     * Initializes the static special descriptions for known rooms.
     * Uses {@link GameEntities} room constants as keys.
     */
	public SpecialItemHandler() {
		
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_GRARG_FEASTING,"grargs feasting");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_GRARG_SLEEPING,"a sleeing grarg");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_PONY_PRESENT," a pony");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_HERMIT_PRESENT," a hermit");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_DOOR_OPEN," an open oak door");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_OGBAN_DEAD," a dead Ogban");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_OGBAN," Lord Ogban");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_TROLL," a nasty troll lurking under the bridge");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_BOAR," Ogban's Boar");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_RUBBLE," rubble blocking the exit");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_HOUND," a vicious looking hound");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_RATS," rats swarming the floor");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_STATUE,"  a defaced statue");
	}
	
    /**
     * Returns the context-sensitive special item description for a room.
     * <ul>
     *   <li>If the room has no special description, returns an empty string.</li>
     *   <li>If a special item is not present/visible (based on item flags/locations or room viewed state), returns an empty string.</li>
     * </ul>
     *
     * @param roomNumber   the current room number
     * @param itemList     the array of {@link Item} instances (indexed by {@link GameEntities} item constants)
     * @param locationList the array of {@link Location} instances (indexed by room number)
     * @return the special description for the room, or an empty string if none should be shown
     */
	public String getSpecialItems(int roomNumber,Item[] itemList, Location[] locationList) {
		int specialItemType = 0;
		
		if (checkInBanquetHall(roomNumber)) {
			if (checkGrargFlag(itemList[GameEntities.ITEM_INSCRIPTION].getItemFlag())) {
				specialItemType = GameEntities.SPECIAL_ITEM_GRARG_FEASTING;
			} else {
				specialItemType = GameEntities.SPECIAL_ITEM_GRARG_SLEEPING;
			}
		} else if (checkPonyPresent(roomNumber)) {
			specialItemType = GameEntities.SPECIAL_ITEM_PONY_PRESENT;
		} else if (checkHermitPresent(roomNumber)) {
			specialItemType = GameEntities.SPECIAL_ITEM_HERMIT_PRESENT;
		} else if (checkDoorOpen(roomNumber,locationList[GameEntities.ROOM_CORRIDOR].getExits())) {
			specialItemType = GameEntities.SPECIAL_ITEM_DOOR_OPEN;
		} else if (checkOgbanDead(roomNumber,itemList[GameEntities.FLAG_OBGAN].getItemFlag())) {
			specialItemType = GameEntities.SPECIAL_ITEM_OGBAN_DEAD;
		} else if (checkOgbanAlive(roomNumber,itemList[GameEntities.FLAG_OBGAN].getItemFlag())) {
			specialItemType = GameEntities.SPECIAL_ITEM_OGBAN;
		} else if (checkTrollPresent(roomNumber,itemList[GameEntities.FLAG_TROLL].getItemFlag())) {
			specialItemType = GameEntities.SPECIAL_ITEM_TROLL;
		} else if (checkBoarPresent(roomNumber,itemList[GameEntities.FLAG_OGBANS_BOAR].getItemFlag())) {
			specialItemType = GameEntities.SPECIAL_ITEM_BOAR;
		} else if (checkRubbleBlocking(roomNumber,itemList[GameEntities.FLAG_RUBBLE_BLOCKING].getItemFlag())) {
			specialItemType = GameEntities.SPECIAL_ITEM_RUBBLE;
		} else if (checkHoundPresent(roomNumber,itemList[GameEntities.FLAG_HOUND].getItemFlag())) {
			specialItemType = GameEntities.SPECIAL_ITEM_HOUND;
		} else if (checkRatsPresent(roomNumber,itemList[GameEntities.FLAG_RATS].getItemFlag())) {
			specialItemType = GameEntities.SPECIAL_ITEM_RATS;
		} else if (checkAtStatue(roomNumber)) {
			specialItemType = GameEntities.SPECIAL_ITEM_STATUE;
		}
		
		String description = itemDescriptions.getOrDefault(specialItemType,"");
		return description;
	}
	
	private boolean checkInBanquetHall(int roomNumber) {
		return roomNumber == GameEntities.ROOM_BANQUET_HALL;
	}
	
	private boolean checkGrargFlag (int itemFlag) {
		return itemFlag == 0;
	}
	
	private boolean checkPonyPresent (int roomNumber) {
		return roomNumber == GameEntities.ROOM_RUSTY_GATES ||
				roomNumber == GameEntities.ROOM_TRACK;
	}
	
	private boolean checkHermitPresent(int roomNumber) {
		return roomNumber == GameEntities.ROOM_VALLEY;
	}
	
	private boolean checkDoorOpen(int roomNumber, boolean[] exits) {
		return roomNumber == GameEntities.ROOM_CORRIDOR &&
				exits[GameEntities.NORTH-1];
	}
	
	private boolean checkOgbanDead(int roomNumber, int ogbanFlag) {
		return roomNumber == GameEntities.ROOM_OGBAN_CHAMBER &&
				ogbanFlag == 1;
	}
	
	private boolean checkOgbanAlive(int roomNumber, int ogbanFlag) {
		return roomNumber == GameEntities.ROOM_OGBAN_CHAMBER &&
				ogbanFlag == 0;
	}
	
	private boolean checkTrollPresent(int roomNumber, int trollFlag) {
		return ((roomNumber == GameEntities.ROOM_BRIDGE_EAST || roomNumber == GameEntities.ROOM_BRIDGE_WEST)
				&& trollFlag ==0);
	}
	
	private boolean checkBoarPresent(int roomNumber, int boarFlag) {
		return (roomNumber == GameEntities.ROOM_COUNTRYSIDE && boarFlag == 0);
	}
	
	private boolean checkRubbleBlocking(int roomNumber, int rubbleFlag) {
		return ((roomNumber == GameEntities.ROOM_STALACTITES || roomNumber == GameEntities.ROOM_TOMB) &&
				rubbleFlag == 0);
	}
	
	private boolean checkHoundPresent(int roomNumber, int houndFlag) {
		return roomNumber == GameEntities.ROOM_GLASS_GATES && houndFlag == 0;
	}
	
	private boolean checkRatsPresent(int roomNumber, int ratFlag) {
		return roomNumber == GameEntities.ROOM_WINE_CELLAR && ratFlag == 0;
	}
	
	private boolean checkAtStatue(int roomNumber) {
		return roomNumber == GameEntities.ROOM_CROSSROADS;
	}
}

/* 3 December 2025 - Created File
 * 7 December 2025 - Removed game related code
 * 8 December 2025 - Increased version
 * 2 January 2026 - Added special items, and code to display them correctly.
 * 12 January 2026 - Added more special items
 * 14 January 2026 - Added special items
 * 8 February 2026 - Added special item statue
 */
