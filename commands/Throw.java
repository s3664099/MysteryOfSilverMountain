/*
Title: Mystery of Silver Mountain Throw Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 29 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

TODO: Add check to see if the troll will let you cross
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
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
				
		return result;
	}
}

/* 30 March 2026 - Created File
 */
