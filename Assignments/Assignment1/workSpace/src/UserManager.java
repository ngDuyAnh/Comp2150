
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
findUser() - Find user from track list.
userReport() - Get the user and return the user report.

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

        // Get the username that we want to create
        final String USERNAME = LOG_PACKAGE.getArguments();

        // Check if the user already exist in track list
        boolean userFound = false;
        for (int counter = 0; counter < this.userList.getLength() && !userFound; counter++)
        {
            // Compare the username
            if (this.userList.peekIndex(counter).printString().equals(USERNAME))
            {
                userFound = true;
            }
        }

        // User found already
        if (userFound)
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
                this.userList.append(USER);
                resultString = "Confirm. " + USER.printString() + " registered.";
            }
            else
            {
                resultString = "The username does not meet the requirements.";
            }
        }

        // Return the result of operation
        return resultString;
    }



    /*
    Public method:
createUser() - Create the user and manage the user instance.
findUser() - Find user from track list.
userReport() - Get the user and return the user report.

Private method:
validUsername() - Check if the given string is valid conditions
    for the username.
    At most 80 non-whitespace characters.
    Uppercase, lowercase, letter, number, and underscore.
     */

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
            if (USERNAME.matches("^[a-z]A-Z0-9_]+$"))
            {
                validUsername = true;
            }

        }

        // Return
        return validUsername;
    }

}
