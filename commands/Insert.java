/*
Title: Mystery of Silver Mountain Insert Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 7 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Insert {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code INSERT} handler for executing an insert command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Insert(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an insert action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeInsert() {
		game.addMessage("You cannot Insert that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		return result;
	}
}

/* 9 April 2026 - Created file
 */
