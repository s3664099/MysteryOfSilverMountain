/*
Title: Mystery of Silver Mountain Examine Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.8
Date: 10 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up

Need to set it so that examine can work as a single command
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
	
    /**
     * Validates whether there is a response from the examine command,
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeExamine() {
		game.addMessage("You see nothing special!", true, false);
		ActionResult result = new ActionResult(game,player,true);
		
		if(isInMosaicRoom(player.getRoom())) {
			result = inMosaicRoom(game,player);
		}
		
		if (command.getVerbNumber() == GameEntities.CMD_COUNT) {
			if (isExamineStones(command.getNounNumber(),player.getRoom())) {
				result = examineStones(game,player);
			} else {
				game.addMessage("You see only one!", true, false);
				result = new ActionResult(game,player,true);
			}
		} else if (command.getVerbNumber() == GameEntities.CMD_READ) {
			if (isReadInscription(command.getNounNumber(),player.getRoom())) {
				result = readInscription(game,player);
			} else if (isExamineBooks(command.getNounNumber(),player.getRoom())) {
				result = examineBooks(game,player);
			} else if (isExamineInscription(command.getNounNumber(),player.getRoom())) {
				result = examineInscription(game,player);
			} else {
				game.addMessage("There is nothing written here!", true, false);
				result = new ActionResult(game,player,true);
			}
		} else {
						
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
	
	/**
	 * Returns true if the command is examining the hut at the hut's location
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineHut(int noun,int room) {
		return ((noun == GameEntities.ITEM_HUT || noun == GameEntities.ITEM_MOUNTAIN_HUT) &&
			room == GameEntities.ROOM_HUT);
	}
	
    /**
     * Executes a response to Examining the hut
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineHut(Game game,Player player) {
		
		game.addMessage("You found something", true, false);
		game.getItem(GameEntities.FLAG_PLANKS).setItemFlag(0);
		
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the chest in the cottage
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineChest(int noun,int room) {
		return (room == GameEntities.ROOM_WHITE_COTTAGE && noun == GameEntities.ITEM_CHEST);
	}
	
    /**
     * Executes a response to Examining the chest
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineChest(Game game, Player player) {
		game.addMessage("It is empty", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the mountain hut
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineMountainHut(int noun, int room) {
		return (room == GameEntities.ROOM_WHITE_COTTAGE && noun == GameEntities.ITEM_MOUNTAIN_HUT);
	}
	
    /**
     * Executes a response to Examining the mountain hut
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineMountainHut(Game game, Player player) {
		game.addMessage("Aha!", true, false);
		game.getItem(GameEntities.FLAG_COINS).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the attic
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineAttic(int noun, int room) {
		return (room == GameEntities.ROOM_ATTIC && noun == GameEntities.ITEM_CUPBOARD);
	}
	
    /**
     * Executes a response to Examining the attic
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineAttic(Game game, Player player) {
		game.addMessage("Ok", true, false);
		game.getItem(GameEntities.FLAG_SHEET).setItemFlag(0);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the uniform
	 * 
     * @param game the current game state
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineUniform(int noun, Game game) {
		return (game.getItem(GameEntities.ITEM_UNIFORM).getItemLocation() == GameEntities.ROOM_CARRYING &&
				noun == GameEntities.ITEM_UNIFORM);
	}
	
    /**
     * Executes a response to Examining the uniform
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineUniform(Game game, Player player) {
		game.addMessage("You find matches in the pocket", true, false);
		game.getItem(GameEntities.ITEM_MATCHES).setItemLocation(GameEntities.ROOM_CARRYING);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is reading the inscripton in the cavern
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isReadInscription(int noun, int room) {
		return noun == GameEntities.ITEM_INSCRIPTION && room == GameEntities.ROOM_INSCRIBED_CAVERN;
	}

    /**
     * Executes a response to reading the inscription
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult readInscription(Game game, Player player) {
		game.addMessage("There are some letters '"+game.getMaze(1)+"'", true, false);		
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the trees
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineTrees(int noun, int room) {
		return noun == GameEntities.ITEM_TREES && room == GameEntities.ROOM_ORCHARD;
	}
	
    /**
     * Executes a response to examining the trees
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineTrees(Game game, Player player) {
		game.addMessage("They are apple trees.", true, false);
		game.getItem(GameEntities.FLAG_APPLES).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the kiln
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineKiln(int noun, int room) {
		return (noun == GameEntities.ITEM_KILN || noun == GameEntities.ITEM_OLD_KILN) &&
				room == GameEntities.ROOM_KILN;
	}
	
    /**
     * Executes a response to examining the kiln
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineKiln(Game game, Player player) {
		game.addMessage("OK", true, false);
		game.getItem(GameEntities.FLAG_JUG).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the ketch
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineKetch(int noun, int room) {
		return noun == GameEntities.ITEM_KETCH && room == GameEntities.ROOM_KETCH;
	}
	
    /**
     * Executes a response to examining the ketch
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineKetch(Game game, Player player) {
		game.addMessage("It is fishy!", true, false);
		game.getItem(GameEntities.FLAG_NET).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the sacks
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineSacks(int noun, int room) {
		return noun == GameEntities.ITEM_SACK && room == GameEntities.ROOM_SACKS;
	}
	
    /**
     * Executes a response to examining the sacks
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineSacks(Game game, Player player) {
		game.addMessage("Ok", true, false);
		game.getItem(GameEntities.FLAG_SEEDS).setItemFlag(0);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the gravestone
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineGravestone(int noun, int room) {
		return noun == GameEntities.ITEM_GRAVESTONE && room == GameEntities.ROOM_GRAVEYARD;
	}

    /**
     * Executes a response to examining the gravestone
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineGravestone(Game game,Player player) {
		game.addMessage("You see a faded inscription.", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the pool
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @param game the current game state
	 * @return boolean
	 */
	private boolean isExaminePool(int noun, int room, Game game) {
		return (noun == GameEntities.ITEM_POOL || noun == GameEntities.ITEM_MISTY_POOL) &&
				game.getItem(GameEntities.FLAG_SHEILD_REVEALED).getItemFlag() == 0 &&
				room == GameEntities.ROOM_POOL;
	}
	
    /**
     * Executes a response to examining the pool
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examinePool(Game game, Player player) {
		game.addMessage("A glimmering from the depths", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the shield has been found
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @param game the current game state
	 * @return boolean
	 */
	private boolean isExaminePoolFoundShield(int noun, int room, Game game) {
		return (noun == GameEntities.ITEM_POOL || noun == GameEntities.ITEM_MISTY_POOL) &&
				game.getItem(GameEntities.FLAG_SHEILD_REVEALED).getItemFlag() == 1 &&
				game.getItem(GameEntities.FLAG_SHIELD).getItemFlag() == 0  &&
				room == GameEntities.ROOM_POOL;
	}
	
    /**
     * Executes a response when the shield is found
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examinePoolFoundShield(Game game, Player player) {
		game.addMessage("Something here ...", true, false);
		game.getItem(GameEntities.FLAG_SHIELD).setItemFlag(0);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the Sluice Gates
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineSluiceGates(int noun, int room) {
		return (noun == GameEntities.ITEM_GATE || noun == GameEntities.ITEM_SLUICE) &&
				room == GameEntities.ROOM_SLUICE_GATES;
	}
	
    /**
     * Executes a response to examining the Sluice Gates
     *
     * @param game the current game state
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineSluiceGates(Game game, Player player) {
		game.addMessage("There is a handle.", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the Phial
	 * 
	 * @param noun the value of the noun entered
     * @param game the current game state
	 * @return boolean
	 */
	private boolean isExaminePhial(int noun, Game game) {
		return game.getItem(GameEntities.ITEM_PHIAL).isAtLocation(GameEntities.ROOM_CARRYING) &&
				noun == GameEntities.ITEM_PHIAL;
	}
	
    /**
     * Executes a response to examining the phial
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examinePhial(Game game, Player player) {
		game.addMessage("The label reads 'Poison'.", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the Sluice books
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineBooks(int noun, int room) {
		return room == GameEntities.ROOM_LIBRARY && noun == GameEntities.ITEM_BOOKS;
	}
	
    /**
     * Executes a response to examining the books
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineBooks(Game game, Player player) {
		
		String wordLocation = game.getRawRoomName(game.getItem(GameEntities.FLAG_WORD_LOCATION).getItemFlag()
												+ Constants.RANDOM_WORD_CONSTANT);
		wordLocation = wordLocation.substring(2,wordLocation.length()-4);
		game.addMessage("Magic words lie at the crossroads, the fountain and the "+wordLocation, true, true);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the grarg
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineGrarg(int noun, int room, Game game) {
		return noun == GameEntities.ITEM_GRARG && room == GameEntities.ROOM_BANQUET_HALL &&
				game.getItem(GameEntities.FLAG_GRARG_ASLEEP).getItemFlag() == 1;
	}
	
    /**
     * Executes a response to examining the grarg
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineGrarg(Game game, Player player) {
		game.addMessage("Very ugly!", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the well bottom
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineWellBottom(int noun, int room) {
		return (noun == GameEntities.ITEM_WALL || noun == GameEntities.ITEM_WELL_BOTTOM) && 
				room == GameEntities.ROOM_WELL_BOTTOM;
	}
	
    /**
     * Executes a response to examining the well bottom
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineWellBottom(Game game, Player player) {
		game.addMessage("There are loose bricks.", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the mosaic hall
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isInMosaicRoom(int room) {
		return room == GameEntities.ROOM_MOSAIC_HALL;
	}
	
    /**
     * Executes a response to examining the mosaic hall
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult inMosaicRoom(Game game, Player player) {
		game.addMessage("Very interesting.", true, false);
		return new ActionResult(game,player,true);
	}
	
	/**
	 * Returns true if the command is examining the Pinnacle
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExaminePinnacle(int noun, int room) {
		return noun == GameEntities.ITEM_PINNACLE && room == GameEntities.ROOM_PINNACLE;
	}
	
	/**
	 * Returns true if the command is examining the fountain
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineFountain(int noun, int room) {
		return noun == GameEntities.ITEM_FOUNTAIN && room == GameEntities.ROOM_FOUNTAIN;
	}
	
	/**
	 * Returns true if the command is examining the statue
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineStatue(int noun, int room) {
		return noun == GameEntities.ITEM_STATUE && room == GameEntities.ROOM_CROSSROADS;
	}
	
    /**
     * Executes a response to when the response is interesting
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult isInteresting(Game game, Player player) {
		game.addMessage("Interesting!", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the stable
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineStables(int noun, int room) {
		return noun == GameEntities.ITEM_STABLES && room == GameEntities.ROOM_STABLE;
	}
	
    /**
     * Executes a response to examining the stables
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineStables(Game game, Player player) {
		game.addMessage("There is a wooden door.", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the door
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineDoor(int noun, int room) {
		return noun == GameEntities.ITEM_DOOR && room == GameEntities.ROOM_STABLE;
	}
	
    /**
     * Executes a response to examining the door
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineDoor(Game game, Player player) {
		game.addMessage("You found somethings.", true, false);
		game.getItem(GameEntities.FLAG_HORSESHOE).setItemFlag(0);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the kitchen
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineKitchen(int noun, int room) {
		return noun == GameEntities.ITEM_KITCHEN && room == GameEntities.ROOM_KITCHEN;
	}
	
    /**
     * Executes a response to examining the kitchen
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineKitchen(Game game, Player player) {
		game.addMessage("You see a large cupboard in the corner.", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the stone
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineStones(int noun, int room) {
		return noun == GameEntities.ITEM_STONES && room == GameEntities.ROOM_STONE_CIRCLE;
	}
	
    /**
     * Executes a response to examining the stones
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
	private ActionResult examineStones(Game game, Player player) {
		game.addMessage("There are nine stones.", true, false);
		return new ActionResult(game,player,true);
	}

	/**
	 * Returns true if the command is examining the inscription on the gravestone
	 * 
	 * @param noun the value of the noun entered
	 * @param room the room the player is in
	 * @return boolean
	 */
	private boolean isExamineInscription(int noun, int room) {
		return noun == GameEntities.ITEM_STONES && room == GameEntities.ROOM_STONE_CIRCLE;
	}
	
    /**
     * Executes a response to examining the inscription on the gravestone
     *
     * @param player the player making the move
     * @return an {@link ActionResult} describing the outcome
     */
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
 * 10 February 2026 - Added javadocs
 */
