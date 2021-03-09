#pragma once

#include "ProcessingUnit.h"

/*
Duy Anh Nguyen 7892957
March 1, 2021
IO.h
class IO
This class represents the IO processing unit.
This processing unit does not have restrictive time-share.

Public method:
IO() - Constructor to create an instance.

Public override method:
scheduleProcessingTime() - Schedule a time to process.
*/

class IO : public ProcessingUnit
{
public:
    // Public method
    IO(); // Constructor to create an instance.

    // Public override method
    int scheduleProcessingTime(Process* const process) override; // Schedule a time to process
};
