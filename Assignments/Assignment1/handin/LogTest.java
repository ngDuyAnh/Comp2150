import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @Test
    void recordLog()
    {
        // Test data
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
        final Log testLog = new Log();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        testLog.recordLog(LOG_PACKAGE1);
        testLog.recordLog(LOG_PACKAGE2);
        testLog.recordLog(LOG_PACKAGE3);

        // Assert
        assertEquals(testLog.getLength(), 3);
        assertEquals(testLog.readLogTime(0), 0);
        assertEquals(testLog.readLogCommand(1), COMMAND2);
        assertEquals(testLog.readLogArguments(2), ARGUMENTS3);
    }

    @Test
    void readLogCommand()
    {
        // Test data
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
        final Log testLog = new Log();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        testLog.recordLog(LOG_PACKAGE1);
        testLog.recordLog(LOG_PACKAGE2);
        testLog.recordLog(LOG_PACKAGE3);

        // Assert
        assertEquals(testLog.readLogCommand(0), COMMAND1);
        assertEquals(testLog.readLogCommand(1), COMMAND2);
        assertEquals(testLog.readLogCommand(2), COMMAND3);
    }

    @Test
    void readLogArguments()
    {
        // Test data
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
        final Log testLog = new Log();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        testLog.recordLog(LOG_PACKAGE1);
        testLog.recordLog(LOG_PACKAGE2);
        testLog.recordLog(LOG_PACKAGE3);

        // Assert
        assertEquals(testLog.readLogArguments(0), ARGUMENTS1);
        assertEquals(testLog.readLogArguments(1), ARGUMENTS2);
        assertEquals(testLog.readLogArguments(2), ARGUMENTS3);
    }

    @Test
    void readLogTime()
    {
        // Test data
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
        final Log testLog = new Log();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        testLog.recordLog(LOG_PACKAGE1);
        testLog.recordLog(LOG_PACKAGE2);
        testLog.recordLog(LOG_PACKAGE3);

        // Assert
        assertEquals(testLog.readLogTime(0), TIME1);
        assertEquals(testLog.readLogTime(1), TIME2);
        assertEquals(testLog.readLogTime(2), TIME3);
    }

    @Test
    void getLength()
    {
        // Test data
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
        final Log testLog = new Log();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        testLog.recordLog(LOG_PACKAGE1);
        testLog.recordLog(LOG_PACKAGE2);
        testLog.recordLog(LOG_PACKAGE3);

        // Assert
        assertEquals(testLog.getLength(), 3);
    }

    @Test
    void printOldNewString()
    {
        // Test data
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
        final Log testLog = new Log();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        testLog.recordLog(LOG_PACKAGE1);
        testLog.recordLog(LOG_PACKAGE2);
        testLog.recordLog(LOG_PACKAGE3);

        // Test data
        final String EXPECTED =
                LOG_PACKAGE1.printString() + "\n" +
                LOG_PACKAGE2.printString() + "\n" +
                LOG_PACKAGE3.printString() + "\n";

        // Assert
        assertEquals(testLog.printOldNewString(), EXPECTED);
    }

    @Test
    void printNewOldString()
    {
        // Test data
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
        final Log testLog = new Log();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND1, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND2, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND3, ARGUMENTS3);
        testLog.recordLog(LOG_PACKAGE1);
        testLog.recordLog(LOG_PACKAGE2);
        testLog.recordLog(LOG_PACKAGE3);

        // Test data
        final String EXPECTED =
                LOG_PACKAGE3.printString() + "\n" +
                LOG_PACKAGE2.printString() + "\n" +
                LOG_PACKAGE1.printString() + "\n";

        // Assert
        assertEquals(testLog.printNewOldString(), EXPECTED);
    }

}
