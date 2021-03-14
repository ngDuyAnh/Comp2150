#pragma once

#include "ProcessingUnit.h"

/*
Duy Anh Nguyen 7892957
March 1, 2021
CPU.h
class CPU
This class represents the CPU processing unit.
This processing unit does have restrictive time-share.

Private member:
RESTRICTIVE_TIME - The max amount of time the processing unit can process
        a process.

Public method:
CPU() - Constructor to create an instance of this class.
        This class does have restrictive time-share.
getRestrictiveTime() - Get the max amount of time the CPU can give a process at a time

Public override method:
scheduleProcessingTime() - Schedule a time to process.
*/

class CPU : public ProcessingUnit
{
private:
    // Private member
    const int RESTRICTIVE_TIME; // The max amount of time allow to spend on a process

public:
    // Public method
    CPU(const int MAX_SHARE_TIME); // Constructor to create an instance of CPU
    int getRestrictiveTime(); // Get the max amount of time the CPU can give a process at a time

    // Public override method
    int scheduleProcessingTime(Process* const process) override; // Schedule a time to process
};
