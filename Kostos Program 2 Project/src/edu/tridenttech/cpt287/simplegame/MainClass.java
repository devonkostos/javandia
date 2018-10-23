/*
 * Simulate a text based game
 * using multiple classes.
 * 
 */

package edu.tridenttech.cpt287.simplegame;

public class MainClass
{
	public static void main(String[] args)
	{
		Game game = Game.getInstance();
		game.loadOpponents("opponents.txt");
		game.play();
	}
}//END class MainClass
