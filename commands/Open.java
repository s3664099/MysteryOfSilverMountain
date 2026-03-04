/*
Title: Mystery of Silver Mountain Open Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 4 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Open {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Give} handler for executing and open command
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Open(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an open action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeOpen() {
		game.addMessage("You cannot open that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isOpen(command.getNounNumber())) {
			result = new Examine(game,player,command).executeExamine();
		} else if (isOpenCupboard(player.getRoom(),command.getNounNumber())) {
			result = openCupboard(game,player);
		}
		
		return result;
	}
	
	private boolean isOpen(int nounNumber) {
		return nounNumber == GameEntities.ITEM_CHEST || nounNumber == GameEntities.ITEM_SACK;
	}
	
	private boolean isOpenCupboard(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_CUPBOARD && roomNumber == GameEntities.ROOM_KITCHEN;
	}
	
	private ActionResult openCupboard(Game game,Player player) {
		game.getItem(GameEntities.ITEM_PHIAL).setItemFlag(0);
		game.addMessage("Ok", true, false);
		return new ActionResult(game,player,true);
	}

	//2230 IF H=2030 THEN F(9)=0: R$="OK"
	//2240 IF H=6030 THEN R$="OK":KET F(3)=0
	//2250 IF H=2444 OR H=1870 THEN R$="YOU ARE NOT STRING ENOUGH"
	//2260 IF H=3756 THEN R$="A PASSAGE!": E$(37)="EW"
	//2270 IF H=5960 THEN GOSUB 3260
	//2280 IF H=6970 THEN R$="IT FALLS OFF ITS HINGES"
	//2290 IF H=4870 THEN R$="IT IS LOCKED"
	
	
}

/* 3 March 2026 - Created File
 * 4 March 2026 - Started added responses
 */
