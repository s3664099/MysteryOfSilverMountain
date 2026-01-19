/*
Title: <Game Name> Post Command Functions
Author: 
Translator: David Sarkies
Version: 1.1
Date: 16 January 2026
Source: 
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

    /**
     * Constructs a {@code PostCommand} handler from an {@link ActionResult}.
     *
     * @param result the result of the previous player action containing game and player states
     */
	public PostCommand(ActionResult result) {
		
		if (isItDark(result.getGame(),result.getPlayer().getRoom())) {
			result = itIsDark(result.getGame(),result.getPlayer());
		} else {
			result = itIsNotDark(result.getGame(),result.getPlayer());
		}
		
		game = result.getGame();
		player = result.getPlayer();
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
		
		if (isWinGame()) {winGame();}
		
		if (isLoseGame()) {loseGame();}
		
		return new ActionResult(game,player,true);
	}

    // ================== Condition Checks ================== //
	
	private boolean isWinGame() {
		return false;
	}
	
	private boolean isLoseGame() {
		return false;
	}
	
	private boolean isItDark(Game game, int roomNumber) {
		return ((roomNumber == GameEntities.ROOM_CASKS || roomNumber == GameEntities.ROOM_WINE_CELLAR) &&
				game.getItem(GameEntities.FLAG_IS_DARK).getItemFlag() == 0);
	}
	
    // ================== Actions ================== //
		
	private void winGame() {

	}
	
	private void loseGame() {

	}
	
	private ActionResult itIsDark(Game game,Player player) {
		player.setPlayerStateDark();
		return new ActionResult(game,player,false);
	}
	
	private ActionResult itIsNotDark(Game game,Player player) {
		if (player.isPlayerStateDark()) {
			player.setPlayerStateNormal();
		}
		return new ActionResult(game,player,false);
	}
}

/* 3 December 2025 - Created File
 * 7 December 2025 - Cleared game related code
 * 8 December 2025 - Increased Version Number
 * 16 January 2026 - Added condition for being dark
 */
