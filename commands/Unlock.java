/*
Title: Mystery of Silver Mountain Unlock Class
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

public class Unlock {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;

    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Unlock} handler for executing a unlock action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Unlock(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a Unlock action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeUnlock() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isUnlockWizardDoor(game,player.getRoom(),command.getNounNumber())) {
			result = unlockWizardDoor(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is unlocking the door in the wizard's lair
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isUnlockWizardDoor(Game game, int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_DOOR && roomNumber == GameEntities.ROOM_WIZARD_LAIR &&
				game.getItem(GameEntities.ITEM_KEY).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response if the player is unlocking the door in the wizard's lair
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult unlockWizardDoor(Game game, Player player) {
		game.addMessage("The key turns!", true, false);
		game.getItem(GameEntities.FLAG_DOOR).setItemFlag(1);
		return new ActionResult(game,player,true);
	}

}

/* 1 May 2026 - Created File
 * 3 May 2026 - Added responses
 */
