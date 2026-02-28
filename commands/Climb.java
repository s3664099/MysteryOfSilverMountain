/*
Title: Mystery of Silver Mountain Climb Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 27 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Climb {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Tie} handler for executing a climb action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Climb(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a climb action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeClimb() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isClimbVine(player.getRoom(),command.getNounNumber(),game)) {
			result = climbVine(game,player);
		} else if (isNotAttached(game,command.getNounNumber(),player.getRoom())) {
			result = notAttached(game,player);
		} else if (isAtTheTop(game,command.getNounNumber(),player.getRoom())) {
			result = atTheTop(game,player);
		} else if (isGoingDown(game,command.getNounNumber(),player.getRoom())) {
			result = goingDown(game,player);
		} else if (isClimbingSheet(game,command.getNounNumber(),player.getRoom())) {
			result = climbingSheet(game,player);
		} else if (isClimbingRope(game,command.getNounNumber(),player.getRoom())) {
			result = climbingRope(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is climbing the vine in the garden
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isClimbVine(int roomNumber, int nounNumber, Game game) {
		return roomNumber == GameEntities.ROOM_GARDEN && nounNumber == GameEntities.ITEM_VINE &&
				game.getItem(GameEntities.FLAG_VINE_CLIMBABLE).getItemFlag()==1;
	}
	
    /**
     * Executes the climb vine action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult climbVine(Game game, Player player) {
		game.addMessage("All right", true, false);
		player.setRoom(GameEntities.ROOM_INSCRIBED_CAVERN);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true on the following conditions:
	 * The noun is the rope and the player is either carrying the rope, or in the room as the rope
	 * The noun is the sheet and the player is either carrying the sheet, or in the room as the sheet
	 * And the room is not the pinnacle, the well, or the well bottom
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isNotAttached(Game game,int nounNumber,int roomNumber) {
		return (nounNumber== GameEntities.ITEM_ROPE && 
				(game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_CARRYING ||
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == roomNumber)) ||
				(nounNumber== GameEntities.ITEM_SHEET && 
				(game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_CARRYING ||
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == roomNumber)) &&
				(roomNumber != GameEntities.ROOM_PINNACLE && roomNumber != GameEntities.ROOM_WELL &&
				roomNumber != GameEntities.ROOM_WELL_BOTTOM);
	}
	
    /**
     * Executes climbing the rope or the sheet in the incorrect location
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult notAttached(Game game,Player player) {
		game.addMessage("Not attached to anything!", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player at the pinnacle
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isAtTheTop(Game game,int nounNumber,int roomNumber) {
		return nounNumber == GameEntities.ITEM_ROPE && roomNumber == GameEntities.ROOM_PINNACLE &&
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_PINNACLE;
	}
	
    /**
     * Executes climbing the rope at the pinnacle
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult atTheTop(Game game,Player player) {
		game.addMessage("You are at the top!", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player at the well, and the rope is tied
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isGoingDown(Game game,int nounNumber,int roomNumber) {
		return nounNumber == GameEntities.ITEM_ROPE && roomNumber == GameEntities.ROOM_WELL &&
				game.getItem(GameEntities.FLAG_ROPE_TIED).getItemFlag() == 1;
	}
	
    /**
     * Executes climbing the rope at the well
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult goingDown(Game game,Player player) {
		game.addMessage("Going down.", true, false);
		player.setRoom(GameEntities.ROOM_WELL_BOTTOM);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player at the well, and the sheet is tied
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isClimbingSheet(Game game,int nounNumber,int roomNumber) {
		return nounNumber == GameEntities.ITEM_SHEET && roomNumber == GameEntities.ROOM_WELL &&
				game.getItem(GameEntities.FLAG_SHEET_TIED).getItemFlag() == 1;
	}
	
    /**
     * Executes climbing the sheet at the well
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult climbingSheet(Game game,Player player) {
		game.addMessage("It is torn.", true, false);
		game.getItem(GameEntities.ITEM_SHEET).setItemLocation(GameEntities.ROOM_DESTROYED);
		game.getItem(GameEntities.FLAG_SHEET_TIED).setItemFlag(0);
		player.setRoom(GameEntities.ROOM_WELL_BOTTOM);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player at the well bottom, and the rope is tied
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isClimbingRope(Game game,int nounNumber,int roomNumber) {
		return nounNumber == GameEntities.ITEM_ROPE && roomNumber == GameEntities.ROOM_WELL_BOTTOM &&
				game.getItem(GameEntities.FLAG_ROPE_TIED).getItemFlag() == 1;
	}
	
    /**
     * Executes climbing the rope at the well bottom
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult climbingRope(Game game,Player player) {
		game.addMessage("It falls down - bump.", true, false);
		game.getItem(GameEntities.ITEM_SHEET).setItemLocation(GameEntities.ROOM_WELL_BOTTOM);
		game.getItem(GameEntities.FLAG_ROPE_TIED).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
}

/* 25 February 2026 - Created file
 * 27 February 2026 - Added branches and started climb actions
 * 28 February 2026 - Completed actions & added javadocs
 */
