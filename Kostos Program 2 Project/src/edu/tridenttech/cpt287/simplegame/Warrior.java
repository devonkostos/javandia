/*
 * Interface that extends
 * the GameEntity interface.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public interface Warrior extends GameEntity
{
	int getSpeed();
	boolean canAttack();
	void attack(GameEntity game);
}//END interface Warrior
