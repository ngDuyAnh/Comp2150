import java.util.ArrayList;
import java.util.Scanner;

/*
Duy Anh Nguyen 7892957
March 30, 2021
WhoDunIt.java
The game "Whodunit?".

Public static member:
numPlayers - Number of players in the game.
weaponCards - All the weapon cards in the game.
suspectCards - All the suspect cards in the game.
locationCards - All the location cards in the game.

Public static method:
main() - The main method as an entry to the game.

Private static method:
setupWeaponCards() - Setup the weapon cards in the game.
setupSuspectCards() - Setup the suspect cards in the game.
setupLocationCards() - Setup the location cards in the game.
*/

public class WhoDunIt
{
    // Private static member
    private static int numPlayers = -1;                                                           // The number of players in the game
    private static ArrayList<Card> weaponCards = null, suspectCards = null, locationCards = null; // The cards in the game


    // Public static method

    /* main()
    The entry to the game.
    */
    public static void main(String[] args)
    {
        // Local variable dictionary
        int numPlayers = -1;                    // The number of players in the game
        Model game = null;                      // The game manager
        Scanner input = new Scanner(System.in); // Get the user input

        // WhoDunIt? game
        System.out.println("Welcome to whodunit?");

        // Get the number computer opponent in the game
        System.out.println("How many computer opponents would you like?");
        do
        {
            while (!input.hasNextInt())
            {
                input.next();
            }
            numPlayers = input.nextInt();
            numPlayers++;
        } while (numPlayers == -1 || numPlayers < 2);

        // Setup the cards for the game
        System.out.println("Setting up players...");
        WhoDunIt.setupSuspectCards();
        WhoDunIt.setupLocationCards();
        WhoDunIt.setupWeaponCards();

        // Setup the game
        game = new Model();

        // Simulate the game
        game.simulateGame(numPlayers, WhoDunIt.weaponCards, WhoDunIt.suspectCards, WhoDunIt.locationCards);

        // Release resource
        input.close();
    }


    // Private static method

    /* setupWeaponCards()
    Setup the weapon cards in the game.
    */
    private static void setupWeaponCards()
    {
        System.out.println("Here are all the weapons:");

        // Add all the weapon cards to the game
        WhoDunIt.weaponCards = new ArrayList<Card>();
        WhoDunIt.weaponCards.add(new Card(Card.CardType.WEAPON, "midterm"));
        WhoDunIt.weaponCards.add(new Card(Card.CardType.WEAPON, "final exam"));
        WhoDunIt.weaponCards.add(new Card(Card.CardType.WEAPON, "assignment"));
        WhoDunIt.weaponCards.add(new Card(Card.CardType.WEAPON, "lab"));

        // Print all the weapon cards in the game
        printListCards(WhoDunIt.weaponCards);
    }

    /* setupSuspectCards()
    Setup the suspect cards in the game.
    */
    private static void setupSuspectCards()
    {
        System.out.println("Here are all the suspects:");

        // Add all the suspect cards to the game
        WhoDunIt.suspectCards = new ArrayList<Card>();
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Boyer"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Domaratzki"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Cameron"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Guderian"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Durocher"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Li"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Miller"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Wang"));
        WhoDunIt.suspectCards.add(new Card(Card.CardType.SUSPECT, "Prof. Bristow"));

        // Print all the weapon cards in the game
        printListCards(WhoDunIt.suspectCards);
    }

    /* setupLocationCards()
    Setup the location cards in the game.
    */
    private static void setupLocationCards()
    {
        System.out.println("Here are all the suspects:");

        // Add all the location cards to the game
        WhoDunIt.locationCards = new ArrayList<Card>();
        WhoDunIt.locationCards.add(new Card(Card.CardType.LOCATION, "COMP 2150 course"));
        WhoDunIt.locationCards.add(new Card(Card.CardType.LOCATION, "COMP 2160 course"));
        WhoDunIt.locationCards.add(new Card(Card.CardType.LOCATION, "COMP 2140 course"));
        WhoDunIt.locationCards.add(new Card(Card.CardType.LOCATION, "COMP 2280 course"));
        WhoDunIt.locationCards.add(new Card(Card.CardType.LOCATION, "COMP 2080 course"));

        // Print all the weapon cards in the game
        printListCards(WhoDunIt.locationCards);
    }


    /* printListCards()
    Print the list of cards
    */
    private static void printListCards(ArrayList<Card> printList)
    {
        for (int counter = 0; counter < printList.size(); counter++)
        {
            System.out.print(printList.get(counter).getValue());

            // Follow
            if (counter + 1 == printList.size())
            {
                System.out.println(".");
            }
            else
            {
                System.out.print(", ");
            }
        }
    }

}
