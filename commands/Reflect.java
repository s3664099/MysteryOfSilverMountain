/*
Title: Mystery of Silver Mountain Reflect Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 6 May 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Reflect {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code REFLECT} handler for executing a reflect command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Reflect(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a reflect action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeReflect() {
		game.addMessage("Nothing happens.", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isInWizardLair(player.getRoom())) {
			result = inWizardLair(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is in the wizard's lair
	 * 
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isInWizardLair(int roomNumber) {
		return roomNumber == GameEntities.ROOM_WIZARD_LAIR;
	}
	
    /**
     * Executes a response if the player is in the wizard's lair
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult inWizardLair(Game game, Player player) {
		game.addMessage("How?", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 6 May 2026 - Created File
 */
