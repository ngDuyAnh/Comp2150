import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
Duy Anh Nguyen 7892957
January 22, 2021
WikiManager.java
public class WikiManager
The job of the WikiManager is to process the commands
    given.
This class will work closely with the UserManager and the
    DocumentManager to log all activities and retrieve
    information.

Private member:
time - Keep track of time for command read.
wikiActive - Flag if the instance is active.
userManager - The user manager.
documentManager - The document manager.

Public static method:
main() - The starting of the application.

Public method:
WikiManager() - Constructor to create an instance of WikiManager.
processCommandLine() - Take an input line, determine the
    command, and call the according method to process the
    command.

Private method:
newUser() - Register a new user. Return a string to confirm
    the registration or user already exist.
newDocument() - Create a new document. Return a string to
    confirm new document create or document already exist.
appendContentsDocument() - Append contents to the given document.
    Return a string result of the operation.
        The result can be "Not Found.", it is when the document or
            the user does not exist.
        The result can be "Success.", able to append the document.
replaceContentsDocument() - Replace the contents of given line
    number in the document. Return a string result of the operation.
        The result can be "Not Found.", it is when the document or
            the user does not exist.
        The result can be "Fail.", it is when the given line number
            to replace does not exist.
        The result can be "Success.", it is when able to replace
            the contents at given line number.
deleteContentsDocument() - Delete the document from the system.
    Return a string result of the operation.
        The result can be "Not Found.", it is when the document or
            the user does not exist.
        The result can be "Fail.", it is when the given line number
            to delete does not exist.
        The result can be "Success.", it is when able to delete the
            contents at given line number.
restoreDocument() - Restore the document to a given period of time.
    Return a string result of the operation.
    Note, you cannot restore to the time before the document exist.
        The result can be "Not Found.", it is when the document or
            the user does not exist.
        The result can be "Success.", it is when able to restore the
            contents at given time.
printDocument() - Print the contents of the document.
    Return a string that is the document contents, or a string that
        is the result of the operation.
            The result can be "Not Found.", it is when the the document
                does not exist.
            Success retrieving the contents of the given document name,
                it will return a string of the contents of the document.
historyDocument() - Get and return the string history of the document.
    Return a string that is the history log of changes to the document,
        or a string that is the result of operation.
            The result can be "Not Found.", it is when the document
                name given does not exist.
            Success retrieving the edit history of the document, it
                will return a string of the contents of the document.
userReport() - Print user activities.
    Return a string that is the user information and activities, or
        a string that is the result of the operation.
            The result can be "Not Found.", it is when the user does
                not exist.
            Success retrieving the user information and activities,
                it will return a string of the user information and
                activities.
quit() - Terminate the WikiManager session.
    For this assignment, all the data will be erase.
    WikiManager, UserManager, and DocumentManager.
    Return a string message "BYE".
getActiveStatus() - Flag if the WikiManager instance is active.
*/

public class WikiManager
{
    // Private member
    private int time = 0;                           // Keep track of time for command time stamp
    private boolean wikiActive = false;             // Flag if the instance is active.
    private UserManager userManager = null;         // User manager
    private DocumentManager documentManager = null; // Document manager




    // Public static method

    /* main()
    This is the entry point to our WikiManagement application.
    It will get the input file name, read line-by-line command
        to and give it to an instance of WikiManager to process.
    */
    public static void main(String[] args)
    {
        // Local variable dictionary
        WikiManager wikiManager = new WikiManager(); // Instance of application manager
        Scanner scan = null;                         // Read input

        // Get the input file name
        scan = new Scanner(System.in); // Read for input file name
        System.out.print("Input file: ");
        final String INPUT_FILE_NAME = scan.nextLine();

        // Process input file
        try
        {
            // Read the input file and process command
            scan = new Scanner(new File(INPUT_FILE_NAME));
            while (scan.hasNext() && wikiManager.getActiveStatus())
            {
                // Get command line
                final String LINE = scan.nextLine().trim();

                // Process the command
                final String OUTPUT = wikiManager.processCommandLine(LINE);

                // Print the output result of the command
                System.out.println(OUTPUT);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }

        // If quit command is missing
        if (wikiManager.getActiveStatus())
        {
            System.out.println("QUIT command was missing. Terminating...");
        }
    }




    // Public method

    /* WikiManager()
    Constructor to create an instance of WikiManager.
    */
    public WikiManager()
    {
        this.time = 0;
        this.wikiActive = true;
        this.userManager = new UserManager();
        this.documentManager = new DocumentManager(this.userManager);
    }


    /* processCommandLine()
    Take an input line, determine the command, and call the
    according method to process the command.

    Parameter:
    COMMAND_LINE - A String command.

    Return:
    A String result of the command operation.
    */
    public String processCommandLine(final String COMMAND_LINE)
    {
        // Local variable dictionary
        Scanner scan = null; // Read the command String
        String processResult = ""; // The result after process

        // Get the line to process
        scan = new Scanner(COMMAND_LINE); // Read the command line
        final String COMMAND = scan.next().trim();
        final String ARGUMENTS = scan.nextLine().trim();

        // Do not process if it is a comment
        boolean isCommand = true;
        if (COMMAND.charAt(0) == '#')
        {
            isCommand = false;
        }

        // Process command
        if (isCommand && this.wikiActive)
        {
            // Package the command request to log
            final LogPackage LOG_PACKAGE =
                    new LogPackage(this.time, COMMAND, ARGUMENTS);

            // Direct the command
            switch (COMMAND)
            {
                case "USER":
                {
                    processResult = this.newUser(LOG_PACKAGE);
                }
                break;

                case "CREATE":
                {
                    processResult += this.newDocument(LOG_PACKAGE);
                }
                break;

                case "APPEND":
                {
                    processResult = this.appendContentsDocument(LOG_PACKAGE);
                }
                break;

                case "REPLACE":
                {
                    processResult = this.replaceContentsDocument(LOG_PACKAGE);
                }
                break;

                case "DELETE":
                {
                    processResult = this.deleteContentsDocument(LOG_PACKAGE);
                }
                break;

                case "PRINT":
                {
                    processResult = this.printDocument(LOG_PACKAGE);
                }
                break;

                case "RESTORE":
                {
                    processResult = this.restoreDocument(LOG_PACKAGE);
                }
                break;

                case "HISTORY":
                {
                    processResult = this.historyDocument(LOG_PACKAGE);
                }
                break;

                case "USERREPORT":
                {
                    processResult = this.userReport(LOG_PACKAGE);
                }
                break;

                case "QUIT":
                {
                    processResult = this.quit();
                }
                break;

                default:
                {
                    processResult += "Command invalid.";
                }
            }

            // Update time count
            this.time++;
        }

        // Return from process
        return processResult;
    }




    // Private method

    /* newUser()
    Register a new user.
    Return a string to confirm
        the registration or user already exist.

    Parameter:
    LOG_PACKAGE - The command log.

    Return:
    String result of operation.
    */
    private String newUser(final LogPackage LOG_PACKAGE)
    {
        return this.userManager.createUser(LOG_PACKAGE);
    }


    /* newDocument()
    Create a new document. Return a string to
            confirm new document create or document already exist.

    Parameter:
    LOG_PACKAGE - The command log.

    Return:
    String result of operation.
    */
    private String newDocument(final LogPackage LOG_PACKAGE)
    {
        return this.documentManager.createDocument(LOG_PACKAGE);
    }

    
    /* appendContentsDocument()
    Append contents to the given document.
    Return a string result of the operation.
        The result can be "Not Found.", it is when the document or
            the user does not exist.
        The result can be "Success.", able to append the document.

    Parameter:
    LOG_PACKAGE - the command log.

    Return:
    String result of operation.
    */
    private String appendContentsDocument(final LogPackage LOG_PACKAGE)
    {
        return this.documentManager.appendContents(LOG_PACKAGE);
    }


    /* replaceContentsDocument()
    Replace the contents of given line
        number in the document. Return a string result of the operation.
    The result can be "Not Found.", it is when the document or
        the user does not exist.
    The result can be "Fail.", it is when the given line number
        to replace does not exist.
    The result can be "Success.", it is when able to replace
        the contents at given line number.

    Parameter:
    LOG_PACKAGE - The command log.

    Return:
    String result of operation.
    */
    private String replaceContentsDocument(final LogPackage LOG_PACKAGE)
    {
        return this.documentManager.replaceContents(LOG_PACKAGE);
    }


    /* restoreDocument()
    Restore the document to a given period of time.
    Return a string result of the operation.
    Note, you cannot restore to the time before the document exist.
        The result can be "Not Found.", it is when the document or
            the user does not exist.
        The result can be "Success.", it is when able to restore the
            contents at given time.

    Parameter:
    LOG_PACKAGE - The command log.

    Return:
    String result of operation.
    */
    private String restoreDocument(final LogPackage LOG_PACKAGE)
    {
        return this.restoreDocument(LOG_PACKAGE);
    }


    /* deleteContentsDocument()
    Delete the document from the system.
    Return a string result of the operation.
        The result can be "Not Found.", it is when the document or
            the user does not exist.
        The result can be "Fail.", it is when the given line number
            to delete does not exist.
        The result can be "Success.", it is when able to delete the
            contents at given line number.

    Parameter:
    LOG_PACKAGE - THe command log.

    Return:
    String result of operation.
    */
    private String deleteContentsDocument(final LogPackage LOG_PACKAGE)
    {
        return this.deleteContentsDocument(LOG_PACKAGE);
    }


    /* printDocument()
    Print the contents of the document.
    Return a string that is the document contents, or a string that
        is the result of the operation.
            The result can be "Not Found.", it is when the the document
                does not exist.
            Success retrieving the contents of the given document name,
                it will return a string of the contents of the document.
    */
    private String printDocument(final LogPackage LOG_PACKAGE)
    {
        return this.documentManager.documentString(LOG_PACKAGE);
    }


    /* historyDocument()
    Get and return the string history of the document.
    Return a string that is the history log of changes to the document,
        or a string that is the result of operation.
            The result can be "Not Found.", it is when the document
                name given does not exist.
            Success retrieving the edit history of the document, it
                will return a string of the contents of the document.
    */
    private String historyDocument(final LogPackage LOG_PACKAGE)
    {
        return this.documentManager.documentHistory(LOG_PACKAGE);
    }


    /* userReport()
    Print user activities.
    Return a string that is the user information and activities, or
        a string that is the result of the operation.
    The result can be "Not Found.", it is when the user does
        not exist.
    Success retrieving the user information and activities,
        it will return a string of the user information and
        activities.

    Parameter:
    LOG_PACKAGE - The command log.

    Return:
    String result of operation.
    */
    private String userReport(final LogPackage LOG_PACKAGE)
    {
        return this.userManager.userReport(LOG_PACKAGE);
    }


    /* quit()
    Terminate the WikiManager session.
    For this assignment, all the data will be erase.
    WikiManager, UserManager, and DocumentManager.
    Return a string message "BYE".

    Return:
    String result of operation.
    */
    private String quit()
    {
        this.wikiActive = false;
        return "BYE.";
    }


    /* getActiveStatus()
    Flag if the WikiManager instance is active.

    Return:
    Flag if WikiManager is active.
    */
    public boolean getActiveStatus()
    {
        return this.wikiActive;
    }

}
