/*
 * Class that inherits Enemy 
 * class and creates a pre-
 * populated Enemy named Jinn.
 * 
 */

package edu.tridenttech.cpt287.simplegame;

public class Jinn extends Enemy
{
	private final static int JINN_STRENGTH = 7; 
	private final static int JINN_SPEED = 8; 
	private final static int JINN_POINTS = 11; 

	public Jinn(int numVials) throws Exception
	{
		super("Jinn", JINN_STRENGTH, JINN_SPEED, numVials, JINN_POINTS);
	}
}//END class Jinn
