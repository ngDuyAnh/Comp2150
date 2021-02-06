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
                REPLACE_STRING + "\n" +
                APPEND_STRING2 + "\n";
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
                APPEND_STRING1 + "\n" +
                APPEND_STRING2 + "\n";
        assertEquals(DOCUMENT.printString(), EXPECTED);

        // Delete contents
        final LogPackage DELETE_PACKAGE = new LogPackage(6, "APPEND", DOC_NAME + " " + USER_NAME + " " + 0);
        documentManager.deleteContents(DELETE_PACKAGE);
        final String EXPECTED_DELETE =
                DOC_NAME + "\n" +
                APPEND_STRING2 + "\n";
        assertEquals(DOCUMENT.printString(), EXPECTED_DELETE);
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
                APPEND_STRING1 + "\n" +
                APPEND_STRING2 + "\n";
        assertEquals(documentManager.documentString(GET_DOCUMENT), EXPECTED);
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
