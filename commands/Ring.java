/*
Title: Mystery of Silver Mountain Ring Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 20 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Ring {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code RING} handler for executing a ring command
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Ring(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;	
	}
	
    /**
     * Validates whether a ring action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeRing() {
		game.addMessage("How?", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isRingBell(player.getRoom(),command.getNounNumber())) {
			
			if (isTwoWords(command)) {
				result = twoWords(game,player);
			} else if (isInteger(command.getSplitFullCommand()[2].trim())) {
				
			}

			//Check if third is an integer
				//Otherwise enter please enter a number
			//Checks if matches flag
		}
		
		return result;
	}
	
	private boolean isRingBell(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_BELL &&
				nounNumber == GameEntities.ITEM_BELL;
	}
	
	private boolean isTwoWords(ParsedCommand command) {
		return command.getSplitFullCommand().length<3;
	}
	
	private ActionResult twoWords(Game game, Player player) {
		game.addMessage("How many times?", false, false);
		return new ActionResult(game,player, true);
	}
	
	private boolean isInteger(String command) {

		boolean integer = true;
		try {
	        Integer.parseInt(command);
	    } catch (NumberFormatException e) {
	    	 integer = false;
	    }
		return integer;
	}
	
	//3030 IF MR=F(42) THEN R$="A ROCK DOOR OPENS": E$(27)="EW":RETURN
	//3040 R$="ZPV IBWF NJTUSFBUFE UIF CFMM!": F(56)=1:GOSUB 4260:RETURN
}

/* 30 April 2026 - Created File
 */
