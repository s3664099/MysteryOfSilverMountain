/*
Title: Mystery of Silver Mountain Use Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 1 March 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Use {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Tie} handler for executing a climb action
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Use(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	 /**
     * Generate a response to a climb action
     *
     * @return an {@link ActionResult} describing validity and effects
     */
	public ActionResult executeUse() {
		ActionResult result = new ActionResult(game,player,true);
		game.addMessage("Nothing happens", true, false);
		
		if (isUseSheet(game,player.getRoom(),command.getNounNumber())) {
			result = useSheet(game,player);
		} else if (isGive(command.getNounNumber())) {
			result = new Give(game,player,command).executeGive();
		} else if (isUseBucket(game,player.getRoom(),command.getNounNumber())) {
			result = useBucket(game,player);
		}
		
		return result;
	}
	
	private boolean isUseSheet(Game game, int roomNumber, int nounNumber) {
		return roomNumber == GameEntities.ROOM_EDGE_LAKE &&
				nounNumber == GameEntities.ITEM_SHEET &&
				game.getItem(GameEntities.ITEM_SHEET).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult useSheet(Game game,Player player) {
		game.addMessage("OK", true, false);
		game.getItem(GameEntities.FLAG_BOAT_POWER).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	
	private boolean isGive(int nounNumber) {
		return nounNumber == GameEntities.ITEM_COINS || nounNumber == GameEntities.ITEM_COIN ||
				nounNumber == GameEntities.ITEM_APPLES || nounNumber == GameEntities.ITEM_APPLE ||
				nounNumber == GameEntities.ITEM_BREAD;
		
	}
	
	private boolean isUseBucket(Game game,int roomNumber,int nounNumber) {
		return roomNumber == GameEntities.ROOM_ROUGH_WATER &&
				nounNumber == GameEntities.ITEM_BUCKET &&
				game.getItem(GameEntities.ITEM_BUCKET).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private ActionResult useBucket(Game game,Player player) {
		game.getItem(GameEntities.FLAG_BOAT_FLAG).setItemFlag(1);
		game.addMessage("You have kept afloat.", true, false);
		return new ActionResult(game,player,true);
	}
	



	//2150 IF H=4115 THEN R$="IT IS NOT BIG ENOUGH!":RETURN
	//2160 IF B=18 OR B=7 THEN GOSUB 2470
	//2170 IF B=13 THEN GOSUB 2730
	//2180 IF B=19 THEN GOSUB 3070
	//2190 IF B=10 THEN GOSUB 2870
	//2200 IF B=16 OR B=6 THEN GOSUB 2380
	
}

/* 28 February 2026 - Created file
 * 1 March 2026 - Started adding action responses
 */
