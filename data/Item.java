/*
Title: Mystery of Silver Mountain Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 10 December 2025
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package data;

import java.io.Serializable;

public class Item implements Serializable {
	
	private static final long serialVersionUID = -2697850646469797958L;
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
	public Item(String item) {
		
		//Random Item Flags
		//4480 F(41)=INT(RND(1)*900)+100: F(42)=INT(RND(1)*3)+2: F(52)=INT(RND(1)*3)
		
		//Saves the descriptions
		this.itemName = item;
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
 */