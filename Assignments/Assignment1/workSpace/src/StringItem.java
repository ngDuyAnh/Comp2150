
/*
Duy Anh Nguyen 7892957
January 31, 2021
StringItem.java
public class StringItem extends Item
This represent String storage to work with
    the linked list.

Private member:
content - The string content.

Public method:
StringItem() - Constructor to create String
    container.
getString() - Get the String content.
print() - Print the content.
*/

public class StringItem extends Item
{
    // Private member
    private String content = ""; // The String content




    // Public method

    /* StringItem()
    Constructor to create String item.
    */
    public StringItem(final String CONTENT)
    {
        this.content = CONTENT;
    }


    /* getString()
    Get the String content.

    Return:
    Return the String content.
    */
    public String getString()
    {
        return this.content;
    }


    /* print()
    Print the content.

    Return:
    Return the string content
    */
    @Override
    public String printString()
    {
        return this.content;
    }

}
