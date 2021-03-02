#pragma once

/*
Duy Anh Nguyen 7892957
Queue.h
class Queue
This will be the queue ADT.
It will build in the basic of doubly linked list.

Private member:
dummyHead - The dummy head of the linked list.
dummyTail - The dummy tail of the linked list.
length - The number of data the queue is holding.

Public method:
Queue() - Constructor to create an instance of queue.
enqueue() - Push the datum to the back of the queue.
dequeue() - Pop the datum from the front of the queue. 
        Datum will get remove as the result of pop.
getLength() - Get the number of data in the queue.
*/

// Forward declaration
class Node;
class ListItem;

class Queue
{
private:
    Node dummyHead(nullptr); // The dummy head of the linked list
    Node dummyTail(nullptr); // The dummy tail of the linked list
    int length = 0; // The number of data in the queue

public:
    Queue(); // Constructor to create an instance of queue
    void enqueue(const ListItem* DATUM); // Push the datum into the back of the queue
    ListItem* dequeue(); // Get and remove the datum at the front of the queue
    int getLength(); // Get the number of data in the queue
};
