/*
Title: Mystery of Silver Mountain Cross Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 21 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
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
			result = crossBridge(game,player);
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
	public boolean isCrossBridge(int roomNumber,int nounNumber) {
		return nounNumber == GameEntities.ITEM_BRIDGE &&
				(roomNumber == GameEntities.ROOM_BRIDGE_EAST ||
				roomNumber == GameEntities.ROOM_BRIDGE_WEST);
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
		return new ActionResult(game,player,true);
	}
}
/* 20 March 2026 - Created File
 * 21 March 2026 - Added responses and JavaDocs
 */
