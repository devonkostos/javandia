/*
 * Interface for entities
 * which collect vials.
 * 
 * @author Devon Kostos
 */

package edu.tridenttech.cpt287.simplegame;

public interface Collector 
{
	int getNumVials();
	void addVials(int vialsToAdd);
	int relinquishVials();
}//END interface Collector
