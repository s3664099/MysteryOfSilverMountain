/*
Title: Mystery of Silver Mountain Post Command Functions
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.6
Date: 27 May 2026
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
		} else (isInRoughWaterplayer.getRoom)) {
			result = inRoughWater(result.getGame(),result.getPlayer());
		}
		
		//670 IF R=56 AND F(35)=0 AND C(10)<>0 THEN R$=X1$+" GETS YOU!":F(56)=1
		//3460 X1$="THE GHOST OF THE GOBLIN GUARDIAN"
		if (isWinGame()) {
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
	
	private boolean hasSank(Game game, int roomNumber) {
		return roomNumber == GameEntities.ROOM_ROUGH_WATER && 
				game.getItem(GameEntities.FLAG_BOAT_WORN).getItemFlag() == 10;
	}
	
	private boolean isInRoughWaterplayer(int roomNumber) {
		return roomNumber == GameEntities.ROOM_ROUGH_WATER;
	}
	
    // ================== Actions ================== //
		
	private void winGame() {
		//740 PRINT "HOOOOORRRRRRAAAAAYYYYYY!"
		//750 PRINT
		//760 PRINT "YOU HAVE SUCCEEDED IN YOUR"
		//770 PRINT "QUEST AND BROUGHT PEACE TO"
		//780 PRINT "THE LAND"
		//790 STOP
	}
	
	private void loseGame() {
		//690 GOSUB 4400:PRINT R$
		//700 PRINT "YOU HAVE FAILED IN YOUR QUEST!"
		//710 PRINT:PRINT "BUT YOU ARE GRANTED ANOTHER TRY"
		//720 GOSUB 3360:RUN
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
	
	private ActionResult sank(Game game, Player player) {
		game.addMessage("You sank!", false, false);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	private ActionResult inRoughWater(Game game,Player player) {
		int boat_hp = game.getItem(GameEntities.FLAG_BOAT_WORN).getItemFlag();
		game.getItem(GameEntities.FLAG_BOAT_WORN).setItemFlag(boat_hp++);
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
 */
