/*
Title: Mystery of Silver Mountain Cross Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 29 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

TODO: Add check to see if the troll will let you cross
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Cross {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code CROSS} handler for executing a cross command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Cross(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a cross action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeCross() {
		game.addMessage("You cannot Cross that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isCrossBridge(player.getRoom(),command.getNounNumber())) {
			if(isTrollBlocking(game)) {
				result = trollBlocking(game,player);
			} else {
				result = crossBridge(game,player);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is crossing the bridge
	 * 
 	 * @param nounNumber the value of the noun entered
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isCrossBridge(int roomNumber,int nounNumber) {
		return nounNumber == GameEntities.ITEM_BRIDGE &&
				(roomNumber == GameEntities.ROOM_BRIDGE_EAST ||
				roomNumber == GameEntities.ROOM_BRIDGE_WEST);
	}
	
	/**
	 * Checks if the troll is blocking the player
	 * 
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isTrollBlocking(Game game) {
		return game.getItem(GameEntities.FLAG_TROLL).getItemFlag()==0;
	}
	
    /**
     * Executes a response if the player is crossing the bridge
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	public ActionResult crossBridge(Game game, Player player) {
		game.addMessage("Ok", true, false);
		if (player.getRoom()==GameEntities.ROOM_BRIDGE_EAST) {
			player.setRoom(GameEntities.ROOM_BRIDGE_WEST);
		} else if (player.getRoom()==GameEntities.ROOM_BRIDGE_WEST) {
			player.setRoom(GameEntities.ROOM_BRIDGE_EAST);
		}
		game.getItem(GameEntities.FLAG_TROLL).setItemFlag(0);	
		return new ActionResult(game,player,true);
	}
	
    /**
     * Handles the troll blocking the exit.
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} indicating failure
     */
	private ActionResult trollBlocking(Game game, Player player) {
		game.addMessage("A troll stops you crossing!", true, true);
		return new ActionResult(game,player,true);
	}
}
/* 20 March 2026 - Created File
 * 21 March 2026 - Added responses and JavaDocs
 * 29 March 2026 - Added check for troll blocking
 */
