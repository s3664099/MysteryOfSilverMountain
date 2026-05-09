/*
Title: Mystery of Silver Mountain Pay Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 9 May 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Pay {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code PAY} handler for executing a pay command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Pay(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a pay action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executePay() {
		game.addMessage("The payment is not accepted.", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isPayTroll(player.getRoom(),command.getNounNumber())) {
			result = payTroll(game,player);
		} else if (isPayCoins(command.getNounNumber())) {
			result = new Give(game,player,command).executeGive();
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is paying the troll
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isPayTroll(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_TROLL && 
				(roomNumber == GameEntities.ROOM_BRIDGE_EAST ||
				 roomNumber == GameEntities.ROOM_BRIDGE_WEST);
	}
	
    /**
     * Executes a response if the player is paying the troll
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult payTroll(Game game, Player player) {
		game.addMessage("What with?", false, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is paying coins
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isPayCoins(int nounNumber) {
		return nounNumber == GameEntities.ITEM_COIN ||
				nounNumber == GameEntities.ITEM_COINS;
	}
}

/* 9 May 2026 - Created File
 */
