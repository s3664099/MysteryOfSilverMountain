/*
Title: Mystery of Silver Mountain Take Item Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 22 January 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ActionResult;
import game.Game;
import game.Player;

public class Take {
	
	private final Game game;
	private final Player player;
	
	public Take(Game game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public ActionResult executeTake() {
		return new ActionResult(game,player,true);
	}

}

/* 22 January 2026 - Created File
 */
