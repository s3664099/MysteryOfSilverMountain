/*
Title: Mystery of Silver Mountain Turn Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 27 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Turn {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Turn} handler for executing a turn action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Turn(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a turn action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeTurn() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
	
		return result;
	}
	//2670 IF H=2340 THEN R$="IT GOES ROUND"
	//2680 IF H=2445 THEN R$="UIF HBUFT PQFO, UIF QPPM FNQUJFT": F(33)=1:GOSUB 2460
}

/* 27 March 2026 - Created file
 */
