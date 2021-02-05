
/*
Duy Anh Nguyen 7892957
February 2, 2021
UserManager.java
public class UserManager
This class will manage the register users.

Private member:
userList - The linked list to keep track of all the users registered.

Public method:
UserManager() - Constructor to initialize user list.
createUser() - Create the user and manage the user instance.
userReport() - Get the user and return the user report.
findUser() - Find user from track list.
findUserInstance() - Find the user from track list and return instance.

Private method:
validUsername() - Check if the given string is valid conditions
        for the username.
        At most 80 non-whitespace characters.
        Uppercase, lowercase, letter, number, and underscore.
*/

public class UserManager
{
    // Private member
    private List userList = null; // Track the registered users




    // Public method

    /* UserManager()
    Constructor to initialize user list.
    */
    public UserManager()
    {
        this.userList = new List();
    }


    /* createUser()
    Create the user and manage the user instance.
    Return String result of operation.

    Parameter:
    LOG_PACKAGE - The log package contains command and
            argument to create new user.

    Return:
    String result of operation.
    */
    public String createUser(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // Get the user report
        if (ARGUMENTS_TOKENS.length == 1 && ARGUMENTS_TOKENS[0].isEmpty())
        {
            final String USERNAME = ARGUMENTS_TOKENS[0];

            // Check if the user already exist in the track list
            if (this.findUser(USERNAME))
            {
                resultString = "Duplicated. User already exist.";
            }
            else
            {
                // Check that the username meets the standard
                boolean validUsername = this.validUsername(USERNAME);

                // Create and add new user
                if (validUsername)
                {
                    final User USER = new User(USERNAME);
                    USER.recordLog(LOG_PACKAGE);
                    this.userList.append(USER);
                    resultString = "Confirm. " + USER.printString() + " registered.";
                }
                else
                {
                    resultString = "The username does not meet the requirements.";
                }
            }
        }
        else
        {
            resultString = "Create user. Too few or too many arguments.";
        }

        // Return the result of operation
        return resultString;
    }


    /* userReport()
    Get the user and return the user report.

    Parameter:
    LOG_PACKAGE - The command package to get the user
            we want to get the report.

    Return:
    String report of the user.
    */
    public String userReport(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // Get the user report
        if (ARGUMENTS_TOKENS.length == 1 && ARGUMENTS_TOKENS[0].isEmpty())
        {
            final String USERNAME = ARGUMENTS_TOKENS[0];

            // Find the user
            if (this.findUser(USERNAME))
            {
                // Get the user report
                resultString = ((User) this.findUserInstance(USERNAME)).userReport();
            }
            else
            {
                resultString = "User not found.";
            }
        }
        else
        {
            resultString = "User report. Too few or too many arguments.";
        }

        // Return the result of operation
        return resultString;
    }


    /* findUser()
    Find user from track list.

    Parameter:
    USERNAME - Given username to find in the track list.

    Return:
    Flag if user exist in the track list.
    */
    public boolean findUser(final String USERNAME)
    {
        // Check if the user already exist in track list
        final User USER_FOUND = this.findUserInstance(USERNAME);
        boolean userFound = true;
        if (USER_FOUND == null)
        {
            userFound = false;
        }

        // Return flag
        return userFound;
    }


    /* findUserInstance()
    Find the user from track list and return instance.

    Parameter:
    USERNAME - String username to find the user.

    Return:
    Instance of the user found.
    */
    public User findUserInstance(final String USERNAME)
    {
        // Local variable dictionary
        User userFound = null;

        // Find user in track list
        for (int counter = 0; counter < this.userList.getLength() && userFound == null; counter++)
        {
            // Compare the username
            if (this.userList.peekIndex(counter).printString().equals(USERNAME))
            {
                userFound = (User) this.userList.peekIndex(counter);
            }
        }

        // Return the instance found
        return userFound;
    }




    // Private method

    /* validUsername()
    Check if the given string is valid conditions
        for the username.
    At most 80 non-whitespace characters.
    Uppercase, lowercase, letter, number, and underscore.

    Parameter:
    USERNAME - Username String.

    Return:
    Flag if the username is valid to use. Meet all the standards.
    */
    private boolean validUsername(final String USERNAME)
    {
        // Local variable dictionary
        boolean validUsername = false;

        // The number of characters cannot be more than 80 characters
        if (USERNAME.length() <= 80)
        {
            if (USERNAME.matches("^[a-zA-Z0-9_]+$"))
            {
                validUsername = true;
            }
        }

        // Return
        return validUsername;
    }

}
