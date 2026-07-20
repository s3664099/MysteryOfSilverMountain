/*
Title: Mystery of Silver Mountain Eat Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.4
Date: 20 July 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Eat {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code EAT} handler for executing a eat action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Eat(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an eat action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeEat() {
		game.addMessage("You can't eat that!", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isEatBread(game,command.getNounNumber())) {
			result = eatBread(game,player,command.getNounNumber());
		} else if (isEatApple(game,command.getNounNumber())) {
			result = eatApple(game,player);
		}
				
		return result;
	}
	
	/**
	 * Returns true if the player is eating bread
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isEatBread(Game game,int commandNumber) {
		return commandNumber == GameEntities.ITEM_BREAD;
	}
	
    /**
     * Executes the eat action if the player is eating bread
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult eatBread(Game game, Player player,int nounNumber) {
		game.addMessage("Yum Yum!", true, false);
		game.getItem(nounNumber).setItemLocation(GameEntities.ROOM_DESTROYED);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is eating an Apple
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isEatApple(Game game,int commandNumber) {
		return commandNumber == GameEntities.ITEM_APPLE;
	}
	
    /**
     * Executes the eat action if the player is eating an apple
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult eatApple(Game game, Player player) {
		game.addMessage("Tasty!", true, false);
		int numberApples = game.getItem(GameEntities.FLAG_NUMBER_APPLES_IN_HAND).getItemFlag();
		
		if (numberApples == 0) {
			game.addMessage("You don't have any!", true, false);
		} else {
			numberApples --;
			game.getItem(GameEntities.FLAG_NUMBER_APPLES_IN_HAND).setItemFlag(numberApples);
			if (numberApples == 1) {
				game.getItem(GameEntities.ITEM_APPLES).setItemLocation(GameEntities.ROOM_DESTROYED);
				game.getItem(GameEntities.ITEM_APPLE).setItemLocation(GameEntities.ROOM_CARRYING);
			} else if (numberApples == 0) {
				game.getItem(GameEntities.ITEM_APPLE).setItemLocation(GameEntities.ROOM_DESTROYED);
			}
		}
		
		return new ActionResult(game,player,true);
	}
}

/* 15 April 2026 - Created File
 * 19 April 2026 - Added responses
 * 20 April 2026 - Added Javadocs
 * 12 June 2026 - Removed checks for carrying item.
 * 20 July 2026 - Added specific method for eating apples
 */
