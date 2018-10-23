/*
 * Class that inherits Obstacle 
 * class and creates a pre-
 * populated Obstacle named Wall.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public class Wall extends Obstacle
{
	private final static int WALL_STRENGTH = 10; 
	
	public Wall()
	{
		super("Wall", WALL_STRENGTH);
	}
}//END class Wall
