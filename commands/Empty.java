package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Empty {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code EMPTY} handler for executing a empty command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Empty(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an empty action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeEmpty() {
		game.addMessage("You cannot Empty that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		return result;
	}
}
