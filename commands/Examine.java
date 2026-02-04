/*
Title: Mystery of Silver Mountain Examine Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.3
Date: 4 February 2026
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
			} else if (isExamineUniform(command.getNounNumber(),game)) {
				result = examineUniform(game,player);
			} else if (isReadInscription(command.getNounNumber(),player.getRoom())) {
				result = readInscription(game,player);
			} else if (isExamineTrees(command.getNounNumber(),player.getRoom())) {
				result = examineTrees(game,player);
			} else if (isExamineKiln(command.getNounNumber(),player.getRoom())) {
				result = examineKiln(game,player);
			} else if (isExamineKetch(command.getNounNumber(),player.getRoom())) {
				result = examineKetch(game,player);
			} else if (isExamineSacks(command.getNounNumber(),player.getRoom())) {
				result = examineSacks(game,player);
			} else if (isExamineGravestone(command.getNounNumber(),player.getRoom())) {
				result = examineGravestone(game,player);
			} else if (isExaminePool(command.getNounNumber(),player.getRoom(),game)) {
				result = examinePool(game,player);
			} else if (isExaminePoolFoundShield(command.getNounNumber(),player.getRoom(),game)) {
				result = examinePoolFoundShield(game,player);
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
	
	private boolean isExamineMountainHut(int noun, int room) {
		return (room == GameEntities.ROOM_WHITE_COTTAGE && noun == GameEntities.ITEM_MOUNTAIN_HUT);
	}
	
	private ActionResult examineMountainHut(Game game, Player player) {
		game.addMessage("Aha!", true, false);
		game.getItem(GameEntities.FLAG_COINS).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	private boolean isExamineAttic(int noun, int room) {
		return (room == GameEntities.ROOM_ATTIC && noun == GameEntities.ITEM_CUPBOARD);
	}
	
	private ActionResult examineAttic(Game game, Player player) {
		game.addMessage("Ok", true, false);
		game.getItem(GameEntities.FLAG_SHEET).setItemFlag(0);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineUniform(int noun, Game game) {
		return (game.getItem(GameEntities.ITEM_UNIFORM).getItemLocation() == GameEntities.ROOM_CARRYING &&
				noun == GameEntities.ITEM_UNIFORM);
	}
	
	private ActionResult examineUniform(Game game, Player player) {
		game.addMessage("You find matches in the pocket", true, false);
		game.getItem(GameEntities.ITEM_MATCHES).setItemLocation(GameEntities.ROOM_CARRYING);
		return new ActionResult(game,player,true);
	}
	
	private boolean isReadInscription(int noun, int room) {
		return noun == GameEntities.ITEM_INSCRIPTION && room == GameEntities.ROOM_INSCRIBED_CAVERN;
	}

	private ActionResult readInscription(Game game, Player player) {
		game.addMessage("There are some letters '"+game.getMaze(1)+"'", true, false);		
		return new ActionResult(game,player,true);
	}

	private boolean isExamineTrees(int noun, int room) {
		return noun == GameEntities.ITEM_TREES && room == GameEntities.ROOM_ORCHARD;
	}
	
	private ActionResult examineTrees(Game game, Player player) {
		game.addMessage("They are apple trees.", true, false);
		game.getItem(GameEntities.FLAG_APPLES).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	private boolean isExamineKiln(int noun, int room) {
		return (noun == GameEntities.ITEM_KILN || noun == GameEntities.ITEM_OLD_KILN) &&
				room == GameEntities.ROOM_KILN;
	}
	
	private ActionResult examineKiln(Game game, Player player) {
		game.addMessage("OK", true, false);
		game.getItem(GameEntities.FLAG_JUG).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	private boolean isExamineKetch(int noun, int room) {
		return noun == GameEntities.ITEM_KETCH && room == GameEntities.ROOM_KETCH;
	}
	
	private ActionResult examineKetch(Game game, Player player) {
		game.addMessage("It is fishy!", true, false);
		game.getItem(GameEntities.FLAG_NET).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	private boolean isExamineSacks(int noun, int room) {
		return noun == GameEntities.ITEM_SACK && room == GameEntities.ROOM_SACKS;
	}
	
	private ActionResult examineSacks(Game game, Player player) {
		game.addMessage("Ok", true, false);
		game.getItem(GameEntities.FLAG_SEEDS).setItemFlag(0);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineGravestone(int noun, int room) {
		return noun == GameEntities.ITEM_GRAVESTONE && room == GameEntities.ROOM_GRAVEYARD;
	}

	private ActionResult examineGravestone(Game game,Player player) {
		game.addMessage("You see a faded inscription.", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean isExaminePool(int noun, int room, Game game) {
		return (noun == GameEntities.ITEM_POOL || noun == GameEntities.ITEM_MISTY_POOL) &&
				game.getItem(GameEntities.FLAG_SHEILD_REVEALED).getItemFlag() == 0 &&
				room == GameEntities.ROOM_POOL;
	}
	
	private ActionResult examinePool(Game game, Player player) {
		game.addMessage("A glimmering from the depths", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isExaminePoolFoundShield(int noun, int room, Game game) {
		return (noun == GameEntities.ITEM_POOL || noun == GameEntities.ITEM_MISTY_POOL) &&
				game.getItem(GameEntities.FLAG_SHEILD_REVEALED).getItemFlag() == 1 &&
				game.getItem(GameEntities.FLAG_SHIELD).getItemFlag() == 0  &&
				room == GameEntities.ROOM_POOL;
	}
	
	private ActionResult examinePoolFoundShield(Game game, Player player) {
		game.addMessage("Something here ...", true, false);
		game.getItem(GameEntities.FLAG_SHIELD).setItemFlag(0);
		return new ActionResult(game,player,true);
	}




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
 * 2 February 2026 - Started added examine results
 * 3 February 2026 - Continued added examine results
 * 4 February 2026 - Added further examine results
 */
