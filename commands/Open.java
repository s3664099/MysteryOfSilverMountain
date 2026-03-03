package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
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
		ActionResult result = new ActionResult(game,player,true);
		return result;
	}
	
	//2220 IF B=76 OR B=38 THEN GOSUB 1470
	//2230 IF H=2030 THEN F(9)=0: R$="OK"
	//2240 IF H=6030 THEN R$="OK":KET F(3)=0
	//2250 IF H=2444 OR H=1870 THEN R$="YOU ARE NOT STRING ENOUGH"
	//2260 IF H=3756 THEN R$="A PASSAGE!": E$(37)="EW"
	//2270 IF H=5960 THEN GOSUB 3260
	//2280 IF H=6970 THEN R$="IT FALLS OFF ITS HINGES"
	//2290 IF H=4870 THEN R$="IT IS LOCKED"
	
	
}
