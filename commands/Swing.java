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
import data.GameEntities;
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
		
		if (isSwingSwordInCobweb(game,player.getRoom(),command.getNounNumber())) {
			result = swingSwordInCobweb(game,player);
		} else if (isSwingSwordOrAxe(game, command.getNounNumber())) {
			result = swingSwordOrAxe(game,player);
		}
		
		return result;
	}
	
	private boolean isSwingSwordInCobweb(Game game,int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_SWORD && roomNumber == GameEntities.ROOM_COBWEB &&
				game.getItem(GameEntities.ITEM_SWORD).getItemLocation() == GameEntities.ROOM_CARRYING;
	}

	private ActionResult swingSwordInCobweb(Game game, Player player) {
		game.addMessage("You cleared the webs", true, false);
		game.getItem(GameEntities.FLAG_COBWEBS).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	//2490 IF H=187 THEN R$="THE DOOR BROKE!": E$(18)="NS": E$(28)="NS"
	//2500 IF H=717 THEN R$="YOU BROKE THROUGH": E$(71)="N"
	
	private boolean isSwingSwordOrAxe(Game game, int nounNumber) {
		return (nounNumber == GameEntities.ITEM_AXE &&
				game.getItem(GameEntities.ITEM_AXE).getItemLocation() == GameEntities.ROOM_CARRYING) ||
				(nounNumber == GameEntities.ITEM_SWORD &&
				game.getItem(GameEntities.ITEM_SWORD).getItemLocation() == GameEntities.ROOM_CARRYING);
	}
	
	private ActionResult swingSwordOrAxe(Game game,Player player) {
		game.addMessage("Thwack!", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 15 March 2026 - Created files
 * 17 March 2026 - Started added responses to actions
 */
