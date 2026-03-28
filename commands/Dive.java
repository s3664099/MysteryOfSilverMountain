/*
Title: Mystery of Silver Mountain Dive Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 28 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import data.GameEntities;
import game.Game;
import game.Player;

public class Dive {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
		
    /**
     * Creates a {@code Dive} handler for executing a dive action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Dive(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	 /**
     * Generate a response to a dive action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeDive() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (hasDrowned(player.getRoom())) {
			result = drowned(game,player);
		}
	
		return result;
	}
	
	/**
	 * Returns true if the player has dived in a place where they will drown
	 * 
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean hasDrowned(int roomNumber) {
		return roomNumber == GameEntities.ROOM_POOL &&
				roomNumber == GameEntities.ROOM_MIDDLE_LAKE;
	}
	
    /**
     * Advises the player they have drowned and ends the game
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult drowned(Game game,Player player) {
		game.addMessage("You have drowned.", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 27 March 2026 - Created file
 * 28 March 2026 - Added responses & JavaDocs
 */
