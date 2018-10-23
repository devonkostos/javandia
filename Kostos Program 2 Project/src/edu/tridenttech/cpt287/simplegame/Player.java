/*
 * Class that implements
 * the Warrior and Collector
 * interfaces, stores and
 * modifies player data. 
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public class Player implements Warrior, Collector
{
	private String name;
	private int strength;
	private int health = 50;
	final private int speed;
	protected boolean canAttack = true;
	private int numVials = 0;
	private int points;
	
	/**
	 * Protagonist in the mythical land of JAVAndia
	 * @param name The name of this player
	 * @param strength How strong the player is
	 * @param speed How fast the player can run away
	 */
	public Player(String name, int strength, int speed)
	{
		this.name = name;
		this.strength = strength;
		this.speed = speed;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public boolean canAttack()
	{
		return canAttack;
	}
	
	// DO NOT MODIFY
	public void attack(GameEntity opponent)
	{
		// The player calls the 'attack' method in Game.
		Game.getInstance().attack(this, opponent);
	}
	
	// DO NOT MODIFY
	public void run(GameEntity opponent)
	{
		// calls the retreat method in Game
		Game.getInstance().retreat(this, opponent);
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
		
		numVials = 0;
		
		return tempVials;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void addPoints(int pointsToAdd)
	{
		points += pointsToAdd;
	}
	
	public void reduceHealth(int healthToReduce)
	{
		health -= healthToReduce;
	}
	
	public boolean isAlive()
	{
		if (health <= 0)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	public void drink()
	{
		final int MAX_HEALTH = 150, GAIN_HEALTH = 50;
		
		if (numVials > 0)
		{
			numVials = getNumVials() - 1;
			
			if (health <= 100)
			{
				health += GAIN_HEALTH;
			}
			else
			{
				health = MAX_HEALTH;
			}
		}	
	}
}//END class Player