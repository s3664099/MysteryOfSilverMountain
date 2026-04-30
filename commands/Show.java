/*
Title: Mystery of Silver Mountain Show Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 30 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Show {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code SHOW} handler for executing a show action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Show(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a show action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeShow() {
		game.addMessage("Ok, done.", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isShowPlateInLair(player.getRoom(),command.getNounNumber(),game)) {
			result = showPlateInLair(game,player);
		} else if (isHoldStone(command.getNounNumber())) {
			result = new Take(game,player,command).executeTake();
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is showing the plate in the wizard's lair
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isShowPlateInLair(int roomNumber, int nounNumber, Game game) {
		return roomNumber == GameEntities.ROOM_WIZARD_LAIR &&
				(nounNumber == GameEntities.ITEM_PLATE || nounNumber == GameEntities.ITEM_SILVER_PLATE) &&
				game.getItem(GameEntities.ITEM_PLATE).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response if the player is showing the plate in the wizard's lair
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult showPlateInLair(Game game, Player player) {
		game.getItem(GameEntities.FLAG_WIZARD_DEAD).setItemFlag(1);
		game.addMessage("You reflected the wizard's glare! he is dead. ", true, false);
	
	
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is holding the Stone of Destiny
	 * @return boolean
	 */
	private boolean isHoldStone(int nounNumber) {
		return nounNumber == GameEntities.ITEM_STONE_DESTINY;
	}
}

/* 27 April 2026 - Created File
 * 29 April 2026 - Added response to show plate
 * 30 April 2026 - Added hold stone
 * 				 - Added JavaDocs
 */
