
/*
Duy Anh Nguyen 7892957
January 31, 2021
ItemNode.java
public class ItemNode
A node to hold ListItem and reference to
    other node in LinkedList ADT.

Private member:
DATUM - Pointer to the datum the node holds.
nextNode - Pointer to the next Node.
prevNode - Pointer to the previous Node.

Public method:
Node() - Constructor to initialize the members.
    Accept only datum.
    Accept datum and next node.
    Accept datum, next node, and previous node.
getDatum() - Return the datum.
getNext() - Return the next node.
getPrev() - Return the previous node.
setNext() - Set the next node.
setPrev() - Set the previous node.
*/

public class ItemNode implements Printable
{
    // Private member
    private Item datum; // The datum
    private ItemNode nextNode = null; // Pointer to the next node
    private ItemNode prevNode = null; // Pointer to the previous node




    // Public method

    /* ItemNode()
    Constructor method to initialize the members.
    */
    public ItemNode(final Item DATUM)
    {
        this.datum = DATUM;
        this.nextNode = null;
        this.prevNode = null;
    }
    
    
    public ItemNode(final Item DATUM, final ItemNode NEXT)
    {
        this(DATUM);
        this.nextNode = NEXT;
    }
    
    
    public ItemNode(final Item DATUM, final ItemNode NEXT, final ItemNode PREV)
    {
        this(DATUM, NEXT);
        this.prevNode = PREV;
    }


    /* getDatum()
    Getter method to get the datum.
    */
    public Item getDatum()
    {
        return this.datum;
    }


    /* getNext()
    Getter method to get the pointer to the next node.
    */
    public ItemNode getNext()
    {
        return this.nextNode;
    }


    /* getPrev()
    Getter method to get the pointer to the previous node.
    */
    public ItemNode getPrev()
    {
        return this.prevNode;
    }


    /* setNext()
    Setter method to set the pointer to the next node.
    */
    public void setNext(final ItemNode NEXT)
    {
        this.nextNode = NEXT;
    }


    /* setPrev()
    Setter method to set the pointer to the previous node.
    */
    public void setPrev(final ItemNode PREV)
    {
        this.prevNode = PREV;
    }


    /* print()
    Print the datum.
    */
    @Override
    public void print()
    {
        System.out.print(this.datum.toString());
    }

}
