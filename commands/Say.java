/*
Title: Mystery of Silver Mountain Say Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.3
Date: 21 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Say {

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
	public Say(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
    /**
     * Responds to what it is said, and determines whether it is game related or not
     *
     * @return an {@link ActionResult} describing effects
     */
	public ActionResult executeSay() {
		game.addMessage("You said it.", true, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isSayMagicWords(command.getNounNumber())) {
			result = sayMagicWords(game,player);
		} else if (isSayingMagicWords(player.getRoom(),command.getNounNumber(),game)) {
			
			if (isSayingAwake(command.getNounNumber(),game)) {
				result = sayingAwake(game,player);
			} else if (isSayingGuide(command.getNounNumber(),game)) {
				result = sayingGuide(game,player);
			} else if (isSayingLastWord(command.getNounNumber(),game)) {
				result = sayingLastWord(game,player);
			} else {
				result = sayingWrongWord(game,player);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is saying "magic words"
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isSayMagicWords(int nounNumber) {
		return nounNumber == GameEntities.ITEM_MAGIC_WORDS;
	}
	
    /**
     * Executes a response if the player says "magic words"
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult sayMagicWords(Game game,Player player)  {
		game.addMessage("You must say them one by one!", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is saying one of the magic words, at the stone of destiny
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSayingMagicWords(int roomNumber,int nounNumber, Game game) {
		return roomNumber == GameEntities.ROOM_SILVER_CHAMBER &&
				nounNumber > GameEntities.ITEM_DOOR &&
				nounNumber < GameEntities.ITEM_CHEST &&
				game.getItem(GameEntities.ITEM_STONE_DESTINY).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	/**
	 * Returns true if the player is saying "awake"
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSayingAwake(int nounNumber,Game game) {
		return game.getItem(GameEntities.FLAG_FIRST_WORD_SPOKEN).getItemFlag() == 0 &&
				nounNumber == GameEntities.ITEM_AWAKE;
	}
	
    /**
     * Executes a response if the player says "awake"
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult sayingAwake(Game game,Player player) {
		game.addMessage("The mountains rumber!", true, false);
		game.getItem(GameEntities.FLAG_FIRST_WORD_SPOKEN).setItemFlag(1);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the player is saying "guide" and "awake" has been said
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSayingGuide(int nounNumber, Game game) {
		return game.getItem(GameEntities.FLAG_FIRST_WORD_SPOKEN).getItemFlag() == 1 &&
				game.getItem(GameEntities.FLAG_SECOND_WORD_SPOKEN).getItemFlag() == 0 &&
				nounNumber == GameEntities.ITEM_AWAKE;		
	}
	
    /**
     * Executes a response if the player says "guide" after saying "awake"
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult sayingGuide(Game game,Player player) {
		game.addMessage("Towers fall down!", true, false);
		game.getItem(GameEntities.FLAG_SECOND_WORD_SPOKEN).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is saying the third word after the previous two
	 * 
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isSayingLastWord(int nounNumber, Game game) {
		return game.getItem(GameEntities.FLAG_FIRST_WORD_SPOKEN).getItemFlag() ==1 &&
				game.getItem(GameEntities.FLAG_SECOND_WORD_SPOKEN).getItemFlag() == 1 &&
				nounNumber == game.getItem(GameEntities.FLAG_WORD_LOCATION).getItemFlag() + 
				Constants.MAGIC_WORD_CONSTANT;
	}
	
    /**
     * Executes a response if the player says the third word after the previous two
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult sayingLastWord(Game game,Player player) {
		game.getItem(GameEntities.FLAG_THIRD_WORD_SPOKEN).setItemFlag(1);
		return new ActionResult(game,player,true);
	}

    /**
     * Executes a response if the player says the wrong word
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult sayingWrongWord(Game game,Player player) {
		game.addMessage("The wrong sacred word!", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 17 February 2026 - Created File
 * 19 February 2026 - Started adding say responses
 * 20 February 2026 - Adding Magic words
 * 21 February 2026 - Completed class
 * 					- Added javadocs
 */