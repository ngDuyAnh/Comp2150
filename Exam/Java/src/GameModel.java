import java.util.ArrayList;
import java.util.Random;

public class GameModel implements Model
{
    // Private member
    private int randomNumber = -1; // The random number the player has to guess
    private boolean winnerFound = false;
    private ArrayList<Player> players = null;

    // Public method

    public GameModel()
    {
        this.winnerFound = false;
        this.players = null;
    }

    @Override
    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    @Override
    public void playGame()
    {
        // Local variable dictionary
        Random random = new Random(); // Generate random number

        // Generate the random number for the players to guess
        this.randomNumber = random.nextInt(101);

        // Set the model for the player
        for (int counter = 0; counter < this.players.size(); counter++)
        {
            this.players.get(counter).setModel(this);
        }

        // Players play the game
        int counter = 0;
        while (!this.winnerFound)
        {
            // Player turn
            Player playerTurn = this.players.get(counter % this.players.size());
            playerTurn.turnStart();

            // Next player guess
            counter++;
        }
    }

    @Override
    public int setGuess(int playerGuess)
    {
        // Local variable dictionary
        int guessResult = 0;

        // Check the player guess
        if (playerGuess < this.randomNumber)
        {
            guessResult = -1;
        }
        else if (playerGuess > this.randomNumber)
        {
            guessResult = 1;
        }
        else
        {
            guessResult = 0;
            this.winnerFound = true;
        }

        // Return the guess result
        return guessResult;
    }

}
