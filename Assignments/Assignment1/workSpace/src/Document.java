
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
deleteContents() - Delete the given line number in the document.
    It will return true if operation success. Else, it will return
    false if fail, which is the case when the given line number does
    not exist in the document.
strDocument() - Return a string that is the contents of the document.
    This string can be use to print the document.
    The string starts with title follow by the contents line-by-line
        in the document.
restoreDocument() - Restore the document to a given time stamp.
    It will return true if the operation success. Else, it will return
    false if fail, which is the case when the document does not exist
    at the time being ask to restore to.
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
    LOG_PACKAGE - The document name.
    */
    public Document(final LogPackage LOG_PACKAGE)
    {
        this.docName = (LOG_PACKAGE.getArguments().split("\\s+"))[0];
        this.docLog = new Log();

        // Log event
        this.docLog.recordLog(LOG_PACKAGE);
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



    */


/*
Public method:
appendContents() - Append the given contents to the end of
    the document. It will return true if operation success.
replaceContents() - Replace the given line number with new contents.
    It will return true of operation success. Else, it will return
    false if fail, which is the case when the given line number does
    not exist in the document.
deleteContents() - Delete the given line number in the document.
    It will return true if operation success. Else, it will return
    false if fail, which is the case when the given line number does
    not exist in the document.
strDocument() - Return a string that is the contents of the document.
    This string can be use to print the document.
    The string starts with title follow by the contents line-by-line
        in the document.
restoreDocument() - Restore the document to a given time stamp.
    It will return true if the operation success. Else, it will return
    false if fail, which is the case when the document does not exist
    at the time being ask to restore to.

recordLog() - Record document change log.
 */



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

















    /* printString()
    Print the document report.
    */
    @Override
    public String printString()
    {
        return this.docName;
    }

}
