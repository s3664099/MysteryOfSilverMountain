/*
Title: Mystery of Silver Mountain Say Class
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.0
Date: 17 February 2026
Source: https://archive.org/details/the-mystery-of-silver-mountain/mode/2up
*/

package commands;

import command_process.ParsedCommand;
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
	
	//1890 R$="YOU SAID IT"
	//1900 IF B=84 THEN R$="YOU MUST SAY THEM ONE BY ONE!":RETURN
	//1910 IF R<>47 OR B<71 OR B>75 OR C(27)<>0 THEN RETURN
	//1920 IF B=71 AND F(60)=0 THEN R$=X7$: F(60)=1:RETURN
	//1930 IF B=72 AND F(60)=1 AND F(61)=1 THEN F(62)=1:RETURN
	//1940 IF B=(F(52)+73) AND F(60)=1 AND F(61)=1 THEN F(62)=1:RETURN
	//1950 R$="THE WRONG SACRED WORD!": F(56)=1:RETURN
}

/* 17 February 2026 - Created File
 */