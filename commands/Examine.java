/*
Title: Mystery of Silver Mountain Examine Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.7
Date: 9 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

Need to set it so that examine can work as a single command
Also, can only read certain objects, others will fail
Change flag 48 to flag grarg asleep
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.Constants;
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
		
		if(isInMosaicRoom(player.getRoom())) {
			result = inMosaicRoom(game,player);
		}
		
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
			} else if (isExamineSluiceGates(command.getNounNumber(),player.getRoom())) {
				result = examineSluiceGates(game,player);
			} else if (isExaminePhial(command.getNounNumber(),game)) {
				result = examinePhial(game,player);
			} else if (isExamineBooks(command.getNounNumber(),player.getRoom())) {
				result = examineBooks(game,player);
			} else if (isExamineGrarg(command.getNounNumber(),player.getRoom(),game)) {
				result = examineGrarg(game,player);
			} else if (isExamineWellBottom(command.getNounNumber(),player.getRoom())) {
				result = examineWellBottom(game,player);
			} else if (isExaminePinnacle(command.getNounNumber(),player.getRoom()) ||
					isExamineFountain(command.getNounNumber(),player.getRoom()) ||
					isExamineStatue(command.getNounNumber(),player.getRoom())) {
				result = isInteresting(game,player);
			} else if (isExamineStables(command.getNounNumber(),player.getRoom())) {
				result = examineStables(game,player);
			} else if (isExamineDoor(command.getNounNumber(),player.getRoom())) {
				result = examineDoor(game,player);
			} else if (isExamineKitchen(command.getNounNumber(),player.getRoom())) {
				result = examineKitchen(game,player);
			} else if (isExamineStones(command.getNounNumber(),player.getRoom())) {
				result = examineStones(game,player);
			} else if (isExamineInscription(command.getNounNumber(),player.getRoom())) {
				result = examineInscription(game,player);
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
	
	private boolean isExamineSluiceGates(int noun, int room) {
		return (noun == GameEntities.ITEM_GATE || noun == GameEntities.ITEM_SLUICE) &&
				room == GameEntities.ROOM_SLUICE_GATES;
	}
	
	private ActionResult examineSluiceGates(Game game, Player player) {
		game.addMessage("There is a handle.", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean isExaminePhial(int noun, Game game) {
		return game.getItem(GameEntities.ITEM_PHIAL).isAtLocation(GameEntities.ROOM_CARRYING) &&
				noun == GameEntities.ITEM_PHIAL;
	}
	
	private ActionResult examinePhial(Game game, Player player) {
		game.addMessage("The label reads 'Poison'.", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineBooks(int noun, int room) {
		return room == GameEntities.ROOM_LIBRARY && noun == GameEntities.ITEM_BOOKS;
	}
	
	private ActionResult examineBooks(Game game, Player player) {
		
		String wordLocation = game.getRawRoomName(game.getItem(GameEntities.FLAG_WORD_LOCATION).getItemFlag()
												+ Constants.RANDOM_WORD_CONSTANT);
		wordLocation = wordLocation.substring(2,wordLocation.length()-4);
		game.addMessage("Magic words lie at the crossroads, the fountain and the "+wordLocation, true, true);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineGrarg(int noun, int room, Game game) {
		return noun == GameEntities.ITEM_GRARG && room == GameEntities.ROOM_BANQUET_HALL &&
				game.getItem(GameEntities.FLAG_FORTY_EIGHT).getItemFlag() == 1;
	}
	
	private ActionResult examineGrarg(Game game, Player player) {
		game.addMessage("Very ugly!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isExamineWellBottom(int noun, int room) {
		return (noun == GameEntities.ITEM_WALL || noun == GameEntities.ITEM_WELL_BOTTOM) && 
				room == GameEntities.ROOM_WELL_BOTTOM;
	}
	
	private ActionResult examineWellBottom(Game game, Player player) {
		game.addMessage("There are loose bricks.", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isInMosaicRoom(int room) {
		return room == GameEntities.ROOM_MOSAIC_HALL;
	}
	
	private ActionResult inMosaicRoom(Game game, Player player) {
		game.addMessage("Very interesting.", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isExaminePinnacle(int noun, int room) {
		return noun == GameEntities.ITEM_PINNACLE && room == GameEntities.ROOM_PINNACLE;
	}
	
	private boolean isExamineFountain(int noun, int room) {
		return noun == GameEntities.ITEM_FOUNTAIN && room == GameEntities.ROOM_FOUNTAIN;
	}
	
	private boolean isExamineStatue(int noun, int room) {
		return noun == GameEntities.ITEM_STATUE && room == GameEntities.ROOM_CROSSROADS;
	}
	
	private ActionResult isInteresting(Game game, Player player) {
		game.addMessage("Interesting!", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineStables(int noun, int room) {
		return noun == GameEntities.ITEM_STABLES && room == GameEntities.ROOM_STABLE;
	}
	
	private ActionResult examineStables(Game game, Player player) {
		game.addMessage("There is a wooden door.", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineDoor(int noun, int room) {
		return noun == GameEntities.ITEM_DOOR && room == GameEntities.ROOM_STABLE;
	}
	
	private ActionResult examineDoor(Game game, Player player) {
		game.addMessage("You found somethings.", true, false);
		game.getItem(GameEntities.FLAG_HORSESHOE).setItemFlag(0);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineKitchen(int noun, int room) {
		return noun == GameEntities.ITEM_KITCHEN && room == GameEntities.ROOM_KITCHEN;
	}
	
	private ActionResult examineKitchen(Game game, Player player) {
		game.addMessage("You see a large cupboard in the corner.", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineStones(int noun, int room) {
		return noun == GameEntities.ITEM_STONES && room == GameEntities.ROOM_STONE_CIRCLE;
	}
	
	private ActionResult examineStones(Game game, Player player) {
		game.addMessage("There are nine stones.", true, false);
		return new ActionResult(game,player,true);
	}

	private boolean isExamineInscription(int noun, int room) {
		return noun == GameEntities.ITEM_STONES && room == GameEntities.ROOM_STONE_CIRCLE;
	}
	
	private ActionResult examineInscription(Game game, Player player) {
		game.addMessage("A faded word - 'M R H S'.", true, false);
		return new ActionResult(game,player,true);
	}
}

/* 1 February 2026 - Created file
 * 2 February 2026 - Started added examine results
 * 3 February 2026 - Continued added examine results
 * 4 February 2026 - Added further examine results
 * 6 February 2026 - Added three more results
 * 7 February 2026 - Completed examine books & other responses
 * 8 February 2026 - Added interesting results
 * 9 February 2026 - Completed examine responses
 */
