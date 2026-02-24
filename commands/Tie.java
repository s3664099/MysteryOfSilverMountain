/*
Title: Mystery of Silver Mountain Tie Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 24 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/


package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Tie {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Tie} handler for executing a tie action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Tie(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a tie action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeTie() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isTyingSheetOrRope(command.getNounNumber(),player.getRoom(),game)) {
			result = tyingSheetOrRope(game,player);
		} else if (isTyingSheet(command.getNounNumber(),player.getRoom(),game)) {
			result = tyingSheet(game,player);
		} else if (isTyingRope(command.getNounNumber(),player.getRoom(),game)) {
			result = tyingRope(game,player);
		}
		
		return result;
	}
	
	private boolean isTyingSheetOrRope(int nounNumber,int roomNumber,Game game) {
		return ((nounNumber == GameEntities.ITEM_SHEET && 
				game.getItem(GameEntities.ITEM_SHEET).getItemLocation() == GameEntities.ROOM_CARRYING) ||
				(nounNumber == GameEntities.ITEM_ROPE && 
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_CARRYING)) &&
				roomNumber != GameEntities.ROOM_WELL;
	}
	
	private ActionResult tyingSheetOrRope(Game game,Player player) {
		game.addMessage("Nothing to tie it to!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isTyingSheet(int nounNumber,int roomNumber,Game game) {
		return (nounNumber == GameEntities.ITEM_SHEET && 
				game.getItem(GameEntities.ITEM_SHEET).getItemLocation() == GameEntities.ROOM_CARRYING) &&
				roomNumber != GameEntities.ROOM_WELL;
	}
	
	private ActionResult tyingSheet(Game game,Player player) {
		game.addMessage("It is tied!", true, false);
		game.getItem(GameEntities.ITEM_SHEET).setItemLocation(GameEntities.ROOM_WELL);
		game.getItem(GameEntities.FLAG_SHEET_TIED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	private boolean isTyingRope(int nounNumber,int roomNumber,Game game) {
		return (nounNumber == GameEntities.ITEM_ROPE && 
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_CARRYING) &&
				roomNumber != GameEntities.ROOM_WELL;
	}
	
	private ActionResult tyingRope(Game game,Player player) {
		game.addMessage("It is tied!", true, false);
		game.getItem(GameEntities.ITEM_ROPE).setItemLocation(GameEntities.ROOM_WELL);
		game.getItem(GameEntities.FLAG_ROPE_TIED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 23 February 2026 - Created Tie Class
 * 24 February 2026 - Completed Tie actions
 */