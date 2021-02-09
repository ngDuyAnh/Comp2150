import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentManagerTest
{

    @Test
    void createDocument()
    {
        // Test data
        final String USERNAME = "User";
        final String VALID_DOC_NAME = "Valid_Doc";
        final String INVALID_DOC_NAME = "NotValid_D@c";
        final String NUM_ARGUMENTS_INVALID = "one two three.";
        final String COMMAND = "CREATE";

        // Test
        final UserManager userManager = new UserManager();
        userManager.createUser(new LogPackage(0, "CREATEUSER", USERNAME));
        final DocumentManager docManager = new DocumentManager(userManager);
        assertEquals(docManager.createDocument(new LogPackage(0, COMMAND, VALID_DOC_NAME + " " + "Nobody")),
                "Create document. User does not exist.");
        assertEquals(docManager.createDocument(new LogPackage(0, COMMAND, VALID_DOC_NAME + " " + USERNAME)),
                "Confirm. " + VALID_DOC_NAME + " created.");
        assertEquals(docManager.createDocument(new LogPackage(0, COMMAND, VALID_DOC_NAME + " " + USERNAME)),
                "Create document. Document already exist.");
        assertEquals(docManager.createDocument(new LogPackage(0, COMMAND, INVALID_DOC_NAME + " " + USERNAME)),
                "The document name does not meet the requirements.");
        assertEquals(docManager.createDocument(new LogPackage(0, COMMAND, NUM_ARGUMENTS_INVALID + " " + USERNAME + " Extra")),
                "Create document. Too few or too many arguments.");
    }

    @Test
    void appendContents()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final LogPackage MAKE_DOC = new LogPackage(0, "CREATE", DOC_NAME + " " + USER_NAME);
        final LogPackage TOO_FEW_ARGUMENTS = new LogPackage(1, "COMMAND", "one two");
        final LogPackage DOC_DOES_NOT_EXIST = new LogPackage(2, "APPEND","NoDocument " + USER_NAME + " Some contents.");
        final LogPackage USER_DOES_NOT_EXIST = new LogPackage(3, "APPEND", DOC_NAME + " DoesNotExist" + " " + "Some contents.");
        final LogPackage APPEND_SUCCESS = new LogPackage(4, "APPEND", DOC_NAME + " " + USER_NAME + " Some document.");

        // Test
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Assert
        assertEquals(documentManager.appendContents(TOO_FEW_ARGUMENTS), "Append document. Too few or too many arguments.");
        assertEquals(documentManager.appendContents(DOC_DOES_NOT_EXIST), "Append contents. Document does not exist.");
        assertEquals(documentManager.appendContents(USER_DOES_NOT_EXIST), "Append contents. User does not exist.");
        assertEquals(documentManager.appendContents(APPEND_SUCCESS), "Append contents. Successfully append contents.");
    }

    @Test
    void replaceContents()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final String REPLACE_STRING = "Replace string.";
        final LogPackage MAKE_DOC = new LogPackage(0, "CREATE", DOC_NAME + " " + USER_NAME);
        final LogPackage TOO_FEW_ARGUMENTS = new LogPackage(1, "COMMAND", "one two");
        final LogPackage DOC_DOES_NOT_EXIST = new LogPackage(2, "APPEND","NoDocument " + USER_NAME + " Some contents.");
        final LogPackage USER_DOES_NOT_EXIST = new LogPackage(3, "APPEND", DOC_NAME + " DoesNotExist" + " " + "Some contents.");
        final LogPackage LINE_NUMBER_INVALID1 = new LogPackage(4, "COMMAND", DOC_NAME + " " + USER_NAME + " " + 100 + " This is the replace.");
        final LogPackage LINE_NUMBER_INVALID2 = new LogPackage(4, "COMMAND", DOC_NAME + " " + USER_NAME + " " + "-a" + " This is the replace.");
        final LogPackage LINE_NUMBER_INVALID3 = new LogPackage(4, "COMMAND", DOC_NAME + " " + USER_NAME + " " + -1 + " This is the replace.");
        final LogPackage REPLACE_SUCCESS = new LogPackage(5, "COMMAND", DOC_NAME + " " + USER_NAME + " " + 0 + " " + REPLACE_STRING);

        // Test
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Append contents
        final String APPEND_STRING1 = "First line.";
        final String APPEND_STRING2 = "Second line.";
        final LogPackage APPEND_SUCCESS1 = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING1);
        final LogPackage APPEND_SUCCESS2 = new LogPackage(7, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING2);
        assertEquals(documentManager.appendContents(APPEND_SUCCESS1), "Append contents. Successfully append contents.");
        assertEquals(documentManager.appendContents(APPEND_SUCCESS2), "Append contents. Successfully append contents.");

        // Assert
        assertEquals(documentManager.replaceContents(TOO_FEW_ARGUMENTS), "Replace contents. Too few or too many arguments.");
        assertEquals(documentManager.replaceContents(DOC_DOES_NOT_EXIST), "Replace contents. Document does not exist.");
        assertEquals(documentManager.replaceContents(USER_DOES_NOT_EXIST), "Replace contents. User does not exist.");
        assertEquals(documentManager.replaceContents(LINE_NUMBER_INVALID1), "Replace contents. Failed contents replace.");
        assertEquals(documentManager.replaceContents(LINE_NUMBER_INVALID2), "Replace contents. Line number is invalid.");
        assertEquals(documentManager.replaceContents(LINE_NUMBER_INVALID3), "Replace contents. Line number is invalid.");
        assertEquals(documentManager.replaceContents(REPLACE_SUCCESS), "Replace contents. Successful contents replace.");

        // Assert check contents
        final Document DOCUMENT = documentManager.findDocumentInstance(DOC_NAME);
        final String EXPECTED =
                DOC_NAME + "\n" +
                "[0] " + REPLACE_STRING + "\n" +
                "[1] " + APPEND_STRING2 + "\n";
        assertEquals(DOCUMENT.printString(), EXPECTED);
    }

    @Test
    void deleteContents()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final LogPackage MAKE_DOC = new LogPackage(0, "CREATE", DOC_NAME + " " + USER_NAME);

        // Test
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Append document
        final String APPEND_STRING1 = "First line.";
        final String APPEND_STRING2 = "Second line.";
        final LogPackage APPEND_SUCCESS1 = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING1);
        final LogPackage APPEND_SUCCESS2 = new LogPackage(7, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING2);
        assertEquals(documentManager.appendContents(APPEND_SUCCESS1), "Append contents. Successfully append contents.");
        assertEquals(documentManager.appendContents(APPEND_SUCCESS2), "Append contents. Successfully append contents.");

        // Assert
        final Document DOCUMENT = documentManager.findDocumentInstance(DOC_NAME);
        final String EXPECTED =
                DOC_NAME + "\n" +
                "[0] " + APPEND_STRING1 + "\n" +
                "[1] " + APPEND_STRING2 + "\n";
        assertEquals(DOCUMENT.printString(), EXPECTED);

        // Delete contents
        final LogPackage DELETE_PACKAGE = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + 0);
        assertEquals(documentManager.deleteContents(DELETE_PACKAGE), "Delete contents. Able contents remove.");
        final String EXPECTED_DELETE =
                DOC_NAME + "\n" +
                "[0] " + APPEND_STRING2 + "\n";
        assertEquals(DOCUMENT.printString(), EXPECTED_DELETE);

        // Too few arguments
        final LogPackage TOO_FEW_ARGUMENTS = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " ");
        assertEquals(documentManager.deleteContents(TOO_FEW_ARGUMENTS), "Delete document. Too few or too many arguments.");

        // Document does not exist
        final LogPackage DOC_DOES_NOT_EXIST = new LogPackage(6, "APPEND", "Something" + " " + USER_NAME + " " + 0);
        assertEquals(documentManager.deleteContents(DOC_DOES_NOT_EXIST), "Delete contents. Document does not exist.");

        // User does not exist
        final LogPackage USER_DOES_NOT_EXIST = new LogPackage(6, "APPEND", DOC_NAME + " " + "Something" + " " + 0);
        assertEquals(documentManager.deleteContents(USER_DOES_NOT_EXIST), "Delete contents. User does not exist.");

        // Given line number is less than 0
        final LogPackage LINE_NUM_BELOW_ZERO = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + -1);
        assertEquals(documentManager.deleteContents(LINE_NUM_BELOW_ZERO), "Delete contents. Line number is invalid.");

        // Given line number more than number of lines in document
        final LogPackage LINE_NUM_TOO_MUCH = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + 100);
        assertEquals(documentManager.deleteContents(LINE_NUM_TOO_MUCH), "Delete contents. Failed contents remove.");
    }

    @Test
    void restoreDocument()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final LogPackage MAKE_DOC = new LogPackage(1, "CREATE", DOC_NAME + " " + USER_NAME);

        // Create document and user
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Get the document handle
        final Document DOCUMENT = documentManager.findDocumentInstance(DOC_NAME);

        // Empty document
        final String EMPTY_DOC_STRING = DOC_NAME + "\n";
        assertEquals(DOCUMENT.printString(), EMPTY_DOC_STRING);

        // Append document
        final String APPEND_STRING1 = "First line.";
        final String APPEND_STRING2 = "Second line.";
        final LogPackage APPEND_SUCCESS1 = new LogPackage(2, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING1);
        final LogPackage APPEND_SUCCESS2 = new LogPackage(3, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING2);
        assertEquals(documentManager.appendContents(APPEND_SUCCESS1), "Append contents. Successfully append contents.");
        assertEquals(documentManager.appendContents(APPEND_SUCCESS2), "Append contents. Successfully append contents.");

        // Assert
        final String APPENDED_DOC_STRING =
                DOC_NAME + "\n" +
                "[0] " + APPEND_STRING1 + "\n" +
                "[1] " + APPEND_STRING2 + "\n";
        assertEquals(DOCUMENT.printString(), APPENDED_DOC_STRING);

        // Replace
        final String REPLACE_STRING = "Replace string";
        final LogPackage REPLACE_SUCCESS = new LogPackage(5, "REPLACE", DOC_NAME + " " + USER_NAME + " " + 1 + " " + REPLACE_STRING);
        assertEquals(documentManager.replaceContents(REPLACE_SUCCESS), "Replace contents. Successful contents replace.");

        // Assert
        final String REPLACED_DOC_STRING =
                DOC_NAME + "\n" +
                "[0] " + APPEND_STRING1 + "\n" +
                "[1] " + REPLACE_STRING + "\n";
        assertEquals(DOCUMENT.printString(), REPLACED_DOC_STRING);

        // Remove the second line
        final LogPackage REMOVE_STRING = new LogPackage(6, "DELETE", DOC_NAME + " " + USER_NAME + " " + 0);
        assertEquals(documentManager.deleteContents(REMOVE_STRING), "Delete contents. Able contents remove.");
        final String REMOVED_DOC_STRING =
                DOC_NAME + "\n" +
                "[0] " + REPLACE_STRING + "\n";
        assertEquals(DOCUMENT.printString(), REMOVED_DOC_STRING);

        // Restore document, too few arguments
        final LogPackage RESTORE_TOO_FEW_ARGUMENTS = new LogPackage(7, "RESTORE", USER_NAME + " " + DOC_NAME + " ");
        assertEquals(documentManager.restoreDocument(RESTORE_TOO_FEW_ARGUMENTS), "Restore document. Too few or too many arguments.");

        // Document does not exist
        final LogPackage RESTORE_DOC_NOT_EXIST = new LogPackage(8, "RESTORE", USER_NAME + " " + "Something" + " " + MAKE_DOC.getTime());
        assertEquals(documentManager.restoreDocument(RESTORE_DOC_NOT_EXIST), "Restore contents. Document does not exist.");

        // User does not exist
        final LogPackage RESTORE_USER_NOT_EXIST = new LogPackage(9, "RESTORE", "Something" + " " + DOC_NAME + " " + MAKE_DOC.getTime());
        assertEquals(documentManager.restoreDocument(RESTORE_USER_NOT_EXIST), "Restore contents. User does not exist.");

        // Invalid time to restore
        final LogPackage RESTORE_INVALID_TIME = new LogPackage(10, "RESTORE", USER_NAME + " " + DOC_NAME + " " + -1);
        assertEquals(documentManager.restoreDocument(RESTORE_INVALID_TIME), "Restore contents. Line number is invalid.");

        // Before the document was created
        final LogPackage RESTORE_BEFORE_DOC_CREATED = new LogPackage(11, "RESTORE", USER_NAME + " " + DOC_NAME + " " + 0);
        assertEquals(documentManager.restoreDocument(RESTORE_BEFORE_DOC_CREATED), "Restore contents. Failed to restore contents.");

        // Restore back to when the document created
        final LogPackage RESTORE_BEGIN_DOC = new LogPackage(12, "RESTORE", USER_NAME + " " + DOC_NAME + " " + MAKE_DOC.getTime());
        assertEquals(documentManager.restoreDocument(RESTORE_BEGIN_DOC), "Restore contents. Successfully restore contents.");
        assertEquals(DOCUMENT.printString(), EMPTY_DOC_STRING);

        // Restore back to when the document appended two lines
        final LogPackage RESTORE_APPEND2 = new LogPackage(13, "RESTORE", USER_NAME + " " + DOC_NAME + " " + APPEND_SUCCESS2.getTime());
        assertEquals(documentManager.restoreDocument(RESTORE_APPEND2), "Restore contents. Successfully restore contents.");
        assertEquals(DOCUMENT.printString(), APPENDED_DOC_STRING);

        // Restore back to when the document replace a line
        final LogPackage RESTORE_REPLACE = new LogPackage(14, "RESTORE", USER_NAME + " " + DOC_NAME + " " + REPLACE_SUCCESS.getTime());
        assertEquals(documentManager.restoreDocument(RESTORE_REPLACE), "Restore contents. Successfully restore contents.");
        assertEquals(DOCUMENT.printString(), REPLACED_DOC_STRING);

        // Restore back to when the document remove a line
        final LogPackage RESTORE_REMOVE = new LogPackage(15, "RESTORE", USER_NAME + " " + DOC_NAME + " " + REMOVE_STRING.getTime());
        assertEquals(documentManager.restoreDocument(RESTORE_REMOVE), "Restore contents. Successfully restore contents.");
        assertEquals(DOCUMENT.printString(), REMOVED_DOC_STRING);

        // Restore to a restore point
        final LogPackage RESTORE_TO_RESTORE = new LogPackage(15, "RESTORE", USER_NAME + " " + DOC_NAME + " " + RESTORE_BEGIN_DOC.getTime());
        assertEquals(documentManager.restoreDocument(RESTORE_TO_RESTORE), "Restore contents. Successfully restore contents.");
        assertEquals(DOCUMENT.printString(), EMPTY_DOC_STRING);
    }

    @Test
    void documentHistory()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final LogPackage MAKE_DOC = new LogPackage(0, "CREATE", DOC_NAME + " " + USER_NAME);

        // Test
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Append document
        final String APPEND_STRING1 = "First line.";
        final String APPEND_STRING2 = "Second line.";
        final LogPackage APPEND_SUCCESS1 = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING1);
        final LogPackage APPEND_SUCCESS2 = new LogPackage(7, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING2);
        assertEquals(documentManager.appendContents(APPEND_SUCCESS1), "Append contents. Successfully append contents.");
        assertEquals(documentManager.appendContents(APPEND_SUCCESS2), "Append contents. Successfully append contents.");

        // Assert
        final LogPackage LOG_PACKAGE = new LogPackage(0, "COMMAND", DOC_NAME);
        final String EXPECTED =
                DOC_NAME + "\n" +
                APPEND_SUCCESS2.printString() + "\n" +
                APPEND_SUCCESS1.printString() + "\n" +
                MAKE_DOC.printString() + "\n";
        assertEquals(documentManager.documentHistory(LOG_PACKAGE), EXPECTED);

        // Too few arguments
        final LogPackage TOO_FEW_ARGUMENTS = new LogPackage(0, "COMMAND", "");
        assertEquals(documentManager.documentHistory(TOO_FEW_ARGUMENTS), "History document. Too few or too many arguments.");

        // Document not found
        final LogPackage DOC_NOT_FOUND = new LogPackage(0, "COMMAND", "Something");
        assertEquals(documentManager.documentHistory(DOC_NOT_FOUND), "History document. Document not found.");
    }

    @Test
    void documentString()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final LogPackage MAKE_DOC = new LogPackage(0, "CREATE", DOC_NAME + " " + USER_NAME);

        // Test
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Append document
        final String APPEND_STRING1 = "First line.";
        final String APPEND_STRING2 = "Second line.";
        final LogPackage APPEND_SUCCESS1 = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING1);
        final LogPackage APPEND_SUCCESS2 = new LogPackage(7, "APPEND", DOC_NAME + " " + USER_NAME + " " + APPEND_STRING2);
        assertEquals(documentManager.appendContents(APPEND_SUCCESS1), "Append contents. Successfully append contents.");
        assertEquals(documentManager.appendContents(APPEND_SUCCESS2), "Append contents. Successfully append contents.");

        // Assert
        final LogPackage GET_DOCUMENT = new LogPackage(0, "COMMAND", DOC_NAME);
        final String EXPECTED =
                DOC_NAME + "\n" +
                "[0] " + APPEND_STRING1 + "\n" +
                "[1] " + APPEND_STRING2 + "\n";
        assertEquals(documentManager.documentString(GET_DOCUMENT), EXPECTED);

        // Too many arguments
        final LogPackage TOO_MANY_ARGUMENTS = new LogPackage(0, "COMMAND", DOC_NAME + " " + "Something");
        assertEquals(documentManager.documentString(TOO_MANY_ARGUMENTS), "Document contents. Too few or too many arguments.");

        // Document not found
        final LogPackage DOC_NOT_FOUND = new LogPackage(0, "COMMAND", "Something");
        assertEquals(documentManager.documentString(DOC_NOT_FOUND), "Document contents. Document not found.");
    }

    @Test
    void findDocument()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final LogPackage MAKE_DOC = new LogPackage(0, "CREATE", DOC_NAME + " " + USER_NAME);

        // Test
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Assert
        assertEquals(documentManager.findDocument(DOC_NAME), true);
        assertEquals(documentManager.findDocument("Something"), false);
    }

    @Test
    void findDocumentInstance()
    {
        // Test data
        final String DOC_NAME = "TestDocument";
        final String USER_NAME = "User";
        final LogPackage MAKE_DOC = new LogPackage(0, "CREATE", DOC_NAME + " " + USER_NAME);

        // Test
        final UserManager userManager = new UserManager();
        final DocumentManager documentManager = new DocumentManager(userManager);
        assertEquals(userManager.createUser(new LogPackage(0, "CREATEUSER", USER_NAME)), "Confirm. " + USER_NAME + " registered.");
        assertEquals(documentManager.createDocument(MAKE_DOC), "Confirm. " + DOC_NAME + " created.");

        // Assert
        assertEquals((documentManager.findDocumentInstance(DOC_NAME) != null), true);
        assertEquals(documentManager.findDocumentInstance("Something"), null);
    }

}
