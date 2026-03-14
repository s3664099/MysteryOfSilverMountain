/*
Title: Mystery of Silver Mountain Water Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 14 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Water {
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code PLANT} handler for executing a water command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Water(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a water action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeWater() {
		game.addMessage("It does not grow!", true, false);
		ActionResult result = new ActionResult(game,player,true);
						
		return result;
	}
}

/* 14 March 2026 - Created File
 */
