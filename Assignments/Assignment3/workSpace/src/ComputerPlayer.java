import java.util.ArrayList;
import java.util.Random;

/*
Duy Anh Nguyen 7892957
March 26, 2021
ComputerPlayer.java
Computer player in the card game "whodunit?".

Private member:
index - The player index in the game.
numPlayers - Number of players in the game.
allGameWeaponCards - All the weapon cards in the game.
allGameSuspectCards - All the suspect cards in the game.
allGameLocationCards - All the location cards in the game.
weaponCardsTrack - Track the weapon cards are still unknown.
suspectCardsTrack - Track the suspect cards are still unknown.
locationCardsTrack - Track the location cards are still unknown.
cards - The cards the player is holding.

Public method:
ComputerPlayer() - Constructor to create and initialize an instance.

Public override implement method:
setUp() - Setup the player for the game.
setCard() - Give player a card.
getIndex() - Get the player index in the game.
canAnswer() - The user to answer another player suggestion.
getGuess() - Get the player guess for the next player.
receiveInfo() - The response after a suggestion guess.
*/

public class ComputerPlayer implements IPlayer
{
    // Private member
    private int index = -1;                              // The player index in the game
    private int numPlayers = -1;                         // The number of players in the game
    private ArrayList<Card> allGameWeaponCards = null;   // All the weapon cards in the game
    private ArrayList<Card> allGameSuspectCards = null;  // All the suspect cards in the game
    private ArrayList<Card> allGameLocationCards = null; // All the location cards in the game
    private ArrayList<Card> weaponCardsTrack = null;     // Track the weapon cards are still unknown
    private ArrayList<Card> suspectCardsTrack = null;    // Track the suspect cards are still unknown
    private ArrayList<Card> locationCardsTrack = null;   // Track the location cards are still unknown
    private ArrayList<Card> cards = null;                // The cards the player is holding


    // Public method

    /* ComputerPlayer()
    Constructor to create and initialize the instance.
    */
    public ComputerPlayer()
    {
        this.allGameWeaponCards = new ArrayList<Card>();
        this.allGameSuspectCards = new ArrayList<Card>();
        this.allGameLocationCards  = new ArrayList<Card>();
        this.weaponCardsTrack = new ArrayList<Card>();
        this.suspectCardsTrack = new ArrayList<Card>();
        this.locationCardsTrack = new ArrayList<Card>();
        this.cards = new ArrayList<Card>();
    }


    // Public override implement method

    /* setUp()
    Setup the player to play the game.

    Parameter:
    numPlayers - The total number of players in the game.
    index - The index of the current player in the game.
    ppl - All the suspect cards in the game.
    places - All the location cards in the game.
    weapons - All the weapon cards in the game.
    */
    @Override
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons)
    {
        this.index = index;
        this.numPlayers = numPlayers;
        this.allGameWeaponCards.addAll(weapons);
        this.allGameSuspectCards.addAll(ppl);
        this.allGameLocationCards.addAll(places);
        this.weaponCardsTrack.addAll(this.allGameWeaponCards);
        this.suspectCardsTrack.addAll(this.allGameSuspectCards);
        this.locationCardsTrack.addAll(this.allGameLocationCards);
    }

    /* setCard()
    Player got dealt a card.

    Parameter:
    c - The card dealt to player.
    */
    @Override
    public void setCard(Card c)
    {
        // Computer player get the card
        this.cards.add(c);

        // The card is known not to be the answer
        if (c.getType() == Card.CardType.WEAPON)
        {
            this.weaponCardsTrack.remove(c);
        }
        else if (c.getType() == Card.CardType.SUSPECT)
        {
            this.suspectCardsTrack.remove(c);
        }
        else if (c.getType() == Card.CardType.LOCATION)
        {
            this.locationCardsTrack.remove(c);
        }
    }

    /* getIndex()
    Return the index of the player in the game.

    Return:
    The index of the player in the game.
    */
    @Override
    public int getIndex()
    {
        return this.index;
    }

    /* canAnswer()
    THe user to answer another player suggestion.

    Parameter:
    g - The player suggestion guess.
    ip - The player given the suggestion guess.
    */
    @Override
    public Card canAnswer(Guess g, IPlayer ip)
    {
        // Local variable dictionary
        Card playerAnswer = null; // The player answer card
        ArrayList<Card> answerableCards = new ArrayList<Card>(); // All the cards that the player can use to answer

        // Get the cards that the player can use to answer
        if (this.cards.contains(g.getSuspectCard()))
        {
            answerableCards.add(g.getSuspectCard());
        }
        if (this.cards.contains(g.getLocationCard()))
        {
            answerableCards.add(g.getLocationCard());
        }
        if (this.cards.contains((g.getWeaponCard())))
        {
            answerableCards.add(g.getWeaponCard());
        }

        // Answer the other player if possible
        if (!answerableCards.isEmpty())
        {
            playerAnswer = answerableCards.get(new Random().nextInt(answerableCards.size()));
        }

        // Return the answer card
        return playerAnswer;
    }

    /* getGuess()
    Get the player guess.

    Return:
    Player guess.
    */
    @Override
    public Guess getGuess()
    {
        // Local variable dictionary
        Card guessWeapon = null, guessSuspect = null, guessLocation = null; // The player guess
        boolean isAccusation = false;                                       // The guess is an accusation

        // Get the random guess
        guessWeapon = this.weaponCardsTrack.get(new Random().nextInt(this.weaponCardsTrack.size()));
        guessSuspect = this.suspectCardsTrack.get(new Random().nextInt(this.suspectCardsTrack.size()));
        guessLocation = this.locationCardsTrack.get(new Random().nextInt(this.locationCardsTrack.size()));

        // It is an accusation
        if (this.weaponCardsTrack.size() == 1 && this.suspectCardsTrack.size() == 1 && this.locationCardsTrack.size() == 1)
        {
            isAccusation = true;
        }

        // Return the guess
        return new Guess(guessWeapon, guessSuspect, guessLocation, isAccusation);
    }

    /* receiveInfo()
    Receive information from the guess.
    */
    @Override
    public void receiveInfo(IPlayer ip, Card c)
    {
        if (ip != null && c != null)
        {
            // The card is known not to be the answer
            if (c.getType() == Card.CardType.WEAPON)
            {
                this.weaponCardsTrack.remove(c);
            }
            else if (c.getType() == Card.CardType.SUSPECT)
            {
                this.suspectCardsTrack.remove(c);
            }
            else if (c.getType() == Card.CardType.LOCATION)
            {
                this.locationCardsTrack.remove(c);
            }
        }
        else
        {
            System.out.println("No one could answer.");
        }
    }

}
