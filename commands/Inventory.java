/*
Title: Mystery of Silver Mountain Inventory Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 21 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Inventory {
	
    /** The active game instance. */
	private final Game game;
	private final Player player;
	
    /**
     * Creates an {@code Inventory} handler for displaying items player carrying
     * 
     * @param game   current game state
     */
	public Inventory(Game game,Player player) {
		this.game = game;
		this.player = player;
	}
		
	public ActionResult getInventory() {
		
		String items = getItemDetails();
		
		game.addMessage("You have "+items, true, true);
		
		return new ActionResult(game,player,true);
	}
	
    /**
     * Collects the names of all carried items for display.
     * @return comma-separated list of item names
     */
	private String getItemDetails() {

		boolean hasItems = false;
		int itemLength = 0;
		String items = "";
		
		for (int i=1;i<Constants.MAX_CARRIABLE_ITEMS+1;i++) {
			if (game.getItem(i).isAtLocation(GameEntities.ROOM_CARRYING)) {
				hasItems = true;
				int extraLength = 1;
				
				if (itemLength>0) {
					items = items+", ";
					extraLength = 2;
				}
				String itemName = game.getItem(i).getItemName();
				
				if(countCoinNumber(i)) {
					itemName = "a coin";
				}
				
				items = items+itemName;
				itemLength += itemName.length()+extraLength;
			}
		}
		
		if (!hasItems) {
			items = "nothing";
		}
		
		return items;
	}
	
	private boolean countCoinNumber(int itemNumber) {
		return (itemNumber == GameEntities.ITEM_COINS && 
				game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag() == 1);
	}
}

/*
 * 20 January 2026 - Created File
 * 21 January 2026 - Added inventory command
 */
