/*
Title: Mystery of Silver Mountain Fill Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 10 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Fill {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code FILL} handler for executing a fill command
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Fill(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an fill action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeFill() {
		game.addMessage("You cannot Fill that", true, false);
		ActionResult result = new ActionResult(game,player,true);
				
		return result;
	}
	
	//2200 IF B=16 OR B=6 THEN GOSUB 2380 - Fill - Add to Use
	//2380 IF (B=16 OR B=6) AND (R=41 OR R=51) THEN R$="YOU CAPSIZED!": F(56)=1
	//2390 IF H=6516 AND C(16)=0 THEN R$="IT IS NOW FULL": F(34)=1
	//2400 IF H=656 THEN R$="IT LEAKS OUT!"
}

/* 10 March 2026 - Created File
 */
