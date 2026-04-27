/*
Title: Mystery of Silver Mountain Move Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 17 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Shift {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code MOVE} handler for executing a shift(move) action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Shift(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a shift (move) action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeMove() {
		game.addMessage("How?", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isMoveRubbleFromStalactites(player.getRoom(),command.getNounNumber())) {
			result = moveRubbleFromStalactites(game,player);
		} else if (isMoveRubbleFromTomb(player.getRoom(),command.getNounNumber())) {
			result = moveRubbleFromTomb(game,player);
		} else if (isMoveBricksInWell(player.getRoom(),command.getNounNumber())) {
			result = moveBricksInWell(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is moving the rubble in the stalactite room
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isMoveRubbleFromStalactites(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_STALACTITES &&
				nounNumber == GameEntities.ITEM_RUBBLE;
	}
	
    /**
     * Executes a response if the player is moving the rubble in the stalactite room
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult moveRubbleFromStalactites(Game game, Player player) {
		game.addMessage("You revealed a secret passage", true, false);
		game.getItem(GameEntities.FLAG_RUBBLE_BLOCKING).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is moving the rubble in the tomb
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isMoveRubbleFromTomb(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_TOMB &&
				nounNumber == GameEntities.ITEM_RUBBLE;		
	}
	
    /**
     * Executes a response if the player is moving the rubble in the tomb
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult moveRubbleFromTomb(Game game, Player player) {
		game.addMessage("You cannot move the rubble from here", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is moving the bricks in well
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isMoveBricksInWell(int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_WELL_BOTTOM &&
				nounNumber == GameEntities.ITEM_BRICKS;
	}
	
    /**
     * Executes a response if the player is moving the bricks in well
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult moveBricksInWell(Game game, Player player) {
		game.addMessage("They are wedged in!", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 15 April 2026 - Created File
 * 17 April 2026 - Added responses
 * 				 - Added JavaDocs
 */
