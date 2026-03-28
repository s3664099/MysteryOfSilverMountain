/*
Title: Mystery of Silver Mountain Dive Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 27 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Dive {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Dive} handler for executing a dive action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Dive(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
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
	
	private boolean hasDrowned(int roomNumber) {
		return roomNumber == GameEntities.ROOM_POOL &&
				roomNumber == GameEntities.ROOM_MIDDLE_LAKE;
	}
	
	private ActionResult drowned(Game game,Player player) {
		game.addMessage("You have drowned.", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 27 March 2026 - Created file
 */
