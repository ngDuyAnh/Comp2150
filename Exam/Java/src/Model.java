import java.util.ArrayList;

public interface Model
{
    public void setPlayers(ArrayList<Player> players);
    public void playGame();
    public int setGuess(int playerGuess);
}
