
/*
Duy Anh Nguyen 7892957
February 2, 2021
DocumentManager.java
public class DocumentManager
This class will manage the documents.

Private member:
docList - The linked list to keep track of all the documents.

Public method:
DocumentManager() - Constructor to initialize document list.
createDocument() - Create the document and manage the document
        instance.
documentHistory() - Get the history of changes of the document.
findDocument() - Find document from track list.
findDocumentInstance() - Find the document from track list and return instance.

Private method:
validUsername() - Check if the given string is valid conditions
        for the username.
        At most 80 non-whitespace characters.
        Uppercase, lowercase, letter, number, and underscore.
*/

public class DocumentManager
{
    // Private member
    private List docList = null; // Linked list to keep track of documents
    private UserManager userManager = null; // Handle to the user manager




    // Public method

    /* DocumentManager()
    Constructor to initialize document list.
    */
    public DocumentManager(final UserManager USER_MANAGER)
    {
        this.docList = new List();
        this.userManager = USER_MANAGER;
    }


    /* createDocument()
    Create the document and manage the document
            instance.

    Parameter:
    LOG_PACKAGE - The log package contains command and
            argument to create new document.

    Return:
    String result of operation.
    */
    public String createDocument(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Get the arguments that contain the document name
        final String ARGUMENTS_LINE = LOG_PACKAGE.getArguments().trim();

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = ARGUMENTS_LINE.trim().split("\\s+");

        // There should only be two arguments
        if (ARGUMENTS_TOKENS.length == 2)
        {
            // Get the user and document name arguments
            final String DOC_NAME = ARGUMENTS_TOKENS[0];
            final String USER_NAME = ARGUMENTS_TOKENS[1];

            // Check if the document already exist
            if (this.findDocument(DOC_NAME))
            {
                resultString = "Duplicated. Document already exist.";
            }
            else if (!this.userManager.findUser(USER_NAME))
            {
                resultString = "User not found. User does not exist.";
            }
            else // User exists and document has not yet created
            {
                // Check that the document name meets the standard
                boolean validDocumentName = this.validDocumentName(DOC_NAME);

                // Create and add new user
                if (validDocumentName)
                {
                    // Make the new document
                    final Document DOC = new Document(LOG_PACKAGE);
                    this.docList.append(DOC);
                    resultString = "Confirm. " + DOC.printString() + " created.";

                    // User activity log
                    User userFound = this.userManager.findUserInstance(USER_NAME);
                    userFound.recordLog(LOG_PACKAGE);
                }
                else
                {
                    resultString = "The username does not meet the requirements.";
                }
            }

        }
        else
        {
            resultString = "Create document. Too few or too many arguments.";
        }

        // Return the result of operation
        return resultString;
    }


    /* documentHistory()
    Get the history of changes of the document.

    Parameter:
    LOG_PACKAGE - The command package to get the document
            we want to get the report.

    Return:
    String report of the document.
    */
    public String documentHistory(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // Get the history of the document
        if (ARGUMENTS_TOKENS.length == 1)
        {
            // Get the document name
            final String DOC_NAME = (LOG_PACKAGE.getArguments().trim().split("\\s+"))[0];

            // Get the history changes of the document
            if (this.findDocument(DOC_NAME))
            {
                // Get the document and get its history log
                resultString = ((Document) this.findDocumentInstance(DOC_NAME)).history();
            }
            else
            {
                resultString = "Document not found.";
            }
        }

        // Return the result of operation
        return resultString;
    }


    /* findDocument()
    Find document from track list.

    Parameter:
    DOCUMENT_NAME - The document name.

    Return:
    Flag if given document name exist in track list.
    */
    public boolean findDocument(final String DOCUMENT_NAME)
    {
        // Check if the document already exist in track list
        final Document DOCUMENT_FOUND = this.findDocumentInstance(DOCUMENT_NAME);
        boolean documentFound = true;
        if (DOCUMENT_FOUND == null)
        {
            documentFound = false;
        }

        // Return flag
        return documentFound;
    }


    /* findDocumentInstance()
    Find the document from track list and return instance.

    Parameter:
    DOCUMENT_NAME - String document name to find the user.

    Return:
    Instance of the user found.
    */
    public Document findDocumentInstance(final String DOCUMENT_NAME) {
        // Local variable dictionary
        Document documentFound = null;

        // Find document in track list
        for (int counter = 0; counter < this.docList.getLength() && documentFound == null; counter++)
        {
            // Compare the document name
            if (this.docList.peekIndex(counter).printString().equals(DOCUMENT_NAME))
            {
                documentFound = (Document) this.docList.peekIndex(counter);
            }
        }

        // Return the instance found
        return documentFound;
    }




    // Private method

    /* validDocumentName()
    Check if the given string is valid conditions
            for the document name.
    At most 80 non-whitespace characters.
    Uppercase, lowercase, letter, number, and underscore.

    Parameter:
    DOC_NAME - Document name String.

    Return:
    Flag if the document name is valid to use. Meet all the standards.
    */
    private boolean validDocumentName(final String DOC_NAME)
    {
        // Local variable dictionary
        boolean validDocName = false;

        // The number of characters cannot be more than 80 characters
        if (DOC_NAME.length() <= 80)
        {
            if (DOC_NAME.matches("^[a-z]A-Z0-9_]+$"))
            {
                validDocName = true;
            }
        }

        // Return
        return validDocName;
    }

}
