/*
Title: Mystery of Silver Mountain Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 29 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package data;

import java.io.Serializable;

public class Item implements Serializable {
	
	private static final long serialVersionUID = -2697850646469797958L;
	
	private static final int PREPOSITION_INDEX = 0;
	private static final int NAME_START_INDEX = 1;
	
	private int itemFlag;
	private int itemLocation;
	private String itemName;
	private boolean wisdomAcquired = false;
		
	/**
     * Constructs an Item with the specified flag, location, and description.
     *
     * @param flag     The flag character (e.g., from ITEM_FLAG).
     * @param location The location character (e.g., from ITEM_LOCATION).
     * @param description The item's description.
     */
	public Item(String item, int location, int flag, String[] prepositions) {
				
		this.itemFlag = flag;
		this.itemLocation = location;
		
		//Saves the descriptions
		int prep = Integer.parseInt(item.substring(PREPOSITION_INDEX,NAME_START_INDEX));
		this.itemName = String.format("%s%s",prepositions[prep],item.substring(NAME_START_INDEX));
		
		System.out.println(this.toString());
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getItemFlag() {
		return this.itemFlag;
	}
	
	public void setItemFlag(int flag) {
		this.itemFlag = flag;
	}
	
	public int getItemLocation() {
		return this.itemLocation;
	}
	
	public void setItemLocation(int newLocation) {
		this.itemLocation = newLocation;
	}
	
	//Checks if the item is present at the location
	public boolean isAtLocation(int location) {
		
		boolean itemPresent = false;
		
		if (location == this.itemLocation)  {
			itemPresent = true;
		}
		
		return itemPresent;
	}	
	
    @Override
    public String toString() {
        return "Item{" +
                "description='" + itemName + '\'' +
                ", location=" + itemLocation +
                ", flag=" + itemFlag +
                ", wisdomGained=" + wisdomAcquired +
                '}';
    }
}

/* 3 December 2025 - Created File
 * 6 December 2025 - Removed game specific code
 * 8 December 2025 - Increased version number
 * 10 December 2025 - Added title
 * 29 December 2025 - Added locations,flags,and prepotisions for item.
 */