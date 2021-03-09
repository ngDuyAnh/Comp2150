#include "ProcessingUnit.h"
#include "PriorityQueue.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
ProcessingUnit.cpp
class ProcessingUnit
*/

// Public method

/* ProcessingUnit()
Constructor to initialize member.
*/
ProcessingUnit::ProcessingUnit()
{
    this->pQueue = new PriorityQueue();
}



/* ~ProcessingUnit()
Destructor to release memory.
*/
ProcessingUnit::~ProcessingUnit()
{
    delete this->pQueue;
}



/* getTimeAvailable()
Get the time the processing unit it available to process.
*/
int ProcessingUnit::getTimeAvailable()
{
    return this->timeAvailable;
}



/* setTime()
Set time to processing unit
*/
void ProcessingUnit::setTime(const int TIME)
{
    this->timeAvailable = TIME;
}
