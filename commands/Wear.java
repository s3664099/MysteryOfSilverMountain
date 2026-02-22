/*
Title: Mystery of Silver Mountain Wear Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 22 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Wear {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Wear} handler for wearing items
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Wear(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeWear() {
		game.addMessage("It's not possible.", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isWearBoots(game,command.getNounNumber())) {
			result = wearBoots(game,player);
		} else if (isWearUniform(game,command.getNounNumber())) {
			result = wearUniform(game,player);
		}
		
		return result;
	}
	
	private boolean isWearBoots(Game game, int nounNumber) {
		return game.getItem(GameEntities.ITEM_BOOTS).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.FLAG_WEARING_BOOTS).getItemFlag() == 0 &&
				nounNumber == GameEntities.ITEM_BOOTS;
	}
	
	private ActionResult wearBoots(Game game,Player player) {
		game.getItem(GameEntities.FLAG_WEARING_BOOTS).setItemFlag(1);
		game.getItem(GameEntities.FLAG_PLAYER_SPOTTED).setItemFlag(0);
		game.addMessage("You are invisble", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isWearUniform(Game game, int nounNumber) {
		return game.getItem(GameEntities.ITEM_UNIFORM).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.FLAG_WEARING_UNFORM).getItemFlag() == 0 &&
				nounNumber == GameEntities.ITEM_UNIFORM;
	}
	
	private ActionResult wearUniform(Game game,Player player) {
		game.getItem(GameEntities.FLAG_WEARING_UNFORM).setItemFlag(1);
		game.getItem(GameEntities.FLAG_PLAYER_SPOTTED).setItemFlag(0);
		game.addMessage("You are disquised", true, false);
		return new ActionResult(game,player,true);
	}
}

/*
 * 22 February 2026 - Created File
 */
