/*
Title: Mystery of Silver Mountain Rig Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.3
Date: 12 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Rig {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code RIG} handler for executing a rig command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Rig(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a throw action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeRig() {
		game.addMessage("You cannot rig that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isUseReeds(game,command.getNounNumber())) {
			
			if (isAtFallenOak(player.getRoom())) {
				result = atFallenOak(game,player);
			} else {
				result = useReeds(game,player);
			}
		} else if (isRigSail(player.getRoom(),command.getNounNumber())) {
			result = rigSail(game,player);
		} else if (isMusic(command.getNounNumber())) {
			result = rigMusic(game,player);
		}
		
		return result;
	}
	
	private boolean isUseReeds(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_REEDS &&
			game.getItem(GameEntities.ITEM_REEDS).getItemLocation() == GameEntities.ROOM_CARRYING;	
	}
	
	private ActionResult useReeds(Game game, Player player) {
		game.addMessage("A nice tune.", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isAtFallenOak(int roomNumber) {
		return roomNumber == GameEntities.ROOM_FALLEN_OAK;
	}
	
	private ActionResult atFallenOak(Game game,Player player) {
		game.getItem(GameEntities.FLAG_GHOST_FREE).setItemFlag(1);
		game.addMessage("The ghost of the goblin guardian is free!", true, false);
		game.getRoom(GameEntities.ROOM_FALLEN_OAK).openExit(GameEntities.NORTH);
		
		return new ActionResult(game,player,true);
	}
	
	private boolean isRigSail(int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_EDGE_LAKE &&
				nounNumber == GameEntities.ITEM_SAIL;
	}
	
	private ActionResult rigSail(Game game,Player player) {
		game.addMessage("What with?", true, false);
		
		return new ActionResult(game,player,true);
	}
	
	private boolean isRigMusic(int nounNumber) {
		return nounNumber == GameEntities.ITEM_MUSIC;
	}
	
	private ActionResult rigMusic(Game game,Player player) {
		game.addMessage("How, o musical one?", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 9 April 2026 - Created File
 * 12 April 2026 - Added Responses & Javadocs
 */
