/*
Title: Mystery of Silver Mountain Insert Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 10 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Insert {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code INSERT} handler for executing an insert command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Insert(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an insert action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeInsert() {
		game.addMessage("You cannot Insert that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isInsertCoinsNone(game,command.getNounNumber())) {
			result = insertCoinsNone(game,player);
		}
		
		return result;
	}
	
	private boolean isInsertCoinsNone(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_COINS &&
				game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag() == 0;
	}
	
	private ActionResult insertCoinsNone(Game game, Player player) {
		game.addMessage("You do not have any", false, false);
		return new ActionResult(game,player,true);
	}
	
	//2810 IF H=5762 AND C(1)=0 AND F(44)>0 THEN GOSUB 3230
}

/* 9 April 2026 - Created file
 * 10 April 2026 - Added insert coins when you have none
 */
