/*
Title: Mystery of Silver Mountain Burn Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 27 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Burn {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code BURN} handler for executing a burn action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Burn(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a burn action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeBurn() {
		game.addMessage("It doesn't work.", true, false);
		ActionResult result = new ActionResult(game,player,true);
				
		return result;
	}
}

/* 27 April 2026 - Created File
 */
