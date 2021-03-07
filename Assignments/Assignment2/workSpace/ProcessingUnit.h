#pragma once

/*
Duy Anh Nguyen 7892957
March 1, 2021
ProcessingUnit.h
class ProcessingUnit
This is parent class represents the common processing unit.
There are two processing unit that is CPU and IO.
The process queue keep track of the processes to be process.

Protected member:
timeAvailable - The time the processing unit is available to accept another process.

Public method:
getTimeAvailable() - Get the time the processing unit will be available.

Public pure virtual method:
scheduleProcessingTime() - Schedule a time to process.
*/

// Forward declaration
class Process;

class ProcessingUnit
{
protected:
    // Private member
    int timeAvailable = 0; // The time the process will be available

public:
    // Public method
    int getTimeAvailable(); // Get the time the processing unit will be available

    // Public pure virtual method
    virtual int scheduleProcessingTime(Process* const process) = 0; // Schedule a time to process
};
