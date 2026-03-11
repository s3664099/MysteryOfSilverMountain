/*
Title: Mystery of Silver Mountain Fill Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 11 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Fill {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code FILL} handler for executing a fill command
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Fill(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an fill action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeFill() {
		game.addMessage("You cannot Fill that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isFillBucketOrJugInLake(game,player.getRoom(),command.getNounNumber())) {
			result = fillBucketOrJugInLake(game,player);
		} else if (isFillJugInValley(game,player.getRoom(),command.getNounNumber())) {
			result = fillJugInValley(game,player);
		} else if (isFillBucketInValley(game,player.getRoom(),command.getNounNumber())) {
			result = fillBucketInValley(game,player);
		}
				
		return result;
	}
	
	/**
	 * Returns true if the player filling the jug or the bucket in the lake
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isFillBucketOrJugInLake(Game game, int roomNumber, int nounNumber) {
		return ((nounNumber == GameEntities.ITEM_JUG &&
				game.getItem(GameEntities.ITEM_JUG).getItemLocation() == GameEntities.ROOM_CARRYING) ||
				(nounNumber == GameEntities.ITEM_BUCKET &&
				game.getItem(GameEntities.ITEM_JUG).getItemLocation() == GameEntities.ROOM_CARRYING)) &&
				(roomNumber == GameEntities.ROOM_ROUGH_WATER || roomNumber == GameEntities.ROOM_MIDDLE_LAKE);
	}
	
    /**
     * Executes a response if the player if filling the jug or the bucket in the lake
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult fillBucketOrJugInLake(Game game,Player player) {
		game.addMessage("You capsized!", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player filling the jug in the valley
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isFillJugInValley(Game game,int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_VALLEY_BOTTOM &&
				nounNumber == GameEntities.ITEM_JUG &&
				game.getItem(GameEntities.ITEM_JUG).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response if the player if filling the jug in the valley
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult fillJugInValley(Game game,Player player) {
		game.addMessage("It is now fill", true, false);
		game.getItem(GameEntities.FLAG_JUG_FULL).setItemFlag(1);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the player filling the bucket in the valley
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isFillBucketInValley(Game game,int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_VALLEY_BOTTOM &&
				nounNumber == GameEntities.ITEM_BUCKET &&
				game.getItem(GameEntities.ITEM_BUCKET).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response if the player if filling the bucket in the valley
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult fillBucketInValley(Game game,Player player) {
		game.addMessage("It leaks out!", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 10 March 2026 - Created File
 * 11 March 2026 - Completed responses
 */
