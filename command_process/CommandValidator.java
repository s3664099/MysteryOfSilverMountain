/*
Title: Mystery of Silver Mountain Command Validator
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.4
Date: 22 May 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package command_process;

import java.util.logging.Logger;

import data.Constants;
import data.GameEntities;
import game.Game;
import game.Player;

/**
 * Validates parsed player commands and ensures they are executable within the game rules. 
 * 
 * <p>This class checks for invalid or incomplete commands, provides feedback to the player,
 * and delegates valid commands to the appropriate handlers (e.g., movement, item actions, consumption). 
 * Special handling is included for game-specific entities such as trapdoors.</p>
 */
public class CommandValidator {
	
	private static final Logger logger = Logger.getLogger(Game.class.getName());
	
    /**
     * Validates a parsed command against the current game and player state.
     *
     * @param command the parsed player command
     * @param game the current game instance
     * @param player the player issuing the command
     * @return an {@link ActionResult} representing the outcome of the validation
     */
	public ActionResult validateCommand(ParsedCommand command, Game game, Player player) {

		boolean validCommand = true;
		
		if (checkVerbAndNounInvalid(command)) {
			logger.info("Validation Failed - Verb & Noun Invalid");
			validCommand = false;
			game = handleCheckVerbAndNounInvalidFails(game);
		} else if (checkVerbOrNounInvalid(command)) {
			logger.info("Validation Failed - Verb or Noun Invalid");
			validCommand = false;
			game = handleVerbOrNounInvalidFails(game,command);
		} else if (checkMissingNoun(command)) {
			logger.info("Validation Failed - Noun Missing");
			validCommand = false;
			game = handleMissingNounFails(game,command);
		} else if (checkNone(command)) {
			logger.info("Validation Failed - Missing Noun Failed");
			validCommand = false;
			game = handleCheckNoneFails(game);
		} else if (isNounMissing(command)) {
			logger.info("Validation Failed - Is Noun Missing True");
			validCommand = false;
			game = handleCheckNounFails(game);
		}
		
		if (validCommand && requiresItem(command.getVerbNumber())) {
			if (!hasItem(game,command.getNounNumber())) {
				game = handleNotCarryingItem(game,command);
				validCommand = false;
			}
		}
		
		ActionResult result = new ActionResult(game,player,validCommand);
		logger.info("Command Valid: "+validCommand);
		
		if (checkResultNull(result)) {
			result = new ActionResult(result.getGame(),player,result.isValid());
		}
		
		return result;
	}
			
    // ===== Command checks =====

    /**
     * @return true if the command contains an invalid verb or noun.
     */
	private boolean checkVerbOrNounInvalid(ParsedCommand command) {
		return (command.getVerbNumber()>Constants.NUMBER_OF_VERBS ||
				command.getNounNumber() == Constants.NUMBER_OF_NOUNS);
	}
	
    /**
     * @return true if both the verb and noun are invalid.
     */
	private boolean checkVerbAndNounInvalid(ParsedCommand command) {

		return (command.getVerbNumber()>Constants.NUMBER_OF_VERBS && 
				command.getNounNumber() == Constants.NUMBER_OF_NOUNS);
	}
	
    /**
     * @return true if a noun is required but missing.
     */
	private boolean checkMissingNoun(ParsedCommand command) {
		return (command.checkMultipleCommandState() && !command.checkNounLength());
	}
	
    /**
     * @return true if the command is unrecognized.
     */
	private boolean checkNone(ParsedCommand command) {
		return (command.checkNoneCommandType());
	}
	
    /**
     * @return true if the noun is missing in a multi-word or movement command.
     */
	private boolean isNounMissing(ParsedCommand command) {
		boolean validCommand = false;
		if (command.checkMoveState() && command.getSplitTwoCommand()[0].equals("go")) {
			if(command.getSplitFullCommand().length==1) {
				validCommand = true;
			}
		}
		return validCommand;
	}
	
    /**
     * @return true if the result is invalid and lacks a player reference.
     */
	private boolean checkResultNull(ActionResult result) {
		return result.getPlayer()==null && !result.isValid();
	}
	
    /**
     * @return true if the result if a verb that doesn't require the item being carried.
     */
	private boolean requiresItem(int verbNumber) {
		return verbNumber == GameEntities.CMD_GET || verbNumber == GameEntities.CMD_TAKE ||
				verbNumber == GameEntities.CMD_PICK || verbNumber == GameEntities.CMD_CLIMB ||
				verbNumber == GameEntities.CMD_HOLD || verbNumber > GameEntities.CMD_BREAK;
	}
	
    /**
     * @return true if the result if the player is carrying the item.
     */
	private boolean hasItem(Game game,int nounNumber) {
		return game.getItem(nounNumber).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	/* Adds response when item isn't being carried */
	private Game handleNotCarryingItem(Game game,ParsedCommand command) {
		game.addMessage("You do not have the "+command.getSplitTwoCommand()[1], true, true);
		return game;
	}
					
    // ===== Error handling =====

	/** Adds a "You can't do that" message when verb/noun invalid. */
	private Game handleVerbOrNounInvalidFails(Game game, ParsedCommand command) {
		game.addMessage("You can't "+command.getCommand(), true, true);
		return game;
	}

    /** Adds a generic "What!!" message for both verb and noun invalid. */
	private Game handleCheckVerbAndNounInvalidFails(Game game) {
		game.addMessage("What!!",true,true);
		return game;
	}
	
    /** Adds a missing noun message. */
	private Game handleMissingNounFails(Game game, ParsedCommand command) {
		
		if (command.getVerbNumber() == GameEntities.CMD_EXAMINE) {
			game.addMessage("You see what you might expect!", true, false);
		} else {
			game.addMessage("Most commands need two words", true, false);
		}
		return game;
	}

	/** Adds an "I don't understand" message. */
	private Game handleCheckNoneFails(Game game) {
		game.addMessage("I don't understand", true, true);
		return game;
	}

    /** Adds a two-word requirement message. */
	private Game handleCheckNounFails(Game game) {
		game.addMessage("Most commands need two words",true,true);
		return game;
	}
}

/* 3 December 2025 - Created File
 * 5 December 2025 - Removed game specific code
 * 6 December 2025 - Removed game specific methods
 * 8 December 2025 - Increased version number
 * 9 December 2025 - Added Title
 * 9 February 2026 - Added alternate response for examine verb only
 * 14 May 2026 - Added check for verb requiring carried item
 * 22 May 2026 - Added validation for carrying item if needed
 */
