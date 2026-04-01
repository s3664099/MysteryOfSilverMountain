/*
Title: Mystery of Silver Mountain Throw Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 1 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Throw {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code THROW} handler for executing a throw command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Throw(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a throw action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeThrow() {
		game.addMessage("You cannot throw that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		
		if (isCarriableItem(command.getNounNumber())) {
			if (isCarryingItem(game,command.getNounNumber())) {
				result = itemThrown(game,player,command.getNounNumber());
			}
			
		}
				
		return result;
	}
	
	private boolean isCarriableItem(int nounNumber) {
		return nounNumber>0 && nounNumber<=Constants.MAX_CARRIABLE_ITEMS;
	}
	
	private boolean isCarryingItem(Game game, int nounNumber) {
		return game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult itemThrown(Game game, Player player, int nounNumber) {
		game.addMessage("Did no go far!", true, false);
		game.getItem(nounNumber).setItemLocation(player.getRoom());
		return new ActionResult(game,player,true);
	}
	//2850 IF H=3317 THEN R$="ZPV DBVHIU UIF CPBS": F(32)=1:GOSUB 4260
}

/* 30 March 2026 - Created File
 * 1 April 2026 - Added basic responses
 */
