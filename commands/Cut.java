/*
Title: Mystery of Silver Mountain Cut Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 22 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Cut {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code CUT} handler for executing a cut command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Cut(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;	
	}
	
    /**
     * Validates whether a cut action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeCut() {
		game.addMessage("How?", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isCutWebs(player.getRoom(),command.getNounNumber())) {
			result = new Swing(game,player,command).executeSwing();
		}
		return result;
	}
	
	private boolean isCutWebs(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_COBWEB &&
				nounNumber == GameEntities.ITEM_COBWEBS;
	}
}

/* 21 April 2026 - Created File
 * 22 April 2026 - Added response & Javadocs
 */
