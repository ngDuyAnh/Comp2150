
public class ComputerPlayer implements Player
{
    // Private member
    private int playerGuess = 50;
    private int highBound = 100;
    private int lowBound = 0;
    private Model gameMode = null;

    // Public method

    public ComputerPlayer()
    {
        this.gameMode = null;
    }

    // Public implement method

    @Override
    public void setModel(Model gameModel)
    {
        this.gameMode = gameModel;
    }

    @Override
    public void turnStart()
    {
        // Give the model the guess
        int guessResult = this.gameMode.setGuess(this.playerGuess);

        // Adjust the guess bounds
        if (guessResult == -1)
        {
            this.lowBound = this.playerGuess;
        }
        else if (guessResult == 1)
        {
            this.highBound = this.playerGuess;
        }

        // Adjust the player guess
        this.playerGuess = (this.highBound + this.lowBound) / 2;
    }

}
