/*
Title: Mystery of Silver Mountain Use Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.7
Date: 29 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Use {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Use} handler for executing a use item action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Use(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a use action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeUse() {
		ActionResult result = new ActionResult(game,player,true);
		game.addMessage("Nothing happens", true, false);
		
		if (isUseSheet(game,player.getRoom(),command.getNounNumber())) {
			result = useSheet(game,player);
		} else if (isGive(command.getNounNumber())) {
			result = new Give(game,player,command).executeGive();
		} else if (isUseBucket(game,player.getRoom(),command.getNounNumber())) {
			result = useBucket(game,player);
		} else if (isUseJug(game,player.getRoom(),command.getNounNumber())) {
			result = useJug(game,player);
		} else if (isUsePlanks(command.getNounNumber())) {
			result = new Drop(game,player,command).executeDrop();
		} else if (isUseBucketOrJug(game,command.getNounNumber())) {
			result = new Fill(game,player,command).executeFill();
		} else if (isUseAxeOrSword(command.getNounNumber())) {
			result = new Swing(game,player,command).executeSwing();		
		} else if (isUseReeds(command.getNounNumber())) {
			result = new Rig(game,player,command).executeRig();
		} else if (isUsePlate(game,command.getNounNumber())) {
			result = new Show(game,player,command).executeShow();
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is using a sheet
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUseSheet(Game game, int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_EDGE_LAKE &&
				nounNumber == GameEntities.ITEM_SHEET &&
				game.getItem(GameEntities.ITEM_SHEET).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response if the player uses a sheet
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult useSheet(Game game,Player player) {
		game.addMessage("OK", true, false);
		game.getItem(GameEntities.FLAG_BOAT_POWER).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the action is a give action
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isGive(int nounNumber) {
		return nounNumber == GameEntities.ITEM_COINS || nounNumber == GameEntities.ITEM_COIN ||
				nounNumber == GameEntities.ITEM_APPLES || nounNumber == GameEntities.ITEM_APPLE ||
				nounNumber == GameEntities.ITEM_BREAD;
		
	}
	
	/**
	 * Returns true if the player is using a bucket
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUseBucket(Game game,int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_ROUGH_WATER &&
				nounNumber == GameEntities.ITEM_BUCKET &&
				game.getItem(GameEntities.ITEM_BUCKET).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response if the player uses a bucket
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult useBucket(Game game,Player player) {
		game.getItem(GameEntities.FLAG_BOAT_FLAG).setItemFlag(1);
		game.addMessage("You have kept afloat.", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is using a jug
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUseJug(Game game,int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_ROUGH_WATER &&
				nounNumber == GameEntities.ITEM_JUG &&
				game.getItem(GameEntities.ITEM_JUG).getItemLocation() == GameEntities.ROOM_CARRYING;
	}

    /**
     * Executes a response if the player uses a jug
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult useJug(Game game,Player player) {
		game.getItem(GameEntities.FLAG_BOAT_FLAG).setItemFlag(1);
		game.addMessage("It is not big enough.", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is using a plank
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isUsePlanks(int nounNumber) {
		return nounNumber == GameEntities.ITEM_PLANKS;
	}
	
	/**
	 * Returns true if the player is using the bucket or the jug
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUseBucketOrJug(Game game, int nounNumber) {
		return (nounNumber == GameEntities.ITEM_JUG &&
				game.getItem(GameEntities.ITEM_JUG).getItemLocation() == GameEntities.ROOM_CARRYING) ||
				(nounNumber == GameEntities.ITEM_BUCKET &&
				game.getItem(GameEntities.ITEM_JUG).getItemLocation() == GameEntities.ROOM_CARRYING);
	}

	/**
	 * Returns true if the player is using the sword or the axe
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUseAxeOrSword(int nounNumber) {
		return nounNumber == GameEntities.ITEM_AXE || nounNumber == GameEntities.ITEM_SWORD;
	}
	
	/**
	 * Returns true if the player is using the reed
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUseReeds(int nounNumber) {
		return nounNumber == GameEntities.ITEM_REEDS;
	}
	
	/**
	 * Returns true if the player is using the plate
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isUsePlate(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_PLATE &&
				game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
}

/* 28 February 2026 - Created file
 * 1 March 2026 - Started adding action responses
 * 2 March 2026 - Added use jug and noted other commands use is used
 * 3 March 2026 - completed other commands
 * 				- Added JavaDocs
 * 11 March 2026 - Added fill response for use
 * 17 March 2026 - Added swing functions
 * 10 April 2026 - Added rig functions
 * 29 April 2026 - Added use plate
 */
