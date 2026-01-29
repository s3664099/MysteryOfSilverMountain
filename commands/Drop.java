/*
Title: Mystery of Silver Mountain Drop Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 29 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Drop {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates an {@code Take} handler for moving items into the users inventory
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Drop(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeDrop() {
		ActionResult result = new ActionResult(game,player,true);
		
		if (isPlayerCarryingItem(game,command.getNounNumber())) {
			result = dropItem(game,player,command.getNounNumber());
		} else {
			result = playerDoesNotHaveItem(game,player);
		}
		
		return result;
	}
	
	private boolean isPlayerCarryingItem(Game game, int nounNumber) {
		return game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult playerDoesNotHaveItem(Game game, Player player) {
		game.addMessage("You do not have that!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private ActionResult dropItem(Game game, Player player, int nounNumber) {
		game.getItem(nounNumber).setItemLocation(player.getRoom());
		game.addMessage("Done", true,true);
		return new ActionResult(game,player,true);
	}
	
	
	

	//2750 IF H=418 OR H=518 THEN R$="YOU DROWNED!": F(56)=1
	//2760 IF B=8 AND F(30)=1 THEN C(2)=R
	//2770 IF B=16 AND F(34)=1 THEN R$="YOU LOST THE WATER!": F(34)=0
	//2780 IF B=2 AND F(30)=1 THEN F(30)=0
	//2790 RETURN
}

/*
 * 28 January 2026 - Created file
 * 29 January 2026 - Added drop functionality
 */