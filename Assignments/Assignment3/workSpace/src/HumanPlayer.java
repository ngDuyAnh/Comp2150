import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/*
Duy Anh Nguyen 7892957
March 26, 2021
HumanPlayer.java
Human player in the card game "whodunit?".

Private member:
index - The player index in the game.
numPlayers - Number of players in the game.
allGameWeaponCards - All the weapon cards in the game.
allGameSuspectCards - All the suspect cards in the game.
allGameLocationCards - All the location cards in the game.
cards - The cards the player is holding.

Public static method:
getCard() - Print the list of available option of cards
        and get the player pick input. It will return
        the card.

Public method:
HumanPlayer() - Constructor to create and initialize instance.

Public override implement method:
setUp() - Setup the player for the game.
setCard() - Give player a card.
getIndex() - Get the player index in the game.
canAnswer() - The user to answer another player suggestion.
getGuess() - Get the player guess for the next player.
receiveInfo() - The response after a suggestion guess.
*/

public class HumanPlayer implements IPlayer
{
    // Private member
    private int index = -1;                              // The player index in the game
    private int numPlayers = -1;                         // The number of players in the game
    private ArrayList<Card> allGameWeaponCards = null;   // All the weapon cards in the game
    private ArrayList<Card> allGameSuspectCards = null;  // All the suspect cards in the game
    private ArrayList<Card> allGameLocationCards = null; // All the location cards in the game
    private ArrayList<Card> cards = null;                // The cards the player is holding


    // Public static method

    /* getCard()
    Print the list of available option of cards
            and get the player pick input.
    It will return the card of the index.

    Parameter:
    cardList - The cards that player can choose.

    Return:
    The card of the index the player choose.
    */
    public static Card getCard(ArrayList<Card> cardList)
    {
        // Local variable dictionary
        int index = -1; // The index of the guess
        Card card = null;
        Scanner playerInput = new Scanner(System.in); // Get the player input

        // Print the available options to guess
        for (int counter = 0; counter < cardList.size(); counter++)
        {
            System.out.println(counter + ": " + cardList.get(counter).getValue());
        }

        // Get the player answer card
        index = playerInput.nextInt();
        while (index < 0 || index >= cardList.size())
        {
            System.out.println("Not valid. Try again.");
            index = playerInput.nextInt();
        }

        // Get the card choose
        card = cardList.get(index);

        // Close the scanner and release resource
        playerInput.close();

        // Return the card player chose
        return card;
    }


    // Public method

    /* HumanPlayer()
    Constructor to create and initialize instance.
    */
    public HumanPlayer()
    {
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
    public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
        this.index = index;
        this.numPlayers = numPlayers;
        this.allGameWeaponCards = weapons;
        this.allGameSuspectCards = ppl;
        this.allGameLocationCards = places;
    }

    /* setCard()
    Player got dealt a card.

    Parameter:
    c - The card dealt to player.
    */
    @Override
    public void setCard(Card c)
    {
        System.out.println("You received the card " + c.getValue());
        this.cards.add(c);
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

        // Answer the other player guess if possible
        System.out.println("Player " + ip.getIndex() + " asked you about " + g.toString());
        if (answerableCards.size() == 0)
        {
            System.out.println(", but you couldn't answer.");
        }
        else if (answerableCards.size() == 1)
        {
            System.out.println(", you only have one card, " + answerableCards.get(0).getValue() +
                    " showed it to them.");

            // Get the answer card
            playerAnswer = answerableCards.get(0);
        }
        else
        {
            System.out.println(". Which do you show?");
            playerAnswer = HumanPlayer.getCard(answerableCards);
        }

        // Return the player answer card
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
        Card guessWeapon = null, guessSuspect = null, guessLocation = null; // The player guess index
        boolean isAccusation = false;                                       // The guess is an accusation
        Scanner input = new Scanner(System.in);                             // Get the player input

        // Get the player guess
        System.out.println("It is your turn.");

        // Get suspect guess
        System.out.println("Which person do you want to suggest?");
        guessSuspect = HumanPlayer.getCard(this.allGameSuspectCards);

        // Get location guess
        System.out.println("Which location do you want to suggest?");
        guessLocation = HumanPlayer.getCard(this.allGameLocationCards);

        // Get weapon guess
        System.out.println("Which weapon do you want to suggest?");
        guessWeapon = HumanPlayer.getCard(this.allGameWeaponCards);

        // Get if this guess is an accusation
        String accusationInput = "";
        System.out.println("Is this an accusation (Y/[N])?");
        accusationInput = input.nextLine().trim();
        while (!accusationInput.equalsIgnoreCase("Y") &&
                !accusationInput.equalsIgnoreCase("N"))
        {
            accusationInput = input.nextLine().trim();
        }
        isAccusation = accusationInput.equalsIgnoreCase("Y");

        // Close the scanner to release resource
        input.close();

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
            System.out.println("Player " +ip.getIndex() + " refuted your suggestion by showing you " + c.getValue() + ".");
        }
        else
        {
            System.out.println("No one could refute your suggestion.");
        }
    }

}
