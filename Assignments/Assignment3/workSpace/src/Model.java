import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
Duy Anh Nguyen 7892957
March 26, 2021
Model.java
This class manages the card game "whodunit?".
It will keep track of all players, cards, and turn of the game.

Private member:
winnerFound - Flag if a player won.
players - The players in the game.
targetWeapon - The answer card of weapon.
targetSuspect - The answer card of suspect.
targetLocation - The answer card of location.
weaponCards - All the weapon cards in the game.
suspectCards - All the suspect cards in the game.
locationCards - All the location cards in the game.

Public method:
Model() - Constructor to create and initialize an instance.
getNumPlayers() - Get the number of players in the game.
simulateGame() - Simulate the game.

Private method:
playerTurn() - Simulate a player turn.
setupPlayers() - Setup the players for the game.
setupCards() - Setup the cards for the game.
dealtCards() - Dealt the cards to all players.
accusation() - Check the given accusation if it is correct.
*/

public class Model
{
    // Private member
    private boolean winnerFound = false;                                                   // Flag if a player won
    private ArrayList<IPlayer> players = null;                                             // The players in the game
    private Card targetWeapon = null, targetSuspect = null, targetLocation = null;         // The answer cards
    private ArrayList<Card> weaponCards = null, suspectCards = null, locationCards = null; // The cards in the game


    // Public method

    /* Model()
    Constructor to create and initialize an instance.
    */
    public Model()
    {
        this.winnerFound = false;
        this.players = new ArrayList<IPlayer>();
        this.weaponCards = new ArrayList<Card>();
        this.suspectCards = new ArrayList<Card>();
        this.locationCards = new ArrayList<Card>();
    }

    /* getNumPlayers()
    Get the number of players in the game.

    Return:
    The number of players in the game.
    */
    public int getNumPlayers()
    {
        return this.players.size();
    }

    /* simulateGame()
    Simulate the game.

    Parameter:
    numPlayers - Number of players in the game.
    weaponCards - The weapon cards in the game.
    suspectCards - The suspect cards in the game.
    locationCards - The location cards in the game.
    */
    public void simulateGame(int numPlayers, ArrayList<Card> weaponCards, ArrayList<Card> suspectCards, ArrayList<Card> locationCards)
    {
        // Local variable dictionary
        int playerIndex = -1; // The player who turn it is

        // Setup the game
        this.setupCards(weaponCards, suspectCards, locationCards);
        this.setupPlayers(numPlayers);

        // Dealing cards
        System.out.println("Dealing cards...");
        this.dealtCards();

        // Simulate the game
        System.out.println("Playing...");
        playerIndex = 0;
        while (!this.winnerFound)
        {
            // Simulate the player turn
            boolean playerInGame = this.playerTurn(playerIndex % this.players.size());

            // Go to next player
            if (playerInGame && !this.winnerFound)
            {
                playerIndex++;
            }
        }

        // Print the winner of the game
        System.out.println("Player " + this.players.get(playerIndex % this.players.size()).getIndex() + " won the game.");
    }


    // Private method

    /* playerTurn()
    Simulate a player turn.

    Parameter:
    playerIndex - The index player turn.

    Return:
    Flag if the current player won.
    */
    private boolean playerTurn(int playerIndex)
    {
        // Local variable dictionary
        boolean playerInGame = true;                    // The player won
        boolean answered = false;                       // Player got guess answer
        IPlayer player = this.players.get(playerIndex); // The player turn
        Guess playerGuess = null;                       // The player guess
        Card answerCard = null;                         // The card the player answer

        // Get the player guess
        playerGuess = player.getGuess();

        // Determine if the player win if it is an accusation
        if (playerGuess.getAccusation())
        {
            System.out.println("Player " + playerIndex + ": Accusation: " + playerGuess.toString());

            // Check if the accusation is correct
            if (this.accusation(playerGuess.getWeaponCard(), playerGuess.getSuspectCard(), playerGuess.getLocationCard()))
            {
                this.winnerFound = true;
            }
            else
            {
                // Remove the player from the game
                this.players.remove(playerIndex);
                playerInGame = false;
            }
        }
        else
        {
            System.out.println(" Player " + playerIndex + ": Suggestion: " + playerGuess.toString());

            // Get the player answer
            for (int counter = 1; counter < this.players.size() && !answered; counter++)
            {
                // Local variable dictionary
                int answerPlayerIndex = (playerIndex + counter) % this.players.size(); // The index of the player to get answer

                // Get the answer from the player
                System.out.println("Asking player " + answerPlayerIndex + ".");
                answerCard = this.players.get(answerPlayerIndex).canAnswer(playerGuess, player);

                // Player received answer or go to the next player for answer
                if (answerCard != null)
                {
                    // Pass the answer to the player
                    player.receiveInfo(this.players.get(answerPlayerIndex), answerCard);
                    System.out.println("Player " + answerPlayerIndex + " answered.");

                    // The player guess has been answered
                    answered = true;
                }
            }

            // If no answer
            if (!answered)
            {
                player.receiveInfo(null, null);
            }
        }

        // Return flag if the current player still in game
        return playerInGame;
    }

    /* setupPlayers()
    Setup the players for the game.
    */
    private void setupPlayers(int numPlayers)
    {
        // Make the computer players
        for (int counter = 0; counter < (numPlayers - 1); counter++) // Last player is the human player
        {
            // Create computer player and setup
            ComputerPlayer comp = new ComputerPlayer();
            comp.setUp(numPlayers, counter, this.suspectCards, this.locationCards, this.weaponCards);

            // Add the computer player to the player list
            this.players.add((IPlayer)comp);
        }

        // Make human player
        HumanPlayer human = new HumanPlayer();
        human.setUp(numPlayers, (numPlayers - 1), suspectCards, locationCards, weaponCards);

        // Add the human player to the player list
        this.players.add((IPlayer)human);
    }

    /* setupCards()
    Setup the cards for the game.

    Parameter:
    weaponCards - the weapon cards in the game.
    suspectCards - The suspect cards in the game.
    locationCards - The location cards in the game.
     */
    private void setupCards(ArrayList<Card> weaponCards, ArrayList<Card> suspectCards, ArrayList<Card> locationCards)
    {
        // Copy the cards content
        this.weaponCards.addAll(weaponCards);
        this.suspectCards.addAll(suspectCards);
        this.locationCards.addAll(locationCards);

        // Randomly select the target cards
        this.targetWeapon = this.weaponCards.get(new Random().nextInt(this.weaponCards.size()));
        this.targetSuspect = this.suspectCards.get(new Random().nextInt(this.suspectCards.size()));
        this.targetLocation = this.locationCards.get(new Random().nextInt(this.locationCards.size()));
    }

    /* dealtCards()
    Dealt the cards to all players.
    Ensure that there exist players and cards to dealt.
    */
    private void dealtCards()
    {
        // Dealt the cards to all players
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.addAll(this.weaponCards);
        deck.addAll(this.suspectCards);
        deck.addAll(this.locationCards);

        // Remove the target cards from the game
        deck.remove(this.targetWeapon);
        deck.remove(this.targetSuspect);
        deck.remove(this.targetLocation);

        // Shuffle and dealing the cards
        Collections.shuffle(deck);
        for (int counter = 0; !deck.isEmpty(); counter++)
        {
            // Get the top card
            Card card = deck.get(0);
            deck.remove(0);

            // Give the card to the player
            this.players.get(counter % this.players.size()).setCard(card);
        }
    }

    /* accusation()
    Check if the given guess accusation is correct.

    Parameter:
    weapon - The weapon card accusation.
    suspect - The suspect card accusation.
    location - The location card accusation.

    Return:
    Flag if the accusation is correct.
    */
    private boolean accusation(Card weapon, Card suspect, Card location)
    {
        // Local variable dictionary
        boolean correctAccusation = false; // Flag if the accusation is correct

        // Check if the accusation is correct
        if (this.targetWeapon == weapon && this.targetSuspect == suspect && this.targetLocation == location)
        {
            correctAccusation = true;
        }

        // Return the flag if the accusation is correct
        return correctAccusation;
    }

}
