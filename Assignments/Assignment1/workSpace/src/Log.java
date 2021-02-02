
/*
Duy Anh Nguyen 7892957
January 31, 2021
Log.java
public class Log
This class job is to log activities.
The activities can be log append and read.

Private member:
logList - The linked list to keep track of the log receive.

Public method:
Log() - Constructor to create log.
recordLog() - Log the given time and command.
readLogCommand() - Read the log command with given index.
readLogTime() - Read the log time with given index.
getLength() - The number of record the log has.
*/

public class Log
{
    // Private member
    private List logList = null; // Track of the log receive




    // Public method

    /* Log()
    Constructor to create log.
    */
    public Log()
    {
        this.logList = new List();
    }


    /* recordLog()
    Log the given time and command.

    Parameter:
    TIME - The time stamp.
    COMMAND - The command to log.
    */
    public void recordLog(final int TIME, final String COMMAND)
    {
        // Create a log package
        final LogPackage LOG = new LogPackage(TIME, COMMAND);

        // Log the event
        this.logList.append(LOG);
    }


    public void recordLog(final LogPackage LOG)
    {
        // Log the event
        this.logList.append(LOG);
    }


    /* readLog()
    Read the log command with given index.

    Parameter:
    INDEX - The index to read the log command.

    Return:
    The command logged.
    */
    public String readLogCommand(final int INDEX)
    {
        // Get the log package at given index
        final LogPackage LOG = (LogPackage) this.logList.peekIndex(INDEX);

        // Get the command
        final String COMMAND = LOG.getCommand();

        // Return the command
        return COMMAND;
    }


    /* readLogTime()
    Read the log time with given index.

    Parameter:
    INDEX - The index the read the log time.

    Return:
    The time logged.
    */
    public int readLogTime(final int INDEX)
    {
        // Get the log package at given index
        final LogPackage LOG = (LogPackage) this.logList.peekIndex(INDEX);

        // Get the time
        final int TIME = LOG.getTime();

        // Return the time
        return TIME;
    }


    /* getLength()
    Get the number of record the log has.

    Return:
    Number of log the in the log list.
    */
    public int getLength()
    {
        return this.logList.getLength();
    }

}
