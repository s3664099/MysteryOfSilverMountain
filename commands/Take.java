/*
Title: Mystery of Silver Mountain Take Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.5
Date: 27 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

public class Take {
	
	private final Game game;
	private final Player player;
	private final ParsedCommand command;
	
	public Take(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeTake() {
		
		ActionResult result = new ActionResult(game,player,true);
		
		if (isTakingWater(player.getRoom(),command.getNounNumber())) {
			result = takingWater(game,player);
		} else if (isTakingWaterInLake(player.getRoom(),command.getNounNumber())) {
			result = takingWaterInLake(game,player);
		} else if (isTakingSacks(player.getRoom(),command.getNounNumber())) {
			result = takingSacks(game,player);
		} else if (isHorseShoeNailedOn(player.getRoom(),command.getNounNumber(),game)) {
			result = horseShoeNailedOn(game,player);
		} else if (isCarryingTooMuch()) {
			result = carryingTooMuch(game,player);
		} else if (isItemCarriable(command.getNounNumber())) {
			result = itemNotCarriable(game,player);
		} else if (isItemAlreadyCarried(command.getNounNumber())) {
			result = itemAlreadyCarried(game,player);
		} else if (isItemNotPresent(command.getNounNumber(),player.getRoom())) {
			result = itemNotPresent(game,player);
		} else if (isItemNotTakeable(command.getNounNumber())) {
			result = itemNotTakeable(game,player,command.getSplitTwoCommand()[1]);
		} else if (isItemTaken(game,command.getNounNumber(),player.getRoom())) {
			result = itemTaken(game,player,command.getNounNumber());
		}
				
		return result;
	}
	
	private boolean isTakingWater(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_VALLEY_BOTTOM && nounNumber == GameEntities.ITEM_WATER;
	}
		
	private ActionResult takingWater(Game game, Player player) {
		game.addMessage("How?", true, true);
		return new ActionResult(game,player, true);
	}
	
	private boolean isTakingWaterInLake(int roomNumber,int nounNumber) {
		return (roomNumber == GameEntities.ROOM_ROUGH_WATER || roomNumber == GameEntities.ROOM_MIDDLE_LAKE) &&
				nounNumber == GameEntities.ITEM_WATER;
	}
	
	private ActionResult takingWaterInLake(Game game,Player player) {
		game.addMessage("You capsized", true, true);
		game.getItem(GameEntities.FLAG_PLAYER_FAILED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}

	private boolean isTakingSacks(int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_SACKS && nounNumber == GameEntities.ITEM_SACK;
	}
	
	private ActionResult takingSacks(Game game,Player player) {
		game.addMessage("Too heavy!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isHorseShoeNailedOn(int roomNumber, int nounNumber, Game game) {
		return roomNumber == GameEntities.ROOM_STABLE && nounNumber == GameEntities.ITEM_HORSESHOE &&
				game.getItem(GameEntities.FLAG_HORSESHOE_NAILED_ON).getItemFlag() == 0;
	}
	
	private ActionResult horseShoeNailedOn(Game game, Player player) {
		game.addMessage("It is firmly nailed on!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isCarryingTooMuch() {
		return game.countItemsCarrying()>Constants.INVENTORY_SPACE; 
	}
	
	private ActionResult carryingTooMuch(Game game,Player player) {
		game.addMessage("You cannot carry any more.", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isItemCarriable(int nounNumber) {
		return nounNumber>=Constants.MAX_CARRIABLE_ITEMS; 
	}
	
	private ActionResult itemNotCarriable(Game game, Player player) {
		game.addMessage("It is not possible to take that.", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isItemAlreadyCarried(int nounNumber) {
		return game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult itemAlreadyCarried(Game game, Player player) {
		game.addMessage("You already have it",true,true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isItemNotPresent(int nounNumber,int playerLocation) {
		return game.getItem(nounNumber).getItemLocation() != playerLocation;
	}
	
	private ActionResult itemNotPresent(Game game, Player player) {
		game.addMessage("It is not here!",true,true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isItemNotTakeable(int nounNumber) {
		return game.getItem(nounNumber).getItemFlag() ==1;
	}
	
	private ActionResult itemNotTakeable(Game game, Player player,String noun) {
		game.addMessage("What "+noun+"?", isCarryingTooMuch(), isCarryingTooMuch());
		return new ActionResult(game,player,true);
	}
	
	private boolean isItemTaken(Game game,int nounNumber,int roomNumber) {
		return game.getItem(nounNumber).getItemLocation() == nounNumber &&
				game.getItem(nounNumber).getItemFlag() == 0;
	}
	
	private ActionResult itemTaken(Game game, Player player, int nounNumber) {
		game.addMessage("You have the "+game.getItem(nounNumber), true, true);
		game.getItem(nounNumber).setItemLocation(GameEntities.ROOM_CARRYING);
		
		if (isItemApples(nounNumber)) {
			game.getItem(GameEntities.ITEM_APPLE).setItemLocation(GameEntities.ROOM_DESTROYED);
		}
		
		if (isItemApple(nounNumber)) {
			game.getItem(GameEntities.ITEM_APPLES).setItemLocation(GameEntities.ROOM_CARRYING);
		}
		
		return new ActionResult(game,player,true);
	}
	
	private boolean isItemApples(int nounNumber) {
		return nounNumber == GameEntities.ITEM_APPLE;
	}
	
	private boolean isItemApple(int nounNumber) {
		return nounNumber == GameEntities.ITEM_APPLES;
	}
	
	//1430 IF C(4)=0 AND C(12)=0 AND C(15)=0 THEN F(54)=1
	//Carrying Horseshoe,Shield & rings
	
	//1440 IF B=8 AND F(30)=1 THEN C(2)=0
	//Boat & takes sheet
	
	//1450 IF B=2 THEN F(30)=0
	//Takes Sheet
}

/* 22 January 2026 - Created File
 * 23 January 2026 - Added parsed command
 * 24 January 2026 - Added taking water
 * 24 January 2026 - Added sacks & horseshoe
 * 25 January 2026 - Added inventory limitation
 */
