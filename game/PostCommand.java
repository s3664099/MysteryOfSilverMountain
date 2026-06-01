/*
Title: Mystery of Silver Mountain Post Command Functions
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.8
Date: 1 June 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package game;

import command_process.ActionResult;
import data.GameEntities;

/**
 * Handles post-command updates in the adventure game.
 *
 * <p>{@code PostCommand} manages game state changes that occur automatically
 * after a player executes a command. This includes environmental effects,
 * NPC actions, item movements, and win/loss checks.</p>
 *
 * <p>The class is designed to be used after every player action to update the game state
 * according to the game's logic rules, including random events tied to rooms
 * or items.</p>
 */
public class PostCommand {
	
	private Game game;
	private Player player;
	private ActionResult result;

    /**
     * Constructs a {@code PostCommand} handler from an {@link ActionResult}.
     *
     * @param result the result of the previous player action containing game and player states
     */
	public PostCommand(ActionResult result) {
				
		this.game = result.getGame();
		this.player = result.getPlayer();
		this.result = result;
	}
	
    /**
     * Processes all post-command updates.
     *
     * <p>This method checks and executes all environmental effects,
     * NPC movements, item effects, win/lose conditions, and other
     * state changes that occur after a player command.</p>
     *
     * @return the updated {@link ActionResult} containing the game and player
     */	
	public ActionResult postUpdates() {
		
		if (isItDark(game,player.getRoom())) {
			result = itIsDark(result.getGame(),result.getPlayer());
		} else {
			result = itIsNotDark(result.getGame(),result.getPlayer());
		} 
		
		if (hasSank(game,player.getRoom())) {
			result = sank(result.getGame(),result.getPlayer());
		} else if (isInRoughWater(player.getRoom())) {
			result = inRoughWater(result.getGame(),result.getPlayer());
		}
		
		if (hasGoblinGhostCaughtYou(game,player.getRoom())) {
			result = goblinGhostCaughtYou(result.getGame(),result.getPlayer());
		}

		if (!isWinGame()) {
			winGame();
		} else if (isLoseGame()) {
			loseGame();
		}
		
		return result;
	}
	
    // ================== Condition Checks ================== //
	
	/**
	 * Returns true if the third word spoken flag has been set
	 * 
	 * @return boolean
	 */
	private boolean isWinGame() {
		return game.getItem(GameEntities.FLAG_THIRD_WORD_SPOKEN).getItemFlag() == 1;
	}
	
	/**
	 * Returns true if the game failed flag has been set
	 * 
	 * @return boolean
	 */
	private boolean isLoseGame() {
		return game.getItem(GameEntities.FLAG_PLAYER_FAILED).getItemFlag() != 0;
	}
	
	/**
	 * Returns true if the player is in a room where the dark flag has been set
	 * 
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isItDark(Game game, int roomNumber) {
		return ((roomNumber == GameEntities.ROOM_CASKS || roomNumber == GameEntities.ROOM_WINE_CELLAR) &&
				game.getItem(GameEntities.FLAG_IS_DARK).getItemFlag() == 0);
	}
	
	/**
	 * Returns true if the boat has sunk
	 * 
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean hasSank(Game game, int roomNumber) {
		return roomNumber == GameEntities.ROOM_ROUGH_WATER && 
				game.getItem(GameEntities.FLAG_BOAT_WORN).getItemFlag() == 10;
	}
	
	/**
	 * Returns true if the player is in the rough water room
	 * 
 	 * @param roomNumber the room the player is in
	 * @return boolean
	 */
	private boolean isInRoughWater(int roomNumber) {
		return roomNumber == GameEntities.ROOM_ROUGH_WATER;
	}
	
	/**
	 * Returns true if the ghost goblin has caught the player
	 * 
 	 * @param roomNumber the room the player is in
     * @param game the current game state
	 * @return boolean
	 */
	private boolean hasGoblinGhostCaughtYou(Game game, int roomNumber) {
		return roomNumber == GameEntities.ROOM_FALLEN_OAK &&
				game.getItem(GameEntities.FLAG_GHOST_FREE).getItemFlag() == 0 &&
				game.getItem(GameEntities.ITEM_REEDS).getItemLocation() != 0;
	}
	
    // ================== Actions ================== //
		
	private void winGame() {
		game.addMessage("Hooooorrrraaaaayyyy!!!!", false, true);
		game.addMessage("", false, true);
		game.addMessage("You have succeeded in your quest", false, true);
		game.addMessage("and brought peace to the land", false, true);
		game.setEndGameState();
	}
	
	private void loseGame() {
		game.addMessage("You have failed in your quest!", false, true);
		game.addMessage("But you are granted another try", false, true);
		game.setEndGameState();
	}
	
    /**
     * Updates the state if the room is dark
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult itIsDark(Game game,Player player) {
		player.setPlayerStateDark();
		return new ActionResult(game,player,false);
	}
	
    /**
     * Updates the state if the room is not dark
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult itIsNotDark(Game game,Player player) {
		if (player.isPlayerStateDark()) {
			player.setPlayerStateNormal();
		}
		return new ActionResult(game,player,false);
	}
	
    /**
     * Updates the state if the player's boat has sunk
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult sank(Game game, Player player) {
		game.addMessage("You sank!", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
    /**
     * Updates the state of the boat if the player is in rough water
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult inRoughWater(Game game,Player player) {
		int boat_hp = game.getItem(GameEntities.FLAG_BOAT_WORN).getItemFlag();
		game.getItem(GameEntities.FLAG_BOAT_WORN).setItemFlag(boat_hp++);
		return new ActionResult(game,player,true);
	}
	
    /**
     * Updates the state if the ghost goblin has caught the player
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult goblinGhostCaughtYou(Game game, Player player) {
		game.addMessage("The ghost of the Goblin Guardian gets you!", true, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}

/* 3 December 2025 - Created File
 * 7 December 2025 - Cleared game related code
 * 8 December 2025 - Increased Version Number
 * 16 January 2026 - Added condition for being dark
 * 10 May 2026 - Added check to see if game failed
 * 25 May 2026 - Updated code to follow rules from CommandExecutor
 * 27 May 2026 - Added win game condition check
 * 30 May 2026 - Added ghost goblin response
 * 1 June 2026 - Win and lose game messages work
 */
