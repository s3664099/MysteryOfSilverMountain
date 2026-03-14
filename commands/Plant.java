/*
Title: Mystery of Silver Mountain Plant Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 14 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Plant {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code PLANT} handler for executing a plant command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Plant(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an plant action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executePlant() {
		game.addMessage("It does not grow!", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isInGarden(game,player.getRoom(),command.getNounNumber())) {
			result = inGarden(game,player);
		}
				
		return result;
	}
	
	private boolean isInGarden(Game game, int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_GARDEN &&
				nounNumber == GameEntities.ITEM_SEEDS &&
				game.getItem(GameEntities.ITEM_SEEDS).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult inGarden(Game game,Player player) {
		game.addMessage("OK", true, false);
		game.getItem(GameEntities.FLAG_SEED_PLANTED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
}
/* 13 March 2026 - Created File
 * 14 March 2026 - Added Action results
 */

