/*
Title: Mystery of Silver Mountain Drop Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.3
Date: 31 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Drop {
	
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates an {@code Take} handler for moving items into the users inventory
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Drop(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a drop is possible based on the parsed command,
     * player state, and item locations.
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeDrop() {
		ActionResult result = new ActionResult(game,player,true);
		
		if (isPlayerCarryingItem(game,command.getNounNumber())) {
			result = dropItem(game,player,command.getNounNumber());
			
			if (haveDroppedBoatInLake(command.getNounNumber(),player.getRoom())) {
				result = droppedBoatInLake(result.getGame(),result.getPlayer());
			} else if (haveDroppedBoat(game,command.getNounNumber())) {
				result = droppedBoat(result.getGame(),result.getPlayer());
			} else if (haveDroppedJug(game,command.getNounNumber())) {
				result = droppedJug(result.getGame(),result.getPlayer());
			} else if (haveDroppedSheet(game,command.getNounNumber())) {
				result = droppedSheet(result.getGame(),result.getPlayer());
			}
			
		} else {
			result = playerDoesNotHaveItem(game,player);
		}
		
		return result;
	}
	
	/**
	 * Returns true if the command is dropping the boat while the player is in a
	 * deep water location
	 */
	private boolean haveDroppedBoatInLake(int nounNumber, int roomNumber) {
		return nounNumber == GameEntities.ITEM_BOAT &&
				(roomNumber == GameEntities.ROOM_ROUGH_WATER ||
				 roomNumber == GameEntities.ROOM_MIDDLE_LAKE);
	}
	
    /**
     * Executes a response to dropping the boat in a deep water location
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult droppedBoatInLake(Game game, Player player) {
		game.addMessage("You drowned!", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is dropping the boat.
	 */
	private boolean haveDroppedBoat(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_BOAT && 
				game.getItem(GameEntities.FLAG_BOAT_POWER).getItemFlag() ==1;
	}
	
    /**
     * Executes a response to dropping the boat
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult droppedBoat(Game game, Player player) {
		game.getItem(GameEntities.ITEM_SHEET).setItemLocation(player.getRoom());
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is dropping the jug while full.
	 */
	private boolean haveDroppedJug(Game game, int nounNumber) {
		return nounNumber == GameEntities.ITEM_JUG &&
				game.getItem(GameEntities.FLAG_JUG_FULL).getItemFlag() == 1;
	}
	
    /**
     * Executes a response to dropping the jug while full
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult droppedJug(Game game,Player player) {
		game.addMessage("You lost the water!", false, false);
		game.getItem(GameEntities.FLAG_JUG_FULL).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is dropping the sheet.
	 */
	private boolean haveDroppedSheet(Game game,int nounNumber) {
		return game.getItem(GameEntities.FLAG_BOAT_POWER).getItemFlag() == 1 &&
				nounNumber == GameEntities.ITEM_SHEET;
	}
	
    /**
     * Executes a response to dropping the sheet
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult droppedSheet(Game game,Player player) {
		game.getItem(GameEntities.FLAG_BOAT_POWER).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the player is carrying the item
	 */
	private boolean isPlayerCarryingItem(Game game, int nounNumber) {
		return game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}

    /**
     * Executes a response if the player does not have the item
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult playerDoesNotHaveItem(Game game, Player player) {
		game.addMessage("You do not have that!", true, false);
		return new ActionResult(game,player,true);
	}
	
    /**
     * Executes a response if the player successfully dropped the item
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult dropItem(Game game, Player player, int nounNumber) {
		game.getItem(nounNumber).setItemLocation(player.getRoom());
		game.addMessage("Done", true,false);
		return new ActionResult(game,player,true);
	}
}

/*
 * 28 January 2026 - Created file
 * 29 January 2026 - Added drop functionality
 * 30 January 2026 - Added post command responses
 * 31 January 2026 - Completed code and added Javadocs
 */