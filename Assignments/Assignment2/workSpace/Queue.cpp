#include "Queue.h"
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
    // Create dummy node
    this->dummyHead = new Node(nullptr);
    this->dummyTail = new Node(nullptr);

    // Setup the dummy node
    this->dummyHead->setPrev(nullptr);
    this->dummyHead->setNext(this->dummyTail);
    this->dummyTail->setPrev(this->dummyHead);
    this->dummyTail->setNext(nullptr);

    // The queue is empty
    this->length = 0;
}



/* ~Queue()
Destructor to delete and release memory of an instance of queue.
*/
Queue::~Queue()
{
    // Release memory of data
    Node* reference = this->dummyHead->getNext();
    for (int counter = 0; counter < this->getLength(); counter++)
    {
        // Get the next node
        Node* const nextNode = reference->getNext();

        // Release memory of the current node
        delete reference;

        // Go to the next node
        reference = nextNode;
    }
    
    // Release dummy node memory
    delete this->dummyHead;
    delete this->dummyTail;
}



/* dequeue()
Get amd remove the datum at the front of the queue.

Return:
Return the removed front datum of the queue.
*/
ListItem* Queue::dequeue()
{
    // Local variable dictionary
    Node* const removeNode = this->dummyHead->getNext(); // The node to remove
    const ListItem* const DATUM = removeNode->getDatum(); // Store the datum to return

    // Remove the node
    this->dummyHead->setNext(removeNode->getNext());
    removeNode->getNext()->setPrev(this->dummyHead);

    // Release memory of the removed nove
    delete removeNode;
	
	// Update the number of data
	this->length--;

    // Return the datum pop
    return const_cast<ListItem*>(DATUM);
}



/* peekHead()
Get the first datum at the front of the queue.

Return:
First datum.
*/
ListItem* Queue::peekHead()
{
    return const_cast<ListItem*>(this->dummyHead->getNext()->getDatum());
}



/* getLength()
Get the number of data in the queue.

Return:
Number of data in the queue.
*/
int Queue::getLength()
{
	return this->length;
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
    this->dummyTail->getPrev()->setNext(insertNode);
    insertNode->setPrev(this->dummyTail->getPrev());
    insertNode->setNext(this->dummyTail);
    this->dummyTail->setPrev(insertNode);
	
	// Update the number of data
	this->length++;
}
