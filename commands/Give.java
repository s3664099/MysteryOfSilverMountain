/*
Title: Mystery of Silver Mountain Give Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 12 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/
package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Give {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Drop} handler for moving items from the users inventory
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Give(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeGive() {
		
		ActionResult result = new ActionResult(game,player,false);
		
		return result;
	}
	
	//1750 IF R=64 THEN R$="HE GIVES IT BACK!"
	//
}

/* 12 February 2026 - Created Class
 */