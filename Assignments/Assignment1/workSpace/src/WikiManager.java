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
userManager - The user manager.
documentManager - The document manager.

Public static method:
main() - The starting of the application.

Public method:
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
validName() - Check if the given string is valid conditions
    for the document name or the user id name. They both have
    the same standards.
    At most 80 non-whitespace characters.
    Uppercase, lowercase, letter, number, and underscore.
*/

public class WikiManager
{
    // Private member
    private int time = 0;                           // Keep track of time for command time stamp
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
            while (scan.hasNext())
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
    }




    // Public method

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
        final String ARGUEMENTS = scan.nextLine().trim();

        // Do not process if it is a comment
        boolean isCommand = true;
        if (COMMAND.charAt(0) == '#')
        {
            isCommand = false;
        }

        // Process command
        if (isCommand)
        {
            // Package the command request to log
            final LogPackage LOG_PACKAGE =
                    new LogPackage(this.time, COMMAND, ARGUEMENTS);

            // Direct the command
            switch (COMMAND)
            {
                case "USER":
                {
                    processResult += this.newUser(LOG_PACKAGE);
                }
                break;

                case "CREATE":
                {

                }
                break;

                case "APPEND":
                {

                }
                break;

                case "REPLACE":
                {

                }
                break;

                case "DELETE":
                {

                }
                break;

                case "PRINT":
                {

                }
                break;

                case "RESTORE":
                {

                }
                break;

                case "HISTORY":
                {

                }
                break;

                case "USERREPORT":
                {
                    processResult = this.userReport(LOG_PACKAGE);
                }
                break;

                case "QUIT":
                {

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







    /*
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
validName() - Check if the given string is valid conditions
    for the document name or the user id name. They both have
    the same standards.
    At most 80 non-whitespace characters.
    Uppercase, lowercase, letter, number, and underscore.
     */










}
