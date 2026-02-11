/*
Title: Mystery of Silver Mountain Take Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.7
Date: 11 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Take {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Take} handler for moving items into the users inventory
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Take(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a take is possible based on the parsed command,
     * player state, and room conditions.
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeTake() {
		
		ActionResult result = new ActionResult(game,player,true);
		
		if (isTakingWater(player.getRoom(),command.getNounNumber())) {
			result = takingWater(game,player);
		} else if (isTakingWaterInLake(player.getRoom(),command.getNounNumber())) {
			result = takingWaterInLake(game,player);
		} else if (isTakingSacks(player.getRoom(),command.getNounNumber())) {
			result = takingSacks(game,player);
		} else if (isHorseShoeNailedOn(player.getRoom(),command.getNounNumber(),game)) {
			result = horseShoeNailedOn(game,player);
		} else if (isCarryingTooMuch()) {
			result = carryingTooMuch(game,player);
		} else if (isItemCarriable(command.getNounNumber())) {
			result = itemNotCarriable(game,player);
		} else if (isItemAlreadyCarried(command.getNounNumber())) {
			result = itemAlreadyCarried(game,player);
		} else if (isItemNotPresent(command.getNounNumber(),player.getRoom())) {
			result = itemNotPresent(game,player);
		} else if (isItemNotTakeable(command.getNounNumber())) {
			result = itemNotTakeable(game,player,command.getSplitTwoCommand()[1]);
		} else if (isItemTaken(game,command.getNounNumber(),player.getRoom())) {
			result = itemTaken(game,player,command.getNounNumber());
		}
				
		return result;
	}
	
	/**
	 * Returns true if the command is taking water
	 */
	private boolean isTakingWater(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_VALLEY_BOTTOM && nounNumber == GameEntities.ITEM_WATER;
	}
	
    /**
     * Executes a response to taking the water
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult takingWater(Game game, Player player) {
		game.addMessage("How?", true, true);
		return new ActionResult(game,player, true);
	}
	
	/**
	 * Returns true if the command is taking water in the lake
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isTakingWaterInLake(int roomNumber,int nounNumber) {
		return (roomNumber == GameEntities.ROOM_ROUGH_WATER || roomNumber == GameEntities.ROOM_MIDDLE_LAKE) &&
				nounNumber == GameEntities.ITEM_WATER;
	}
	
    /**
     * Executes a response to taking the water in the lake
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult takingWaterInLake(Game game,Player player) {
		game.addMessage("You capsized", true, true);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is taking sacks
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isTakingSacks(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_SACKS && nounNumber == GameEntities.ITEM_SACK;
	}
	
    /**
     * Executes a response to taking the sacks
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult takingSacks(Game game,Player player) {
		game.addMessage("Too heavy!", true, true);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is taking the horseshoe when it is nailed on
	 * 
     * @param game the current game state
 	 * @param nounNumber the value of the noun entered
	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isHorseShoeNailedOn(int roomNumber, int nounNumber, Game game) {
		return roomNumber == GameEntities.ROOM_STABLE && nounNumber == GameEntities.ITEM_HORSESHOE &&
				game.getItem(GameEntities.FLAG_HORSESHOE_NAILED_ON).getItemFlag() == 0;
	}
	
    /**
     * Executes a response to taking a horseshoe when nailed on
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult horseShoeNailedOn(Game game, Player player) {
		game.addMessage("It is firmly nailed on!", true, true);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is carrying too much
	 *
	 * @return boolean
	 */
	private boolean isCarryingTooMuch() {
		return game.countItemsCarrying()>Constants.INVENTORY_SPACE; 
	}
	
    /**
     * Executes a response to the player carrying too much
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult carryingTooMuch(Game game,Player player) {
		game.addMessage("You cannot carry any more.", true, true);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the item is not carryable
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemCarriable(int nounNumber) {
		return nounNumber>=Constants.MAX_CARRIABLE_ITEMS; 
	}
	
    /**
     * Executes a response is the item is not carryable
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult itemNotCarriable(Game game, Player player) {
		game.addMessage("It is not possible to take that.", true, true);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the item is being carried
	 * 
	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isItemAlreadyCarried(int nounNumber) {
		return game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes a response is the item is being carried
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult itemAlreadyCarried(Game game, Player player) {
		game.addMessage("You already have it",true,true);
		return new ActionResult(game,player,true);
	}
	
	
	/**
	 * Returns true if the item is not in the room
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isItemNotPresent(int nounNumber,int roomNumber) {
		return game.getItem(nounNumber).getItemLocation() != roomNumber;
	}
	
    /**
     * Executes a response is the item is not in the room
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult itemNotPresent(Game game, Player player) {
		game.addMessage("It is not here!",true,true);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the item is not takeable at that time
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemNotTakeable(int nounNumber) {
		return game.getItem(nounNumber).getItemFlag() ==1;
	}
	
    /**
     * Executes a response is the item is not takeable at that time
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult itemNotTakeable(Game game, Player player,String noun) {
		game.addMessage("What "+noun+"?", isCarryingTooMuch(), isCarryingTooMuch());
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the item is taken
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isItemTaken(Game game,int nounNumber,int roomNumber) {
		return game.getItem(nounNumber).getItemLocation() == roomNumber &&
				game.getItem(nounNumber).getItemFlag() == 0;
	}
	
    /**
     * Executes a response is the item is taken
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult itemTaken(Game game, Player player, int nounNumber) {
		
		game.addMessage("You have the "+game.getItem(nounNumber).getItemName(), true, true);
		game.getItem(nounNumber).setItemLocation(GameEntities.ROOM_CARRYING);
		
		if (isItemApples(nounNumber)) {
			game.getItem(GameEntities.ITEM_APPLE).setItemLocation(GameEntities.ROOM_DESTROYED);
		}
		
		if (isItemApple(nounNumber)) {
			game.getItem(GameEntities.ITEM_APPLES).setItemLocation(GameEntities.ROOM_CARRYING);
		}
		
		if (haveSpecialItems()) {
			game.getItem(GameEntities.FLAG_FORCES).setItemFlag(1);
		} else {
			game.getItem(GameEntities.FLAG_FORCES).setItemFlag(0);
		}
		
		if (isItemBoat(game,command.getNounNumber())) {
			game.getItem(GameEntities.ITEM_SHEET).setItemLocation(GameEntities.ROOM_CARRYING);
		}
		
		if (isItemSheet(command.getNounNumber())) {
			game.getItem(GameEntities.FLAG_BOAT_POWER).setItemFlag(0);
		}
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the items are apples
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemApples(int nounNumber) {
		return nounNumber == GameEntities.ITEM_APPLE;
	}
	
	/**
	 * Returns true if the item is an apple
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemApple(int nounNumber) {
		return nounNumber == GameEntities.ITEM_APPLES;
	}
	
	/**
	 * Returns true if the item is the boat
	 * 
     * @param game the current game state
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemBoat(Game game,int nounNumber) {
		return game.getItem(GameEntities.FLAG_BOAT_POWER).getItemLocation() == 1 &&
				nounNumber == GameEntities.ITEM_BOAT;
	}
	
	/**
	 * Returns true if the item is the sheet
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemSheet(int nounNumber) {
		return nounNumber == GameEntities.ITEM_SHEET;
	}
	
	/**
	 * Returns true if the player is carrying the special items
	 * 
	 * @return boolean
	 */
	private boolean haveSpecialItems() {
		return game.getItem(GameEntities.ITEM_HORSESHOE).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.ITEM_SHIELD).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.ITEM_RINGS).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
}

/* 22 January 2026 - Created File
 * 23 January 2026 - Added parsed command
 * 24 January 2026 - Added taking water
 * 24 January 2026 - Added sacks & horseshoe
 * 25 January 2026 - Added inventory limitation
 * 27 January 2026 - Added other limitations and the successful taking
 * 28 January 2026 - Completed the take
 * 11 February 2026 - Updated JavaDocs
 */
