/*
Title: Mystery of Silver Mountain Climb Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 27 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Climb {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Tie} handler for executing a climb action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Climb(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a climb action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeClimb() {
		game.addMessage("You are unable to do that.", false, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isClimbVine(player.getRoom(),command.getNounNumber(),game)) {
			result = climbVine(game,player);
		} else if (isNotAttached(game,command.getNounNumber(),player.getRoom())) {
			result = notAttached(game,player);
		} else if (isAtTheTop(game,command.getNounNumber(),player.getRoom())) {
			result = atTheTop(game,player);
		} else if (isGoingDown(game,command.getNounNumber(),player.getRoom())) {
			result = goingDown(game,player);
		} else if (isClimbingSheet(game,command.getNounNumber(),player.getRoom())) {
			result = climbingSheet(game,player);
		} else if (isClimbingRope(game,command.getNounNumber(),player.getRoom())) {
			result = climbingRope(game,player);
		}
		
		return result;
	}
	
	private boolean isClimbVine(int roomNumber, int nounNumber, Game game) {
		return roomNumber == GameEntities.ROOM_GARDEN && nounNumber == GameEntities.ITEM_VINE &&
				game.getItem(GameEntities.FLAG_VINE_CLIMBABLE).getItemFlag()==1;
	}
	
	private ActionResult climbVine(Game game, Player player) {
		game.addMessage("All right", true, false);
		player.setRoom(GameEntities.ROOM_INSCRIBED_CAVERN);
		return new ActionResult(game,player,true);
	}
	
	private boolean isNotAttached(Game game,int nounNumber,int roomNumber) {
		return (nounNumber== GameEntities.ITEM_ROPE && 
				(game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_CARRYING ||
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == roomNumber)) ||
				(nounNumber== GameEntities.ITEM_SHEET && 
				(game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_CARRYING ||
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == roomNumber)) &&
				(roomNumber != GameEntities.ROOM_PINNACLE && roomNumber != GameEntities.ROOM_WELL &&
				roomNumber != GameEntities.ROOM_WELL_BOTTOM);
	}
	
	private ActionResult notAttached(Game game,Player player) {
		game.addMessage("Not attached to anything!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isAtTheTop(Game game,int nounNumber,int roomNumber) {
		return nounNumber == GameEntities.ITEM_ROPE && roomNumber == GameEntities.ROOM_PINNACLE &&
				game.getItem(GameEntities.ITEM_ROPE).getItemLocation() == GameEntities.ROOM_PINNACLE;
	}
	
	private ActionResult atTheTop(Game game,Player player) {
		game.addMessage("You are at the top!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGoingDown(Game game,int nounNumber,int roomNumber) {
		return nounNumber == GameEntities.ITEM_ROPE && roomNumber == GameEntities.ROOM_WELL &&
				game.getItem(GameEntities.FLAG_ROPE_TIED).getItemFlag() == 1;
	}
	
	private ActionResult goingDown(Game game,Player player) {
		game.addMessage("Going down.", true, false);
		player.setRoom(GameEntities.ROOM_WELL_BOTTOM);
		return new ActionResult(game,player,true);
	}
	
	private boolean isClimbingSheet(Game game,int nounNumber,int roomNumber) {
		return nounNumber == GameEntities.ITEM_SHEET && roomNumber == GameEntities.ROOM_WELL &&
				game.getItem(GameEntities.FLAG_SHEET_TIED).getItemFlag() == 1;
	}
	
	private ActionResult climbingSheet(Game game,Player player) {
		game.addMessage("It is torn.", true, false);
		game.getItem(GameEntities.ITEM_SHEET).setItemLocation(GameEntities.ROOM_DESTROYED);
		game.getItem(GameEntities.FLAG_SHEET_TIED).setItemFlag(0);
		player.setRoom(GameEntities.ROOM_WELL_BOTTOM);
		return new ActionResult(game,player,true);
	}

	//2100 IF H=7114 AND F(53)=1 THEN C(14)=71: F(53)=0: R$="IT FALLS DOWN-BUMP!"	
}

/* 25 February 2026 - Created file
 * 27 February 2026 - Added branches and started climb actions
 */
