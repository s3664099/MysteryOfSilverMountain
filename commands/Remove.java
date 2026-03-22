/*
Title: Mystery of Silver Mountain Remove Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 11 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Remove {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code REMOVE} handler for executing a remove command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Remove(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an remove action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeRemove() {
		game.addMessage("You cannot remove that", true, false);
		ActionResult result = new ActionResult(game,player,true);
				
		return result;
	}
	//2610 IF (B=3 AND F(29)=1) THEN R$="TAKEN OFF": F(29)=0
	//2620 IF (B=20 AND F(51)=1) THEN R$="OK": F(51)=0
	//2630 IF B=36 OR B=50 THEN GOSUB 2950
}

/* 22 March 2026 - Created Class
 */
