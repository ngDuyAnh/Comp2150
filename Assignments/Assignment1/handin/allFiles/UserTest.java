import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{

    @Test
    void userReport()
    {
        // Test data
        final String USERNAME = "TestUser";
        final int TIME1 = 0;
        final int TIME2 = 1;
        final int TIME3 = 2;
        final String COMMAND1 = "COMMAND1";
        final String COMMAND2 = "COMMAND2";
        final String COMMAND3 = "COMMAND3";
        final String ARGUMENTS1 = "ARG1";
        final String ARGUMENTS2 = "ARG2";
        final String ARGUMENTS3 = "ARG3";
        final String EXPECTED =
                USERNAME + "\n" +
                        COMMAND1 + " " + ARGUMENTS1 + "\n" +
                        COMMAND2 + " " + ARGUMENTS2 + "\n" +
                        COMMAND3 + " " + ARGUMENTS3 + "\n";

        // Test
        final User USER1 = new User(USERNAME);
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        USER1.recordLog(LOG_PACKAGE1);
        USER1.recordLog(LOG_PACKAGE2);
        USER1.recordLog(LOG_PACKAGE3);

        // Assert
        assertEquals(USER1.userReport(), EXPECTED);
    }

    @Test
    void recordLog()
    {
        // Test data
        final String USERNAME = "TestUser";
        final int TIME1 = 0;
        final int TIME2 = 1;
        final int TIME3 = 2;
        final String COMMAND1 = "COMMAND1";
        final String COMMAND2 = "COMMAND2";
        final String COMMAND3 = "COMMAND3";
        final String ARGUMENTS1 = "ARG1";
        final String ARGUMENTS2 = "ARG2";
        final String ARGUMENTS3 = "ARG3";

        // Test
        final User USER1 = new User(USERNAME);
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);

        // Assert
        USER1.recordLog(LOG_PACKAGE1);
        final String EXPECTED1 =
                USERNAME + "\n" +
                COMMAND1 + " " + ARGUMENTS1 + "\n";
        assertEquals(USER1.userReport(), EXPECTED1);

        // Assert
        USER1.recordLog(LOG_PACKAGE2);
        final String EXPECTED2 =
                USERNAME + "\n" +
                COMMAND1 + " " + ARGUMENTS1 + "\n" +
                COMMAND2 + " " + ARGUMENTS2 + "\n";
        assertEquals(USER1.userReport(), EXPECTED2);

        // Assert
        USER1.recordLog(LOG_PACKAGE3);
        final String EXPECTED3 =
                USERNAME + "\n" +
                COMMAND1 + " " + ARGUMENTS1 + "\n" +
                COMMAND2 + " " + ARGUMENTS2 + "\n" +
                COMMAND3 + " " + ARGUMENTS3 + "\n";
        assertEquals(USER1.userReport(), EXPECTED3);
    }

    @Test
    void printString()
    {
        // Test data
        final String USERNAME = "TestUser";
        final int TIME1 = 0;
        final int TIME2 = 1;
        final int TIME3 = 2;
        final String COMMAND1 = "COMMAND1";
        final String COMMAND2 = "COMMAND2";
        final String COMMAND3 = "COMMAND3";
        final String ARGUMENTS1 = "ARG1";
        final String ARGUMENTS2 = "ARG2";
        final String ARGUMENTS3 = "ARG3";
        final String EXPECTED =
                USERNAME + "\n" +
                COMMAND1 + " " + ARGUMENTS1 + "\n" +
                COMMAND2 + " " + ARGUMENTS2 + "\n" +
                COMMAND3 + " " + ARGUMENTS3 + "\n";

        // Test
        final User USER1 = new User(USERNAME);
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        USER1.recordLog(LOG_PACKAGE1);
        USER1.recordLog(LOG_PACKAGE2);
        USER1.recordLog(LOG_PACKAGE3);

        // Assert
        assertEquals(USER1.printString(), USERNAME);
    }

}
