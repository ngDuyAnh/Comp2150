#pragma once

/*
Duy Anh Nguyen 7892957
March 1, 2021
ProcessingUnit.h
class ProcessingUnit
This is parent class represents the common processing unit.
There are two processing unit that is CPU and IO.
The process queue keep track of the processes to be process.

Public member:
pQueue - Process queue waiting for process.

Protected member:
timeAvailable - The time the processing unit is available to accept another process.

Public method:
ProcessingUnit() - Constructor to initialize member.
~ProcessingUnit() - Destructor to release memory.
getTimeAvailable() - Get the time the processing unit will be available.
setTime() - Set time to processing unit

Public pure virtual method:
scheduleProcessingTime() - Schedule a time to process.
*/

// Forward declaration
class Process;
class PriorityQueue;

class ProcessingUnit
{
public:
    // Public member
    PriorityQueue* pQueue = nullptr; // Queue for processing process

protected:
    // Private member
    int timeAvailable = 0; // The time the process will be available

public:
    // Public method
    ProcessingUnit(); // Constructor to initialze member
    ~ProcessingUnit(); // Destructor to release memory
    int getTimeAvailable(); // Get the time the processing unit will be available
    void setTime(const int TIME); // Add time to processing unit

    // Public pure virtual method
    virtual int scheduleProcessingTime(Process* const process) = 0; // Schedule a time to process
};
