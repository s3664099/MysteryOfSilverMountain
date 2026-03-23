/*
Title: Mystery of Silver Mountain Remove Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 23 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

TODO - Add to drop - if wearing uniform/boots and drop them, then reset flag
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Remove {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code REMOVE} handler for executing a remove command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Remove(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an remove action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeRemove() {
		game.addMessage("You cannot remove that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isBoots(game,command.getNounNumber())) {
			result = boots(game,player);
		} else if (isUniform(game,command.getNounNumber())) {
			result = uniform(game,player);
		}
		return result;
	}
	
	/**
	 * Returns true if the player is removing the boots
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isBoots(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_BOOTS &&
				game.getItem(GameEntities.ITEM_BOOTS).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.FLAG_BOOT_WEAR_STATUS).getItemFlag() == 1;
	}
	
    /**
     * Executes a response if the player is removing the boots
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult boots(Game game,Player player) {
		game.addMessage("Taken off", true, false);
		game.getItem(GameEntities.FLAG_BOOT_WEAR_STATUS).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is removing the uniform
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUniform(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_UNIFORM &&
				game.getItem(GameEntities.ITEM_UNIFORM).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.FLAG_WEARING_UNIFORM).getItemFlag() == 1;
	}
	
    /**
     * Executes a response if the player is removing the uniform
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult uniform(Game game,Player player) {
		game.addMessage("Ok", true, false);
		game.getItem(GameEntities.FLAG_WEARING_UNIFORM).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
}

/* 22 March 2026 - Created Class
 * 23 March 2026 - Added responses & JavaDocs
 */
