#include <iostream>
#include <iomanip>
#include "Process.h"
#include "Queue.h"
#include "IntegerItem.h"
using namespace std;

/*
Duy Anh Nguyen 7892957
March 2, 2021
PriorityQueue.cpp
class PriorityQueue
*/

// Private static member
int Process::processCount = 1;

// Public method

/* Process()
Constructor to create an instance of process.

Parameter:
ID_NUMBER - Process ID number.
ARRIVAL_TIME - The time the process arrival and request 
        process.
*/
Process::Process(const int ARRIVAL_TIME)
{
    this->PROCESS_NUMBER = Process::processCount;
    this->PROCESS_ARRIVAL = ARRIVAL_TIME;
    this->processExit = -1;
    this->processWait = 0;
    this->processingQueue = new Queue();

    // Update process count
    Process::processCount++;
}



/* ~Process()
Destructor to release memory.
*/
Process::~Process()
{
    delete this->processingQueue;
}



/* setProcessExit()
Set the time the process exit processing.

Parameter:
EXIT_TIME - The time the process done processing.
*/
void Process::setProcessExit(const int EXIT_TIME)
{
    this->processExit = EXIT_TIME;
}



/* addProcessWait()
Add an amount of time the process is waiting to get process.

Parameter:
AMOUNT_TIME - The amount of time the process is waiting to get process.
*/
void Process::addProcessWaitTime(const int AMOUNT_TIME)
{
    this->processWait += AMOUNT_TIME;
}



/* printProcessInfo()
Print the process information to std output.
<Process ID> <Time arrival> <Exit time> <Wait time>
*/
void Process::printProcessInfo()
{
    cout << setw(10) << this->PROCESS_NUMBER;
    cout << setw(10) << this->PROCESS_ARRIVAL;
    cout << setw(10) << this->processExit;
    cout << setw(10) << this->processWait;
}



/* addToProcessingQueue()
Add the processing need to be done to the queue.

Parameter:
PROCESSING_REQUEST - Processing request to be done.
*/
void Process::addToProcessingQueue(const int PROCESSING_REQUEST)
{
    // Create integer container to add to queue
    IntegerItem* const addToQueue = new IntegerItem(PROCESSING_REQUEST);
    this->processingQueue->enqueue(dynamic_cast<ListItem*>(addToQueue));
}



/* setCurrentProcessingLength()
Set the current processing length request.

Parameter:
VALUE - The value to set the new processing length.
*/
void Process::setCurrentProcessingLenght(const int VALUE)
{
    // Get the current processing length container
    IntegerItem* const currentLength = dynamic_cast<IntegerItem*>(this->processingQueue->peekHead());

    // Set the new value
    currentLength->setValue(VALUE);
}



/* getCurrentProcessingLength()
Get the current request for processing.

Return:
The next processing request.
*/
int Process::getCurrentProcessingLength()
{
    return this->processingQueue->peekHead()->getValue();
}



/* getNextProcessingLength()
Remove the current processing, get and return the next processing.

Return:
Next processing length.
*/
int Process::getNextProcessingLength()
{
    // Remove the current processing
    ListItem* currentProcessing = this->processingQueue->dequeue();
    delete currentProcessing;

    // Get the next processing
    ListItem* nextProcessing = this->processingQueue->peekHead();

    // Return the next processing
    return nextProcessing->getValue();
}



/* doneProcessing()
Flag indicate if process done all the required processing.

Return:
Bool indicate if the process has done all the required processing.
*/
bool Process::doneProcessing()
{
    return static_cast<bool>(this->processingQueue->getLength() == 0);
}



// Public override method

/* getValue()
Get hte value for compareTo() method.
For process, it is the process ID number.
*/
int Process::getValue()
{
    return this->PROCESS_NUMBER;
}
