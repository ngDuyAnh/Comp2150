
/*
Duy Anh Nguyen 7892957
January 31, 2021
LogPackage.java
public class LogPackage
This class acts as container to store the String command
    and time stamp.

Private member:
COMMAND - The string command.
ARGUMENTS - The string arguments.
timeStamp - The time this command got executed.

Public method:
LogPackage() - Construction to create a log event.
getCommand() - Return the command.
getArguments() - Return the arguments.
getTime() - Return the time stamp.
*/

public class LogPackage extends Item
{
    // Private member
    private final String COMMAND; // The command
    private final String ARGUMENTS; // The arguments of command
    private final int TIME;  // The time stamp




    // Public method

    /* LogPackage()
    Constructor to create a log event.
    */
    public LogPackage(final int TIME, final String COMMAND, final String ARGUEMENTS)
    {
        this.COMMAND = COMMAND;
        this.ARGUMENTS = ARGUEMENTS;
        this.TIME = TIME;
    }


    /* getCommand()
    Return the command.

    Return:
    Return the command string.
    */
    public String getCommand()
    {
        return this.COMMAND;
    }


    /* getArguments()
    Return the arguments.

    Return:
    Return the arguements string.
    */
    public String getArguments()
    {
        return this.ARGUMENTS;
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


    /* printString()
    Return the command and arguments in one line.

    Return:
    String command and arguments in one line.
    */
    @Override
    public String printString()
    {
        return this.COMMAND + " " + this.ARGUMENTS;
    }

}
