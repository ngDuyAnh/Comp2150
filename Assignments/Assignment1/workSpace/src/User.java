
/*
Duy Anh Nguyen 7892957
January 22, 2021
User.java
public class User
This class will represent a register user.
There will be two public static methods. One public static
    method is use to register new user. The other public
    static method is use to retrieve registered user.
All activities perform, request, by the user must be log
    and keep track.

Private member:
userName - Unique user name.
userLog - The file name to the user activities log.

Public method:
User() - Constructor create a new instance of user.
recordLog() - Log user's activity.
userReport() - Return a string that is the user's information and
    activities. The order of the activities will be from oldest
    to newest.
*/

public class User extends Item
{
    // Private member
    private final String userName; // The user name
    private Log userLog = null;    // Log of user activities




    // Public method

    /* User()
    Constructor create a new instance of user.
    */
    public User(final String USERNAME)
    {
        this.userName = USERNAME;
        this.userLog = new Log();
    }


    /* recordLog()
    Log the user's activity.

    Parameter:
    LOG - The LogPackage to log.
    */
    public void recordLog(final LogPackage LOG)
    {
        this.userLog.recordLog(LOG);
    }


    /* userReport()
    Return a String that is the user's information and activities.
    Format...
        User name
        { activities from oldest to newest }

    Return:
    A String that is all the user report information.
    */
    public String userReport()
    {
        // Local variable dictionary
        String report = "";

        // Get the user name
        report += this.userName + "\n";

        // Get the user activities
        for (int counter = 0; counter < this.userLog.getLength(); counter++)
        {
            // Get the log package
            final int TIME = this.userLog.readLogTime(counter);
            final String COMMAND = this.userLog.readLogCommand(counter);

            // Add to the report
            report += TIME + " " + COMMAND + "\n";
        }

        // Return the user's report
        return report;
    }


    /* print()
    Print the user report.
    */
    @Override
    public void print()
    {
        System.out.println(this.userReport());
    }

}
