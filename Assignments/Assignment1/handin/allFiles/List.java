
/*
Duy Anh Nguyen 7892957
January 31, 2021
List.java
public class List extends ListItem
Doubly Linked List abstract data type.

Private member:
dummyHead - Pointer to the dummy Node head.
dummyTail - Pointer to the dummy Node tail.
length - The number of Node, data, the linked
    list is holding.

Public method:
List() - Constructor to initialize members.
append() - Add the given data to the end of
    the structure.
prepend() - Add the given data to the front of
    the structure.
insertIndex() - Insert the given data to the
    given index.
removeHead() - Remove and return the data of
    the head, first Node.
removeTail() - remove and return the data of
    the tail, last Node.
removeIndex() - remove and return the data at
    the given index.
peekHead() - get the content of the head but
    not remove.
peekTail() - get the content of the tail but not
    remove.
peekIndex() - get the content of given index but
    not remove.
isEmpty() - return if the structure is empty, does
    not contain any Node or data.
getLength() - return the number of Node, data, the
    structure contains.
*/

public class List implements Printable
{
    // Private member
    private final ItemNode dummyHead; // The dummy Head
    private int length = 0;




    // Public method

    /* List()
    Constructor to initialize members.
    */
    public List()
    {
        // Setup the dummy nodes
        this.dummyHead = new ItemNode(null);
        final ItemNode DUMMY_TAIL = new ItemNode(null);
        this.dummyHead.setNext(DUMMY_TAIL);
        DUMMY_TAIL.setPrev(this.dummyHead);

        // Currently, it holds zero data
        this.length = 0;
    }


    /* append()
    Add the given datum to the end of the list.

    Parameter:
    DATUM - The datum to add to the list.
    */
    public void append(final Item DATUM)
    {
        // Insert datum to the end of the list
        this.insertIndex(this.length, DATUM);
    }


    /* prepend()
    Add the given datum to the front of the list.

    Parameter:
    DATUM - The datum to add to the list.
    */
    public void prepend(final Item DATUM)
    {
        // Insert the datum to the front of the list
        this.insertIndex(0, DATUM);
    }


    /* insertIndex()
    Insert the datum at the given index.
    If the given index is below zero, just perform prepend().
    If the given index is above and out of range, just perform append().

    Parameter:
    INDEX - The index to insert the datum to the list.
    DATUM - The datum to add to the list.
    */
    public void insertIndex(final int INDEX, final Item DATUM)
    {
        // Insert the datum to the list
        if (INDEX < 0)
        {
            this.prepend(DATUM);
        }
        else if (INDEX > this.length)
        {
            this.append(DATUM);
        }
        else
        {
            // Go to the position to insert, one node before the insert position
            ItemNode reference = this.dummyHead;
            for (int counter = 0; counter < INDEX; counter++)
            {
                reference = reference.getNext();
            }

            // Insert the node
            final ItemNode INSERT_NODE =
                    new ItemNode(DATUM, reference.getNext(), reference.getPrev()); // Node container
            reference.setNext(INSERT_NODE);
            INSERT_NODE.getNext().setPrev(INSERT_NODE);

            // Update the number of data the list holds
            this.length++;
        }
    }


    /* removeHead()
    Remove and return the datum at the head of the list.

    Return:
    Return the datum.
    */
    public Item removeHead()
    {
        return this.removeIndex(0);
    }


    /* removeTail()
    Remove and return the datum at the tail of the list.

    Return:
    Return the datum.
    */
    public Item removeTail()
    {
        return this.removeIndex(this.length - 1);
    }


    /* removeIndex()
    Remove and return the datum at the given index.
    If the index given is less than zero, perform removeHead().
    If the index given is above and out of range, perform removeTail().

    Parameter:
    INDEX - Index to remove the node from the list.

    Return:
    The datum at given index.
    */
    public Item removeIndex(final int INDEX)
    {
        // Local variable dictionary
        Item datum = null; // Pointer to datum

        // Get datum and remove to node from list
        if (INDEX < 0)
        {
            datum = this.removeHead();
        }
        else if (INDEX >= this.length)
        {
            datum = this.removeTail();
        }
        else if (!this.isEmpty())
        {
            // Go to the position to get and remove node, one before target node
            ItemNode reference = this.dummyHead;
            for (int counter = 0; counter < INDEX; counter++)
            {
                reference = reference.getNext();
            }

            // Get the datum from the node
            datum = reference.getNext().getDatum();

            // Remove to node from list
            final ItemNode PREV_NODE = reference;
            final ItemNode REMOVE_NODE = reference.getNext();
            final ItemNode NEXT_NODE = REMOVE_NODE.getNext();
            PREV_NODE.setNext(NEXT_NODE);
            NEXT_NODE.setPrev(PREV_NODE);

            // Update number of data in list
            this.length--;
        }

        // Return the datum
        return datum;
    }


    /* peekHead()
    Get the datum at the head of the list.

    Return:
    Datum at the head of the list.
    */
    public Item peekHead()
    {
        return this.peekIndex(0);
    }


    /* peekTail()
    Get the datum at the tail of the list.

    Return:
    Datum at the tail of the list.
    */
    public Item peekTail()
    {
        return this.peekIndex(this.length - 1);
    }


    /* peekIndex()
    Get the datum at given index.
    If index given is less than zero, perform peekHead().
    If index given is above and out of range, perform peekTail().

    Parameter:
    INDEX - The index to get the datum.

    Return:
    Datum at given index.
    */
    public Item peekIndex(final int INDEX)
    {
        // Local variable dictionary
        Item datum = null; // Pointer to datum

        // Get the datum
        if (INDEX < 0)
        {
            datum = this.peekHead();
        }
        else if (INDEX >= this.length)
        {
            datum = this.peekTail();
        }
        else if (!this.isEmpty())
        {
            // Go to the position to get datum, one before the target node
            ItemNode reference = this.dummyHead;
            for (int counter = 0; counter < INDEX; counter++)
            {
                reference = reference.getNext();
            }

            // Get the datum
            datum = reference.getNext().getDatum();
        }

        // Return the datum
        return datum;
    }


    /* isEmpty()
    Boolean flag if the list is empty.

    Return:
    Flag if the list is empty.
    */
    public boolean isEmpty()
    {
        // Local variable dictionary
        boolean empty = true;

        // Check if the list is empty
        if (this.length > 0)
            empty = false;

        // Return flag if  the structure is empty
        return empty;
    }


    /* getLength()
    Return the number of Node, data, the
        structure contains.

    Return:
    Number of data in the list.
    */
    public int getLength()
    {
        return this.length;
    }


    /* printString()
    Return String of all the nodes' datum.

    Return:
    String of all node's datum.
    */
    @Override
    public String printString()
    {
        // Local variable dictionary
        String returnOutput = ""; // The return String

        // Run though all the node and print
        for (int counter = 0; counter < this.getLength(); counter++)
        {
            // Get the node
            final Item ITEM = this.peekIndex(counter);

            // Print the datum
            returnOutput += ITEM.printString();
            returnOutput += "\n";
        }

        // Return the output String
        return returnOutput;
    }

}
