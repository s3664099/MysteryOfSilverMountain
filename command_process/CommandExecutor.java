/*
Title: Mystery of Silver Mountain Command Executor Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.10
Date: 22 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package command_process;

import java.util.logging.Logger;

import commands.Drop;
import commands.Examine;
import commands.Give;
import commands.Inventory;
import commands.Take;
import commands.Tie;
import commands.Wear;
import commands.Move;
import commands.Say;
import game.Game;
import game.Player;
import game.PostCommand;
import persistence.Persistence;

/**
 * The {@code CommandExecutor} is responsible for carrying out parsed and validated
 * commands issued by the player. It interprets the {@link ParsedCommand} type and
 * dispatches execution to the appropriate command handler (movement, item handling,
 * combat, persistence, etc.). It acts as the final stage of the command-processing
 * pipeline after parsing and validation.
 */
public class CommandExecutor {
	
	private static final Logger logger = Logger.getLogger(CommandExecutor.class.getName());
	
	/**
	 * Executes a player command within the context of the current {@link Game} state.
	 * <p>
	 * This method determines the category of the {@link ParsedCommand}, then delegates
	 * execution to the relevant command class such as {@link Move}, {@link ItemCommands},
	 * {@link Combat}, {@link Miscellaneous}, or {@link Persistence}.
	 * <p>
	 * Special cases, such as moving through a trapdoor, are also handled here.
	 *
	 * @param game    the current game instance containing rooms, items, and global state
	 * @param player  the player issuing the command
	 * @param command the parsed player command to be executed
	 * @return an {@link ActionResult} describing the outcome of the command execution,
	 *         including updated game state and feedback messages
	 */
	public ActionResult executeCommand(Game game,Player player,ParsedCommand command) {
		
		ActionResult result = new ActionResult(game,player,false);

		if (command.checkMoveState()) {
			logger.info("Moving");
			result = new Move().executeMove(game,player,command);
		} else if (command.checkInventory()) {
			logger.info("Inventory");
			result = new Inventory(game,player).getInventory();
		} else if (command.checkTake()) {
			logger.info("Take");
			result = new Take(game,player,command).executeTake();
		} else if (command.checkDrop()) {
			logger.info("Drop");
			result = new Drop(game,player,command).executeDrop();
		} else if (command.checkExamine()) {
			logger.info("Examine");
			result = new Examine(game,player,command).executeExamine();
		} else if (command.checkGive()) {
			logger.info("Give");
			result = new Give(game,player,command).executeGive();
		} else if (command.checkSay()) {
			logger.info("Say");
			result = new Say(game,player,command).executeSay();
		} else if (command.checkWear()) {
			logger.info("Wear");
			result = new Wear(game,player,command).executeWear();
		} else if (command.checkTie()) {
			logger.info("Tie");
			result = new Tie(game,player,command).executeTie();
		} else if (command.checkSave()) {
			logger.info("Save");
			result = new Persistence(game,player,command).save();
		} else if (command.checkLoad()) {
			logger.info("Load");
			Persistence load = new Persistence(game,player,command);
			result = load.load();
		} else if (command.checkQuit()) {
			logger.info("Quit");
			result = new Persistence(game,player,command).quit();
		} else if (command.checkRestart()) {
			logger.info("Restart");
			result = new Persistence(game,player,command).restart();
		}
		PostCommand updates = new PostCommand(result);
		return updates.postUpdates();
	}
}

/* 3 December 2025 - Increased version number
 *                 - Removed all but move, save, load, quit & restart
 * 4 December 2025 - Added Class Title Back
 * 8 December 2025 - Increased version number
 * 9 December 2025 - Added Title
 * 20 January 2026 - Added Inventory command
 * 21 January 2026 - Completed inventory
 * 23 January 2026 - Passed Parsed Command through to take
 * 28 January 2026 - Added Drop command
 * 31 January 2026 - Added Examine, Pick, Give, Say
 * 1 February 2026 - Added call to examine class
 * 12 February 2026 - Added call to give class
 * 16 February 2026 - Removed pick
 * 19 February 2026 - Added executeSay
 * 22 February 2026 - Added wear command
 */