/*
Title: Mystery of Silver Mountain Poison Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 1 May 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Poison {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
		
    /**
     * Creates a {@code Poison} handler for executing a poison action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Poison(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
	}
	
	 /**
     * Generate a response to a Poison action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executePoison() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
	
		return result;
	}
	//2990 IF (B=67 OR B=68) AND C(9)=0 AND R=49 THEN R$="OK": F(47)=1
}

/* 1 May 2026 - Created File
 */