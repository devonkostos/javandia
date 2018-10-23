/*
 * Class that implements
 * the Game Entity interface,
 * stores and modifies 
 * obstacle data. 
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public class Obstacle implements GameEntity
{
	final private String name;
	final private int strength;
	final protected int points;
	private int health = 50;
	
	protected Obstacle(String name, int strength)
	{
		this(name, strength, 2*strength);
	}

	protected Obstacle(String name, int strength, int points)
	{
		this.name = name;
		this.strength = strength;
		this.points = points;
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

	public int getPoints()
	{
		return points;
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
}//END class Obstacle