/*
Title: Mystery of Silver Mountain Bail Class
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

public class Bail {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code BAIL} handler for executing a bail command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Bail(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a bail action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeBail() {
		game.addMessage("You cannot do that", true, false);
		ActionResult result = new ActionResult(game,player,true);
				
		return result;
	}
}

/* 20 March 2026 - Created File
 */
