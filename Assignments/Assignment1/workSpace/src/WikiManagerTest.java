import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WikiManagerTest
{

    @Test
    void processCommandLine()
    {
        // Local variable dictionary
        final WikiManager wikiManager = new WikiManager();

        // Create a new user
        final String USER_EXIST = "UserExist";
        assertEquals(wikiManager.processCommandLine("USER " + USER_EXIST), "Confirm. " + USER_EXIST + " registered.");

        // Create user with username does not meet the requirements
        assertEquals(wikiManager.processCommandLine("USER " + "$*&*name"), "The username does not meet the requirements.");

        // Create a user that already existed
        assertEquals(wikiManager.processCommandLine("USER " + USER_EXIST), "Duplicated. User already exist.");

        // Too few arguments
        assertEquals(wikiManager.processCommandLine("USER " + ""), "Create user. Too few or too many arguments.");

        // Too many arguments
        assertEquals(wikiManager.processCommandLine("USER " + USER_EXIST + " Something"), "Create user. Too few or too many arguments.");


        // Empty user report
        final String USER_REPORT_EMPTY =
                USER_EXIST + "\n" +
                "USER " + USER_EXIST + "\n";
        assertEquals(wikiManager.processCommandLine("USERREPORT " + USER_EXIST), USER_REPORT_EMPTY);

        // User report on non-exist user
        assertEquals(wikiManager.processCommandLine("USERREPORT " + "Something"), "User not found.");

        // User report too few arguments
        assertEquals(wikiManager.processCommandLine("USERREPORT " + ""), "User report. Too few or too many arguments.");

        // User report too many arguments
        assertEquals(wikiManager.processCommandLine("USERREPORT " + USER_EXIST + " something"), "User report. Too few or too many arguments.");


        // Create document, user does not exist
        assertEquals(wikiManager.processCommandLine("CREATE " + "Something" + " " + "Something"), "Create document. User does not exist.");

        // Create document
        final String DOC_EXIST = "TestDocument";
        assertEquals(wikiManager.processCommandLine("CREATE " + DOC_EXIST + " " + USER_EXIST), "Confirm. " + DOC_EXIST + " created.");

        // Create document, document already exist
        assertEquals(wikiManager.processCommandLine("CREATE " + DOC_EXIST + " " + USER_EXIST), "Create document. Document already exist.");

        // Create document, document name does not meet requirements
        assertEquals(wikiManager.processCommandLine("CREATE " + "!doc&@" + " " + USER_EXIST), "The document name does not meet the requirements.");

        // Too few arguments
        assertEquals(wikiManager.processCommandLine("CREATE " + DOC_EXIST), "Create document. Too few or too many arguments.");

        // Too many arguments
        assertEquals(wikiManager.processCommandLine("CREATE " + "Something" + " " + USER_EXIST + " extra"), "Create document. Too few or too many arguments.");


        // Append contents to document
        final String APPEND_CONTENTS_1 = "Line1.";
        assertEquals(wikiManager.processCommandLine("APPEND " + DOC_EXIST + " " + USER_EXIST + " " + APPEND_CONTENTS_1), "Append contents. Successfully append contents.");

        // Append document does not exist
        assertEquals(wikiManager.processCommandLine("APPEND " + "Something" + " " + USER_EXIST + " " + APPEND_CONTENTS_1), "Append contents. Document does not exist.");

        // Append document does not exist
        assertEquals(wikiManager.processCommandLine("APPEND " + DOC_EXIST + " " + "Something" + " " + APPEND_CONTENTS_1), "Append contents. User does not exist.");

        // Append but too few arguments
        assertEquals(wikiManager.processCommandLine("APPEND " + DOC_EXIST + " " + USER_EXIST + " "), "Append document. Too few or too many arguments.");

        // Append contents to document
        final String APPEND_CONTENTS_2 = "Line2.";
        assertEquals(wikiManager.processCommandLine("APPEND " + DOC_EXIST + " " + USER_EXIST + " " + APPEND_CONTENTS_2), "Append contents. Successfully append contents.");

        // Append contents to document
        final String APPEND_CONTENTS_3 = "Line3.";
        assertEquals(wikiManager.processCommandLine("APPEND " + DOC_EXIST + " " + USER_EXIST + " " + APPEND_CONTENTS_3), "Append contents. Successfully append contents.");


        // Replace contents, first line
        final String REPLACE_CONTENTS_1 = "Replace1.";
        assertEquals(wikiManager.processCommandLine("REPLACE " + DOC_EXIST + " " + USER_EXIST + " " + 0 + " " + REPLACE_CONTENTS_1), "Replace contents. Successful contents replace.");

        // Replace contents, too few arguments
        assertEquals(wikiManager.processCommandLine("REPLACE " + DOC_EXIST + " " + USER_EXIST + " " + 0 + " "), "Replace contents. Too few or too many arguments.");

        // Replace contents, document does not exist
        assertEquals(wikiManager.processCommandLine("REPLACE " + "Something" + " " + USER_EXIST + " " + 0 + " " + REPLACE_CONTENTS_1), "Replace contents. Document does not exist.");

        // Replace contents, user does not exist
        assertEquals(wikiManager.processCommandLine("REPLACE " + DOC_EXIST + " " + "Something" + " " + 0 + " " + REPLACE_CONTENTS_1), "Replace contents. User does not exist.");

        // Replace contents, line number below zero
        assertEquals(wikiManager.processCommandLine("REPLACE " + DOC_EXIST + " " + USER_EXIST + " " + -1 + " " + REPLACE_CONTENTS_1), "Replace contents. Line number is invalid.");

        // Replace contents, line number above the number of line the document is contain
        assertEquals(wikiManager.processCommandLine("REPLACE " + DOC_EXIST + " " + USER_EXIST + " " + 1000 + " " + REPLACE_CONTENTS_1), "Replace contents. Failed contents replace.");


        // Delete contents. delete the last line, 2
        assertEquals(wikiManager.processCommandLine("DELETE " + DOC_EXIST + " " + USER_EXIST + " " + 2), "Delete contents. Able contents remove.");


        // Restore the contents to before the replace period
        assertEquals(wikiManager.processCommandLine("RESTORE " + USER_EXIST + " " + DOC_EXIST + " " + 21), "Restore contents. Successfully restore contents.");


        // Print the history of document
        System.out.println(wikiManager.processCommandLine("HISTORY " + DOC_EXIST));
        System.out.println(wikiManager.processCommandLine("USERREPORT " + USER_EXIST));
        System.out.println(wikiManager.processCommandLine("PRINT " + DOC_EXIST));


        // Quit
        assertEquals(wikiManager.getActiveStatus(), true);
        assertEquals(wikiManager.processCommandLine("QUIT"), "BYE.");
        assertEquals(wikiManager.getActiveStatus(), false);
    }

}
