/*
Title: Mystery of Silver Mountain Light Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 9 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Light {

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
	public Light(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
    /**
     * Validates whether an open action is possible and the response
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeLight() {
		game.addMessage("You cannot light that", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (isLightMatches(game,command.getNounNumber())) {
			if (isInCellar(player.getRoom())) {
				result = lightMatchesInCellar(game,player);
			} else {
				result = lightMatches(game,player);
			}
		} else if (isLightLampWithMatches(game,command.getNounNumber())) {
			result = lightLampWithMatches(game,player);
		} else if (isLightStableDoor(game,command.getNounNumber(),player.getRoom())) {
			result = lightStableDoor(game,player);
		} else if (isLightWithoutMatches(game,command.getNounNumber(),player.getRoom())) {
			result = lightWithoutMatches(game,player);
		} else if (isBurnSomething(command.getNounNumber())) {
			result = burnSomething(game,player);
		}
		
		return result;
	}
	
	private boolean isLightMatches(Game game,int nounNumber) {
		return game.getItem(GameEntities.ITEM_MATCHES).getItemLocation() == GameEntities.ROOM_CARRYING &&
				nounNumber == GameEntities.ITEM_MATCHES;
	}
	
	private ActionResult lightMatches(Game game,Player player) {
		game.addMessage("You lit them", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isInCellar(int roomNumber) {
		return roomNumber == GameEntities.ROOM_WINE_CELLAR;
	}
	
	private ActionResult lightMatchesInCellar(Game game,Player player) {
		game.addMessage("Not bright enough", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isLightLampWithMatches(Game game,int nounNumber) {
		return game.getItem(GameEntities.ITEM_MATCHES).getItemLocation() == GameEntities.ROOM_CARRYING &&
				game.getItem(GameEntities.ITEM_LAMP).getItemLocation() == GameEntities.ROOM_CARRYING &&
				nounNumber == GameEntities.ITEM_LAMP;
	}
	
	private ActionResult lightLampWithMatches(Game game,Player player) {
		game.addMessage("A bright light.", true, false);
		game.getItem(GameEntities.FLAG_IS_DARK).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	private boolean isLightStableDoor(Game game,int nounNumber,int roomNumber) {
		return game.getItem(GameEntities.ITEM_MATCHES).getItemLocation() == GameEntities.ROOM_CARRYING &&
				roomNumber == GameEntities.ROOM_STABLE &&
				nounNumber == GameEntities.ITEM_LAMP;
	}
	
	private ActionResult lightStableDoor(Game game,Player player) {
		game.addMessage("It has turned to ashes.", true, false);
		game.getItem(GameEntities.FLAG_HORSESHOE_NAILED_ON).setItemFlag(1);
		return new ActionResult(game,player,true);
	}	

	private boolean isLightWithoutMatches(Game game,int nounNumber,int roomNumber) {
		return (game.getItem(GameEntities.ITEM_LAMP).getItemLocation() == GameEntities.ROOM_CARRYING &&
				nounNumber == GameEntities.ITEM_LAMP) ||
				(player.getRoom()==GameEntities.ROOM_STABLE &&
				nounNumber == GameEntities.ITEM_DOOR);
	}
	
	private ActionResult lightWithoutMatches(Game game,Player player) {
		game.addMessage("No matches.", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isBurnSomething(int nounNumber) {
		return nounNumber > Constants.MAX_CARRIABLE_ITEMS;
	}
	
	private ActionResult burnSomething(Game game,Player player) {
		game.addMessage("It does not burn.", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 9 March 2026 - Created File
 */
