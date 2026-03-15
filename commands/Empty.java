/*
Title: Mystery of Silver Mountain Empty Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 15 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Empty {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code EMPTY} handler for executing a empty command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Empty(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an empty action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeEmpty() {
		game.addMessage("You cannot Empty that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		return result;
	}
	
	//2520 IF B=16 THEN B=22:GOSUB 2450 - Executes a water
	//2530 IF H=499 THEN R$="WHERE?"
}
/* 15 March 2026 - Created class
*/