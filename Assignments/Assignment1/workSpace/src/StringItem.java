
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


    /* print()
    Print the content.

    Return:
    Return the string content
    */
    @Override
    public void print()
    {
        System.out.print(this.content);
    }

}
