#include "CPU.h"
#include "Process.h"

/*
Duy Anh Nguyen
March 5, 2021
CPU.cpp
class CPU
*/

// Public method

/* CPU()
Constructor to create an instance of CPU

Parameter:
MAX_SHARE_TIME - The max restriction time the CPU can perform on 
        a process.
*/
CPU::CPU(const int MAX_SHARE_TIME) : RESTRICTIVE_TIME(MAX_SHARE_TIME)
{

}



/* getRestrictiveTime()
Get the CPU restrictive time.

Return:
CPU restrictive time.
*/
int CPU::getRestrictiveTime()
{
    return this->RESTRICTIVE_TIME;
}



// Public override method

/* scheduleProcessingTime()
Schedule a time to process.

Parameter:
process - The process to schedule processing time.

Return:
The time the start processing will take place.
*/
int CPU::scheduleProcessingTime(Process* const process)
{
    // Local variable dictionary
    const int START_TIME = ProcessingUnit::timeAvailable;

    // Determine the amount of time CPU will spend with this process
    if (process->getCurrentProcessingLength() < this->RESTRICTIVE_TIME)
    {
        ProcessingUnit::timeAvailable += process->getCurrentProcessingLength();
    }
    // Time out
    else
    {
        ProcessingUnit::timeAvailable += this->RESTRICTIVE_TIME;
    }

    // Return the schedule time
    return START_TIME;
}
