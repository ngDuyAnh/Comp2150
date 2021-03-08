#pragma once

#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
March 3, 2021
Process.h
class Process
This class represents a process waiting to be process 
        by processing units.

Private static member:
processCount - Track count of the number of process created.

Private member:
PROCESS_NUMBER - The process ID number.
PROCESS_ARRIVAL - The process arrival time.
processExit - The process exit time.
processWait - The amount of time the process has to wait 
        to get process.
processingQueue - The list of processing need to be done.

Public method:
Process() - Constructor to create an instance of process.
        It will take the process ID number and arrival time.
~Process() - Destructor to release memory.
setProcessExit() - Set the time the process exit.
addProcessWaitTime() - Add process waiting time to be process.
printProcessInfo() - Print the process information to the 
        standard output.
addToProcessingQueue() - Add the processing need to be done to the 
        processing queue.
getCurrentProcessing() - Get the current request for processing.
getNextProcessing() - Get the next processing request.
doneProcessing() - The processing queue is empty.
        There is nothing to process left.

Public override method:
getValue() - Get the value for compareTo() method.
*/

// Forward declaration
class IntegerItem;
class Queue;

class Process : public ListItem
{
private:
    // Private static member
    static int processCount;

    // Private member
    int PROCESS_NUMBER;    // The process number
    int PROCESS_ARRIVAL;   // The process arrival time
    int processExit = -1;  // The process exit time
    int processWait = 0;   // The precess wait time to be process
    Queue* processingQueue; // The list of processing need to be done


public:
    // Public method
    Process(const int ARRIVAL_TIME); // Constructor to create an instance
    ~Process(); // Destructor to release instance memory
    void setProcessExit(const int EXIT_TIME);   // Set the time the process exit processing
    void addProcessWaitTime(const int AMOUNT_TIME); // Add an amount of time the process wait
    void printProcessInfo();                    // Print the process information to std output
    void addToProcessingQueue(const int PROCESSING_REQUEST); // Add the processing need to be done 
                                                                       // to the queue
    void setCurrentProcessingLenght(const int VALUE); // Set the current processing length request
    int getCurrentProcessingLength(); // Get the current request for processing
    int getNextProcessingLength();    // Remove the current processing, get and return the next processing
    bool doneProcessing();      // All processing required is done

    // Public override method
    int getValue() override; // Get the value for compareTo() method.
};
