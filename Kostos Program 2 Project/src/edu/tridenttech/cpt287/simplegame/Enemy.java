/*
 * Class that inherits the 
 * Obstacle class, implements
 * the Warrior and Collector
 * interfaces, stores and
 * modifies enemy data. 
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public class Enemy extends Obstacle implements Warrior, Collector
{
	final private int speed;
	protected boolean canAttack = true;
	private int numVials;
	
	/**
	 * Creates an Enemy with the given parameters.  The points are calculated by adding the strength plus half the speed.
	 * @param name The name of this enemy
	 * @param strength Strength used in determining damage done to opponent
	 * @param speed Speed used to determine whether opponent is caught when pursued
	 * @param numVials The number of vials currently in this Enemy's possession
	 */
	public Enemy(String name, int strength, int speed, int numVials) throws Exception
	{
		super(name, strength);
		this.speed = speed;
		this.numVials = numVials;
	}

	/**
	 * Creates an Enemy with the given parameters.
	 * @param name The name of this enemy
	 * @param strength Strength used in determining damage done to opponent
	 * @param speed Speed used to determine whether opponent is caught when pursued
	 * @param numVials The number of vials currently in this Enemy's possession
	 * @param points The number points that this Enemy is worth
	 */
	public Enemy(String name, int strength, int speed, int numVials, int points) throws ArithmeticException
	{
		super(name, strength, points);
		this.speed = speed;
		this.numVials = numVials;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public boolean canAttack()
	{
		return canAttack;
	}
	
	// This implements the attack method for the Enemy.  Do not modify.
	public void attack(GameEntity player)
	{
		Game.getInstance().attack(this, player);
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
}//END class Enemy
