#include "PriorityQueue.h"

/*
Duy Anh Nguyen 7892957
March 2, 2021
PriorityQueue.cpp
class PriorityQueue
*/

// Public override method

/* enqueue()
Push the datum to the queue and order it.
The datum will have a method compareTo() to 
        help determine the value to order.

Parameter:
DATUM - Datum to push into the queue.
*/
void PriorityQueue::enqueue(const ListItem* const DATUM)
{
    // Local variable dictionary
    bool foundInsert = false; // Found the place to insert

    // Search for the right position to insert the node
    Node* reference = this->dummyHead;
    for (int counter = 0; counter < this->getLength() && !foundInsert; counter++)
    {
        // Go next node and get the datum to check
        reference = reference->getNext();
        const ListItem* const NODE_DATUM = reference->getDatum() && !foundInsert;

        // Check if can insert before
        if (DATUM->compareTo(NODE_DATUM) == -1) // Less than
        {
            foundInsert = true;
        }
    }

    // Insert the node
    if (foundInsert == false) // Insert tail, this is greater than all in queue value
    {
        Queue::enqueue(DATUM)
    }
    else
    {
        // The node to insert
        Node* const insertNode = new Node(DATUM, reference, reference->getPrev());

        // Change the reference node link
        reference->setPrev(insertNode);
        
        // Update the number of data
        this->length++;
    }
}
