
/*
Duy Anh Nguyen 7892957
April 12, 2021
ListNode.js
class ListNode
Container class to hold a datum in the linked list.

Private member:
#datum - Pointer to the datum.
#nextNode - Pointer to the next node.
#prevNode - Pointer to the previous node.

Public method:
constructor() - Constructor to create and initialize an instance.
datum() - Getter method return datum.
nextNode() - Getter method return the next node.
prevNode() - Getter method return the previous node.
setNextNode() - Setter method to set the next node field.
setPrevNode() - Setter method to set the previous node field.
*/

class ListNode
{
    // Private member
    #datum = null;    // The datum
    #nextNode = null; // Pointer to the next node
    #prevNode = null; // Pointer to the previous node


    // Public method

    /* constructor()
    Constructor to create and initialize an instance.

    Parameter:
    DATUM - The datum the node holds.
    NEXT - Pointer to the next node.
    PREV - Pointer to the previous node.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    */
    constructor(DATUM, NEXT, PREV)
    {
        if (arguments.length > 3)
        {
            throw "Too many arguments";
        }
        else
        {
            this.#datum = DATUM;
            this.#nextNode = NEXT;
            this.#prevNode = PREV;
        }
    }

    /* getDatum()
    Getter method return datum.

    Return:
    Return the datum the node holds.
    */
    get datum()
    {
        return this.#datum;
    }

    /* getNextNode()
    Getter method return the next node.

    Return:
    Return the next node.
    */
    get nextNode()
    {
        return this.#nextNode;
    }

    /* prevNode()
    Getter method return the previous node.

    Return:
    Return the previous node.
    */
    get prevNode()
    {
        return this.#prevNode;
    }

    /* setNextNode()
    Setter method to set the next node field.

    Parameter:
    NEXT - Pointer to the next node.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Invalid type" - The parameter type is incorrect.
    */
    setNextNode(NEXT)
    {
        if (arguments.length > 1)
        {
            throw "Too many arguments";
        }
        else if (!(NEXT instanceof ListNode))
        {
            throw "Invalid type"
        }
        else
        {
            this.#nextNode = NEXT;
        }
    }

    /* setPrevNode()
    Setter method to set the previous node field.

    Parameter:
    PREV - Pointer to the next node.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Invalid type" - The parameter type is incorrect.
    */
    setPrevNode(PREV)
    {
        if (arguments.length > 1)
        {
            throw "Too many arguments";
        }
        else if (!(PREV instanceof ListNode))
        {
            throw "Invalid type"
        }
        else
        {
            this.#prevNode = PREV;
        }
    }

}

// Export
module.exports = ListNode;
