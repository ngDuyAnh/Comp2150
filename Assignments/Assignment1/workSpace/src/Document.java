
/*
Duy Anh Nguyen 7892957
January 22, 2021
Document.java
public class Document
This class will be the handle to make changes to the document
    and keep update the log that track changes to the document.
All changes to the document will get record.

Private member:
docName - The document name.
docContents - The document contents
docLog - The document log.

Public method:
Document() - Constructor to create a new instance of a document.
appendContents() - Append the given contents to the end of
    the document. It will return true if operation success.
replaceContents() - Replace the given line number with new contents.
    It will return true of operation success. Else, it will return
    false if fail, which is the case when the given line number does
    not exist in the document.
removeContents() - Delete the given line number in the document.
    It will return true if operation success. Else, it will return
    false if fail, which is the case when the given line number does
    not exist in the document.
strDocument() - Return a string that is the contents of the document.
    This string can be use to print the document.
    The string starts with title follow by the contents line-by-line
        in the document.
getLogList() - Return the log list.
deleteAllContents() - Delete all contents in document.
history() - Return a string that contain all the information
    about the recorded changes to the document.
    The history will contain all replace, delete, restore, and append
    commands in the order of newest or oldest.

Private method:
recordLog() - Record document change log.
*/

public class Document extends Item
{
    // Private member
    private final String docName;    // The document name
    private List docContents = null; // The document contents
    private Log docLog = null;       // The document log




    // Public method

    /* Document()
    Constructor to create a new instance of a document.

    Parameter:
    DOCUMENT_NAME - String name of the document create.
    */
    public Document(final String DOCUMENT_NAME)
    {
        this.docName = DOCUMENT_NAME;
        this.docLog = new Log();
    }


    /* appendContents()
    Append the given contents to the end of
        the document. It will return true if operation success.

    Parameter:
    CONTENTS - String contents to append.
    */
    public void appendContents(final String CONTENTS)
    {
        // String container to append to the list contents
        StringItem STRING_CONTAINER = new StringItem(CONTENTS);

        // Append to the document
        this.docContents.append(STRING_CONTAINER);
    }


    /* replaceContents()
    Replace the given line number with new contents.
    It will return true of operation success. Else, it will return
        false if fail, which is the case when the given line number does
        not exist in the document.

    Parameter:
    LINE_NUM - Line number to change the contents.
    CONTENTS - String contents to replace.

    Return:
    Flag if contents successfully edited.
    */
    public boolean replaceContents(final int LINE_NUM, final String CONTENTS)
    {
        // Local variable dictionary
        boolean replaced = false;

        // Check if the given line number is valid
        if (LINE_NUM < this.docContents.getLength() && LINE_NUM > -1)
        {
            // Pop the given line index
            final StringItem OLD_CONTENTS = (StringItem) this.docContents.removeIndex(LINE_NUM);

            // Replace the contents
            final StringItem NEW_CONTENTS = new StringItem(CONTENTS);
            this.docContents.insertIndex(LINE_NUM, NEW_CONTENTS);

            // Flag update
            replaced = true;
        }

        // Return the replaced flag
        return replaced;
    }


    /* removeContents()
    Delete the given line number in the document.
    It will return true if operation success. Else, it will return
        false if fail, which is the case when the given line number does
        not exist in the document.

    Parameter:
    LINE_NUMBER - The line to remove contents.

    Return:
    Flag if the requested line to remove is success.
    */
    public boolean removeContents(final int LINE_NUMBER)
    {
        // Local variable dictionary
        boolean removed = false;

        // Check if the given line number is valid
        if (LINE_NUMBER < this.docContents.getLength() && LINE_NUMBER > -1)
        {
            // Pop the contents at given index
            final StringItem OLD_CONTENTS = (StringItem) this.docContents.removeIndex(LINE_NUMBER);

            // Flag update
            removed = true;
        }

        // Return the replaced flag
        return removed;
    }


    /* getLogList()
    Return the log list.

    Return:
    Return the log list.
    */
    public Log getLogList()
    {
        return this.docLog;
    }


    /* deleteAllContents()
    Delete all contents in document.
    */
    public void deleteAllContents()
    {
        // Delete all contents in document
        this.docContents = new List();
    }


    /* history()
    Return a string that contain all the information
            about the recorded changes to the document.
    The history will contain all replace, delete, restore, and append
            commands in the order of newest or oldest.

    Return:
    A String that is all changes to the document.
    */
    public String history()
    {
        // Local variable dictionary
        String history = "";

        // Get the document name
        history += this.docName + "\n";

        // Get the document changes history
        history += this.docLog.printNewOldString();

        // Return the history
        return history;
    }


    /* recordLog()
    Record document change log.

    Parameter:
    LOG - The LogPackage to log.
    */
    public void recordLog(final LogPackage LOG_PACKAGE)
    {
        this.docLog.recordLog(LOG_PACKAGE);
    }


    /* printString()
    Return the document contents in String form.

    Return:
    Document contents.
    */
    @Override
    public String printString()
    {
        // Local variable dictionary
        String docContents = ""; // Document contents to return

        // Get document name
        docContents += this.docName + "\n";

        // Get document contents
        docContents += this.docContents.printString();

        // Return the document contents
        return this.docName;
    }

}
