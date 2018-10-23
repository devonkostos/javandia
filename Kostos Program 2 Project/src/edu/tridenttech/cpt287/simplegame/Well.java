/*
 * Class that inherits Obstacle 
 * class, implements Collector 
 * interface and creates a pre-
 * populated Obstacle named Well.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public class Well extends Obstacle implements Collector 
{
	private final static int WELL_STRENGTH = 10; 
	private int numVials;
	
	public Well()
	{
		super("Well", WELL_STRENGTH);
	}
	
	public int getNumVials()
	{
		return numVials;
	}
	
	public void addVials(int vialsToAdd)
	{
		numVials += vialsToAdd;
	}
	
	public int relinquishVials()
	{
		int tempVials = getNumVials();
		
		numVials -= numVials;
		
		return tempVials;
	}
}//END class Well
