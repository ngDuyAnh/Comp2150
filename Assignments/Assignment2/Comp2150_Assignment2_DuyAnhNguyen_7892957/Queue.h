#pragma once

#include "Node.h"

/*
Duy Anh Nguyen 7892957
Queue.h
class Queue
This will be the queue ADT.
It will build in the basic of doubly linked list.

Protected member:
dummyHead - The dummy head of the linked list.
dummyTail - The dummy tail of the linked list.
length - The number of data the queue is holding.

Public method:
Queue() - Constructor to create an instance of queue.
~Queue() - Destructor to delete and release memory of an instance of queue
dequeue() - Pop the datum from the front of the queue. 
        Datum will get remove as the result of pop.
peekHead() - Get the first datum.
getLength() - Get the number of data in the queue.

Public virtual method:
enqueue() - Push the datum to the back of the queue.
        This method is virtual to enable the ability to 
        overwrite for the PriorityQueue that will extend 
        from this class.
*/

// Forward declaration
class Node;
class ListItem;

class Queue
{
protected:
    // Protected member
    Node* dummyHead = nullptr; // The dummy head of the linked list
    Node* dummyTail = nullptr; // The dummy tail of the linked list
    int length = 0; // The number of data in the queue

public:
    // Public method
    Queue();  // Constructor to create an instance of queue
    virtual ~Queue(); // Destructor to delete and release memory of an instance of queue
    ListItem* dequeue(); // Get and remove the datum at the front of the queue
    ListItem* peekHead(); // Get the first datum at the front of the queue
    int getLength(); // Get the number of data in the queue
	
	// Public virtual method
	virtual void enqueue(const ListItem* const DATUM); // Push the datum into the back of the queue
};
