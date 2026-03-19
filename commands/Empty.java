/*
Title: Mystery of Silver Mountain Empty Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 19 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Empty {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code EMPTY} handler for executing a empty command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Empty(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an empty action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeEmpty() {
		game.addMessage("You cannot Empty that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if isWater(command.getNounNumber()) {
			result = new Water(game,player,command).executeWater();
		} else if isEmptyPhial(game,player.getRoom(),command.getNounNumber()) {
			result = emptyPhial(game,player);
		}
		
		return result;
	}
	
	private boolean isWater(int nounNumber) {
		return nounNumber == GameEntities.ITEM_SEEDS ||
				nounNumber == GameEntities.ITEM_JUG;
	}
	
	private boolean isEmptyPhial(Game game, int roomNumber, int nounNumber) {
		return game.getItem(GameEntities.ITEM_PHIAL).getItemLocation() == GameEntities.CARRYING &&
				roomNumber == GameEntities.ROOM_MOSAIC_HALL && nounNumber == GameEntities.ITEM_PHIAL;
	}
	
	private ActionResult emptyPhial(Game game,Player player) {
		game.addMessage("Where?", true, false);
		return new ActionResult(game,player,true);
	}
}
/* 15 March 2026 - Created class
 * 19 March 2026 - Added the responses
 */
*/