/*
Title: Mystery of Silver Mountain Insert Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 11 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Insert {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code INSERT} handler for executing an insert command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Insert(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an insert action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeInsert() {
		game.addMessage("You cannot Insert that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isInsertCoinsNone(game,command.getNounNumber())) {
			result = insertCoinsNone(game,player);
		} else if (isInsertCoinInTurret(game,player.getRoom(),command.getNounNumber())) {
			result = insertCoinInTurrent(game,player);
		}
		return result;
	}
	
	/**
	 * Returns true if the player has no coins
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isInsertCoinsNone(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_COINS &&
				game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag() == 0;
	}
	
    /**
     * Alerts the player that they have no coins left
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult insertCoinsNone(Game game, Player player) {
		game.addMessage("You do not have any", false, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is inserting coins in the tower
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isInsertCoinInTurret(Game game,int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_TURRET && nounNumber == GameEntities.ITEM_COIN &&
				game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag()>0 &&
				game.getItem(GameEntities.ITEM_COINS).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
    /**
     * Executes the insert coin action in the tower
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult insertCoinInTurrent(Game game, Player player) {
		int numberOfCoins = game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag();
		game.getItem(GameEntities.FLAG_COIN_NUMBERS).setItemFlag(numberOfCoins--);
		game.addMessage(String.format("A number appears - %s",game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag()), false, false);
		if (!noCoinsLeft(numberOfCoins)) {
			game.getItem(GameEntities.ITEM_COINS).setItemLocation(GameEntities.ROOM_DESTROYED);
		}
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player has not inserted the last coin
	 * 
 	 * @param numberOfCoins the number of coins the player has
	 * @return boolean
	 */
	private boolean noCoinsLeft(int numberOfCoins) {
		return GameEntities.FLAG_COIN_NUMBERS>0;
	}
}

/* 9 April 2026 - Created file
 * 10 April 2026 - Added insert coins when you have none
 * 11 April 2026 - Added insert coins in turret
 * 				 - Added Javadocs
 */
