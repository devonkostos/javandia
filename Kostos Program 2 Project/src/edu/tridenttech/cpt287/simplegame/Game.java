/*
 * Class to display enemy and
 * player information, add and 
 * remove players, load enemies 
 * from text file, specify hit 
 * points based on Random number 
 * and determine who wins or loses.
 * 
 */

package edu.tridenttech.cpt287.simplegame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
	private static Game instance;
	final public int ATTACK_LOSS_FACTOR = 5;
	final public int DEFEND_LOSS_FACTOR = 6;
	final public int MAX_STRENGTH = 10;
	final public int DEFAULT_STRENGTH = 6;
	final public int MAX_ABILITIES = 15;

	Scanner console = new Scanner(System.in);
	Random rand = new Random();

	ArrayList<Obstacle> opponents = new ArrayList<>();
	ArrayList<Player> players = new ArrayList<>();
	
	protected Game()
	{
		//Singleton constructor
	}
	
	public static Game getInstance()
	{
		if (instance == null) 
		{
			instance = new Game();
		}
		
		return instance;
	}
	
	public void loadOpponents(String filename)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String fileRead = br.readLine();
			
			int lineNum = 0;
			
			while (fileRead != null)
			{
				String[] fields = fileRead.split(",");
				
				String name = fields[0];
				
				lineNum++;
				
				try 
				{
					
					if (name.equals("Wall")) 
					{
						if (fields.length != 1)
						{
							throw new Exception("Too many parameters!");
						}
						opponents.add(new Wall());
					}
					else if (name.equals("Well"))
					{
						opponents.add(new Well());
						
						if (fields.length != 1)
						{
							throw new Exception("Too many parameters!");
						}
					}
					else if (name.equals("Jinn"))
					{
						int numVials = Integer.parseInt(fields[1]);
						opponents.add(new Jinn(numVials));
						
						if (fields.length != 2)
						{
							throw new Exception("Too many parameters!");
						}
					}
					else if (name.equals("Goblin"))
					{
						int numVials = Integer.parseInt(fields[1]);
						opponents.add(new Goblin(numVials));
						
						if (fields.length != 2)
						{
							throw new Exception("Too many parameters!");
						}
					}
					else if (name.equals("Troll"))
					{
						int numVials = Integer.parseInt(fields[1]);
						opponents.add(new Troll(numVials));
						
						if (fields.length != 2)
						{
							throw new Exception("Too many parameters!");
						}
					}
					else
					{
						int strength = Integer.parseInt(fields[1]),
							speed = Integer.parseInt(fields[2]),
							numVials = Integer.parseInt(fields[3]),
							points = Integer.parseInt(fields[4]);
						opponents.add(new Enemy(name, strength, speed, numVials, points));
						
						if (fields.length != 5)
						{
							throw new Exception("Too many parameters!");
						}
					}
				}
				catch (Exception ex)
				{
					System.out.printf("Error line %d ---> %s", lineNum, fileRead);
					System.out.printf(" %s\n", ex.getMessage());
				}
		
				fileRead = br.readLine();
			}
			
			br.close();
		}
		
		catch(IOException ex)
		{
			ex.printStackTrace();
		}  
	}
	
	public void play()
	{
		Player player;
		String name;
		int strength;
		String decision;
		Obstacle badGuy = null;
		int count = 0;

		// have we loaded the opponents?
		if (opponents.size() == 0) {
			System.err.println("No opponents have been loaded");
			return;
		}

		System.out.print("Please enter a name for your player. ");
		name = console.nextLine();
		System.out.printf("Please enter the strength for %s. [1-10] ", name);
		strength = console.nextInt();
		console.nextLine();

		if (strength > MAX_STRENGTH || strength < 1)
		{
			System.err.printf("invalid strength, setting to default(%d)%n", DEFAULT_STRENGTH);
			strength = DEFAULT_STRENGTH;
		}

		player = new Player(name, strength, MAX_ABILITIES - strength);
		players.add(player);
		
		System.out.printf("Would you like to add another player? [Y/N] ");
		decision = console.nextLine();
		
		while (decision.toUpperCase().equals("Y"))
		{
			System.out.print("Please enter a name for your player. ");
			name = console.nextLine();
			System.out.printf("Please enter the strength for %s. [1-10] ", name);
			strength = console.nextInt();
			console.nextLine();
			
			if (strength > MAX_STRENGTH || strength < 1)
			{
				System.err.printf("invalid strength, setting to default(%d)%n", DEFAULT_STRENGTH);
				strength = DEFAULT_STRENGTH;
			}
			
			player = new Player(name, strength, MAX_ABILITIES - strength);
			players.add(player);
			
			System.out.printf("Would you like to add another player? [Y/N] ");
			decision = console.nextLine();
		}

		// while loop to play game
		while (opponents.size() > 0 && players.size() > 0)
		{
			count %= players.size();
			
			int selection;
			// get the opponent at the top of the list
			if (badGuy == null || !badGuy.isAlive())
			{
				badGuy = opponents.remove(opponents.size()-1);
			}

			// display information to the user
			show(players.get(count));
			show(badGuy);

			selection = getMenuSelection();
			switch (selection)
			{
				case 'R':
					System.out.println("RETREAT!");
					retreat(players.get(count), badGuy);
					if (!badGuy.isAlive())
					{
						// announce the defeat and award points
						System.out.printf("Congratulations!  You have defeated %s%n", badGuy.getName());
						players.get(count).addPoints(badGuy.getPoints());
					} else {
						// return the opponent to the list
						opponents.add(rand.nextInt(opponents.size()-1), badGuy);
						badGuy = null;
					}
				break;
				case 'A':
					System.out.println("ATTACK!");
					players.get(count).attack(badGuy);
					if (!badGuy.isAlive()) {
						System.out.printf("Congratulations!  You have defeated %s%n", badGuy.getName());
						players.get(count).addPoints(badGuy.getPoints());
					}
				break;
				case 'D':
					System.out.println("DRINK!");
					drink(players.get(count));
				break;
				case 'Q':
					System.out.println("QUIT!");
				return;
			}
			
			if (!players.get(count).isAlive())
			{
				System.out.printf("Sorry, %s!  You have been killed %n", players.get(count).getName());
				
				players.remove(count);
				
				//return;
			}
			
			count++;
		}
	}
	
	private int getMenuSelection()
	{
		char selection = 'x';

		System.out.println("What would you like to do?");
		System.out.println("(R)etreat");
		System.out.println("(A)ttack");
		System.out.println("(D)rink vial");
		System.out.println("(Q)uit");
		selection = console.next().charAt(0);
		return Character.toUpperCase(selection);
	}

	private void show(GameEntity o)
	{
		System.out.println("-----------------------------");
		System.out.printf("Name:     %10s%n", o.getName());
		System.out.printf("Strength: %10d%n", o.getStrength());
		System.out.printf("Health:   %10d%n", o.getHealth());
		System.out.printf("Points:   %10d%n", o.getPoints());
		if (o instanceof Collector) {
			System.out.printf("Vials:   %10d%n", ((Collector)o).getNumVials());
		}
	}

	// Note:  may be called from player (attack) or enemy (retreat)
	public void attack(Warrior attacker, GameEntity defender)
	{
		GameEntity winner = null; // 
		GameEntity loser = null;

		// determine losses based on random number
		// the plus one assures that some damage is always inflicted
		int defenderLoss = (rand.nextInt(attacker.getStrength()) + 1) * ATTACK_LOSS_FACTOR;

		// defender losses
		System.err.printf("Defender looses %d%n", defenderLoss);
		defender.reduceHealth(defenderLoss);

		// if defender is killed on first strike, attacker has no loss
		// otherwise, defender strikes back and attacker also loses health
		if (defender.isAlive())
		{
			int attackerLoss = rand.nextInt(defender.getStrength()) * DEFEND_LOSS_FACTOR;
			System.err.printf("Attacker looses %d%n", attackerLoss);
			attacker.reduceHealth(attackerLoss);
			// if the attacker was killed, make the defender the winner
			if (!attacker.isAlive()) {
				winner = defender;
				loser = attacker;
			}
		} else {
			winner = attacker;
			loser = defender;
		}

		// If we have a winner (still alive) and they collects vials, move them to the winner
		if (winner != null && winner.isAlive() && winner instanceof Collector && loser instanceof Collector)
				((Collector)winner).addVials(((Collector)loser).relinquishVials());
	}
	
	// called from player class
	public void retreat(Player player, GameEntity defender)
	{
		// can defender attack?
		if (defender instanceof Warrior)
		{
			Warrior pursuer = (Warrior)defender;
			// chase attacker
			// the player with the greater speed has an advantage, but doesn't necessarily win
			if (player.getSpeed() * rand.nextDouble() < pursuer.getSpeed() * rand.nextDouble())
			{
				pursuer.attack(player);
			}
		}
	}
	
	private void drink(Player player)
	{
		player.drink();
	}
}//END class Game
