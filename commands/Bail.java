/*
Title: Mystery of Silver Mountain Bail Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 30 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import game.Game;
import game.Player;

public class Bail {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /**
     * Creates a {@code BAIL} handler for executing a bail command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Bail(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
    /**
     * Validates whether a bail action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeBail() {
		game.addMessage("How?", true, false);
		ActionResult result = new ActionResult(game,player,true);
				
		return result;
	}
}

/* 20 March 2026 - Created File
 */
