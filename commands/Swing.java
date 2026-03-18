/*
Title: Mystery of Silver Mountain Swing Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 18 March 2026
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
		} else if (isSwingAxeInCorridor(game, player.getRoom(),command.getNounNumber())) {
			result = swingAxeInCorridor(game,player);
		} else if (isSwingAxeInWellBottom(game, player.getRoom(), command.getNounNumber())) {
			result = swingAxeInWellBottom(game,player);
		} else if (isSwingSwordOrAxe(game, command.getNounNumber())) {
			result = swingSwordOrAxe(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is swinging the sword in the cobwebs room
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSwingSwordInCobweb(Game game,int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_SWORD && roomNumber == GameEntities.ROOM_COBWEB &&
				game.getItem(GameEntities.ITEM_SWORD).getItemLocation() == GameEntities.ROOM_CARRYING;
	}

    /**
     * Executes a response if the player swings the sword in the cobwebs room
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult swingSwordInCobweb(Game game, Player player) {
		game.addMessage("You cleared the webs", true, false);
		game.getItem(GameEntities.FLAG_COBWEBS).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is swinging the axe in the corridor
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSwingAxeInCorridor(Game game,int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_AXE && roomNumber == GameEntities.ROOM_CORRIDOR &&
				game.getItem(GameEntities.ITEM_AXE).getItemLocation() == GameEntities.ROOM_CARRYING;
	}

    /**
     * Executes a response if the player swings the axe in the corridor
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult swingAxeInCorridor(Game game, Player player) {
		game.addMessage("The door broke", true, false);
		game.getRoom(GameEntities.ROOM_CORRIDOR).openExit(GameEntities.SOUTH);
		game.getRoom(GameEntities.ROOM_DUNGEONS).openExit(GameEntities.NORTH);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is swinging the axe in the well bottom
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSwingAxeInWellBottom(Game game,int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_AXE && roomNumber == GameEntities.ROOM_WELL_BOTTOM &&
				game.getItem(GameEntities.ITEM_AXE).getItemLocation() == GameEntities.ROOM_CARRYING;
	}

    /**
     * Executes a response if the player swings the axe in the well bottom
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult swingAxeInWellBottom(Game game, Player player) {
		game.addMessage("You broke through", true, false);
		game.getRoom(GameEntities.ROOM_WELL_BOTTOM).openExit(GameEntities.NORTH);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is swinging the axe or the sword
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSwingSwordOrAxe(Game game, int nounNumber) {
		return (nounNumber == GameEntities.ITEM_AXE &&
				game.getItem(GameEntities.ITEM_AXE).getItemLocation() == GameEntities.ROOM_CARRYING) ||
				(nounNumber == GameEntities.ITEM_SWORD &&
				game.getItem(GameEntities.ITEM_SWORD).getItemLocation() == GameEntities.ROOM_CARRYING);
	}
	
    /**
     * Executes a response if the player swings the axe or the sword
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult swingSwordOrAxe(Game game,Player player) {
		game.addMessage("Thwack!", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 15 March 2026 - Created files
 * 17 March 2026 - Started added responses to actions
 * 18 March 2026 - Completed adding response to actions
 * 				 - Added javadocs
 */
