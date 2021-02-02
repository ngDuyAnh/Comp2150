
/*
Duy Anh Nguyen 7892957
January 31, 2021
LogPackage.java
public class LogPackage
This class acts as container to store the String command
    and time stamp.

Private member:
command - The string command.
timeStamp - The time this command got executed.

Public method:
LogPackage() - Construction to create a log event.
getCommand() - Return the command.
getTime() - Return the time stamp.
*/

public class LogPackage extends Item
{
    // Private member
    private final String COMMAND; // The command
    private final int TIME;  // The time stamp




    // Public method

    /* LogPackage()
    Constructor to create a log event.
    */
    public LogPackage(final int TIME, final String COMMAND)
    {
        this.COMMAND = COMMAND;
        this.TIME = TIME;
    }


    /* getCommand()
    Return the command.

    Return:
    Return the command.
    */
    public String getCommand()
    {
        return this.COMMAND;
    }


    /* getTime()
    Return the time stamp.

    Return:
    Return the time stamp.
    */
    public int getTime()
    {
        return this.TIME;
    }


    /* print()
    Print the time and command.
    */
    @Override
    public void print()
    {
        System.out.println(this.COMMAND);
    }

}
