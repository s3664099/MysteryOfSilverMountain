/*
Title: Mystery of Silver Mountain Examine Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 1 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

Need to set it so that examine can work as a single command
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Examine {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates an {@code Examine} handler for retrieving details of an item
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Examine(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeExamine() {
		game.addMessage("You see what you might expect!", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if (command.getNounNumber()>0) {
			game.addMessage("You see nothing special!", true, false);
			
			if (isExamineHut(command.getNounNumber(),player.getRoom())) {
				result = examineHut(game,player);
			} else if (isExamineChest(command.getNounNumber(),player.getRoom())) {
				result = examineChest(game,player);
			} else if (isExamineMountainHut(command.getNounNumber(),player.getRoom())) {
				result = examineMountainHut(game,player);
			} else if (isExamineAttic(command.getNounNumber(),player.getRoom())) {
				result = examineAttic(game,player);
			}
		}
				
		return result;
	}
	
	private boolean isExamineHut(int command,int room) {
		return ((command == GameEntities.ITEM_HUT || command == GameEntities.ITEM_MOUNTAIN_HUT) &&
			room == GameEntities.ROOM_HUT);
	}
	
	private ActionResult examineHut(Game game,Player player) {
		
		game.addMessage("You found something", true, false);
		game.getItem(GameEntities.FLAG_PLANKS).setItemFlag(0);
		
		return new ActionResult(game,player,true);
	}
	
	private boolean isExamineChest(int noun,int room) {
		return (room == GameEntities.ROOM_WHITE_COTTAGE && noun == GameEntities.ITEM_CHEST);
	}
	
	private ActionResult examineChest(Game game, Player player) {
		game.addMessage("It is empty", true, false);
		return new ActionResult(game,player,true);
	}





	//1510 IF H=8080 THEN R$="AHA!": F(1)=0
	//1520 IF H=7029 THEN R$="OK": F(2)=0
	//1530 IF B=20 THEN R$="NBUDIFT JO QPDLFU":GOSUB 4260: C(26)=0
	//1540 IF H=1648 THEN R$="THERE ARE SOME TERS '"+G$(2)+"'"
	//1550 IF H=7432 THEN R$="UIFZ BSF BQQMF USFFT":GOSUB 4260: F(5)=0
	//1560 IF H=2134 OR H=2187 THEN R$="OK": F(16)=0
	//1570 IF B=35 THEN R$="IT IS FISHY!": F(17)=0
	//1580 IF H=3438 THEN R$="OK": F(22)=0
	//1590 IF H=242 THEN R$="A FADED INSCRIPTION"
	//1600 IF(H=1443ORH=1485)ANDF(33)=0THENR$="B HMJNNFSJOH GSPN UIF EFQUIT":GOSUB4260
	//1610 IF (H=1443 OR H=1485) AND F(33)=1 THEN R$="SOMETHING HERE...": F(12)=0
	//1620 IF H=2479 OR H=2444 THEN R$="THERE IS A HANDLE"
	//1630 IF B=9 THEN R$="UIF MBCFM SFBET 'QPJTPO'":GOSUB 4260
	//1640 IF H=4055 THEN GOSUB 3290
	//1650 IF H=2969 AND F(48)=1 THEN R$="VERY UGLY!"
	//1660 IF H=7158 OR H=7168 THEN R$="THERE ARE LOOSE BRICKS"
	//1670 IF R=49 THEN R$="VERY INTERESTING!"
	//1680 IF B=52 OR B=82 OR B=81 THEN R$="INTERESTING!"
	//1690 IF H=6978 THEN R$="THERE IS A WOODEN DOOR"
	//1700 IF H=6970 THEN R$="YOU FOUND SOMETHING": F(4)=0
	//1710 IF H=2066 THEN R$="A LARGE CUPBOARD IN THE CORNER"
	//1720 IF H=6865 OR H=6853 THEN R$="THERE ARE NINE STONES"
	//1730 IF H=248 THEN R$="B GBEFE XPSE - 'N S I T'":GOSUB 4260
}

/* 1 February 2026 - Created file
*/