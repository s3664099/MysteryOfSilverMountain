/*
Title: Mystery of Silver Mountain Enter Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 20 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Enter {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code ENTER} handler for executing a enter command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Enter(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an enter action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeEnter() {
		game.addMessage("You cannot Enter that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isEnterWindmill(player.getRoom(),command.getNounNumber())) {
			result = enterWindmill(game,player);
		} else if (isEnterHut(player.getRoom(),command.getNounNumber())) {
			result = enterHut(game,player);
		}
		
		return result;
	}
	
	private boolean isEnterWindmill(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_OUTSIDE_WINDMILL &&
				nounNumber == GameEntities.ITEM_WINDMILL;
	}
	
	private ActionResult enterWindmill(Game game, Player player) {
		game.addMessage("Ok",true,true);
		player.setRoom(player.getRoom()+1);
		return new ActionResult(game,player, true);
	}
	
	private boolean isEnterHut(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_HUT &&
				nounNumber == GameEntities.ITEM_HUT;
	}
	
	private ActionResult enterHut(Game game, Player player) {
		game.addMessage("You found something!",true,true);
		game.getItem(GameEntities.FLAG_PLANKS).setItemFlag(0);
		return new ActionResult(game,player, true);
	}
}
