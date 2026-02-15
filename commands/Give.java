/*
Title: Mystery of Silver Mountain Give Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 14 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/
package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Give {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Drop} handler for moving items from the users inventory
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Give(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeGive() {
		
		ActionResult result = new ActionResult(game,player,false);
		
		if (isPlayerCarryingItem(game,command.getNounNumber())) {
			result = playerDoesNotHaveItem(game,player);
		} else if (isGiveBroach(player.getRoom(),game,command.getNounNumber())) {
			result = giveBroach(game,player);
		} else if (isInValley(player.getRoom())) {
			result = inValley(game,player);
		} else if (hasNoCoins(command.getNounNumber(),game)) {
			result = noCoins(game,player);
		} else if (isGiveCoinToTroll(player.getRoom(),game,command.getNounNumber())) {
			result = giveCoinToTroll(game,player);
		} else if (isAtBridge(player.getRoom())) {
			if(isGiveCoins(command.getNounNumber(),game)) {
				result = giveCoins(game,player);
			} else {
				result = atBridge(game,player);
			}
		} else if (isGiveAppleAtTrack(player.getRoom(),command.getNounNumber(),game)) {
			result = giveAppleAtTrack(game,player);
		} else if (isGiveAppleAtGates(player.getRoom(),command.getNounNumber(),game)) {
			result = giveAppleAtGates(game,player);
		} else if (isGiveBone(player.getRoom(),command.getNounNumber())) {
			result = giveBone(game,player);
		} else if (isItEaten(player.getRoom())) {
			result = itIsEaten(game,player,command.getNounNumber());
		} else if (isGiveToRats(player.getRoom(),command.getNounNumber())) {
			result = giveToRats(game,player,command.getNounNumber());
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is carrying the item
	 * 
	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isPlayerCarryingItem(Game game, int nounNumber) {
		return game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}

    /**
     * Executes a response if the player does not have the item
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult playerDoesNotHaveItem(Game game, Player player) {
		game.addMessage("You do not have that!", true, false);
		return new ActionResult(game,player,true);
	}
		
	private boolean isInValley(int room) {
		return room == GameEntities.ROOM_VALLEY;
	}

	private ActionResult inValley(Game game,Player player) {
		game.addMessage("He gives it back!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGiveBroach(int room, Game game, int nounNumber) {
		return room == GameEntities.ROOM_VALLEY && nounNumber == GameEntities.ITEM_BROOCH &&
				game.getItem(GameEntities.ITEM_BROOCH).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult giveBroach(Game game, Player player) {
		game.getItem(GameEntities.ITEM_BROOCH).setItemLocation(GameEntities.ROOM_DESTROYED);
		game.addMessage("He takes it and says '"+game.getItems(GameEntities.FLAG_RING_NUMBER)+" "
					+ "rings are needed'.", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGiveCoinToTroll(int room,Game game,int nounNumber) {
		return (room == GameEntities.ROOM_BRIDGE_EAST || room == GameEntities.ROOM_BRIDGE_WEST) &&
				game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag()>0 &&
				game.getItem(GameEntities.ITEM_COIN).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private boolean anyCoinsLeft(int coinNumbers) {
		return coinNumbers == 0;
	}
	
	private ActionResult giveCoinToTroll(Game game, Player player) {
		game.addMessage("He takes it", true, false);
		game.getItem(GameEntities.FLAG_TROLL).setItemFlag(1);
		int coinNumbers = game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag();
		game.getItem(GameEntities.FLAG_COIN_NUMBERS).setItemFlag(coinNumbers --);
		if (anyCoinsLeft(coinNumbers --)) {
			game.getItem(GameEntities.ITEM_COIN).setItemLocation(GameEntities.ROOM_DESTROYED);
		}
		return new ActionResult(game,player,true);
	}
	
	private boolean isAtBridge(int roomNumber) {
		return roomNumber == GameEntities.ROOM_BRIDGE_EAST || roomNumber == GameEntities.ROOM_BRIDGE_WEST;
	}
	
	private ActionResult atBridge(Game game, Player player) {
		game.addMessage("He does not want it.", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean hasNoCoins(int nounNumber, Game game) {
		return (game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag()==0) &&
				nounNumber == GameEntities.ITEM_COIN;
	}
	
	private ActionResult noCoins(Game game, Player player) {
		game.addMessage("You have run out!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGiveCoins(int nounNumber, Game game) {
		return (game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag()>0) &&
				nounNumber == GameEntities.ITEM_COINS;
	}

	private ActionResult giveCoins(Game game, Player player) {
		game.addMessage("He takes them all.", true, false);
		game.getItem(GameEntities.ITEM_COIN).setItemLocation(GameEntities.ROOM_DESTROYED);
		game.getItem(GameEntities.FLAG_TROLL).setItemFlag(1);
		game.getItem(GameEntities.FLAG_COIN_NUMBERS).setItemFlag(0);
		return new ActionResult(game,player,true);
	}

	private boolean isGiveAppleAtTrack(int roomNumber, int nounNumber, Game game) {
		return (nounNumber == GameEntities.ITEM_APPLE || nounNumber == GameEntities.ITEM_APPLES) &&
				roomNumber == GameEntities.ROOM_TRACK;
	}
	
	private boolean hasOneApple(Game game) {
		return game.getItem(GameEntities.ITEM_APPLES).getItemLocation() == GameEntities.ROOM_DESTROYED;
	}
	
	private boolean isGiveAppleAtGates(int roomNumber, int nounNumber, Game game) {
		return (nounNumber == GameEntities.ITEM_APPLE || nounNumber == GameEntities.ITEM_APPLES) && 
				roomNumber == GameEntities.ROOM_RUSTY_GATES;
	}
	
	private ActionResult giveAppleAtTrack(Game game, Player player) {
		game.addMessage("He leads you north.", true, false);
		player.setRoom(GameEntities.ROOM_RUSTY_GATES);
		if (hasOneApple(game)) {
			game.getItem(GameEntities.ITEM_APPLE).setItemLocation(GameEntities.ROOM_DESTROYED);
		}
		return new ActionResult(game,player,true);
	}
	
	private ActionResult giveAppleAtGates(Game game, Player player) {
		game.addMessage("He leads you south.", true, false);
		player.setRoom(GameEntities.ROOM_TRACK);
		if (hasOneApple(game)) {
			game.getItem(GameEntities.ITEM_APPLE).setItemLocation(GameEntities.ROOM_DESTROYED);
		}
		return new ActionResult(game,player,true);
	}

	private boolean isItEaten(int roomNumber) {
		return roomNumber == GameEntities.ROOM_GLASS_GATES ||
				roomNumber == GameEntities.ROOM_COUNTRYSIDE;
	}

	private ActionResult itIsEaten(Game game, Player player, int nounNumber) {
		game.addMessage("He eats it!", true, false);
		game.getItem(nounNumber).setItemLocation(GameEntities.ROOM_DESTROYED);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGiveBone(int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_GLASS_GATES && nounNumber == GameEntities.ITEM_BONE;
	}
	
	private ActionResult giveBone(Game game, Player player) {
		game.addMessage("He is distracted", true, false);
		game.getItem(GameEntities.FLAG_HOUND).setItemFlag(1);
		game.getItem(GameEntities.ITEM_BONE).setItemLocation(GameEntities.ROOM_DESTROYED);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGiveToRats(int roomNumber,int nounNumber) {
		return (nounNumber == GameEntities.ITEM_APPLES || nounNumber == GameEntities.ITEM_BREAD) &&
				roomNumber == GameEntities.ROOM_WINE_CELLAR;
	}
	
	private ActionResult giveToRats(Game game, Player player, int nounNumber) {
		game.addMessage("They scurry away.", true, false);
		game.getItem(nounNumber).setItemLocation(GameEntities.ROOM_DESTROYED);
		game.getItem(GameEntities.FLAG_RATS).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 12 February 2026 - Created Class
 * 13 February 2026 - Added give brooch and started with troll
 * 14 February 2026 - Completed the giving items to the troll
 * 					- Added give apple
 * 					- Added validation carrying item
 * 15 February 2026 - Completed the give methods
 */
