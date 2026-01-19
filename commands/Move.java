/*
Title: Mystery of Silver Mountain Move Command
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.13
Date: 19 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

import command_process.ActionResult;
import command_process.ParsedCommand;

/**
 * Handles player movement logic, including parsing movement commands,
 * validating moves, applying restrictions, and executing room transitions.
 */
public class Move {
	
    /** Direction modifiers for room transitions (N, E, S, W). */
	private static final int[] DIRECTION_MODIFIERS = {-10, +01, +10, -01};
		
    /**
     * Normalizes a parsed move command by resolving direction, special rooms,
     * and coded commands into a usable noun number.
     *
     * @param command the parsed command to normalize
     * @param room the player's current room
     * @return a normalized {@link ParsedCommand}
     */
	public ParsedCommand normaliseMoveCommand(ParsedCommand command,int room) {
		
		int verbNumber = command.getVerbNumber();
		int nounNumber = GameEntities.NOUN_NIL;
		
		return new ParsedCommand(verbNumber,nounNumber,command.getSplitTwoCommand(),command.getCommand());
	}
	
    /**
     * Resolves single-direction commands where the noun may be omitted.
     *
     * @param nounNumber the noun number from the command
     * @param verbNumber the verb number from the command
     * @return a corrected noun number representing a direction
     */
	public int parseSingleDirection(int nounNumber, int verbNumber) {

		if (nounNumber == -1) {
			nounNumber = verbNumber;
		} else if (nounNumber>Constants.NUMBER_OF_ITEMS && nounNumber<Constants.NUMBER_OF_NOUNS) {
			nounNumber = nounNumber-Constants.NUMBER_OF_ITEMS;
		}
		return nounNumber;
	}
	
    /**
     * Validates whether a move is possible based on the parsed command,
     * player state, and room conditions.
     *
     * @param command the move command
     * @param game the current game state
     * @param player the player attempting the move
     * @return an {@link ActionResult} describing validity and effects
     */
	private ActionResult validateMove(ParsedCommand command, Game game, Player player){
		
		boolean validMove = true;
		int room = player.getRoom();
		int direction = command.getVerbNumber();
		ActionResult result = new ActionResult(game,player,validMove);
		
		if (direction == GameEntities.CMD_SOUTH) {
			if (!isSouthDown(room)) {
				direction = GameEntities.CMD_VALID_SOUTH;
			} 
		} else if (direction == GameEntities.CMD_EAST) {
			direction = GameEntities.CMD_VALID_EAST;
		} else if (direction == GameEntities.CMD_NORTH) {
			if (isNorthUp(room)) {
				direction = GameEntities.CMD_VALID_EAST;
			}
		} else if (direction == GameEntities.CMD_DOWN) {
			if (isSouthDown(room)) {
				direction = GameEntities.CMD_VALID_SOUTH;
			} else {
				result = exitBlocked(game,player);
			}
		} else if (direction == GameEntities.CMD_UP) {
			if (isNorthUp(room)) {
				direction = GameEntities.CMD_NORTH;
			} else {
				result = exitBlocked(game,player);
			}
		}
		
		if (isNotDirection(command)) {
			result = notDirection(game,player);
		} else if (isExitBlocked(game,room,direction)) {
			result = exitBlocked(game,player);
		} 		
		return result;
	}
	
	/**
	 * Returns true if the command is executed in a room where south is instead down
	 */
	private boolean isSouthDown(int roomNumber) {
		return roomNumber == GameEntities.ROOM_SACKS ||
				roomNumber == GameEntities.ROOM_ICY_PATH ||
				roomNumber == GameEntities.ROOM_ATTIC;
	}
	
	/**
	 * Returns true if the command is executed in a room where north is instead up
	 */
	private boolean isNorthUp(int roomNumber) {
		return roomNumber == GameEntities.ROOM_LOWER_MILL ||
				roomNumber == GameEntities.ROOM_ICY_PATH ||
				roomNumber == GameEntities.ROOM_WHITE_COTTAGE;
	}
	
    /**
     * Executes a validated move by updating the player's room,
     * applying room entry effects, and generating messages.
     *
     * @param game the current game state
     * @param player the player making the move
     * @param command the parsed move command
     * @return an {@link ActionResult} describing the outcome
     */
	public ActionResult executeMove(Game game, Player player, ParsedCommand command) {
		
		ActionResult blockedCheck = new ActionResult(game,player,false);

		if (player.isPlayerStateMaze()) {
			blockedCheck = moveInMaze(game,player,command.getVerbNumber());
		} else {
			blockedCheck = evaluateMovementRestrictions(game,player,command);
			//Move is not blocked
			if (!blockedCheck.isValid()) {
				blockedCheck = validateMove(command,game,player);
				if (blockedCheck.isValid()) {
					int direction = getDirectionNumber(command.getVerbNumber(),player.getRoom());
					int newRoom = calculateNewRoom(player.getRoom(),direction);
					player.setRoom(newRoom);
					game.addMessage("Ok",true,true);
					game.getRoom(newRoom).setVisited();			
					blockedCheck = handleRoomEntryEffects(game,player,command);
				}	
			}
		}
		
		return blockedCheck;
	}
	
	/**
	 * Determines the direction the player is heading numerically
	 * This is for converting up to north and down to south where applicable.
	 * 
	 * @param directionNumber the direction that the player is heading
	 * @param roomNumber the room the player is currently in
	 * @return the actual direction number
	 */
	private int getDirectionNumber(int directionNumber, int roomNumber) {
		
		if (directionNumber == GameEntities.CMD_DOWN) {
			if (isSouthDown(roomNumber)) {
				directionNumber = GameEntities.CMD_SOUTH;
			} 
		} else if (directionNumber == GameEntities.CMD_UP) {
			if (isNorthUp(roomNumber)) {
				directionNumber = GameEntities.CMD_NORTH;
			}
		}
		
		return directionNumber;
	}
	
    /**
     * Calculates the new room index based on the current room and direction.
     *
     * @param currentRoom the player's current room
     * @param direction the movement direction
     * @return the new room index
     */
	private int calculateNewRoom(int currentRoom, int direction) {
		return currentRoom + DIRECTION_MODIFIERS[direction-1];
	}
	
    /**
     * Evaluates whether movement is blocked by entities, events,
     * or environmental restrictions.
     *
     * @param game the current game state
     * @param player the player attempting to move
     * @param command the parsed command
     * @return an {@link ActionResult} indicating if movement is blocked
     */
	private ActionResult evaluateMovementRestrictions(Game game, Player player, ParsedCommand command) {
		
		ActionResult result = new ActionResult(game,player,false);
		
		if (isTrollBlockingBridge(game, player.getRoom(),command.getVerbNumber())) {
			result = trollBlocking(game,player);
		}
		
		if (areGragsInvolved(game)) {
			
			if (haveGrargsGotYou(game)) {
				result = grargsCaptureYou(game,player);
			} else if (doGrargsSeeYou(game, player.getRoom())) {
				result = grargsSpotYou(game,player);
			} else if (isPatrolApproaching(player.getRoom())) {
				result = grargPatrolApproaching(game,player);
			}
		}
		
		if (isCarryingBoat(game,player.getRoom(),command.getVerbNumber())) {
			result = carryingBoat(game,player);
		} else if (isNotCarryingBoat(game,player.getRoom(),command.getVerbNumber())) {
			result = notCarryingBoat(game,player);
		} else if (hasBoatNoPower(game,player.getRoom(),command.getVerbNumber())) {
			result = boatHasNoPower(game,player);
		} else if (isBoatSinking(game,player.getRoom(),command.getVerbNumber())) {
			result = boatIsSinking(game,player);
		} else if (isBoarBlocking(game,player.getRoom(),command.getVerbNumber())) {
			result = boarIsBlocking(game,player);
		} else if (isRubbleBlockingPath(game,player.getRoom(),command.getVerbNumber())) {
			result = rubbleBlockingPath(game,player);
		} else if (isIceBreaking(game,player.getRoom())) {
			result = iceIsBreaking(game,player);
		} else if (isEnteringTunnels(player.getRoom(),command.getVerbNumber())) {
			result = enteringTunnels(game,player,command.getVerbNumber());
		} else if (isPassageSteep(player.getRoom(),command.getVerbNumber())) {
			result = steepPassage(game,player);
		} else if (isHoundBlocking(game,player.getRoom(),command.getVerbNumber())) {
			result = houndBlocking(game,player);
		} else if (isItDark(game,player.getRoom())) {
			result = tooDark(game,player);
		} else if (areForcesPresent(game,player.getRoom(),command.getVerbNumber())) {
			result = forcesArePresent(game,player);
		} else if (isOgbanPresent(game,player.getRoom(),command.getVerbNumber())) {
			result = ogbanIsPresent(game,player);
		} else if (areRatsPresent(game,player.getRoom())) {
			result = ratsArePresent(game,player);
		} else if (isCaughtInWebs(game,player.getRoom(),command.getVerbNumber())) {
			result = caughtInwebs(game,player);
		} else if (isDoorClosed(game,player.getRoom(),command.getVerbNumber())) {
			result = doorIsClosed(game,player);
		} else if (isSteep(game,player.getRoom(),command.getVerbNumber())) {
			result = passageIsSteep(game,player);
		} else {
		
			if(isLeavingBanquetHall(player.getRoom(),command.getVerbNumber())) {
				result = leavingBanquetHall(game,player);
			}
			
			if (isInLibrary(game,player.getRoom())) {
				result = inLibrary(game,player);
			}
		}
		
		return result;
	}
	
    /**
     * Handles effects that trigger upon entering a room (e.g., traps, ferry).
     *
     * @param game the current game state
     * @param player the player entering the room
     * @param command the parsed move command
     * @return an {@link ActionResult} describing room entry outcomes
     */
	private ActionResult handleRoomEntryEffects(Game game,Player player,ParsedCommand command) {
		ActionResult result = new ActionResult(game,player,true);
		
		if (isTrollAway(game)) {
			result = trollAway(result.getGame(),result.getPlayer());
		}
				
		if (isCrossingBridge(player.getDisplayRoom(),command.getVerbNumber())) {
			result = crossingBridge(result.getGame(),result.getPlayer());
		}
		
		if (isWearingBoots(game)) {
			result = increaseWearStatus(result.getGame(),result.getPlayer());
			
			if (haveBootsWornOut(result.getGame())) {
				result = bootsWornOut(result.getGame(),result.getPlayer());
			}
		}

		return result;
	}
	
	private ActionResult moveInMaze(Game game, Player player, int direction) {
		
		String mazeMove = player.getMazeMove();
		String maze = game.getMaze(player.getMazeNumber());
		
		if (direction == 1) {
			mazeMove = "N"+mazeMove;
		} else if (direction == 2) {
			mazeMove = "E"+mazeMove;
		} else if (direction == 3) {
			mazeMove = "S"+mazeMove;
		} else {
			mazeMove = "W"+mazeMove;
		}
		
		if (mazeMove.length()==9) {
			mazeMove = mazeMove.substring(0,8);
		}
		
		if (mazeMove.equals(maze)) {
			int newRoom = calculateNewRoom(player.getRoom(),player.getMazeDirection());
			player.setRoom(newRoom);
			player.setPlayerStateNormal();
			player.setMazeMove("");
			game.addMessage("You escaped the maze of tunnels", true, true);
		} else {
			player.setMazeMove(mazeMove);
		}

		return new ActionResult(game,player,true);
	}
	
    /**
     * Checks if the command is not a valid direction.
     */
	private boolean isNotDirection(ParsedCommand command) {
		return command.getVerbNumber()>GameEntities.MOVE_NOT_DIRECTION;
	}
	
    /**
     * Handles invalid directional commands.
     *
     * @return an {@link ActionResult} indicating invalid input
     */
	private ActionResult notDirection(Game game,Player player) {
		game.addMessage("I don't understand",true,true);
		return new ActionResult(game,player,false);
	}
	
    /**
     * Checks if there is no exit in the given direction.
     */
	private boolean isExitBlocked(Game game, int room, int nounNumber) {
		return !game.checkExit(room,nounNumber-1);
	}
	
    /**
     * Handles blocked exits (no exit in that direction).
     *
     * @return an {@link ActionResult} indicating failure
     */
	private ActionResult exitBlocked(Game game, Player player) {
		game.addMessage("You can't go that way",true,true);
		return new ActionResult(game, player, false);
	}
	
	/**
	 * Checks if the player is crossing the bridge
	 */
	private boolean isTrollBlockingBridge(Game game, int roomNumber, int direction) {

		return (((roomNumber == GameEntities.ROOM_BRIDGE_EAST && direction == GameEntities.CMD_WEST) ||
				(roomNumber == GameEntities.ROOM_BRIDGE_WEST && direction == GameEntities.CMD_EAST)) &&
				game.getItem(GameEntities.FLAG_TROLL).getItemFlag()==0);
	}
	
	private ActionResult trollBlocking(Game game, Player player) {
		game.addMessage("A troll stops you crossing!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isTrollAway(Game game) {
		return game.getItem(GameEntities.FLAG_TROLL).getItemFlag()==1;
	}
	
	private ActionResult trollAway(Game game, Player player) {
		game.getItem(GameEntities.FLAG_TROLL).setItemFlag(0);
		return new ActionResult(game,player,false);
	}
		
	private boolean areGragsInvolved(Game game) {
		return game.getItem(GameEntities.FLAG_FIFTY_ONE).getItemFlag() == 0 &&
				game.getItem(GameEntities.FLAG_WEARING_BOOTS).getItemFlag() == 0;
	}
	
	private boolean haveGrargsGotYou(Game game) {
		return game.getItem(GameEntities.FLAG_PLAYER_SPOTTED).getItemFlag()==1;
	}
	
	private ActionResult grargsCaptureYou(Game game, Player player) {
		game.getItem(GameEntities.FLAG_PLAYER_CAPTURED).setItemFlag(1);
		game.addMessage("Grargs have got you!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean doGrargsSeeYou(Game game, int currentRoom) {
		return currentRoom == GameEntities.ROOM_BANQUET_HALL &&
				game.getItem(GameEntities.FLAG_FORTY_EIGHT).getItemFlag()==0;
	}
	
	private ActionResult grargsSpotYou(Game game, Player player) {
		game.addMessage("Grargs will see you!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isPatrolApproaching(int currentRoom) {
		return currentRoom == GameEntities.ROOM_CAMPFIRE ||
				currentRoom == GameEntities.ROOM_PLOUGHED_FIELD ||
				currentRoom == GameEntities.ROOM_SENTRY_POST ||
				currentRoom == GameEntities.ROOM_GUARD_ROOM;
	}
	
	private ActionResult grargPatrolApproaching(Game game, Player player) {
		game.getItem(GameEntities.FLAG_PLAYER_SPOTTED).setItemFlag(1);
		game.addMessage("A Grarg patrol approaches!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isCarryingBoat(Game game,int roomNumber,int command) {
		return game.getItem(GameEntities.ITEM_BOAT).getItemLocation() == GameEntities.CARRYING &&
				((roomNumber == GameEntities.ROOM_EDGE_LAKE && command == GameEntities.CMD_EAST) ||
				 (roomNumber == GameEntities.ROOM_SHORE && command != GameEntities.CMD_SOUTH));		
	}
	
	private ActionResult carryingBoat(Game game, Player player) {
		game.addMessage("The boat is too heavy", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isNotCarryingBoat(Game game,int roomNumber,int command) {
		return game.getItem(GameEntities.ITEM_BOAT).getItemLocation() != GameEntities.CARRYING &&
				((roomNumber == GameEntities.ROOM_EDGE_LAKE && command == GameEntities.CMD_WEST) ||
				 (roomNumber == GameEntities.ROOM_SHORE && command == GameEntities.CMD_SOUTH));		
	}
	
	private ActionResult notCarryingBoat(Game game, Player player) {
		game.addMessage("You cannot swim", true, true);
		return new ActionResult(game,player,true);	
	}
	
	private boolean hasBoatNoPower(Game game,int roomNumber,int command) {
		return (roomNumber == GameEntities.ROOM_EDGE_LAKE && command == GameEntities.CMD_WEST &&
				game.getItem(GameEntities.ITEM_BOAT).getItemLocation() == GameEntities.CARRYING &&
				game.getItem(GameEntities.FLAG_BOAT_POWER).getItemFlag() == 0);
	}
	
	private ActionResult boatHasNoPower(Game game,Player player) {
		game.addMessage("No power!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isBoatSinking(Game game, int roomNumber,int command) {
		return (roomNumber == GameEntities.ROOM_ROUGH_WATER && command == GameEntities.CMD_SOUTH
				&& game.getItem(GameEntities.FLAG_BOAT_FLAG).getItemFlag()==0);
	}
	
	private ActionResult boatIsSinking(Game game,Player player) {
		game.addMessage("The boat is sinking", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isBoarBlocking(Game game,int roomNumber,int command) {
		return (roomNumber == GameEntities.ROOM_COUNTRYSIDE && command == GameEntities.CMD_NORTH
				&& game.getItem(GameEntities.FLAG_OGBANS_BOAR).getItemFlag()==0);
	}
	
	private ActionResult boarIsBlocking(Game game,Player player) {
		game.addMessage("Ogban's boar blocks your path", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isRubbleBlockingPath(Game game, int roomNumber, int command) {
		
		return (((roomNumber == GameEntities.ROOM_TOMB && command == GameEntities.CMD_EAST) ||
				(roomNumber == GameEntities.ROOM_STALACTITES && command == GameEntities.CMD_WEST)) &&
				game.getItem(GameEntities.FLAG_RUBBLE_BLOCKING).getItemFlag() == 0);
	}
	
	private ActionResult rubbleBlockingPath(Game game,Player player) {
		game.addMessage("A pile of rubble blocks your path", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isIceBreaking(Game game, int roomNumber) {
		return (game.getItem(GameEntities.ITEM_PLANKS).getItemLocation() != GameEntities.ROOM_POND &&
				roomNumber == GameEntities.ROOM_POND);
	}
	
	private ActionResult iceIsBreaking(Game game,Player player) {
		game.addMessage("The ice is breaking!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isEnteringTunnels(int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_TUNNELS && 
				(command == GameEntities.CMD_EAST || command == GameEntities.CMD_WEST));
	}
	
	private ActionResult enteringTunnels(Game game, Player player, int direction) {
		game.addMessage("You are lost in the tunnels!", true, true);
		player.setPlayerStateMaze(direction);
		return new ActionResult(game,player,true);
	}
	
	private boolean isPassageSteep(int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_STALACTITES && command == GameEntities.CMD_WEST);
	}
	
	private ActionResult steepPassage(Game game,Player player) {
		game.addMessage("The passage is too steep!", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isHoundBlocking(Game game, int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_GLASS_GATES && command == GameEntities.CMD_EAST &&
				game.getItem(GameEntities.FLAG_HOUND).getItemFlag() == 0);
	}
	
	private ActionResult houndBlocking(Game game,Player player) {
		game.addMessage("A huge hound bars your way.", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isItDark(Game game, int roomNumber) {
		return ((roomNumber == GameEntities.ROOM_CASKS || roomNumber == GameEntities.ROOM_WINE_CELLAR) &&
				game.getItem(GameEntities.FLAG_IS_DARK).getItemFlag() == 0);
	}
	
	private ActionResult tooDark(Game game,Player player) {
		game.addMessage("It is too dark.", true, true);
		return new ActionResult(game,player,true);	
	}
		
	private boolean areForcesPresent(Game game, int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_MOSAIC_HALL && command == GameEntities.CMD_EAST &&
				game.getItem(GameEntities.FLAG_FORCES).getItemFlag() == 0);
	}
	
	private ActionResult forcesArePresent(Game game, Player player) {
		game.addMessage("Mysterious forces hold you back.", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isOgbanPresent(Game game, int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_MOSAIC_HALL && command == GameEntities.CMD_SOUTH &&
				game.getItem(GameEntities.FLAG_OBGAN).getItemFlag() == 0);
	}
	
	private ActionResult ogbanIsPresent(Game game, Player player) {
		game.addMessage("You met Ogban!!", true, true);
		game.getItem(GameEntities.FLAG_PLAYER_CAPTURED).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	private boolean areRatsPresent(Game game, int roomNumber) {
		return (roomNumber == GameEntities.ROOM_WINE_CELLAR &&
				game.getItem(GameEntities.FLAG_RATS).getItemFlag()==0);
	}
	
	private ActionResult ratsArePresent(Game game,Player player) {
		game.addMessage("Rats nibble your ankles.", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isCaughtInWebs(Game game, int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_COBWEB && 
				(command==GameEntities.CMD_NORTH || command == GameEntities.CMD_WEST) &&
				game.getItem(GameEntities.FLAG_COBWEBS).getItemFlag()==0);
	}
	
	private ActionResult caughtInwebs(Game game,Player player) {
		game.addMessage("You get caught in the webs!", true, true);
		return new ActionResult(game,player,true);	
	}
	
	private boolean isDoorClosed(Game game, int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_WIZARD_LAIR && command == GameEntities.CMD_WEST &&
				game.getItem(GameEntities.FLAG_DOOR).getItemFlag()==0);
	}
	
	private ActionResult doorIsClosed(Game game,Player player) {
		game.addMessage("The door does not open.", true, true);
		return new ActionResult(game,player,true);	
	}
	
	private boolean isInLibrary(Game game, int roomNumber) {
		return (game.getItem(GameEntities.FLAG_FORTY_SEVEN).getItemFlag()==1 &&
				roomNumber == GameEntities.ROOM_LIBRARY);
	}
	
	private ActionResult inLibrary(Game game, Player player) {
		game.getItem(GameEntities.FLAG_OBGAN).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	private boolean isSteep(Game game, int roomNumber, int command) {
		boolean[] exits = game.getRoomExits(GameEntities.ROOM_CASKS);
		return (roomNumber == GameEntities.ROOM_CASKS && command == GameEntities.CMD_WEST &&
				!exits[0] && !exits[1] && exits[2] && exits[3]);
	}
	
	private ActionResult passageIsSteep(Game game, Player player) {
		player.setRoom(GameEntities.ROOM_SHADY_HOLLOW);
		game.addMessage("The passage was steep.", true, true);
		return new ActionResult(game,player,true);
	}
	
	private boolean isLeavingBanquetHall(int roomNumber, int command) {
		return roomNumber == GameEntities.ROOM_BANQUET_HALL && command == GameEntities.CMD_SOUTH;
	}
	
	private ActionResult leavingBanquetHall(Game game,Player player) {
		game.getItem(GameEntities.FLAG_FORTY_EIGHT).setItemFlag(1);
		game.getItem(GameEntities.FLAG_TWENTY).setItemFlag(0);
		return new ActionResult(game,player,false);
	}

	private boolean isCrossingBridge(int roomNumber, int command) {
		return (roomNumber == GameEntities.ROOM_BRIDGE_EAST && command == GameEntities.CMD_EAST) ||
				(roomNumber == GameEntities.ROOM_BRIDGE_WEST && command == GameEntities.CMD_WEST);
	}
	
	private ActionResult crossingBridge(Game game,Player player) {
		game.addMessage("You crossed", true, true);
		return new ActionResult(game,player,false);
	}
	
	private boolean isWearingBoots(Game game) {
		return game.getItem(GameEntities.FLAG_WEARING_BOOTS).getItemFlag() == 1;
	}
	
	private ActionResult increaseWearStatus(Game game,Player player) {
		int flag = game.getItem(GameEntities.FLAG_BOOT_WEAR_STATUS).getItemFlag();
		game.getItem(GameEntities.FLAG_BOOT_WEAR_STATUS).setItemFlag(flag+=1);
		return new ActionResult(game,player,false);
	}

	private boolean haveBootsWornOut(Game game) {
		return game.getItem(GameEntities.FLAG_BOOT_WEAR_STATUS).getItemFlag()>5;
	}
	
	private ActionResult bootsWornOut(Game game, Player player) {
		game.addMessage("Your boots have worn out", true, true);
		game.getItem(GameEntities.FLAG_WEARING_BOOTS).setItemFlag(0);
		game.getItem(GameEntities.ITEM_BOOTS).setItemLocation(GameEntities.ROOM_DESTROYED);
		return new ActionResult(game,player,false);
	}
}

/* 3 December 2025 - Created File
 * 6 December 2025 - Removed game specific code
 * 8 December 2025 - Fixed errors
 * 				   - Increased version number
 * 9 December 2025 - Added Title
 * 5 January 2026 - Fixed code so can now move
 * 				  - Added troll
 * 6 January 2026 - Completed troll blocking
 * 				  - Added Grarg Movement blocking
 * 7 January 2026 - Added boat related blocking
 * 9 January 2026 - Added boat sinking and boar
 * 				  - Added validation of movement
 * 10 January 2026 - Added next lot of restrictions to movement
 * 11 January 2026 - Completed Movement restrictions
 * 				   - Fixed up some minor issues and started moving results into separate function
 * 12 January 2026 - Completed moving results to separate functions
 * 13 January 2026 - Added the after move updates
 * 14 January 2026 - Added function to reset troll.
 * 16 January 2026 - Moved setting dark to post condition
 * 17 January 2026 - Added functionality for going up and down
 * 18 January 2026 - Added functionality for moving through the maze
 * 19 January 2026 - Added movement through maze
 */