/*
Title: Mystery of Silver Mountain Plant Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 13 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Plant {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code PLANT} handler for executing a plant command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Plant(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an plant action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executePlant() {
		game.addMessage("You cannot Fill that", true, false);
		ActionResult result = new ActionResult(game,player,true);
				
		return result;
	}
	
	//2420 IF B<>22 OR R<>15 THEN R$="DOES NOT GROW!":RETURN
	//2430 IF R$="OK":KET F(37)=1
}
/* 13 March 2026 - Created File
 */

