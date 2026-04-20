/*
Title: Mystery of Silver Mountain Parsed Command
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.27
Date: 20 April 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package command_process;

import data.GameEntities;

/**
 * Represents a parsed player command in the game.
 * <p>
 * This class encapsulates the raw command string entered by the player,
 * its parsed verb/noun components, and the derived command state and type.
 * Commands are categorized as movement, single-command, or multi-command,
 * and mapped to specific {@link CommandType} values for handling.
 * </p>
 */
public class ParsedCommand {
	
	private final String command;
	private final String[] splitTwoCommand;
	private final String[] splitFullCommand;
	private final int verbNumber;
	private final int nounNumber;
	
    /**
     * Represents the general structure/state of a parsed command.
     */
	private enum CommandState {
		
        /** Command does not map to a known state. */
		NONE,
		
		/** Movement command (e.g. north, south, etc.). */
		MOVE,
		
		/** Inventory. */
		INVENTORY,
		
		/** Command requiring only a verb*/
		SINGLE_COMMAND, 
		
		/** Command requiring an additional noun (e.g. TAKE SWORD). */
		MULTIPLE_COMMAND 
	};
	
    /**
     * Represents the specific type of command identified by the parser.
     */
	private enum CommandType { NONE,TAKE,GIVE,DROP,EXAMINE,SAY,WEAR,TIE,CLIMB,USE,OPEN,LIGHT,FILL,
								PLANT,WATER,SWING,EMPTY,ENTER,CROSS,REMOVE,FEED,TURN,DIVE,BAIL,
								THROW,INSERT,RIG,EAT,MOVE,RING,CUT,SHOW,BURN,POISON,UNLOCK,DRINK,
								LOAD,SAVE,QUIT,RESTART};
	//POISON, SHOW - Multi command names
							
	private CommandState commandState = CommandState.NONE;
	private CommandType commandType = CommandType.NONE;
	
    /**
     * Creates a new parsed command instance.
     *
     * @param verbNumber   numeric identifier for the verb portion of the command
     * @param nounNumber   numeric identifier for the noun portion of the command
     * @param codedCommand encoded representation of the command
     * @param splitCommand split command array (up to two words: verb and noun)
     * @param command      the full raw command string as entered by the player
     */
	public ParsedCommand(int verbNumber, int nounNumber, String[] splitCommand, String command) {
				
		this.splitTwoCommand = splitCommand;
		this.splitFullCommand = command.split(" ");
		this.verbNumber = verbNumber;
		this.nounNumber = nounNumber;
		this.command = command;
		
		setState(verbNumber);
	}
	
    /**
     * Updates the command state based on a new verb number.
     *
     * @param verbNumber numeric identifier for the new verb
     */
	public void updateState(int verbNumber) {
		setState(verbNumber);
	}
	
    /**
     * Determines the {@link CommandState} of the current command based on the given verb number.  
     * <p>
     * - If the verb is within the MOVE range, the command is classified as {@link CommandState#MOVE}.  
     * - If the verb matches one of the recognized single-action commands (e.g., EAT, DRINK, WAIT),  
     *   the command is classified as {@link CommandState#SINGLE_COMMAND} and forwarded to 
     *   {@link #setSingleCommand(int)} for further classification.  
     * - Otherwise, the command is treated as a {@link CommandState#MULTIPLE_COMMAND} and delegated 
     *   to {@link #setMultipleCommand(int)}.  
     *
     * @param verbNumber the numeric identifier of the parsed verb, as defined in {@link GameEntities}
     */
	private void setState(int verbNumber) {
		
		if (verbNumber>GameEntities.MOVE_BOTTOM && verbNumber<GameEntities.MOVE_TOP) {
			commandState = CommandState.MOVE;
		} else if (verbNumber == GameEntities.CMD_INVENTORY) {
			commandState = CommandState.INVENTORY;
		} else if (verbNumber == GameEntities.CMD_DIVE) {
			commandType = CommandType.DIVE;
			commandState = CommandState.SINGLE_COMMAND;
		} else {
			commandState = CommandState.MULTIPLE_COMMAND;
			setMultipleCommand(verbNumber);
		}
	}
		
    /**
     * Assigns the {@link CommandType} for commands that usually involve a target object or entity.  
     * Examples include TAKE, GIVE, DROP, ATTACK, or EXAMINE.  
     * <p>
     * This method is called when {@link #setState(int)} identifies the command as a 
     * {@link CommandState#MULTIPLE_COMMAND}.
     *
     * @param verbNumber the numeric identifier of the verb to classify as a multiple command
     */
	private void setMultipleCommand(int verbNumber) {
		
		if (verbNumber == GameEntities.CMD_TAKE || verbNumber == GameEntities.CMD_GET ||
			verbNumber == GameEntities.CMD_STEAL || verbNumber == GameEntities.CMD_GATHER ||
			verbNumber == GameEntities.CMD_PICK) {
			commandType = CommandType.TAKE;
		} else if (verbNumber == GameEntities.CMD_DROP || verbNumber == GameEntities.CMD_LEAVE) {
			commandType = CommandType.DROP;
		} else if (verbNumber == GameEntities.CMD_EXAMINE || verbNumber == GameEntities.CMD_READ ||
					verbNumber == GameEntities.CMD_COUNT) {
			commandType = CommandType.EXAMINE;
		} else if (verbNumber == GameEntities.CMD_LIGHT || verbNumber == GameEntities.CMD_BURN) {
			commandType = CommandType.LIGHT;
		} else if (verbNumber == GameEntities.CMD_USE || verbNumber == GameEntities.CMD_WITH)  {
			commandType = CommandType.USE;
		} else if (verbNumber == GameEntities.CMD_RIG || verbNumber == GameEntities.CMD_MAKE ||
					verbNumber == GameEntities.CMD_BLOW)  {
			commandType = CommandType.RIG;
		} else if (verbNumber == GameEntities.CMD_GIVE) {
			commandType = CommandType.GIVE;
		} else if (verbNumber == GameEntities.CMD_SAY) {
			commandType = CommandType.SAY;
		} else if (verbNumber == GameEntities.CMD_WEAR) {
			commandType = CommandType.WEAR;
		} else if (verbNumber == GameEntities.CMD_TIE)  {
			commandType = CommandType.TIE;
		} else if (verbNumber == GameEntities.CMD_CLIMB)  {
			commandType = CommandType.CLIMB;
		} else if (verbNumber == GameEntities.CMD_OPEN) {
			commandType = CommandType.OPEN;
		} else if (verbNumber == GameEntities.CMD_FILL) {
			commandType = CommandType.FILL;
		} else if (verbNumber == GameEntities.CMD_PLANT) {
			commandType = CommandType.PLANT;
		} else if (verbNumber == GameEntities.CMD_WATER) {
			commandType = CommandType.WATER;
		} else if (verbNumber == GameEntities.CMD_SWING) {
			commandType = CommandType.SWING;
		} else if (verbNumber == GameEntities.CMD_EMPTY) {
			commandType = CommandType.EMPTY;
		} else if (verbNumber == GameEntities.CMD_ENTER) {
			commandType = CommandType.ENTER;
		} else if (verbNumber == GameEntities.CMD_CROSS) {
			commandType = CommandType.CROSS;
		} else if (verbNumber == GameEntities.CMD_REMOVE) {
			commandType = CommandType.ENTER;
		} else if (verbNumber == GameEntities.CMD_FEED) {
			commandType = CommandType.CROSS;
		} else if (verbNumber == GameEntities.CMD_TURN) {
			commandType = CommandType.TURN;
		} else if (verbNumber == GameEntities.CMD_THROW) {
			commandType = CommandType.THROW;
		} else if (verbNumber == GameEntities.CMD_BAIL) {
			commandType = CommandType.BAIL;
		} else if (verbNumber == GameEntities.CMD_INSERT) {
			commandType = CommandType.INSERT;
		} else if (verbNumber == GameEntities.CMD_EAT) {
			commandType = CommandType.EAT;
		} else if (verbNumber == GameEntities.CMD_MOVE) {
			commandType = CommandType.MOVE;
		} else if (verbNumber == GameEntities.CMD_RING) {
			commandType = CommandType.RING;
		} else if (verbNumber == GameEntities.CMD_CUT) {
			commandType = CommandType.CUT;
		}
	}
	
    // --------------------
    // Getters
    // --------------------

    /**
     * @return the numeric identifier of the verb portion
     */
	public int getVerbNumber() {
		return verbNumber;
	}
	
    /**
     * @return the numeric identifier of the noun portion
     */
	public int getNounNumber() {
		return nounNumber;
	}
	
    /**
     * @return the raw command string entered by the player
     */
	public String getCommand() {
		return command;
	}
		
    /**
     * @return an array of up to two elements: verb and noun
     */
	public String[] getSplitTwoCommand() {
		return splitTwoCommand;
	}
	
    /**
     * @return the full command split into tokens
     */
	public String[] getSplitFullCommand() {
		return splitFullCommand;
	}
	
    // --------------------
    // State checks
    // --------------------

    /**
     * Checks whether the noun part of the command is present and non-empty.
     *
     * @return true if a valid noun is supplied, false otherwise
     */
	public boolean checkNounLength() {
		return splitTwoCommand.length > 1 && !splitTwoCommand[1].isEmpty();
	}
	
    /**
     * @return true if this command is a movement command
     */
	public boolean checkMoveState() {
		return commandState == CommandState.MOVE;
	}
	
    /**
     * @return true if this command is a multi-command (noun required)
     */
	public boolean checkMultipleCommandState() {
		return commandState == CommandState.MULTIPLE_COMMAND;
	}
	
    /**
     * @return true if the command type is {@link CommandType#NONE}
     */
	public boolean checkNoneCommandType() {
		return commandState == CommandState.NONE;
	}
	
    // --------------------
    // Command type checks
    // --------------------

	/** @return true if the command is an INVENTORY command */
	public boolean checkInventory() {
		return commandState == CommandState.INVENTORY;
	}
	
    /** @return true if the command is a TAKE command */
	public boolean checkTake() {
		return commandType == CommandType.TAKE;
	}
	
    /** @return true if the command is a DROP command */
	public boolean checkDrop() {
		return commandType == CommandType.DROP;
	}
	
    /** @return true if the command is a GIVE command */
	public boolean checkGive() {
		return commandType == CommandType.GIVE;
	}
	
    /** @return true if the command is a SAY command */
	public boolean checkSay() {
		return commandType == CommandType.SAY;
	}
	
    /** @return true if the command is a WEAR command */
	public boolean checkWear() {
		return commandType == CommandType.WEAR;
	}
	
    /** @return true if the command is a TIE command */
	public boolean checkTie() {
		return commandType == CommandType.TIE;
	}
	
    /** @return true if the command is a CLIMB command */
	public boolean checkClimb() {
		return commandType == CommandType.CLIMB;
	}
	
    /** @return true if the command is a EXAMINE command */
	public boolean checkExamine() {
		return commandType == CommandType.EXAMINE;
	}
	
    /** @return true if the command is a USE command */
	public boolean checkUse() {
		return commandType == CommandType.USE;
	}
	
    /** @return true if the command is a OPEN command */
	public boolean checkOpen() {
		return commandType == CommandType.OPEN;
	}
	
    /** @return true if the command is a LIGHT command */
	public boolean checkLight() {
		return commandType == CommandType.LIGHT;
	}
	
    /** @return true if the command is a FILL command */
	public boolean checkFill() {
		return commandType == CommandType.FILL;
	}
	
    /** @return true if the command is a PLANT command */
	public boolean checkPlant() {
		return commandType == CommandType.PLANT;
	}
	
    /** @return true if the command is a WATER command */
	public boolean checkWater() {
		return commandType == CommandType.WATER;
	}
	
    /** @return true if the command is a SWING command */
	public boolean checkSwing() {
		return commandType == CommandType.SWING;
	}
	
    /** @return true if the command is a EMPTY command */
	public boolean checkEmpty() {
		return commandType == CommandType.EMPTY;
	}
	
    /** @return true if the command is a ENTER command */
	public boolean checkEnter() {
		return commandType == CommandType.ENTER;
	}
	
    /** @return true if the command is a CROSS command */
	public boolean checkCross() {
		return commandType == CommandType.CROSS;
	}
	
    /** @return true if the command is a REMOVE command */
	public boolean checkRemove() {
		return commandType == CommandType.REMOVE;
	}
	
    /** @return true if the command is a FEED command */
	public boolean checkFeed() {
		return commandType == CommandType.FEED;
	}
	
    /** @return true if the command is a TURN command */
	public boolean checkTurn() {
		return commandType == CommandType.TURN;
	}
	
    /** @return true if the command is a DIVE command */
	public boolean checkDive() {
		return commandType == CommandType.DIVE;
	}
	
    /** @return true if the command is a THROW command */
	public boolean checkThrow() {
		return commandType == CommandType.THROW;
	}
	
    /** @return true if the command is a BAIL command */
	public boolean checkBail() {
		return commandType == CommandType.BAIL;
	}
	
    /** @return true if the command is a INSERT command */
	public boolean checkInsert() {
		return commandType == CommandType.INSERT;
	}
	
    /** @return true if the command is a BAIL command */
	public boolean checkRig() {
		return commandType == CommandType.RIG;
	}
	
    /** @return true if the command is a EAT command */
	public boolean checkEat() {
		return commandType == CommandType.EAT;
	}
	
    /** @return true if the command is a MOVE command */
	public boolean checkMove() {
		return commandType == CommandType.MOVE;
	}
	
    /** @return true if the command is a EAT command */
	public boolean checkRing() {
		return commandType == CommandType.RING;
	}
	
    /** @return true if the command is a MOVE command */
	public boolean checkCut() {
		return commandType == CommandType.CUT;
	}
	
    /** @return true if the command is a LOAD command */
	public boolean checkLoad() {
		return commandType == CommandType.LOAD;
	}
	
    /** @return true if the command is a SAVE command */
	public boolean checkSave() {
		return commandType == CommandType.SAVE;
	}
	
    /** @return true if the command is a QUIT command */
	public boolean checkQuit() {
		return commandType == CommandType.QUIT;
	}
	
    /** @return true if the command is a RESTART command */
	public boolean checkRestart() {
		return commandType == CommandType.RESTART;
	}
}

/* 3 December 2025 - Created file
 * 5 December 2025 - Cleared game specific data
 * 6 December 2025 - Removed coded command
 * 8 December 2025 - Fixed errors and removed noun table & room functions
 * 				   - Increased version number
 * 9 December 2025 - Added Title
 * 25 December 2025 - Removed commands not added
 * 5 January 2026 - Removed the single command enum
 * 20 January 2026 - Added Inventory Command
 * 21 January 2026 - Moved Inventory to CommandState
 * 22 January 2026 - Added all take like commands
 * 28 January 2026 - Added all drop like commands
 * 31 January 2026 - Added Examine, Give, Say, Pick
 * 16 February 2026 - Merged Pick with Take
 * 22 February 2026 - Added wear command
 * 23 February 2026 - Added Tie Command
 * 25 February 2026 - Added Climb Command
 * 28 February 2026 - Added Use command
 * 3 March 2026 - Added Open Command
 * 8 March 2026 - Added Light Command
 * 10 March 2026 - Added Fill Command
 * 13 March 2026 - Added Plant command and added more command types
 * 14 March 2026 - Added water command
 * 15 March 2026 - Added Swing & Empty Commands
 * 20 March 2026 - Added enter & cross commands
 * 22 March 2026 - Added Remove & Feed
 * 27 March 2026 - Added Turn & Dive
 * 28 March 2026 - Added single command for dive
 * 29 March 2026 - Added next lot of commands
 * 30 March 2026 - Added Throw & Bail
 * 8 April 2026 - Added Insert & Rig
 * 15 April 2026 - Added Eat & Move
 * 20 April 2026 - Added Ring & Cut
 */