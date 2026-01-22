/*
Title: Mystery of Silver Mountain Command Executor Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.2
Date: 20 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package command_process;

import java.util.logging.Logger;

import commands.Inventory;
import commands.Take;
import commands.Move;
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
			result = new Take(game,player).executeTake();
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
 */