/*
Title: Mystery of Silver Mountain Open Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.3
Date: 7 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Open {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Give} handler for executing and open command
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Open(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an open action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeOpen() {
		game.addMessage("You cannot open that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isOpen(command.getNounNumber())) {
			result = new Examine(game,player,command).executeExamine();
		} else if (isOpenKitchenCupboard(player.getRoom(),command.getNounNumber())) {
			result = openKitchenCupboard(game,player);
		} else if (isOpenCupboard(player.getRoom(),command.getNounNumber())) {
			result = openCupboard(game,player);
		} else if (isOpenGates(player.getRoom(),command.getNounNumber())) {
			result = openGates(game,player);
		} else if (isOpenCasks(player.getRoom(),command.getNounNumber())) {
			result = openCasks(game,player);
		} else if (isOpenSafe(player.getRoom(),command.getNounNumber())) {
			result = openSafe(game,player,command);
		} else if (isOpenStableDoor(player.getRoom(),command.getNounNumber())) {
			result = openStableDoor(game,player);
		} else if (isOpenWizardDoor(player.getRoom(),command.getNounNumber())) {
			result = openWizardDoor(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the player is opening the chest or the sacks
	 * 
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpen(int nounNumber) {
		return nounNumber == GameEntities.ITEM_CHEST || nounNumber == GameEntities.ITEM_SACK;
	}
	
	/**
	 * Returns true if the player is opening the kitchen cupboard
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpenKitchenCupboard(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_CUPBOARD && roomNumber == GameEntities.ROOM_KITCHEN;
	}
	
    /**
     * Executes the open kitchen cupboard action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult openKitchenCupboard(Game game,Player player) {
		game.getItem(GameEntities.ITEM_PHIAL).setItemFlag(0);
		game.addMessage("Ok", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the player is opening the cupboard
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpenCupboard(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_CUPBOARD && roomNumber == GameEntities.ROOM_CUPBOARD;
	}
	
    /**
     * Executes the open cupboard action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult openCupboard(Game game,Player player) {
		game.getItem(GameEntities.ITEM_BOOTS).setItemFlag(0);
		game.addMessage("Ok", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the player is opening the gates
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpenGates(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_GATE && roomNumber == GameEntities.ROOM_SLUICE_GATES ||
				nounNumber == GameEntities.ITEM_DOOR && roomNumber == GameEntities.ROOM_CORRIDOR;
	}
	
    /**
     * Executes the open gate action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult openGates(Game game,Player player) {
		game.addMessage("You are not strong enough.", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is opening the casks
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpenCasks(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_CASKS && roomNumber == GameEntities.ROOM_CASKS;
	}
	
    /**
     * Executes the open cask action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult openCasks(Game game,Player player) {
		game.addMessage("A passage!", true, false);
		game.getRoom(GameEntities.ROOM_CASKS).openExit(GameEntities.WEST);
		return new ActionResult(game,player,true);
	}	

	/**
	 * Returns true if the player is opening the safe in Ogban's Room
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpenSafe(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_SAFE && roomNumber == GameEntities.ROOM_OGBAN_CHAMBER;
	}
	
    /**
     * Executes the open safe action
     *
     * @param game the current game state
     * @param player the player making the move
     * @param command the parsed command being executed
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult openSafe(Game game,Player player, ParsedCommand command) {
		
		if(command.getSplitFullCommand().length != 3) {
			game.addMessage("Enter the combination after the command", true, false);
			game.addMessage("OPEN SAFE <COMBINATION>", false, false);
		} else if (command.getSplitFullCommand()[2].equals(Integer.toString(game.getItem(GameEntities.FLAG_SAFE_CODE).getItemFlag()))) {
			game.addMessage("It opens", true, false);
			game.getItem(GameEntities.FLAG_SAFE_OPEN).setItemFlag(0);
		} else {
			game.addMessage("Wrong!", true, false);
		}
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is opening the stable door
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpenStableDoor(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_DOOR && roomNumber == GameEntities.ROOM_STABLE;
	}
	
    /**
     * Executes the open stable door action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult openStableDoor(Game game,Player player) {
		game.addMessage("It falls off its hinges", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is opening the door in the wizard's lair
	 * 
	 * @param roomNumber the room the player is in
 	 * @param nounNumber the value of the noun entered
	 * @return boolean
	 */
	private boolean isOpenWizardDoor(int roomNumber, int nounNumber) {
		return nounNumber == GameEntities.ITEM_DOOR && roomNumber == GameEntities.ROOM_WIZARD_LAIR;
	}
	
    /**
     * Executes the open door in the wizard's lair action
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult openWizardDoor(Game game,Player player) {
		game.addMessage("It is locked", true, false);
		return new ActionResult(game,player,true);
	}	
}

/* 3 March 2026 - Created File
 * 4 March 2026 - Started added responses
 * 6 March 2026 - Added open casks
 * 7 March 2026 - Added open Ogban's Safe
 * 				- Completed Actions
 * 				- Added Java Docs
 */
