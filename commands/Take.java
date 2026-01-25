/*
Title: Mystery of Silver Mountain Take Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 24 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
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
		}
				
		//1330 CO=0:FOR I=1 TO G-1: IF C(I)=0 THEN CO=CO+1
		//1340 NEXT I:IF CO>13 THEN R$="YOU CANNOT CARRY ANY MORE":RETURN
		//Carrying too much
		
		//1350 IF B>G THEN R$="YOU CANNOT GET THE "+T$:RETURN
		//Can take this?
		
		//1360 IF B=0 THEN RETURN
		//Not an item?
		
		//1390 IF C(B)=0 THEN R$="YOU ALREADY HAVE IT"
		//Already carrying item
		
		//1370 IF C(B)<>R THEN R$="IT IS NOT HERE!"
		//Item not in room
		
		//1380 IF F(B)=1 THEN R$="WHAT "+T$+"?"
		//Flag of Item
		
		//1400 IF C(B)=R AND F(B)=0 THEN C(B)=0:R$="YOU HAVE THE "+T$
		//Move item to inventory
		
		//1410 IF B=28 THEN C(5)=81
		//1420 IF B=5 THEN C(28)=0
		//Apples?
		
		//1430 IF C(4)=0 AND C(12)=0 AND C(15)=0 THEN F(54)=1
		//Carrying Horseshoe,Shield & rings
		
		//1440 IF B=8 AND F(30)=1 THEN C(2)=0
		//Boat & takes sheet
		
		//1450 IF B=2 THEN F(30)=0
		//Takes Sheet
		
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
}

/* 22 January 2026 - Created File
 * 23 January 2026 - Added parsed command
 * 24 January 2026 - Added taking water
 */
