/*
Title: Mystery of Silver Mountain Ring Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 25 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Ring {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code RING} handler for executing a ring command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Ring(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;	
	}
	
    /**
     * Validates whether a ring action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeRing() {
		game.addMessage("How?", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isRingBell(player.getRoom(),command.getNounNumber())) {
			
			if (isTwoWords(command)) {
				result = twoWords(game,player);
			} else if (!isInteger(command.getSplitFullCommand()[2].trim())) {
				result = enterNumber(game,player);
			} else if (isCorrectNumber(game,Integer.parseInt(command.getSplitFullCommand()[2].trim()))) {
				result = correctNumber(game,player);
			} else {
				result = incorrectNumber(game,player);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is ringing the bell
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isRingBell(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_BELL &&
				nounNumber == GameEntities.ITEM_BELL;
	}
	
	/**
	 * Returns true if the player has entered a two word or less command
	 * 
	 * @param command the command the player entered
	 * @return boolean
	 */
	private boolean isTwoWords(ParsedCommand command) {
		return command.getSplitFullCommand().length<3;
	}
	
    /**
     * Response is player has entered a command with two or less words
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult twoWords(Game game, Player player) {
		game.addMessage("How many times?", false, false);
		return new ActionResult(game,player, true);
	}
	
	/**
	 * Returns true if the third word the player has entered is an integer
	 * 
	 * @param command the third word the player entered
	 * @return boolean
	 */
	private boolean isInteger(String command) {

		boolean integer = true;
		try {
	        Integer.parseInt(command);
	    } catch (NumberFormatException e) {
	    	 integer = false;
	    }
		return integer;
	}
	
    /**
     * Response if the third word the player entered is no a number
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult enterNumber(Game game,Player player) {
		game.addMessage("Please use a number, such as 1", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if player has entered the correct number
	 * 
	 * @param command the third word the player entered
	 * @return boolean
	 */
	private boolean isCorrectNumber(Game game, int numberEntered) {
		return numberEntered == game.getItem(GameEntities.FLAG_RING_NUMBER).getItemFlag();
	}
	
    /**
     * Response if player entered the correct number
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult correctNumber(Game game, Player player) {
		game.addMessage("A rock door opens", true, false);
		game.getRoom(GameEntities.ROOM_BELL).openExit(GameEntities.EAST);
		return new ActionResult(game,player,true);
	}
	
    /**
     * Response if player did not enter the correct number
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult incorrectNumber(Game game, Player player) {
		game.addMessage("You have mistreated the bell", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 30 April 2026 - Created File
 * 24 April 2026 - Added check for integer
 * 25 April 2026 - Added ring bell
 * 				 - Added java docs
 */
