/*
Title: Mystery of Silver Mountain Eat Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 19 April 2026
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
		
		if (isEatApplesOrBread(game,command.getNounNumber())) {
			result = eatApplesOrBread(game,player,command.getNounNumber());
		}
				
		return result;
	}
	
	/**
	 * Returns true if the player is eating apples or bread
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isEatApplesOrBread(Game game,int commandNumber) {
		return (commandNumber == GameEntities.ITEM_BREAD && game.getItem(GameEntities.ITEM_BREAD).getItemLocation() == GameEntities.ROOM_CARRYING) ||
			(commandNumber == GameEntities.ITEM_APPLES && game.getItem(GameEntities.ITEM_APPLES).getItemLocation()==GameEntities.ROOM_CARRYING);
	}
	
    /**
     * Executes the eat action if the player is eating apples or bread
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult eatApplesOrBread(Game game, Player player,int nounNumber) {
		game.addMessage("Yum Yum!", true, false);
		game.getItem(nounNumber).setItemLocation(GameEntities.ROOM_DESTROYED);
		return new ActionResult(game,player,true);
	}
}

/* 15 April 2026 - Created File
 * 19 April 2026 - Added responses
 * 20 April 2026 - Added Javadocs
 */
