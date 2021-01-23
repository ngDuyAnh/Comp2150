
/*
Duy Anh Nguyen 7892957
January 22, 2021
Document.java
public class Document
This class will be the handle to make changes to the document
    and keep update the log that track changes to the document.
There will be two public static methods. One public static
    method is use to create new document. The other public
    static method is use to retrieve document.
All changes to the document will get record.

Private member:
documentName - The name of the document.
documentLogFileName - The document log file name.

Public static method:
addNewDocument() - Add new document.
retrieveDocument() - Retrieve document.

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
history() - Return a string that contain all the information
    about the recorded changes to the document.
    The history will contain all replace, delete, restore, and append
    commands in the order of newest or oldest.
*/

public class Document
{

}
