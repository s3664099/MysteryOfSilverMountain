package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import game.Game;
import game.Player;

public class Swing {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code SWING} handler for executing a swing command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Swing(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an swing action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeSwing() {
		game.addMessage("You cannot Swing that", true, false);
		ActionResult result = new ActionResult(game,player,true);		
		return result;
	}
}
