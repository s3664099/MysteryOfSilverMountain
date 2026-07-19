/*
Title: Mystery of Silver Mountain Take Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.13
Date: 19 July 2026
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
	
	/** flag indicating whether command was a success **/
	private boolean success = false;
	
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
	
	//Taking Apples
	//	If player has none, has one apple - check
	//	if player has one, then has more - check
	//	Reduce on tree, increase carrying - check
	//  Also why change the appearance on the tree
	//
	//	Drop all is carrying multiple, one if carrying only one.
	//  Feed reduced by one (unless use plural then takes all, like troll with coins)
	//  Eat - only one at a time
	// public static final int FLAG_NUMBER_APPLES_ON_TREE = 71;
	// public static final int FLAG_NUMBER_APPLES_IN_HAND = 72;
	// Do same with coins
	
	
    /**
     * Validates whether a take is possible based on the parsed command,
     * player state, and room conditions.
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeTake() {
		
		ActionResult result = new ActionResult(game,player,true);

		if (isPick(command)) {
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
			} else if (isTakeApple(game,player,command.getNounNumber())) {
				result = takeApple(game,player,command.getNounNumber());
			} else if (isItemCarriable(command.getNounNumber())) {
				result = itemNotCarriable(game,player);
			} else if (isItemNotTakeable(command.getNounNumber())) {
				result = itemNotTakeable(game,player,command.getSplitTwoCommand()[1]);
			} else if (isItemTaken(game,command.getNounNumber(),player.getRoom())) {
				result = itemTaken(game,player,command.getNounNumber());
			}
			System.out.println("Taken");
		} else {
			this.game.addMessage("You can't "+command.getCommand(), true, false);
			result = new ActionResult(game,player,true);
		}	
		return result;
	}
	
	/**
	 * Returns true if the command is picking apples or reeds, or one of the other take commands
	 */
	private boolean isPick(ParsedCommand command) {
		
		return (command.getVerbNumber() == GameEntities.CMD_PICK && 
				(command.getNounNumber() == GameEntities.ITEM_APPLES ||
				command.getNounNumber() == GameEntities.ITEM_APPLE ||
				command.getNounNumber() == GameEntities.ITEM_REEDS)) ||
				command.getVerbNumber() != GameEntities.CMD_PICK;
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
		return game.countItemsCarrying()==Constants.INVENTORY_SPACE; 
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
	 * Returns true if the item is not carriable
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemCarriable(int nounNumber) {
		System.out.println(nounNumber);
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
		
		game.addMessage("You have "+game.getItem(nounNumber).getItemName(), true, true);
		game.getItem(nounNumber).setItemLocation(GameEntities.ROOM_CARRYING);
		this.success = true;
				
		if (haveSpecialItems()) {
			game.getItem(GameEntities.FLAG_FORCES).setItemFlag(1);
		} else {
			game.getItem(GameEntities.FLAG_FORCES).setItemFlag(0);
		}
		
		if (isItemBoat(game,command.getNounNumber())) {
			game.getItem(GameEntities.ITEM_SHEET).setItemLocation(GameEntities.ROOM_CARRYING);
		}
		
		if (isItemSheetAsSail(command.getNounNumber(),game)) {
			game.getItem(GameEntities.FLAG_BOAT_POWER).setItemFlag(0);
		} else if (isItemSheetAsRope(command.getNounNumber(),game)) {
			game = itemSheetAsRope(game);
		} else if (isItemRope(command.getNounNumber(),game)) {
			game = itemRope(game);
		}
		
		return new ActionResult(game,player,true);
	}
		
	/**
	 * Returns true if the item is the boat
	 * 
     * @param game the current game state
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemBoat(Game game,int nounNumber) {
		return game.getItem(GameEntities.FLAG_BOAT_POWER).getItemFlag() == 1 &&
				nounNumber == GameEntities.ITEM_BOAT;
	}
	
	/**
	 * Returns true if the item is the sheet and it is being used as a sail
	 * 
     * @param game the current game state
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemSheetAsSail(int nounNumber,Game game) {
		return nounNumber == GameEntities.ITEM_SHEET && game.getItem(GameEntities.FLAG_BOAT_POWER).getItemFlag()==1;
	}
	
	/**
	 * Returns true if the item is the sheet and it is tied
	 * 
     * @param game the current game state
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemSheetAsRope(int nounNumber,Game game) {
		return nounNumber == GameEntities.ITEM_SHEET && game.getItem(GameEntities.FLAG_SHEET_TIED).getItemFlag()==1;
	}
	
    /**
     * Executes a response is the item is the sheet and it is tied to the well
     *
     * @param game the current game state
     * @return an {@link Game} describing the outcome
     */
	private Game itemSheetAsRope(Game game) {
		game.getItem(GameEntities.FLAG_SHEET_TIED).setItemFlag(0);
		game.addMessage("You untie it first.", true, false);
		return game;
	}
	
	/**
	 * Returns true if the item is the rope and it is tied to the well
	 * 
     * @param game the current game state
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isItemRope(int nounNumber,Game game) {
		return nounNumber == GameEntities.ITEM_ROPE && game.getItem(GameEntities.FLAG_ROPE_TIED).getItemFlag()==1;
	}
	
    /**
     * Executes a response is the item is the rope and it is tied to the well
     *
     * @param game the current game state
     * @return an {@link Game} describing the outcome
     */
	private Game itemRope(Game game) {
		game.getItem(GameEntities.FLAG_ROPE_TIED).setItemFlag(0);
		game.addMessage("You untie it first.", true, false);
		return game;
	}
	
	/**
	 * Returns true if the player is picking apples in the orchard and apples are on the tree (and none on the ground)
	 * 
	 * @return boolean
	 */
	private boolean isTakeApple(Game game, Player player, int nounNumber) {
		System.out.println(GameEntities.FLAG_FOUND_APPLES);
		return player.getRoom() == GameEntities.ROOM_ORCHARD && game.getItem(GameEntities.ITEM_APPLES).getItemLocation() != player.getRoom() &&
				game.getItem(GameEntities.FLAG_FOUND_APPLES).getItemFlag() == 0 && 
				game.getItem(GameEntities.FLAG_NUMBER_APPLES_ON_TREE).getItemFlag() > 0 &&
				(nounNumber == GameEntities.ITEM_APPLE || nounNumber == GameEntities.ITEM_APPLES);
	}
	
    /**
     * Executes a response to the player picking an apple
     *
     * @param game the current game state
     * @return an {@link Game} describing the outcome
     */
	private ActionResult takeApple(Game game, Player player,int nounNumber) {
		System.out.println("Picking Apple");
		if(nounNumber == GameEntities.ITEM_APPLE) {
			game.addMessage("You pick an apple from a tree", true, true);
			int applesOnTrees = game.getItem(GameEntities.FLAG_NUMBER_APPLES_ON_TREE).getItemFlag();
			int applesInHand = game.getItem(GameEntities.FLAG_NUMBER_APPLES_IN_HAND).getItemFlag();
			game.getItem(GameEntities.FLAG_NUMBER_APPLES_ON_TREE).setItemFlag(applesOnTrees--);
			applesInHand++;
			game.getItem(GameEntities.FLAG_NUMBER_APPLES_ON_TREE).setItemFlag(applesInHand);
			
			if (applesInHand==1) {
				game.getItem(GameEntities.ITEM_APPLE).setItemLocation(0);
			} else {
				game.getItem(GameEntities.ITEM_APPLES).setItemLocation(0);
				game.getItem(GameEntities.ITEM_APPLE).setItemLocation(81);
			}
		}
		
		return new ActionResult(game,player,true);
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
	
	/**
	 * Returns the value of the success flag
	 * 
	 * @return boolean
	 */
	public boolean isSuccess() {
		return this.success;
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
 * 16 February 2026 - Added pick command
 * 21 June 2026 - Removed unusued methods
 * 28 June 2026 - Added inventory counter
 * 29 June 2026 - Fixed so boat has no power if take sheet
 * 5 July 2026 - Handle taking sheet and rope if tied to the well
 * 19 July 2026 - Started picking apples
 */
