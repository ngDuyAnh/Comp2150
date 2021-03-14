#pragma once

#include "Queue.h"

/*
Duy Anh Nguyen 7892957
March 2, 2021
PriorityQueue.h
class PriorityQueue
This class will extends from class Queue.
The only changes to this class is that it will override 
        enqueue() method to order the queue when insert.

Public method:
PriorityQueue() - Constructor to create an instance.
~PriorityQueue() - Destructor to release memory.

Public override method:
enqueue() - Push the datum and order it in the queue.
*/

class PriorityQueue : public Queue
{
public:
    // Public method
    PriorityQueue();  // Constructor to create an instance
    ~PriorityQueue(); // Destructor to release memory

    // Public override method
    void enqueue(const ListItem* const DATUM) override; // Push the datum to the queue
};
