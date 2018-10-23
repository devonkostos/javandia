/*
 * Interface implemented
 * by every player and
 * obstacle in the game.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public interface GameEntity 
{
	String getName();
	int getStrength();
	int getHealth();
	int getPoints();
	void reduceHealth(int healthToReduce);
	boolean isAlive();
}//END interface GameEntity
