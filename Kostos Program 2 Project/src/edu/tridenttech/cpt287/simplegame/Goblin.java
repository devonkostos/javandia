/*
 * Class that inherits Enemy 
 * class and creates a pre-
 * populated Enemy named Goblin.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public class Goblin extends Enemy
{
	private final static int GOBLIN_STRENGTH = 5; 
	private final static int GOBLIN_SPEED = 10; 

	public Goblin(int numVials) throws Exception
	{
		super("Goblin", GOBLIN_STRENGTH, GOBLIN_SPEED, numVials);
	}
}//END class Goblin
