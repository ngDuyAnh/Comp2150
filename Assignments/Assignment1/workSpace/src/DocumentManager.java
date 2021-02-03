
/*
Duy Anh Nguyne 7892957
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

Private method:
findDocument() - Find document from track list.
findUserIndex() - Find the document and return the index the document is
        in the list.
validUsername() - Check if the given string is valid conditions
        for the username.
        At most 80 non-whitespace characters.
        Uppercase, lowercase, letter, number, and underscore.
*/

public class DocumentManager
{
    // Private member
    private List docList = null; // Linked list to keep track of documents




    // Public method

    /* DocumentManager()
    Constructor to initialize document list.
    */
    public DocumentManager()
    {
        this.docList = new List();
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
        final String[] ARGUMENTS_TOKENS = ARGUMENTS_LINE.split("\\s+");

        // There should only be two arguments
        if (ARGUMENTS_TOKENS.length == 2)
        {
            final String DOC_NAME = ARGUMENTS_TOKENS[0];

            // Check if the document already exist
            if (this.findDocument(DOC_NAME))
            {
                resultString = "Duplicated. Document already exist.";
            }
            else
            {
                // Check that the document name meets the standard
                boolean validDocumentName = this.validUsername(DOC_NAME);

                // Create and add new user
                if (validDocumentName)
                {
                    final Document DOC = new Document(LOG_PACKAGE);
                    this.docList.append(DOC);
                    resultString = "Confirm. " + DOC.printString() + " created.";
                }
                else
                {
                    resultString = "The username does not meet the requirements.";
                }
            }

        }
        else
        {
            resultString = "Too many or few arguments. Expected 2 arguments.";
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

        // Get the arguments that contain the document name
        final String ARGUMENTS_LINE = LOG_PACKAGE.getArguments();

        // Get the document name
        final String DOC_NAME = (LOG_PACKAGE.getArguments().trim().split("\\s+"))[0];

        // Get the history changes of the document
        if (this.findDocument(DOC_NAME))
        {
            // Get the index of the user
            final int INDEX = this.findDocumentIndex(DOC_NAME);
            resultString = ((Document) this.docList.peekIndex(INDEX)).history();
        }
        else
        {
            resultString = "Document not found.";
        }

        // Return the result of operation
        return resultString;
    }


    

    // Private method

    /* findDocument()
    Find document from track list.

    Parameter:
    DOCUMENT_NAME - The document name.

    Return:
    Flag if given document name exist in track list.
    */
    private boolean findDocument(final String DOCUMENT_NAME)
    {
        // Check if the user already exist in track list
        final int INDEX = findDocumentIndex(DOCUMENT_NAME);
        boolean docFound = false;
        if (INDEX >= 0)
        {
            docFound = true;
        }

        // Return flag
        return docFound;
    }


    /*
    findUserIndex() - Find the document and return the index the document is
            in the list.

    Parameter:
    DOCUMENT_NAME - The document name.

    Return:
    Index of given document name exist in track list.
    */
    private int findDocumentIndex(final String DOCUMENT_NAME)
    {
        // Local variable dictionary
        int docIndex = -1;

        // Check if the user already exist in track list
        boolean docFound = false;
        for (int counter = 0; counter < this.docList.getLength() && !docFound; counter++)
        {
            // Compare the username
            if (this.docList.peekIndex(counter).printString().equals(DOCUMENT_NAME))
            {
                docFound = true;
                docIndex = counter;
            }
        }

        // Return the index of the user
        return docIndex;
    }


    /* validUsername()
    Check if the given string is valid conditions
            for the document name.
    At most 80 non-whitespace characters.
    Uppercase, lowercase, letter, number, and underscore.

    Parameter:
    DOC_NAME - Document name String.

    Return:
    Flag if the document name is valid to use. Meet all the standards.
    */
    private boolean validUsername(final String DOC_NAME)
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
