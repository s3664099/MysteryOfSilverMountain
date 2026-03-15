/*
Title: Mystery of Silver Mountain Water Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 15 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Water {
    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code PLANT} handler for executing a water command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Water(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether a water action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeWater() {
		game.addMessage("That is not possible!", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isWaterSeeds(game,player.getRoom(),command.getNounNumber())) {
			result = waterSeeds(game,player);
		}
		
		return result;
	}
	
	private boolean isWaterSeeds(Game game,int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_GARDEN &&
				nounNumber == GameEntities.ITEM_SEEDS &&
				game.getItem(GameEntities.ITEM_JUG).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.FLAG_JUG_FULL).getItemFlag() == 1 &&
				game.getItem(GameEntities.FLAG_SEED_PLANTED).getItemFlag() == 1;
	}
	
	private ActionResult waterSeeds(Game game,Player player) {
		game.getItem(GameEntities.FLAG_VINE_CLIMBABLE).setItemFlag(1);
		game.getItem(GameEntities.FLAG_JUG_FULL).setItemFlag(0);
		game.addMessage("A large vine grows in seconds!",true, false);
		return new ActionResult(game,player,true);
	}
}

/* 14 March 2026 - Created File
 * 15 March 2026 - Completed action responses
 */
