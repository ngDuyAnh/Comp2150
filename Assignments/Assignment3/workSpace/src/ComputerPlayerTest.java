import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/*
Duy Anh Nguyen 7892957
March 31, 2021
ComputerPlayerTest.java
Test unit for class ComputerPlayer.

Private member:
numPlayers - Number of players.
players - The players in the test.
weaponCards - The weapon cards use for test.
suspectCards - The suspect cards use for test.
locationCards - THe location cards use for test.

Test method:
test1() - Player does not have any card to answer. Return should be null.
test2() - Player has exactly one card. That card should be return.
test3() - Player has more than one card, can answer should return
    one of the card.
test4() - If computer player is given all but n cards of the three section, the computer
    should only ask for the cards that it does not have information of.
test5() - Computer make guess accusation when there is only one last combination.
test6() - Computer given all but four cards from the set of cards.
test7() - Human player get call canAnswer() and it must return one of the card the player has with the guess.
*/

class ComputerPlayerTest
{
    // Private member
    private int numPlayers = -1;               // Number of players
    private ArrayList<IPlayer> players = null; // Players in the test
    private ArrayList<Card> weaponCards = null, suspectCards = null, locationCards = null; // The cards in the test


    // Setup method
    @BeforeEach
    void setup() {
        // Initialize the cards holder
        this.weaponCards = new ArrayList<Card>();
        this.suspectCards = new ArrayList<Card>();
        this.locationCards = new ArrayList<Card>();

        // Weapon cards in the test
        String[] weaponCardsName = {"Wea0", "Wea1", "Wea2"};

        // Suspect cards in the test
        String[] suspectCardsName = {"Sus0", "Sus1", "Sus2"};

        // Location cards in the test
        String[] locationCardsName = {"Loc0", "Loc1", "Loc2"};

        // Make the cards for the game
        for (int counter = 0; counter < 3; counter++)
        {
            this.weaponCards.add(new Card(Card.CardType.WEAPON, weaponCardsName[counter]));
            this.suspectCards.add(new Card(Card.CardType.SUSPECT, suspectCardsName[counter]));
            this.locationCards.add(new Card(Card.CardType.LOCATION, locationCardsName[counter]));
        }

        // There will be two players test, one computer and one human
        this.numPlayers = 2;
        this.players = new ArrayList<IPlayer>(this.numPlayers);
        this.players.add(new ComputerPlayer());
        this.players.add(new HumanPlayer());
        this.players.get(0).setUp(this.numPlayers, 0, this.suspectCards, this.locationCards, this.weaponCards);
        this.players.get(1).setUp(this.numPlayers, 1, this.suspectCards, this.locationCards, this.weaponCards);
    }


    // Test method

    /* test1()
    Player does not have any card to answer. Return should be null.
    */
    @Test
    void test1()
    {
        // Make guess of many combination
        Guess guess = new Guess(this.weaponCards.get(0), this.suspectCards.get(0), this.locationCards.get(0), false);

        // Player does not hold any cards
        assertEquals(players.get(0).canAnswer(guess, this.players.get(1)), null);
        assertEquals(players.get(1).canAnswer(guess, this.players.get(0)), null);
    }

    /* test2()
    Player has exactly one card, that card should be return.
    */
    @Test
    void test2()
    {
        // Expect card
        Card expectCard = this.weaponCards.get(0);

        // Give the player the expected card
        this.players.get(0).setCard(expectCard);
        this.players.get(1).setCard(expectCard);

        // Make guess
        Guess guess = new Guess(expectCard, this.suspectCards.get(0), this.locationCards.get(0), false);

        // Player only hold that one card
        assertEquals(players.get(0).canAnswer(guess, this.players.get(1)), expectCard);
        assertEquals(players.get(1).canAnswer(guess, this.players.get(0)), expectCard);
    }

    /* test3()
    Player has more than one card, can answer should return
        one of the card.
    */
    @Test
    void test3()
    {
        // Expect card
        Card expectCard = this.weaponCards.get(0);
        Card expectCard1 = this.suspectCards.get(0);

        // Give the player the expected card
        this.players.get(0).setCard(expectCard);
        this.players.get(0).setCard(expectCard1);

        // Make guess
        Guess guess = new Guess(expectCard, expectCard1, this.locationCards.get(0), false);

        // Player hold either of the two expected cards
        Card gotCard = players.get(0).canAnswer(guess, this.players.get(1));
        assertEquals(gotCard == expectCard || gotCard == expectCard1, true);
        gotCard = players.get(0).canAnswer(guess, this.players.get(1));
        assertEquals(gotCard == expectCard || gotCard == expectCard1, true);
    }

    /* test4()
    Player given a number of cards and when it makes a guess, the guess should not contain any of the cards.
    */
    @Test
    void test4()
    {
        // Expect cards
        Card expectWeapon = this.weaponCards.get(2);
        Card expectSuspect = this.suspectCards.get(2);
        Card expectLocation = this.locationCards.get(2);

        // Computer player get cards beside the expected cards
        this.players.get(0).setCard(this.weaponCards.get(0));
        this.players.get(0).setCard(this.weaponCards.get(1));
        this.players.get(0).setCard(this.suspectCards.get(0));
        this.players.get(0).setCard(this.suspectCards.get(1));
        this.players.get(0).setCard(this.locationCards.get(0));
        this.players.get(0).setCard(this.locationCards.get(1));

        // Get the computer player guess
        Guess guess = this.players.get(0).getGuess();

        // The guess must be expected
        assertEquals(guess.getWeaponCard(), expectWeapon);
        assertEquals(guess.getSuspectCard(), expectSuspect);
        assertEquals(guess.getLocationCard(), expectLocation);
    }

    /* test5()
    The last combination of answers must be an accusation.
    */
    @Test
    void test5()
    {
        // Computer player get cards beside the expected cards
        this.players.get(0).setCard(this.weaponCards.get(0));
        this.players.get(0).setCard(this.weaponCards.get(1));
        this.players.get(0).setCard(this.suspectCards.get(0));
        this.players.get(0).setCard(this.suspectCards.get(1));
        this.players.get(0).setCard(this.locationCards.get(0));
        this.players.get(0).setCard(this.locationCards.get(1));

        // Get the computer player guess
        Guess guess = this.players.get(0).getGuess();

        // The guess must be expected
        assertEquals(guess.getAccusation(), true);
    }

    /* test6()
    Receive four cards the computer guess should not guess an accusation.
    Use the receiveInfo() method to give information about a card then call the guess method again.
    The second guess should return an accusation back.
    */
    @Test
    void test6()
    {
        // Computer player get cards beside the expected cards
        this.players.get(0).setCard(this.weaponCards.get(0));
        this.players.get(0).setCard(this.weaponCards.get(1));
        this.players.get(0).setCard(this.suspectCards.get(0));
        this.players.get(0).setCard(this.suspectCards.get(1));
        this.players.get(0).setCard(this.locationCards.get(0));

        // Get the computer player guess
        Guess guess = this.players.get(0).getGuess();

        // Guess is not an accusation
        assertEquals(guess.getAccusation(), false);

        // Computer player receive information of a card
        this.players.get(0).receiveInfo(this.players.get(1), this.locationCards.get(1));

        // Get the guess
        guess = this.players.get(0).getGuess();

        // The guess now is an accusation
        assertEquals(guess.getAccusation(), true);
    }

    /* test7()
    Human player get call canAnswer() and it must return one of the card the player has with the guess.
    */
    @Test
    void test7()
    {
        // Give player a card
        this.players.get(1).setCard(this.weaponCards.get(0));

        //  Make a guess with the card just gave to the player
        Guess guess = new Guess(this.weaponCards.get(0), this.suspectCards.get(0), this.locationCards.get(0), false);

        // Check if the player has that card
        assertEquals(this.players.get(1).canAnswer(guess, this.players.get(0)), this.weaponCards.get(0));

        // Guess that the player does not have
        guess = new Guess(this.weaponCards.get(1), this.suspectCards.get(1), this.locationCards.get(1), false);

        // Player should not have
        assertEquals(this.players.get(1).canAnswer(guess, this.players.get(0)), null);
    }

}
