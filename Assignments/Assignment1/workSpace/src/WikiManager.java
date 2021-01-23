
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

Static method:
processCommandLine() - Take an input line, determine the
    command, and call the according method to process the
    command.
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
*/

public class WikiManager
{

}
