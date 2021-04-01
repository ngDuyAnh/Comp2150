
/*
Duy Anh Nguyen 7892957
March 26, 2021
Card.java
Card in the game "whodunit?".
It will have two fields that is type of card
    and the value of the card.

Public data type:
CardType - The type of card.

Private member:
type - The type of the card.
value - The value of the card.

Public method:
Card() - Constructor to create and initialize an instance.
getType() - Get the card type.
getValue() - Get the card value.
*/

public class Card
{
    // Public data type

    /* enum CardType
    The type of card.
    There are 3 types of card in the card game
        "whodunit?".
    The 3 types of card are:
    WEAPON - Weapon card.
    SUSPECT - Suspect card.
    LOCATION - Location card.
    */
    public enum CardType
    {
        WEAPON,
        SUSPECT,
        LOCATION
    }


    // Private member
    private CardType type = null; // The type of the card
    private String value = "";    // The value of the card


    // Public method

    /* Card()
    Constructor to create and initialize an instance.

    Parameter:
    cardType - The type of the card.
    cardValue - The value of the card.
    */
    public Card(CardType cardType, String cardValue)
    {
        this.type = cardType;
        this.value = cardValue;
    }

    /* getType()
    Get the card type.

    Return:
    The card type.
    */
    public CardType getType()
    {
        return this.type;
    }

    /* getValue()
    Get the card value.

    Return:
    The card value.
    */
    public String getValue()
    {
        return this.value;
    }

}
