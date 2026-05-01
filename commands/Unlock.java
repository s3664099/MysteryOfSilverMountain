/*
Title: Mystery of Silver Mountain Unlock Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 1 May 2026
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
	}
	
	 /**
     * Generate a response to a Unlock action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeUnlock() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
	
		return result;
	}
}

/* 1 May 2026 - Created File
 */
