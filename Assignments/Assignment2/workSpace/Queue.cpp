#include "Queue.h"
#include "Node.h"
#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
March 1, 2021
Queue.h
class Queue
*/

// Public method

/* Queue()
Constructor to create an instance of queue.
*/
Queue::Queue()
{
    // Setup the dummy node
    this->dummyHead.setNext(this->dummyTail);
    this->dummyTail.setPrev(this->dummyHead);

    // The queue is empty
    this->length = 0;
}



/* dequeue()
Get amd remove the datum at the front of the queue.

Return:
Return the removed front datum of the queue.
*/
ListItem* Queue::dequeue()
{
    // Local variable dictionary
    const Node* const REMOVE_NODE = this->dummyHead.getNext(); // The node to remove
    const ListItem* const DATUM = REMOVE_NODE->getDatum(); // Store the datum to return

    // Remove the node
    this->dummyHead.setNext(REMOVE_NODE->getNext());
    REMOVE_NODE->getNext()->setPrev(this->dummyHead);

    // Return the datum pop
    return DATUM;
}



// Public virtual method

/* enqueue()
Push the datum into the back of the queue.

Parameter:
DATUM - Datum to push into the queue.
*/
void Queue::enqueue(const ListItem* const DATUM)
{
    // Local variable dictionary
    Node* const insertNode = new Node(DATUM); // The node to insert

    // Insert, push, the node to the end of the queue
    this->dummyTail.getPrev().setNext(insertNode);
    insertNode->setPrev(this->dummyTail.getPrev());
    insertNode->setNext(this.dummyTail);
    this->dummyTail.setPrev(insertNode);
}
