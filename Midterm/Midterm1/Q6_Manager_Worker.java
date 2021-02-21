
/*

Employee
int salary
String name

public void print() - Print all store information.
	Manager, print the worker's name associate with the manager.


Manager
role
listWorker - workers list.

public void addWorker() - add worker to the track list.




Worker
division string



LinkedList
add()
size()
get()
*/

public abstract class Employee
{
	// Private member
	private int salary = -1;  // Employee's salary
	private String name = ""; // Employee's name
	
	
	// Public method
	
	public Employee(final String NAME, final int SALARY)
	{
		this.name = NAME;
		this.salary = SALARY;
	}
	
	public int getSalary()
	{
		return this.salary;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void print()
	{
		System.out.println(this.name + " " + this.salary);
	}
}

public class Manager extends Employee
{
	// Private member
	String role = "";        // Manager's role
	List workersList = null; // List track workers under manager
	
	
	// Public method
	
	public Manager(final String NAME, final String SALARY, final String ROLE)
	{
		super(NAME, SALARY);
		this.role = ROLE;
		this.workersList = new List();
	}
	
	public void addWorker(final Worker WORKER)
	{
		this.workersList.add(WORKER);
	}
	
	public String getRole()
	{
		return this.role;
	}
	
	public void print()
	{
		// Print the employee information
		super.print();
		
		// Print the Manager dole
		System.out.println(this.role);
		
		// Print the workers
		for (int counter = 0; counter < this.workersList.size(); counter++)
		{
			this.workersList.get(counter).print();
		}
	}
}

public class Worker extends Employee
{
	// Private member
	String division = ""; // Worker's division
	
	
	// Public method
	
	public Worker(final String NAME,   final String SALARY, final String DIVISION)
	{
		super(NAME, SALARY);
		this.division = DIVISION;
	}
	
	public String getDivision()
	{
		return this.division;
	}
	
	public void print()
	{
		// Print the employee information
		super.print();
		
		// Print the worker division
		System.out.println(this.division);
	}

}
