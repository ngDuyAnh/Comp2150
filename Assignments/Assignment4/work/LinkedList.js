let ListNode = require("./ListNode.js")

/*
Duy Anh Nguyen 7892957
April 12, 2021
LinkedList.js
class LinkedList
Linked list abstract data type to hold instances of ListNode.

Private member:
#dummyHead - Pointer to the dummy node that is head of the linked list.
#length - The number of data the linked list holds.

Public method:
constructor() - Constructor to create and initialize an instance.
prepend() - Add the given datum to the front of the linked list.
append() - Add the given datum to the end of the linked list.
insertIndex() - Insert the given datum to the given index.
removeHead() - Remove and return the first datum in the linked list.
removeTail() - Remove and return the last datum in the linked list.
removeIndex() - Remove and return the datum at given index.
peekHead() - Get the first datum in the linked list but do not remove.
peekTail() - Get the last datum in the linked list but do not remove.
peekIndex() - Get the datum at given index in the linked list but do
        not remove.
isEmpty() - Return boolean flag if the linked list is empty.
getLength() - Return the number of data the linked list holds.
*/

class LinkedList
{
    // Private member
    #dummyHead = null;
    #length = -1;


    // Public method

    /* constructor()
    Constructor to create and initialize an instance.
    */
    constructor()
    {
        this.#dummyHead = new ListNode();
        this.#length = 0;

        // Insert dummy tail
        this.#dummyHead.setNextNode(new ListNode());
    }

    /* prepend()
    Add the given datum to the front of the linked list.

    Parameter:
    arguments, All data given to method.
    */
    prepend()
    {
        for (let counter = 0; counter < arguments.length; counter++)
        {
            this.insertIndex(arguments[counter], 0);
        }
    }

    /* append()
    Add the given datum to the end of the linked list.

    Parameter:
    arguments - All data given to method.
    */
    append()
    {
        for (let counter = 0; counter < arguments.length; counter++)
        {
            this.insertIndex(arguments[counter], this.#length);
        }
    }

    /* insertIndex()
    Insert the given datum to the given index.

    Parameter:
    DATUM - The given datum to be add to the linked list.
    INDEX - The index to add the datum in the linked list.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    "Index less than 0" - The given index is invalid, less than zero.
    "Index out of bounds" - The given index is out of bounds.
    */
    insertIndex(DATUM, INDEX)
    {
        if (arguments.length > 2)
        {
            throw "Too many arguments";
        }
        else if (arguments.length < 2)
        {
            throw "Too few arguments";
        }
        else if (typeof(INDEX) != "number")
        {
            throw "Invalid type";
        }
        else
        {
            if (INDEX < 0)
            {
                throw "Index less than 0";
            }
            else if (INDEX > this.#length)
            {
                throw "Index out of bounds";
            }
            else
            {
                // Go to the position to insert, one node before the insert position
                let referenceNode = this.#dummyHead;
                for (let counter = 0; counter < INDEX; counter++)
                {
                    referenceNode = referenceNode.nextNode;
                }

                // Insert the new node
                const INSERT_NODE = new ListNode(DATUM, referenceNode.nextNode, referenceNode);
                referenceNode.setNextNode(INSERT_NODE);
                INSERT_NODE.nextNode.setPrevNode(INSERT_NODE);

                // Update the number of data in the linked list
                this.#length++;
            }
        }
    }

    /* removeHead()
    Remove and return the first datum in the linked list.

    Return:
    The datum at head.
    */
    removeHead()
    {
        return this.removeIndex(0);
    }

    /* removeTail()
    Remove and return the last datum in the linked list.

    Return:
    The datum at tail.
    */
    removeTail()
    {
        return this.removeIndex(this.#length - 1);
    }

    /* removeIndex()
    Remove and return the datum at given index.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    "Index less than 0" - The given index is invalid, less than zero.
    "Index out of bounds" - The given index is out of bounds.

    Return:
    The datum at given index.
    */
    removeIndex(INDEX)
    {
        // Local variable dictionary
        let datum = null;

        // Get the datum at given index
        if (arguments.length > 1)
        {
            throw "Too many arguments";
        }
        else if (arguments.length < 1)
        {
            throw "Too few arguments";
        }
        else if (typeof(INDEX) != "number")
        {
            throw "Invalid type";
        }
        else if (!this.isEmpty())
        {
            if (INDEX < 0)
            {
                throw "Index less than 0";
            }
            else if (INDEX >= this.#length)
            {
                throw "Index out of bounds";
            }
            else
            {
                // Go to the position to get datum, one before the target node
                let referenceNode = this.#dummyHead;
                for (let counter = 0; counter < INDEX; counter++)
                {
                    referenceNode = referenceNode.nextNode;
                }

                // Get the datum
                datum = referenceNode.nextNode.datum;

                // Remove the node from the linked list
                const PREV_NODE = referenceNode;
                const REMOVE_NODE = referenceNode.nextNode;
                const NEXT_NODE = REMOVE_NODE.nextNode;
                PREV_NODE.setNextNode(NEXT_NODE);
                NEXT_NODE.setPrevNode(PREV_NODE);

                // Update the number of data in linked list
                this.#length--;
            }
        }

        // Return the datum
        return datum;
    }

    /* peekHead()
    Get the first datum in the linked list but do not remove.

    Return:
    The datum at head.
    */
    peekHead()
    {
        return this.peekIndex(0);
    }

    /* peekTail()
    Get the last datum in the linked list but do not remove.

    Return:
    The datum at tail.
    */
    peekTail()
    {
        return this.peekIndex(this.#length - 1);
    }

    /* peekIndex()
    Get the datum at given index in the linked list but do
            not remove.

    Parameter:
    INDEX - The index to get the datum.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    "Index less than 0" - The given index is invalid, less than zero.
    "Index out of bounds" - The given index is out of bounds.

    Return:
    Datum at given index.
    */
    peekIndex(INDEX)
    {
        // Local variable dictionary
        let datum = null;

        // Get the datum at given index
        if (arguments.length > 1)
        {
            throw "Too many arguments";
        }
        else if (arguments.length < 1)
        {
            throw "Too few arguments";
        }
        else if (typeof(INDEX) != "number")
        {
            throw "Invalid type";
        }
        else if (!this.isEmpty())
        {
            if (INDEX < 0)
            {
                throw "Index less than 0";
            }
            else if (INDEX >= this.#length)
            {
                throw "Index out of bounds";
            }
            else
            {
                // Go to the position to get datum, one before the target node
                let referenceNode = this.#dummyHead;
                for (let counter = 0; counter < INDEX; counter++)
                {
                    referenceNode = referenceNode.nextNode;
                }

                // Get the datum
                datum = referenceNode.nextNode.datum;
            }
        }

        // Return the datum
        return datum;
    }


    /* isEmpty()
    Return boolean flag if the linked list is empty.

    Return:
    The flag if the linked list is empty.
    */
    isEmpty()
    {
        return (this.#length === 0);
    }

    /* getLength()
    Return the number of data the linked list holds.

    Return:
    Number of data the linked list holds.
    */
    getLength()
    {
        return this.#length;
    }


}

// Export
module.exports = LinkedList;
