import java.util.Scanner;

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
appendContents() - Append contents to the document.
replaceContents() - Replace a line contents in the document.
deleteContents() - Delete a line contents in the document.
restoreDocument() - Get the LogPackage and perform restore on document.
documentString() - Return the document contents.
documentHistory() - Get the history of changes of the document.
findDocument() - Find document from track list.
findDocumentInstance() - Find the document from track list and return instance.

Private method:
validUsername() - Check if the given string is valid conditions
        for the username.
        At most 80 non-whitespace characters.
        Uppercase, lowercase, letter, number, and underscore.
restoreDocument() - Restore the document at given time.
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

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // There should only be two arguments
        if (ARGUMENTS_TOKENS.length == 2)
        {
            // Get the user and document name arguments
            final String DOC_NAME = ARGUMENTS_TOKENS[0];
            final String USER_NAME = ARGUMENTS_TOKENS[1];

            // Check if the document already exist
            if (this.findDocument(DOC_NAME))
            {
                resultString = "Create document. Document already exist.";
            }
            else if (!this.userManager.findUser(USER_NAME))
            {
                resultString = "Create document. User does not exist.";
            }
            else // User exists and document has not yet created
            {
                // Check that the document name meets the standard
                boolean validDocumentName = this.validDocumentName(DOC_NAME);

                // Create and add new user
                if (validDocumentName)
                {
                    // Make the new document
                    final Document DOC = new Document(DOC_NAME);
                    DOC.recordLog(LOG_PACKAGE);
                    this.docList.append(DOC);
                    resultString = "Confirm. " + DOC_NAME + " created.";

                    // User activity log
                    User userFound = this.userManager.findUserInstance(USER_NAME);
                    userFound.recordLog(LOG_PACKAGE);
                }
                else
                {
                    resultString = "The document name does not meet the requirements.";
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


    /* appendContents()
    Append contents to the document.

    Parameter:
    LOG_PACKAGE - The command package request to append.

    Return:
    String result of operation.
    */
    public String appendContents(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // There should be 3 arguments
        if (ARGUMENTS_TOKENS.length >= 3)
        {
            // Get the username, document name, and append contents arguments
            Scanner scan = new Scanner(LOG_PACKAGE.getArguments().trim());
            final String DOC_NAME = scan.next().trim();
            final String USER_NAME = scan.next().trim();
            final String CONTENTS = scan.nextLine().trim();

            // Check if document and user exist
            if (!this.findDocument(DOC_NAME))
            {
                resultString = "Append contents. Document does not exist.";
            }
            else if (!this.userManager.findUser(USER_NAME))
            {
                resultString = "Append contents. User does not exist.";
            }
            else
            {
                // Append contents to document
                Document doc = (Document) this.findDocumentInstance(DOC_NAME);
                doc.appendContents(CONTENTS);
                doc.recordLog(LOG_PACKAGE);

                // Update user activity
                User user = (User) this.userManager.findUserInstance(USER_NAME);
                user.recordLog(LOG_PACKAGE);

                // Operation return string
                resultString = "Append contents. Successfully append contents.";
            }
        }
        else
        {
            resultString = "Append document. Too few or too many arguments.";
        }

        // Return the result of operation
        return resultString;
    }


    /* replaceContents()
    Replace a line contents in the document.

    Parameter:
    LOG_PACKAGE - The command package to get instruction to replace contents.

    Return:
    String report of the document.
    */
    public String replaceContents(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // There should be 4 arguments
        if (ARGUMENTS_TOKENS.length < 4)
        {
            resultString = "Replace contents. Too few or too many arguments.";
        }
        else
        {
            // Get the user and document name arguments
            Scanner scan = new Scanner(LOG_PACKAGE.getArguments().trim());
            final String DOC_NAME = scan.next().trim();
            final String USER_NAME = scan.next().trim();

            // Get line number
            int lineNumber = -1;
            if (scan.hasNextInt())
            {
                lineNumber = scan.nextInt();
            }

            // Get the replace contents
            final String REPLACE_CONTENTS = scan.nextLine().trim();

            // Check if document and user exist
            if (!this.findDocument(DOC_NAME))
            {
                resultString = "Replace contents. Document does not exist.";
            }
            else if (!this.userManager.findUser(USER_NAME))
            {
                resultString = "Replace contents. User does not exist.";
            }
            else if (lineNumber < 0)
            {
                resultString = "Replace contents. Line number is invalid.";
            }
            else
            {
                // Replace contents to document
                Document doc = (Document) this.findDocumentInstance(DOC_NAME);
                boolean operationSuccess = doc.replaceContents(lineNumber, REPLACE_CONTENTS);

                // Operation failed or success
                if (!operationSuccess)
                {
                    resultString = "Replace contents. Failed contents replace.";
                }
                else
                {
                    // Log for document
                    doc.recordLog(LOG_PACKAGE);

                    // Update user activity
                    User user = (User) this.userManager.findUserInstance(USER_NAME);
                    user.recordLog(LOG_PACKAGE);

                    // Operation success
                    resultString = "Replace contents. Successful contents replace.";
                }
            }
        }

        // Return the result of operation
        return resultString;
    }


    /* deleteContents()
    Delete a line contents in the document.

    Parameter:
    LOG_PACKAGE 0 The command package to get the document and arguments
            to replace a line contents.

    Return:
    String report of the document.
    */
    public String deleteContents(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // There should be 4 arguments
        if (ARGUMENTS_TOKENS.length < 3)
        {
            resultString = "Delete document. Too few or too many arguments.";
        }
        else
        {
            // Get the user and document name arguments
            Scanner scan = new Scanner(LOG_PACKAGE.getArguments().trim());
            final String DOC_NAME = scan.next().trim();
            final String USER_NAME = scan.next().trim();

            // Get line number
            int lineNumber = -1;
            if (scan.hasNextInt())
            {
                lineNumber = scan.nextInt();
            }

            // Check if document and user exist
            if (!this.findDocument(DOC_NAME))
            {
                resultString = "Delete contents. Document does not exist.";
            }
            else if (!this.userManager.findUser(USER_NAME))
            {
                resultString = "Delete contents. User does not exist.";
            }
            else if (lineNumber < 0)
            {
                resultString = "Delete contents. Line number is invalid.";
            }
            else
            {
                // Remove contents to document
                Document doc = (Document) this.findDocumentInstance(DOC_NAME);
                boolean contentsRemoved = doc.removeContents(lineNumber);

                // Update the log
                if (!contentsRemoved)
                {
                    resultString = "Delete contents. Failed contents remove.";
                }
                else
                {
                    // Update log for the document
                    doc.recordLog(LOG_PACKAGE);

                    // Update user activity
                    User user = (User) this.userManager.findUserInstance(USER_NAME);
                    user.recordLog(LOG_PACKAGE);

                    // Success operation
                    resultString = "Delete contents. Able contents remove.";
                }
            }
        }

        // Return operation result
        return resultString;
    }


    /* restoreDocument()
    Restore the document as given time.

    Parameter:
    LOG_PACKAGE - The command package to get the document
            we want to get the report.

    Return:
    String report of the document.
    */
    public String restoreDocument(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // There should be 3 arguments
        if (ARGUMENTS_TOKENS.length != 3)
        {
            resultString = "Restore document. Too few or too many arguments.";
        }
        else
        {
            // Get the user and document name arguments
            Scanner scan = new Scanner(LOG_PACKAGE.getArguments().trim());
            final String USER_NAME = scan.next().trim();
            final String DOC_NAME = scan.next().trim();

            // Get line number
            int restoreTime = -1;
            if (scan.hasNextInt())
            {
                restoreTime = scan.nextInt();
            }

            // Check if document and user exist
            if (!this.findDocument(DOC_NAME))
            {
                resultString = "Restore contents. Document does not exist.";
            }
            else if (!this.userManager.findUser(USER_NAME))
            {
                resultString = "Restore contents. User does not exist.";
            }
            else if (restoreTime < 0)
            {
                resultString = "Restore contents. Line number is invalid.";
            }
            else
            {
                // Restore document
                Document DOC = (Document) this.findDocumentInstance(DOC_NAME);
                boolean restored = this.restoreDocument(DOC, restoreTime);

                // Record log
                if (!restored)
                {
                    resultString = "Restore contents. Failed to restore contents.";
                }
                else
                {
                    // Record document log
                    DOC.recordLog(LOG_PACKAGE);

                    // Update document activity
                    User user = (User) this.userManager.findUserInstance(USER_NAME);
                    user.recordLog(LOG_PACKAGE);

                    // Operation return string
                    resultString = "Restore contents. Successfully restore contents.";
                }
            }
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
        if (ARGUMENTS_TOKENS.length == 1 && !ARGUMENTS_TOKENS[0].isBlank())
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
                resultString = "History document. Document not found.";
            }
        }
        else
        {
            resultString = "History document. Too few or too many arguments.";
        }

        // Return the result of operation
        return resultString;
    }


    /* documentString()
    Return the document contents.

    Parameter:
    LOG_PACKAGE - The command package.

    Return:
    String contents of the document.
    */
    public String documentString(final LogPackage LOG_PACKAGE)
    {
        // Local variable dictionary
        String resultString = "";

        // Token the arguments
        final String[] ARGUMENTS_TOKENS = LOG_PACKAGE.getArguments().trim().split("\\s+");

        // Get the contents of the document
        if (ARGUMENTS_TOKENS.length != 1)
        {
            resultString = "Document contents. Too few or too many arguments.";
        }
        else
        {
            // Get the document name
            final String DOC_NAME = (LOG_PACKAGE.getArguments().trim().split("\\s+"))[0];

            // Get the history changes of the document
            if (this.findDocument(DOC_NAME))
            {
                // Get the document and get its history log
                resultString = ((Document) this.findDocumentInstance(DOC_NAME)).printString();
            }
            else
            {
                resultString = "Document contents. Document not found.";
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
    public Document findDocumentInstance(final String DOCUMENT_NAME)
    {
        // Local variable dictionary
        Document documentFound = null;

        // Find document in track list
        for (int counter = 0; counter < this.docList.getLength() && documentFound == null; counter++)
        {
            // Compare the document name
            if (( (Document) this.docList.peekIndex(counter)).getDocName().equals(DOCUMENT_NAME))
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
            if (DOC_NAME.matches("^[a-zA-Z0-9_]+$"))
            {
                validDocName = true;
            }
        }

        // Return
        return validDocName;
    }


    /* restoreDocument()
    Restore the document at given time.

    Parameter:
    DOC- Handle to document.
    */
    private boolean restoreDocument(final Document DOC, final int RESTORE_TIME)
    {
        // Local variable dictionary
        boolean doneRestore = false;
        Scanner scan = null;      // Read arguments

        // Get the document and document log to restore
        Log docLog = DOC.getLogList();

        // Get the time to document created
        final int DOC_CREATED = docLog.readLogTime(0);

        // Restore the document
        if (RESTORE_TIME >= DOC_CREATED)
        {
            // Delete all contents in document
            DOC.deleteAllContents();

            // Restore the document to the right point
            for (int counter = 0; counter < docLog.getLength() && !doneRestore; counter++)
            {
                // Get the log package to perform the restore
                final int TIME = docLog.readLogTime(counter);
                final String COMMAND = docLog.readLogCommand(counter);
                final String ARGUMENTS = docLog.readLogArguments(counter);

                // Setup the arguments for process
                scan = new Scanner(ARGUMENTS.trim());

                // Process the command
                if (TIME > RESTORE_TIME)
                {
                    doneRestore = true;
                }
                else
                {
                    switch (COMMAND)
                    {
                        case "APPEND":
                        {
                            // Get the username, document name, and append contents arguments
                            final String ARG_DOC_NAME = scan.next().trim();
                            final String ARG_USER_NAME = scan.next().trim();
                            final String ARG_CONTENTS = scan.nextLine().trim();

                            // Append the contents to the document
                            DOC.appendContents(ARG_CONTENTS);
                        }
                        break;

                        case "REPLACE":
                        {
                            // Get the user and document name arguments
                            final String ARG_DOC_NAME = scan.next().trim();
                            final String ARG_USER_NAME = scan.next().trim();

                            // Get line number
                            int lineNumber = -1;
                            if (scan.hasNextInt())
                            {
                                lineNumber = scan.nextInt();
                            }

                            // Get the replace contents
                            final String REPLACE_CONTENTS = scan.nextLine().trim();
                            DOC.replaceContents(lineNumber, REPLACE_CONTENTS);
                        }
                        break;

                        case "DELETE":
                        {
                            // Get the user and document name arguments
                            final String ARG_DOC_NAME = scan.next().trim();
                            final String ARG_USER_NAME = scan.next().trim();

                            // Get line number
                            int lineNumber = -1;
                            if (scan.hasNextInt())
                            {
                                lineNumber = scan.nextInt();
                            }

                            // Delete contents
                            DOC.removeContents(lineNumber);
                        }
                        break;

                        case "RESTORE":
                        {
                            // Get the user and document name arguments
                            final String ARG_USER_NAME = scan.next().trim();
                            final String ARG_DOC_NAME = scan.next().trim();

                            // Get line number
                            int time = -1;
                            if (scan.hasNextInt())
                            {
                                time = scan.nextInt();
                            }

                            // Restore the document
                            this.restoreDocument(DOC, time);
                        }
                        break;

                        default:
                        {
                            /* Do nothing */
                        }
                    }
                }
            }
        }

        // Return flag
        return doneRestore;
    }

}
