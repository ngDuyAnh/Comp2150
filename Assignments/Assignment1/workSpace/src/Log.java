
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
readLogArguments() - Read the log arguments with given index.
readLogTime() - Read the log time with given index.
getLength() - The number of record the log has.
printOldNewString() - Printable String the log from order
    oldest to newest.
printNewOldString() - Printable String the log from order
    newest to oldest.
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
    LOG_PACKAGE - The log package to add to the log list.
    */
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


    /* readLogArguments()
    Read the log arguments with given index.

    Parameter:
    INDEX - The index to read the log arguments.

    Return:
    The arguments logged.
    */
    public String readLogArguments(final int INDEX)
    {
        // Get the log package at given index
        final LogPackage LOG = (LogPackage) this.logList.peekIndex(INDEX);

        // Get the arguments
        final String ARGUMENTS = LOG.getArguments();

        // Return the arguments
        return ARGUMENTS;
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


    /* printOldNewString()
    Return printable String the log from order oldest to newest.

    Return:
    Return String printable String of the log history.
    */
    public String printOldNewString()
    {
        // Local variable dictionary
        String logString = ""; // The String of the log to return

        // Oldest to newest is start from index zero to go forward
        for (int counter = 0; counter < this.logList.getLength(); counter++)
        {
            // Get the log package
            final Item LOG_PACKAGE = this.logList.peekIndex(counter);

            // Get the log package String
            logString += LOG_PACKAGE.printString() + "\n";
        }

        // Return the String of the log
        return logString;
    }


    /* printNewOldString()
    Return printable String the log from order newest to oldest.

    Return:
    Return String printable String of the log history.
    */
    public String printNewOldString()
    {
        // Local variable dictionary
        String logString = ""; // The String of the log to return

        // Oldest to newest is start from index zero to go forward
        for (int counter = 0; counter < this.logList.getLength(); counter++)
        {
            // Get the log package
            final Item LOG_PACKAGE = this.logList
                    .peekIndex((this.logList.getLength() - 1) - counter);

            // Get the log package String
            logString += LOG_PACKAGE.printString() + "\n";
        }

        // Return the String of the log
        return logString;
    }

}
