/*
Title: Mystery of Silver Mountain Tie Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 23 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/


package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Tie {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Tie} handler for executing a tie action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Tie(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a tie action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeTie() {
		ActionResult result = new ActionResult(game,player,false);
		return result;
	}
}

/* 23 February 2026 - Created Tie Class
*/