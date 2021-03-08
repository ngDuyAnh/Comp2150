#include "ProcessingUnit.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
ProcessingUnit.cpp
class ProcessingUnit
*/

// Public method

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
