/*
Title: Mystery of Silver Mountain Examine Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 1 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Examine {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates an {@code Examine} handler for retrieving details of an item
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Examine(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeExamine() {
		return new ActionResult(game,player,true);
	}
	
}

/* 1 February 2026 - Created file
*/