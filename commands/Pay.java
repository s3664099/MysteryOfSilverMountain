/*
Title: Mystery of Silver Mountain Pay Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 9 May 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Pay {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code PAY} handler for executing a pay command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Pay(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a pay action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executePay() {
		game.addMessage("You cannot Cross that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		return result;
	}
	
	//3100 IF H=7549 OR H=7649 THEN R$="WHAT WITH?"
	//3110 IF B=1 OR B=62 THEN GOSUB 1750
}

/* 9 May 2026 - Created File
 */
