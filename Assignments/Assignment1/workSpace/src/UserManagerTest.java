import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest
{

    @Test
    void createUser()
    {
        // Test data
        final int TIME1 = 0;
        final int TIME2 = 1;
        final int TIME3 = 2;
        final String COMMAND = "CREATEUSER";
        final String ARGUMENTS1 = "User1";
        final String ARGUMENTS2 = "User_2";
        final String ARGUMENTS3 = "C@n0t";

        // Test
        final UserManager userManager = new UserManager();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND, ARGUMENTS3);
        System.out.println(userManager.createUser(LOG_PACKAGE1));
        System.out.println(userManager.createUser(LOG_PACKAGE2));
        System.out.println(userManager.createUser(LOG_PACKAGE3));

        // Assert
        assertEquals(userManager.findUser(ARGUMENTS1), true);
        assertEquals(userManager.findUser(ARGUMENTS2), true);
        assertEquals(userManager.findUser(ARGUMENTS3), false);

        // Test create duplicate user
        assertEquals(userManager.createUser(LOG_PACKAGE1), "Duplicated. User already exist.");

        // Create user given empty username
        final LogPackage EMPTY_USERNAME = new LogPackage(TIME1, COMMAND, "");
        assertEquals(userManager.createUser(EMPTY_USERNAME), "Create user. Too few or too many arguments.");
    }

    @Test
    void userReport()
    {
        // Test data
        final int TIME1 = 0;
        final int TIME2 = 1;
        final int TIME3 = 2;
        final String COMMAND = "CREATEUSER";
        final String ARGUMENTS1 = "User1";
        final String ARGUMENTS2 = "User2";
        final String ARGUMENTS3 = "User3";

        // Test
        final UserManager userManager = new UserManager();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND, ARGUMENTS3);
        System.out.println(userManager.createUser(LOG_PACKAGE1));
        System.out.println(userManager.createUser(LOG_PACKAGE2));
        System.out.println(userManager.createUser(LOG_PACKAGE3));

        // Test data
        final String TARGET_USER = "User2";
        final int USER_TIME1 = 10;
        final int USER_TIME2 = 20;
        final int USER_TIME3 = 30;
        final String USER_COMMAND1 = "COMMAND1";
        final String USER_COMMAND2 = "COMMAND2";
        final String USER_COMMAND3 = "COMMAND3";
        final String USER_ARGUMENTS1 = "ARG1";
        final String USER_ARGUMENTS2 = "ARG2";
        final String USER_ARGUMENTS3 = "ARG3";

        // Test
        final User USER = userManager.findUserInstance(TARGET_USER);
        final LogPackage USER_LOG_PACKAGE1 = new LogPackage(USER_TIME1, USER_COMMAND1, USER_ARGUMENTS1);
        final LogPackage USER_LOG_PACKAGE2 = new LogPackage(USER_TIME2, USER_COMMAND2, USER_ARGUMENTS2);
        final LogPackage USER_LOG_PACKAGE3 = new LogPackage(USER_TIME3, USER_COMMAND3, USER_ARGUMENTS3);
        USER.recordLog(USER_LOG_PACKAGE1);
        USER.recordLog(USER_LOG_PACKAGE2);
        USER.recordLog(USER_LOG_PACKAGE3);

        // Assert
        final LogPackage GET_USER_REPORT_LOG_PACKAGE =
                new LogPackage(100, "USERREPORT", TARGET_USER);
        System.out.println(USER.userReport());
        assertEquals(userManager.userReport(GET_USER_REPORT_LOG_PACKAGE), USER.userReport());

        // User does not exist test
        final String USER_DOES_NOT_EXISTS = "DoesNotExist.";
        final LogPackage NO_USER_REPORT_PACKAGE =
                new LogPackage(101, "USERREPORT", USER_DOES_NOT_EXISTS);

        // Assert
        assertEquals(userManager.userReport(NO_USER_REPORT_PACKAGE), "User not found.");

        // The arguments give is too few
        final String EMPTY = "";
        final LogPackage EMPTY_ARGUMENTS_PACKAGE =
                new LogPackage(101, "USERREPORT", EMPTY);

        // Assert
        assertEquals(userManager.userReport(EMPTY_ARGUMENTS_PACKAGE), "User report. Too few or too many arguments.");

        // The arguments give is too few
        final String TOO_MANY_ARGUMENTS = "first second third.";
        final LogPackage TOO_MANY_ARGUMENTS_PACKAGE =
                new LogPackage(101, "USERREPORT", TOO_MANY_ARGUMENTS);

        // Assert
        assertEquals(userManager.userReport(TOO_MANY_ARGUMENTS_PACKAGE), "User report. Too few or too many arguments.");
    }

    @Test
    void findUser()
    {
        // Test data
        final int TIME1 = 0;
        final int TIME2 = 1;
        final int TIME3 = 2;
        final String COMMAND = "CREATEUSER";
        final String ARGUMENTS1 = "User1";
        final String ARGUMENTS2 = "User2";
        final String ARGUMENTS3 = "User3";

        // Test
        final UserManager userManager = new UserManager();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND, ARGUMENTS3);
        System.out.println(userManager.createUser(LOG_PACKAGE1));
        System.out.println(userManager.createUser(LOG_PACKAGE2));
        System.out.println(userManager.createUser(LOG_PACKAGE3));

        // Assert
        assertEquals(userManager.findUser(ARGUMENTS1), true);
        assertEquals(userManager.findUser(ARGUMENTS2), true);
        assertEquals(userManager.findUser(ARGUMENTS3), true);
        assertEquals(userManager.findUser("ANON"), false);
        assertEquals(userManager.findUser("CHICKEN"), false);
        assertEquals(userManager.findUser("GO NUT"), false);
    }

    @Test
    void findUserInstance()
    {
        // Test data
        final int TIME1 = 0;
        final int TIME2 = 1;
        final int TIME3 = 2;
        final String COMMAND = "CREATEUSER";
        final String ARGUMENTS1 = "User1";
        final String ARGUMENTS2 = "User2";
        final String ARGUMENTS3 = "User3";

        // Test
        final UserManager userManager = new UserManager();
        final LogPackage LOG_PACKAGE1 = new LogPackage(TIME1, COMMAND, ARGUMENTS1);
        final LogPackage LOG_PACKAGE2 = new LogPackage(TIME2, COMMAND, ARGUMENTS2);
        final LogPackage LOG_PACKAGE3 = new LogPackage(TIME3, COMMAND, ARGUMENTS3);
        System.out.println(userManager.createUser(LOG_PACKAGE1));
        System.out.println(userManager.createUser(LOG_PACKAGE2));
        System.out.println(userManager.createUser(LOG_PACKAGE3));

        // Assert
        assertEquals(userManager.findUserInstance(ARGUMENTS1).printString(), "User1");
        assertEquals(userManager.findUserInstance(ARGUMENTS2).printString(), "User2");
        assertEquals(userManager.findUserInstance(ARGUMENTS3).printString(), "User3");
        assertEquals(userManager.findUserInstance("Does not exist."), null);
    }

}
