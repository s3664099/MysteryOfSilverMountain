/*
Title: Mystery of Silver Mountain Feed Class
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

public class Feed {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code FEED} handler for executing a feed command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Feed(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an feed action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeFeed() {
		game.addMessage("You cannot feed that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		return result;
	}
	//2650 IF H=3859 OR H=3339 OR H=1241 OR H=2241 OR H=751 THEN R$="WITH WHAT?"
}

/* 22 March 2026 - Created File
 */
