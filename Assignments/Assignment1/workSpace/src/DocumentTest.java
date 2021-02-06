import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest
{

    @Test
    void appendContents()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String CONTENT1 = "First line.";
        final String CONTENT2 = "Second line.";
        final String CONTENT3 = "Third line.";
        final String EXPECTED =
                DOCUMENT_NAME + "\n" +
                CONTENT1 + "\n" +
                CONTENT2 + "\n" +
                CONTENT3 + "\n";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.appendContents(CONTENT1);
        testDoc.appendContents(CONTENT2);
        testDoc.appendContents(CONTENT3);

        // Assert
        assertEquals(testDoc.printString(), EXPECTED);
    }

    @Test
    void replaceContents()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String CONTENT1 = "First line.";
        final String CONTENT2 = "Second line.";
        final String CONTENT3 = "Third line.";
        final String REPLACE1 = "First replace.";
        final String REPLACE2 = "Second replace.";
        final String REPLACE3 = "Third replace.";
        final String EXPECTED =
                DOCUMENT_NAME + "\n" +
                REPLACE1 + "\n" +
                REPLACE2 + "\n" +
                REPLACE3 + "\n";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.appendContents(CONTENT1);
        testDoc.appendContents(CONTENT2);
        testDoc.appendContents(CONTENT3);
        testDoc.replaceContents(0, REPLACE1);
        testDoc.replaceContents(1, REPLACE2);
        testDoc.replaceContents(2, REPLACE3);

        // Assert
        assertEquals(testDoc.printString(), EXPECTED);
    }

    @Test
    void removeContents()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String CONTENT1 = "First line.";
        final String CONTENT2 = "Second line.";
        final String CONTENT3 = "Third line.";
        final String EXPECTED =
                DOCUMENT_NAME + "\n" +
                CONTENT1 + "\n" +
                CONTENT2 + "\n";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.appendContents(CONTENT1);
        testDoc.appendContents(CONTENT2);
        testDoc.appendContents(CONTENT3);

        // Assert
        assertEquals(testDoc.removeContents(100), false);
        assertEquals(testDoc.removeContents(-1), false);

        // Assert
        assertEquals(testDoc.removeContents(2), true);
        assertEquals(testDoc.printString(), EXPECTED);
    }

    @Test
    void getLogList()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String COMMAND = "COMMAND";
        final String ARGUMENT1 = "ARG1";
        final String ARGUMENT2 = "ARG2";
        final String EXPECTED =
                COMMAND + " " + ARGUMENT1 + "\n" +
                COMMAND + " " + ARGUMENT2 + "\n";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.recordLog(new LogPackage(0, COMMAND, ARGUMENT1));
        testDoc.recordLog(new LogPackage(1, COMMAND, ARGUMENT2));

        // Assert
        final Log LOG = testDoc.getLogList();
        assertEquals(LOG.printOldNewString(), EXPECTED);
    }

    @Test
    void deleteAllContents()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String CONTENT1 = "First line.";
        final String CONTENT2 = "Second line.";
        final String CONTENT3 = "Third line.";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.appendContents(CONTENT1);
        testDoc.appendContents(CONTENT2);
        testDoc.appendContents(CONTENT3);
        testDoc.deleteAllContents();

        // Assert
        assertEquals(testDoc.printString(), DOCUMENT_NAME + "\n");
    }

    @Test
    void history()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String COMMAND = "COMMAND";
        final String ARGUMENT1 = "ARG1";
        final String ARGUMENT2 = "ARG2";
        final String EXPECTED =
                    DOCUMENT_NAME + "\n" +
                    COMMAND + " " + ARGUMENT2 + "\n" +
                    COMMAND + " " + ARGUMENT1 + "\n";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.recordLog(new LogPackage(0, COMMAND, ARGUMENT1));
        testDoc.recordLog(new LogPackage(1, COMMAND, ARGUMENT2));

        // Assert
        assertEquals(testDoc.history(), EXPECTED);
    }

    @Test
    void recordLog()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String COMMAND = "COMMAND";
        final String ARGUMENT1 = "ARG1";
        final String ARGUMENT2 = "ARG2";
        final String EXPECTED =
                DOCUMENT_NAME + "\n" +
                        COMMAND + " " + ARGUMENT2 + "\n" +
                        COMMAND + " " + ARGUMENT1 + "\n";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.recordLog(new LogPackage(0, COMMAND, ARGUMENT1));
        testDoc.recordLog(new LogPackage(1, COMMAND, ARGUMENT2));

        // Assert
        assertEquals(testDoc.history(), DOCUMENT_NAME + "\n" + testDoc.getLogList().printNewOldString());
    }

    @Test
    void printString()
    {
        // Test data
        final String DOCUMENT_NAME = "Test document.";
        final String COMMAND = "COMMAND";
        final String ARGUMENT1 = "ARG1";
        final String ARGUMENT2 = "ARG2";
        final String EXPECTED =
                    DOCUMENT_NAME + "\n" +
                    ARGUMENT1 + "\n" +
                    ARGUMENT2 + "\n";

        // Test
        Document testDoc = new Document(DOCUMENT_NAME);
        testDoc.appendContents(ARGUMENT1);
        testDoc.appendContents(ARGUMENT2);

        // Assert
        assertEquals(testDoc.history(), DOCUMENT_NAME + "\n" + testDoc.getLogList().printNewOldString());
    }

}
