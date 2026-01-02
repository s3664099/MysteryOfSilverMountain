/*
Title: <Game Name> Special Item Handler Class
Author: 
Translator: David Sarkies
Version: 1.0
Date: 8 December 2025
Source: 
*/

package game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import data.Constants;
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
		
		//140 IF R=29 AND F(48)=0 THEN J$=J$+" GRARGS FEASTING,"
		//150 IF R=29 AND F(48)=1 THEN J$=J$+" A SLEEPNG GRARG,"
		
		
		//180 IF R=18 AND E$(18)="N" THEN J$=$J+" AN OAK DOOR,"
		//190 IF R=59 AND F(68)=1 THEN J$=J$+" OGBAN (DEAD),"
		
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_GRARG_FEASTING,"grargs feasting");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_GRARG_SLEEPING,"a sleeing grarg");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_PONY_PRESENT," a pony");
		itemDescriptions.put(GameEntities.SPECIAL_ITEM_HERMIT_PRESENT," a hermit");
		itemDescriptions.put(GameEntities.ROOM_CORRIDOR," an oak door");
		itemDescriptions.put(GameEntities.ROOM_OGBAN_CHAMBER," a dead Ogban");
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
}

/* 3 December 2025 - Created File
 * 7 December 2025 - Removed game related code
 * 8 December 2025 - Increased version
 * 2 January 2025 - Added special items, and code to display them correctly.
 */
