/*
Title: Mystery of Silver Mountain Swing Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 15 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Swing {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code SWING} handler for executing a swing command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Swing(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an swing action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeSwing() {
		game.addMessage("You cannot Swing that", true, false);
		ActionResult result = new ActionResult(game,player,true);		
		return result;
	}
	//- 	//2160 IF B=18 OR B=7 THEN GOSUB 2470 - Swing - Add to Use
	//2470 IF B=7 OR B=18 THEN R$="THWACK!"
	//2480 IF H=5818 THEN R$="YOU CLEARED THE WEBS": F(66)=1
	//2490 IF H=187 THEN R$="THE DOOR BROKE!": E$(18)="NS": E$(28)="NS"
	//2500 IF H=717 THEN R$="YOU BROKE THROUGH": E$(71)="N"
}

/* 15 March 2026 - Created files
 */
