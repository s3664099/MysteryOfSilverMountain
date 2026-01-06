/*
Title: Mystery of Silver Mountain Move Command
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.3
Date: 6 January 2026
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
	public ActionResult validateMove(ParsedCommand command, Game game, Player player) {

		boolean validMove = true;
		int room = player.getRoom();
		ActionResult result = new ActionResult(game,player,validMove);
		
		if (isNotDirection(command)) {
			result = notDirection(game,player);
		} else if (isExitBlocked(game,room,command.getNounNumber())) {
			result = exitBlocked(game,player);
		} 
		
		return result;
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
		
		ActionResult blockedCheck = evaluateMovementRestrictions(game,player,command);
		
		//Move is not blocked
		if (!blockedCheck.isValid()) {
			
			int direction = command.getVerbNumber();
			int newRoom = calculateNewRoom(player.getRoom(),direction);
			player.setRoom(newRoom);
			game.addMessage("Ok",true,true);
			game.getRoom(newRoom).setVisited();			
			blockedCheck = handleRoomEntryEffects(game,player,command);
		}
		
		return blockedCheck;
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
		
		if (isCrossingBridge(player.getRoom(),command.getVerbNumber())) {
			
			if (doesTrollStop(game.getItem(GameEntities.ITEM_SILVER_PLATE).getItemFlag())) {
				game.addMessage("A troll stops you crossing!", true, true);
				result = new ActionResult(game,player,true);
			} else {
				game.getItem(GameEntities.ITEM_SILVER_PLATE).setItemFlag(0);
				result = new ActionResult(game,player,false);
			}
		}
		
		if (areGragsInvolved(game.getItem(GameEntities.ITEM_HOUND).getItemFlag(),
							game.getItem(GameEntities.ITEM_CUPBOARD).getItemFlag())) {
			if (haveGrargsGotYou(game.getItem(GameEntities.ITEM_BOOKS).getItemFlag())) {
				game.getItem(GameEntities.ITEM_CASKS).setItemFlag(1);
				game.addMessage("Grargs have got you!", true, true);
				result = new ActionResult(game,player,true);
			} else if (doGrargsSeeYou(player.getRoom(),game.getItem(GameEntities.ITEM_INSCRIPTION).getItemFlag())) {
				game.addMessage("Grargs will see you!", false, false);
				result = new ActionResult(game,player,true);
			} else if (isPatrolApproaching(player.getRoom())) {
				
			}

			
			//890 IF  THEN R$="A GRARG PATROL APPROACHES":F(55)=1:RETURN
			
		}
		
		//900 IFC(8)=0AND((R=52 AND D=2)OR(R=31ANDD<>3))THENR$="THE BOAR IS TOO HEAVY":RET
		//910 IFC(8)<>0AND((R=52ANDD=4)OR(R=31ANDD=3))THENR$="YOU CANNOT SWIM":RETURN
		//920 IF R=52 AND C(8)=0 AND D=4 AND F(30)=0 THEN R$="NO POWER!":RETURN
			
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
		return result;
	}
	
    /**
     * Checks if the command is not a valid direction.
     */
	private boolean isNotDirection(ParsedCommand command) {
		return command.getNounNumber()>GameEntities.MOVE_NOT_DIRECTION;
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
	private boolean isCrossingBridge(int roomNumber, int direction) {

		return (roomNumber == GameEntities.ROOM_BRIDGE_EAST && direction == GameEntities.CMD_WEST) ||
				(roomNumber == GameEntities.ROOM_BRIDGE_WEST && direction == GameEntities.CMD_EAST);
	}
	
	private boolean doesTrollStop(int silverPlateFlag) {
		return silverPlateFlag == 0;
	}
	
	private boolean areGragsInvolved(int houndFlag,int cupboardFlag) {
		return houndFlag == 0 && cupboardFlag == 0;
	}
	
	private boolean haveGrargsGotYou(int bookFlag) {
		return bookFlag == 1;
	}
	
	private boolean doGrargsSeeYou(int currentRoom, int inscriptionFlag) {
		return currentRoom == GameEntities.ROOM_BANQUET_HALL && inscriptionFlag == 0;
	}
	
	private boolean isPatrolApproaching(int currentRoom) {
		return currentRoom == GameEntities.ROOM_CAMPFIRE ||
				currentRoom == GameEntities.ROOM_PLOUGHED_FIELD ||
				currentRoom == GameEntities.ROOM_SENTRY_POST ||
				currentRoom == GameEntities.ROOM_GUARD_ROOM;
	}
}

/* 3 December 2025 - Created File
 * 6 December 2025 - Removed game specific code
 * 8 December 2025 - Fixed errors
 * 				   - Increased version number
 * 9 December 2025 - Added Title
 * 5 January 2025 - Fixed code so can now move
 * 				  - Added troll
 * 6 January 2025 - Completed troll blocking
 * 				  - Added Grarg Movement blocking
 */