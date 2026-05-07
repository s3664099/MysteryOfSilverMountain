/*
Title: Mystery of Silver Mountain Break Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 7 May 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Break {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code BREAK} handler for executing a break command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Break(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a cross action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeBreak() {
		game.addMessage("You are not strong enough.", true, false);
		ActionResult result = new ActionResult(game,player,true);
			
			if (isBreakDoor(player.getRoom(),command.getNounNumber())) {
				result = breakDoor(game,player);
			}
		
		return result;
	}
	
	/**
	 * Returns true if the player is attempting to break the door in the corridor
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isBreakDoor(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_CORRIDOR &&
				nounNumber == GameEntities.ITEM_DOOR;
	}
	
    /**
     * Executes a response if the player is attempting to break the door in the corridor
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult breakDoor(Game game, Player player) {
		game.addMessage("How?", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 7 May 2026 - Created File
 */
