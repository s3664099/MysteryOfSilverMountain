/*
Title: Mystery of Silver Mountain Turn Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 28 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Turn {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Turn} handler for executing a turn action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Turn(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a turn action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeTurn() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isTurnWaterWheel(player.getRoom(),command.getNounNumber())) {
			result = turnWaterWheel(game,player);
		} else if (isTurnHandle(player.getRoom(),command.getNounNumber())) {
			result = turnHandle(game,player);
		}

		return result;
	}
	
	/**
	 * Returns true if the player is turning the waterwheel
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isTurnWaterWheel(int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_WATERWHEEL &&
				nounNumber == GameEntities.ITEM_WHEEL;
	}
	
    /**
     * Executes a response if the player is turning the water wheel
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult turnWaterWheel(Game game,Player player) {
		game.addMessage("It goes round.", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is turning the handle
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isTurnHandle(int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_SLUICE_GATES &&
				nounNumber == GameEntities.ITEM_HANDLE;
	}
	
    /**
     * Executes a response if the player turning the handle
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult turnHandle(Game game,Player player) {
		game.addMessage("The gates open, the room empties.", true, false);
		game.getItem(GameEntities.FLAG_SHEILD_REVEALED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 27 March 2026 - Created file
 * 28 March 2026 - Added action responses
 */
