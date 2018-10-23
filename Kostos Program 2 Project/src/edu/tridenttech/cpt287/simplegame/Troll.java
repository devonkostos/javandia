/*
 * Class that inherits Enemy 
 * class and creates a pre-
 * populated Enemy named Troll.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public class Troll extends Enemy 
{
	private final static int TROLL_STRENGTH = 7; 
	private final static int TROLL_SPEED = 5; 
	private final static int TROLL_POINTS = 10; 

	public Troll(int numVials) throws Exception
	{
		super("Troll", TROLL_STRENGTH, TROLL_SPEED, numVials, TROLL_POINTS);
	}
}//END class Troll
