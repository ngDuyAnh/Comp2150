#include "IO.h"
#include "Process.h"

/*
Duy Anh Nguyen
March 5, 2021
IO.cpp
class IO
*/

// Public method

/* IO()
Constructor to create an instance.
*/
IO::IO() : ProcessingUnit::ProcessingUnit()
{

}



// Public override method

/* scheduleProcessingTime()
Schedule a time to process.

Parameter:
process - The process to schedule processing time.

Return:
The time the start processing will take place.
*/
int IO::scheduleProcessingTime(Process* const process)
{
    // Local variable dictionary
    const int START_TIME = ProcessingUnit::timeAvailable;

    // Update the next time available
    ProcessingUnit::timeAvailable += (process->getCurrentProcessingLength() * -1);

    // Return the schedule time
    return START_TIME;
}
