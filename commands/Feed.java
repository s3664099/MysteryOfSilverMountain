/*
Title: Mystery of Silver Mountain Feed Class
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

public class Feed {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code FEED} handler for executing a feed command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Feed(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an feed action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeFeed() {
		game.addMessage("You cannot feed that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isFeedResponse(player.getRoom(),command.getNounNumber())) {
			result = feedResponse(game,player);
		}
		return result;
	}
	
	/**
	 * Returns true if there is a response for the feed action
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isFeedResponse(int roomNumber,int nounNumber) {
		return (roomNumber == GameEntities.ROOM_WINE_CELLAR && nounNumber == GameEntities.ITEM_RAT) ||
				(roomNumber == GameEntities.ROOM_COUNTRYSIDE && nounNumber == GameEntities.ITEM_BOAR) ||
				(roomNumber == GameEntities.ROOM_RUSTY_GATES && nounNumber == GameEntities.ITEM_PONY) ||
				(roomNumber == GameEntities.ROOM_TRACK && nounNumber == GameEntities.ITEM_PONY) ||
				(roomNumber == GameEntities.ROOM_GLASS_GATES && nounNumber == GameEntities.ITEM_HOUND);
	}
	
    /**
     * Executes a response for the feed action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult feedResponse(Game game,Player player) {
		game.addMessage("With what?", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 22 March 2026 - Created File
 * 25 March 2026 - Added responses and javadocs
 */
