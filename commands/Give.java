/*
Title: Mystery of Silver Mountain Give Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 13 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/
package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Give {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Drop} handler for moving items from the users inventory
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Give(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeGive() {
		
		ActionResult result = new ActionResult(game,player,false);
		
		if (isGiveBroach(player.getRoom(),game,command.getNounNumber())) {
			result = giveBroach(game,player);
		} else if (isInValley(player.getRoom())) {
			result = inValley(game,player);
		} else if (isGiveCoinToTroll(player.getRoom(),game,command.getNounNumber())) {
			result = giveCoinToTroll(game,player);
		}
		
		return result;
	}
	
	private boolean isInValley(int room) {
		return room == GameEntities.ROOM_VALLEY;
	}
	
	private ActionResult inValley(Game game,Player player) {
		game.addMessage("He gives it back!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGiveBroach(int room, Game game, int nounNumber) {
		return room == GameEntities.ROOM_VALLEY && nounNumber == GameEntities.ITEM_BROOCH &&
				game.getItem(GameEntities.ITEM_BROOCH).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult giveBroach(Game game, Player player) {
		game.getItem(GameEntities.ITEM_BROOCH).setItemLocation(GameEntities.ROOM_DESTROYED);
		game.addMessage("He takes it and says '"+game.getItems(GameEntities.FLAG_RING_NUMBER)+" "
					+ "rings are needed'.", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGiveCoinToTroll(int room,Game game,int nounNumber) {
		return (room == GameEntities.ROOM_BRIDGE_EAST || room == GameEntities.ROOM_BRIDGE_WEST) &&
				game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag()>0 &&
				game.getItem(GameEntities.ITEM_COIN).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private boolean anyCoinsLeft(int coinNumbers) {
		return coinNumbers == 0;
	}
	
	private ActionResult giveCoinToTroll(Game game, Player player) {
		game.addMessage("He takes it", true, false);
		game.getItem(GameEntities.FLAG_TROLL).setItemFlag(1);
		int coinNumbers = game.getItem(GameEntities.FLAG_COIN_NUMBERS).getItemFlag();
		game.getItem(GameEntities.FLAG_COIN_NUMBERS).setItemFlag(coinNumbers --);
		if (anyCoinsLeft(coinNumbers --)) {
			game.getItem(GameEntities.ITEM_COIN).setItemLocation(GameEntities.ROOM_DESTROYED);
		}
		return new ActionResult(game,player,true);
	}
	

	


	//1770 IF R=75 OR R=76 THEN R$="HE DOES NOT WANT IT" - Give any to troll
	//1780 IF B=62 AND F(44)=0 THEN R$="YOU HAVE RUN OUT!" - No coins
	//1810 IF B=1 THEN R$="HE TAKES THEM ALL!": C(1)=81: F(64)=1: F(44)=0 - give coins


	//1820 IF H=2228 AND C(5)=81 THEN R$=XB$+"NORTH": C(28)=81: R=12
	//1830 IF (H=2228 AND C(5)=0) OR H=225 THEN R$=XB$+"NORTH": R=12
	//1840 IF (H=1228 AND C(5)=0) OR H=125 THEN R$=XB$+"SOUTH": R=22
	//1850 IF R=7 OR R=33 THEN R$="HE EATS IT!": C(B)=81
	//1860 IF H=711 THEN F(46)=1: R$="HE IS DISTRACTED"
	//1870 IF H=385 OR H=3824 THEN R$="THEY SCURRY AWAY": C(B)=81: F(65)=1
}

/* 12 February 2026 - Created Class
 * 13 February 2026 - Added give brooch and started with troll
 */
