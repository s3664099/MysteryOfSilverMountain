/*
Title: Mystery of Silver Mountain Rig Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.4
Date: 25 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
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
		} else if (isRigMusic(command.getNounNumber())) {
			result = rigMusic(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player using the reeds
	 * 
	 * @param game the current game state
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isUseReeds(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_REEDS &&
			game.getItem(GameEntities.ITEM_REEDS).getItemLocation() == GameEntities.ROOM_CARRYING;	
	}
	
    /**
     * Executes using the reeds
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult useReeds(Game game, Player player) {
		game.addMessage("A nice tune.", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is at the fallen oak
	 * 
	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isAtFallenOak(int roomNumber) {
		return roomNumber == GameEntities.ROOM_FALLEN_OAK;
	}
	
    /**
     * Executes using the reeds at the fallen oak
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult atFallenOak(Game game,Player player) {
		game.getItem(GameEntities.FLAG_GHOST_FREE).setItemFlag(1);
		game.addMessage("The ghost of the goblin guardian is free!", true, false);
		game.getRoom(GameEntities.ROOM_FALLEN_OAK).openExit(GameEntities.NORTH);
		
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player attempting to rig the sail
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isRigSail(int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_EDGE_LAKE &&
				nounNumber == GameEntities.ITEM_SAIL;
	}
	
    /**
     * Executes rigging the sail
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult rigSail(Game game,Player player) {
		game.addMessage("What with?", true, false);
		
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player attempting to play music
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isRigMusic(int nounNumber) {
		return nounNumber == GameEntities.ITEM_MUSIC;
	}
	
    /**
     * Executes rigging the music
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult rigMusic(Game game,Player player) {
		game.addMessage("How, o musical one?", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 9 April 2026 - Created File
 * 12 April 2026 - Added Responses & Javadocs
 * 25 April 2026 - Removed unused include
 */
