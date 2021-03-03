#pragma once

/*
Duy Anh Nguyen 7892957
March 1, 2021
ProcessingUnit.h
class ProcessingUnit
This is parent class represents the common processing unit.
There are two processing unit that is CPU and IO.
The process queue keep track of the processes to be process.

Private member:
processQueue - The queue to track of the processes need to be 
        process.

Public method:
enqueueProcess() - Push the process require processing to the queue.
dequeueProcess() - Remove and return the head process from the queue.

Public pure virtual method:
calculateTimeProcess() - Calculate the time it will take to process 
        the head process.
*/

class ProcessingUnit
{
private:
    // Private member
    const PriorityQueue processQueue; // Queue to track of processes 
                                              // need to be process

public:
    // Public method
    void enqueueProcess(const Process* const PROCESS); // Push the process to processing queue
    Process* dequeueProcess(); // Pop the head process and return it from the queue

    // Public pure virtual method
    virtual int calculateTimeProcess() = 0; // Calcualte the amount of time to process head process
}
