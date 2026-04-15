/*
Title: Mystery of Silver Mountain Eat Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 15 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import game.Game;
import game.Player;

public class Eat {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /**
     * Creates a {@code EAT} handler for executing a eat command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Eat(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
    /**
     * Validates whether an eat action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeEat() {
		game.addMessage("You can't eat that!", true, false);
		ActionResult result = new ActionResult(game,player,true);
				
		return result;
	}
}

/* 15 April 2026 - Created File
 */
