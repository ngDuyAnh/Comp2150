
/*
Duy Anh Nguyen 7892957
March 26, 2021
Guess.java
Player make guess to the game "whodunit?".
A guess holds 3 cards.
A guess can be either a suggestion or an accusation.
    Accusation is the final guess of a player.
        If correct, the player guessed win the game.
        If incorrect, the player eliminated.

Private member:
weaponCard - The guess weapon card.
suspectCard - The guess suspect card.
locationCard - The guess location card.
accusation - Flag if the guess is an accusation.

Public method:
Guess() - Constructor to initialize the fields.
getWeaponCard() - Get the guess weapon card.
getSuspectCard() - Get the guess suspect card.
getLocationCard() - Get the guess location card.
getAccusation() - Get the guess flag if it is an
    accusation.
*/

public class Guess
{
    // Private member
    private Card weaponCard = null;     // Guess weapon card
    private Card suspectCard = null;    // Guess suspect card
    private Card locationCard = null;   // Guess location card
    private boolean accusation = false; // The guess accusation flag


    // Public method

    /* Guess()
    Constructor to initialize the fields.

    Parameter:
    weapon - The weapon card guess.
    suspect - The suspect card guess.
    location - The location card guess.
    isAccusation - Flag if the guess is an accusation.
    */
    public Guess(Card weapon, Card suspect, Card location, boolean isAccusation)
    {
        // Check if the cards are correct

        // Package the guess cards

        // Set flag if this guess is an accusation
        this.accusation = isAccusation;
    }

    /* getWeaponCard()
    Get the guess weapon card.

    Return:
    Guess weapon card.
    */
    public Card getWeaponCard()
    {
        return this.weaponCard;
    }

    /* getSuspectCard()
    Get the guess suspect card.

    Return:
    Guess suspect card.
    */
    public Card getSuspectCard()
    {
        return this.suspectCard;
    }

    /* getLocationCard()
    Get the guess location card.

    Return:
    Guess location card.
    */
    public Card getLocationCard()
    {
        return this.locationCard;
    }

}
