
/*

abstract TeamIndividual
	int salary
	String name
	public void print() - Print all stored information.


Player
	String position
	print()


Coach
	String role
	List of players
	
	public void addPlayer() // Add player to coach player list
	print()
	

List class
	add()
	size()
	get()
*/

public abstract class TeamIndividual
{
    // Private member
	private int salary = -1;
	private String name = "";
	
	
	// Public method
	
	public TeamIndividual(final String NAME, final int SALARY)
	{
	    this.name = NAME;
		this.salary = SALARY;
	}
	
	public 
	
	public void print()
	{
	    System.out.println(this.name + " " + this.salary);
	}
	
}




public class Player extends TeamIndividual
{
    // Private member
	private String position = "";
	
	
	// Public method
	
	public Player(final String NAME, final int SALARY, final String POSITION)
	{
	    super(NAME, SALARY);
		this.position = POSITION;
	}
	
	public void print()
	{
	    // Print the player's information
		super.print()
		
		// Print the player position
		System.out.println(this.position);
	}

}




public class Coach extends TeamIndividual
{
    // Private member
	private String role;
	private List playerList = null;
	
	
	// Public method
	
	public Coach(final String NAME, final String SALARY, final String ROLE)
	{
	    super(NAME, SALARY);
		this.role = ROLE;
		this.playerList = new List();
	}
	
	public void addPlayer(final Player PLAYER)
	{
	    this.playerList.add(PLAYER);
	}
	
	public void print()
	{
	    // Print coach information
		super.print();
		
		// Print coach role
		System.out.println(this.role);
		
		// Print players' information
		for (int counter = 0; counter < this.playerList.size(); counter++)
		{
		    this.playerList.get(counter).print();
		}
	}
	
}
