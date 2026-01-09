/*
Title: Mystery of Silver Mountain Move Command
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.5
Date: 9 January 2026
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
			direction = GameEntities.CMD_VALID_SOUTH;
		} else if (direction == GameEntities.CMD_EAST) {
			direction = GameEntities.CMD_VALID_EAST;
		}
		
		if (isNotDirection(command)) {
			result = notDirection(game,player);
		} else if (isExitBlocked(game,room,direction)) {
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
			blockedCheck = validateMove(command,game,player);
			if (blockedCheck.isValid()) {
				int direction = command.getVerbNumber();
				int newRoom = calculateNewRoom(player.getRoom(),direction);
				player.setRoom(newRoom);
				game.addMessage("Ok",true,true);
				game.getRoom(newRoom).setVisited();			
				blockedCheck = handleRoomEntryEffects(game,player,command);
			}	
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
			
			if (doesTrollStop(game.getItem(GameEntities.FLAG_TROLL).getItemFlag())) {
				game.addMessage("A troll stops you crossing!", true, true);
				result = new ActionResult(game,player,true);
			} else {
				game.getItem(GameEntities.FLAG_TROLL).setItemFlag(0);
				result = new ActionResult(game,player,false);
			}
		}
		
		if (areGragsInvolved(game.getItem(GameEntities.FLAG_FIFTY_ONE).getItemFlag(),
							game.getItem(GameEntities.FLAG_TWENTY_NINE).getItemFlag())) {
			
			if (haveGrargsGotYou(game.getItem(GameEntities.FLAG_PLAYER_SPOTTED).getItemFlag())) {
				game.getItem(GameEntities.FLAG_PLAYER_CAPTURED).setItemFlag(1);
				game.addMessage("Grargs have got you!", true, true);
				result = new ActionResult(game,player,true);
			} else if (doGrargsSeeYou(player.getRoom(),game.getItem(GameEntities.FLAG_FORTY_EIGHT).getItemFlag())) {
				game.addMessage("Grargs will see you!", true, true);
				result = new ActionResult(game,player,true);
			} else if (isPatrolApproaching(player.getRoom())) {
				game.getItem(GameEntities.FLAG_PLAYER_SPOTTED).setItemFlag(1);
				game.addMessage("A Grarg patrol approaches!", true, true);
				result = new ActionResult(game,player,true);
			}
		}
		
		if (isCarryingBoat(game,player.getRoom(),command.getVerbNumber())) {
			game.addMessage("The boat is too heavy", true, true);
			result = new ActionResult(game,player,true);
		} else if (isNotCarryingBoat(game,player.getRoom(),command.getVerbNumber())) {
			game.addMessage("You cannot swim", true, true);
			result = new ActionResult(game,player,true);			
		} else if (boatHasNoPower(game,player.getRoom(),command.getVerbNumber())) {
			game.addMessage("No power!", true, true);
			result = new ActionResult(game,player,true);	
		} else if (isBoatSinking(game,player.getRoom(),command.getVerbNumber())) {
			game.addMessage("The boat is sinking", true, true);
			result = new ActionResult(game,player,true);
		} else if (isBoarBlocking(game,player.getRoom(),command.getVerbNumber())) {
			game.addMessage("Ogban's boar blocks your path", true, true);
			result = new ActionResult(game,player,true);
		}
		return result;
	}
	
	//950 IF ((R=3 AND D=2) OR (R=4 AND D=4)) AND F(45)=0 THEN R$=X5$:RETURN
	//960 IF R=35 AND C(13)<>R THEN R$="THE ICE IS BREAKING!":RETURN
	//970 IF R=5 AND (D=2 OR D=4) THEN GOSUB 4310
	//980 IF R=4 AND D=4 THEN R$="PASSAGE IS TOO STEEP":RETURN
	//990 IF R=7 AND D=2 AND F(46)=0 THEN R$="A HUGE HOUND BARS YOUR WAY":RETURN
	//1000 IF (R=38 OR R=37) AND F(50)=0 THEN R$="JU JT UPP EBSL":GOSUB 3260:RETURN
	//1010 IFR=49ANDD=2ANDF(54)=0THENR$="MYSTERIOUS FORCES HOLD YOU BACK":RETURN
	//1020 IF R=49 AND D=3 AND F(68)=0 THEN R$="YOU MET OGBAN!!":F(56)=1:RETURN
	//1030 IF R=38 AND F(65)=0 THEN R$="RATS NIBBLE YOUR ANKLES":RETURN
	//1040 IFR=58AND(D=1ORD=4)ANDF(66)=0THENR$="YOU GET CAUGHT IN THE WEBS!":RETURN
	//1050 IF R=48 AND D=4 AND F(70)=0 THEN R$="THE DOOR DOES NOT OPEN":RETURN
	//1060 IF R=40 AND F(47)=1 THEN F(68)=1
	//1070 IFR=37ANDD=4ANDE$(37)="EW"THENR=67:R$="THE PASSAGE WAS STEEP!":RETURN
	//1080 IF R=29 AND D=3 THEN F(48)=1:F(20)=0
	//1090 IF R=8 AND D=2 THEN F(46)=0
	//1100 OM=R:FOR I=1 TO LEN(E$(R))
	//1170 IF R=OM THEN R$="YOU CANNOT GO THAT WAY"
	//1180 IF ((OM=75 AND D=2) OR (OM=76 AND D=4)) THEN R$="OK, YOU CROSSED"
	//1190 IF F(29)=1 THEN F(39)=F(39)+1
	//1200 IFF(39)>5ANDF(29)=1THENR$="CPPUT IBWF XPSO PVU":GOSUB4260:F(29)=0:C(3)=81
	
	//Need to do the up/down. Special directions & also can only do u/d in those rooms (and blocks the cardinals)
	//Errors in map
	
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
		return command.getVerbNumber()>GameEntities.MOVE_NOT_DIRECTION;
	}
	
    /**
     * Handles invalid directional commands.
     *
     * @return an {@link ActionResult} indicating invalid input
     */
	private ActionResult notDirection(Game game,Player player) {
		System.out.println("Hello");
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
	
	private boolean doesTrollStop(int trollFlag) {
		return trollFlag == 0;
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
	
	private boolean isCarryingBoat(Game game,int roomNumber,int command) {
		return game.getItem(GameEntities.ITEM_BOAT).getItemLocation() == GameEntities.CARRYING &&
				((roomNumber == GameEntities.ROOM_EDGE_LAKE && command == GameEntities.CMD_EAST) ||
				 (roomNumber == GameEntities.ROOM_SHORE && command != GameEntities.CMD_SOUTH));		
	}
	
	private boolean isNotCarryingBoat(Game game,int roomNumber,int command) {
		return game.getItem(GameEntities.ITEM_BOAT).getItemLocation() != GameEntities.CARRYING &&
				((roomNumber == GameEntities.ROOM_EDGE_LAKE && command == GameEntities.CMD_WEST) ||
				 (roomNumber == GameEntities.ROOM_SHORE && command == GameEntities.CMD_SOUTH));		
	}
	
	private boolean boatHasNoPower(Game game,int roomNumber,int command) {
		return (roomNumber == GameEntities.ROOM_EDGE_LAKE && command == GameEntities.CMD_WEST &&
				game.getItem(GameEntities.ITEM_BOAT).getItemLocation() == GameEntities.CARRYING &&
				game.getItem(GameEntities.FLAG_BOAT_POWER).getItemFlag() == 0);
	}
	
	private boolean isBoatSinking(Game game, int roomNumber,int command) {
		return (roomNumber == GameEntities.ROOM_ROUGH_WATER && command == GameEntities.CMD_SOUTH
				&& game.getItem(GameEntities.FLAG_BOAT_FLAG).getItemFlag()==0);
	}
	
	private boolean isBoarBlocking(Game game,int roomNumber,int command) {
		return (roomNumber == GameEntities.ROOM_COUNTRYSIDE && command == GameEntities.CMD_NORTH
				&& game.getItem(GameEntities.FLAG_OGBANS_BOAR).getItemFlag()==0);
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
 * 9 January 2025 - Added boat sinking and boar
 * 				  - Added validation of movement
 */