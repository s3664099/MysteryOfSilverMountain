/*
Title: Mystery of Silver Mountain Poison Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 3 May 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Poison {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;

    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Poison} handler for executing a poison action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Poison(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a Poison action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executePoison() {
		game.addMessage("That is not possible.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isPoisonWine(game,player.getRoom(),command.getNounNumber())) {
			result = poisonWine(game,player);
		}
		return result;
	}
	
	/**
	 * Returns true if the player is poisoning the wine
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isPoisonWine(Game game, int roomNumber, int nounNumber) {
		return (nounNumber == GameEntities.ITEM_WINE || nounNumber == GameEntities.ITEM_GOBLET) &&
				roomNumber == GameEntities.ROOM_MOSAIC_HALL && 
				game.getItem(GameEntities.ITEM_PHIAL).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response if the player is poisoning the wine
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult poisonWine(Game game, Player player) {
		game.addMessage("OK", true, false);
		game.getItem(GameEntities.FLAG_WINE_POISONED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 1 May 2026 - Created File
 * 3 May 2026 - Added responses
 */