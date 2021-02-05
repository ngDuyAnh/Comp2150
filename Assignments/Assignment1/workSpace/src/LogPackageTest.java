import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogPackageTest {

    @Test
    void getCommand()
    {
        // Test data
        final int TIME = 10;
        final String COMMAND = "TEST_COMMAND";
        final String ARGUMENTS = "TEST_ARGUMENTS";

        // Test
        final LogPackage LOG_PACKAGE = new LogPackage(TIME, COMMAND, ARGUMENTS);

        // Assert
        assertEquals(LOG_PACKAGE.getCommand(), COMMAND);
    }

    @Test
    void getArguments()
    {
        // Test data
        final int TIME = 10;
        final String COMMAND = "TEST_COMMAND";
        final String ARGUMENTS = "TEST_ARGUMENTS";

        // Test
        final LogPackage LOG_PACKAGE = new LogPackage(TIME, COMMAND, ARGUMENTS);

        // Assert
        assertEquals(LOG_PACKAGE.getArguments(), ARGUMENTS);
    }

    @Test
    void getTime()
    {
        // Test data
        final int TIME = 10;
        final String COMMAND = "TEST_COMMAND";
        final String ARGUMENTS = "TEST_ARGUMENTS";

        // Test
        final LogPackage LOG_PACKAGE = new LogPackage(TIME, COMMAND, ARGUMENTS);

        // Assert
        assertEquals(LOG_PACKAGE.getTime(), TIME);
    }

    @Test
    void printString()
    {
        // Test data
        final int TIME = 10;
        final String COMMAND = "TEST_COMMAND";
        final String ARGUMENTS = "TEST_ARGUMENTS";
        final String EXPECTED = COMMAND + " " + ARGUMENTS;

        // Test
        final LogPackage LOG_PACKAGE = new LogPackage(TIME, COMMAND, ARGUMENTS);

        // Assert
        assertEquals(LOG_PACKAGE.printString(), EXPECTED);
    }

}
