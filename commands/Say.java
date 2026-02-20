/*
Title: Mystery of Silver Mountain Say Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 19 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import command_process.ParsedCommand;
import data.GameEntities;
import game.Game;
import game.Player;

public class Say {

    /** The active game instance. */
	private final Game game;
	
    /** The active player instance. */
	private final Player player;
	
    /** The current command instance. */
	private final ParsedCommand command;
	
    /**
     * Creates a {@code Drop} handler for moving items from the users inventory
     * to the current location
     * 
     * @param game   current game state
     * @param player   current player state
     * @param command   current command
     */
	public Say(Game game, Player player, ParsedCommand command) {
		this.game = game;
		this.player = player;
		this.command = command;
	}
	
	public ActionResult executeSay() {
		game.addMessage("You said it.", true, false);
		ActionResult result = new ActionResult(game,player,false);
		
		if (isSayMagicWords(command.getNounNumber())) {
			result = sayMagicWords(game,player);
		} else if (isSayingMagicWords(player.getRoom(),command.getNounNumber(),game)) {
			
			if (isSayingAwake(command.getNounNumber(),game)) {
				result = sayingAwake(game,player);
			} else if (isSayingGuide(command.getNounNumber(),game)) {
				result = sayingGuide(game,player);
			} else if (isSayingLastWord(command.getNounNumber(),game)) {
				result = sayingLastWord(game,player);
			} else {
				result = sayingWrongWord(game,player);
			}

			//1940 IF B=(F(52)+73) AND F(60)=1 AND F(61)=1 THEN F(62)=1:RETURN
			//1950 R$="THE WRONG SACRED WORD!": F(56)=1:RETURN
		}
		
		return result;
	}
	
	private boolean isSayMagicWords(int nounNumber) {
		return nounNumber == GameEntities.ITEM_MAGIC_WORDS;
	}
	
	private ActionResult sayMagicWords(Game game,Player player)  {
		game.addMessage("You must say them one by one!", true, false);
		return new ActionResult(game,player,true);
	}
	
	private boolean isSayingMagicWords(int roomNumber,int nounNumber, Game game) {
		return roomNumber == GameEntities.ROOM_SILVER_CHAMBER &&
				nounNumber > GameEntities.ITEM_DOOR &&
				nounNumber < GameEntities.ITEM_CHEST &&
				game.getItem(GameEntities.ITEM_STONE_DESTINY).getItemLocation() == GameEntities.ROOM_CARRYING;
	}
	
	private boolean isSayingAwake(int nounNumber,Game game) {
		return game.getItem(GameEntities.FLAG_FIRST_WORD_SPOKEN).getItemFlag() == 0 &&
				nounNumber == GameEntities.ITEM_AWAKE;
	}
	
	private ActionResult sayingAwake(Game game,Player player) {
		game.addMessage("The mountains rumber!", true, false);
		game.getItem(GameEntities.FLAG_FIRST_WORD_SPOKEN).setItemFlag(1);
		return new ActionResult(game,player,true);
	}
	//1930 IF B=72 AND F(60)=1 AND F(61)=0 THEN R$=X8$: F(61)=1:RETURN
	private boolean isSayingGuide(int nounNumber, Game game) {
		return game.getItem(GameEntities.FLAG_FIRST_WORD_SPOKEN).getItemFlag() == 1 &&
				game.getItem(GameEntities.FLAG_SECOND_WORD_SPOKEN).getItemFlag() == 0 &&
				nounNumber == GameEntities.ITEM_AWAKE;		
	}
}

/* 17 February 2026 - Created File
 * 19 February 2026 - Started adding say responses
 */