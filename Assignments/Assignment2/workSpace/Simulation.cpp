#include <iostream>
#include <iomanip>
#include <fstream>
#include <sstream>
#include <string>
#include "Simulation.h"
#include "PriorityQueue.h"
#include "CPU.h"
#include "IO.h"
#include "Process.h"
using std::cout;
using std::endl;
using std::ifstream;
using std::istringstream;
using std::string;

/*
Duy Anh Nguyen 7892957
March 5, 2021
Simulation.cpp
class Simulation
*/

// Public method

/* ~Simualtion()
Destruction to release memory of an instance.
*/
Simulation::~Simulation()
{
    // Delete the CPU unit
    if (this->cpuUnit != nullptr)
    {
        delete this->cpuUnit;
        this->cpuUnit = nullptr;
    }

    // Delete the IO unit
    if (this->ioUnit != nullptr)
    {
        delete this->ioUnit;
        this->ioUnit = nullptr;
    }

    // Delete process history
    if (this->processHistory != nullptr)
    {
        delete this->processHistory;
        this->processHistory = nullptr;
    }

    // Delete event queue
    if (this->eventsQueue != nullptr)
    {
        delete this->eventsQueue;
        this->eventsQueue = nullptr;
    }
}



/* runSimulation()
Activate the simulation.

Parameter:
INOUT_FILE_NAME - The input file name to stimulate the simulation.
*/
void Simulation::runSimulation(const char* INPUT_FILE_NAME)
{
    // Local variable dictionary
    ifstream readFile(INPUT_FILE_NAME); // Read file input

    // Process input file
    if (readFile.is_open())
    {
        // Read the CPU restriction
        int cpuRestriction = 0;
        readFile >> cpuRestriction;

        // Intialize the processing units
        this->cpuUnit = new CPU(cpuRestriction);
        this->ioUnit = new IO();

        // Simulate
        while (this->eventsQueue->getLength() != 0 || !readFile.eof())
        {
            // Read file and create a process
            if (!readFile.eof())
            {
                // Local variable dictionary
                string line; // The file line read
                int processArrivalTime = -1;

                // Get the line and setup for process
                getline(readFile, line);

                // Create new arrival event for simulation
                ArrivalEvent::newArrivalEvent(this, line);
            }

            // Get the coming event and process
            Event* comingEvent = dynamic_cast<Event*>(this->eventsQueue->dequeue());

            // Perform the event action
            comingEvent->handleEvent();

            // Release the handled event memory
            delete comingEvent;
        }
    }
    cout << "... All processes complete." << endl << endl;

    // Release memory use in running simulation
    delete this->cpuUnit;
    delete this->ioUnit;
}



/* summary()
Print the summary of the simulation.
*/
void Simulation::summary()
{
    // Print the processes stats
    cout << "Final summary." << endl;
    cout << std::setw(9) << "Process#";
    cout << std::setw(9) << "Arrival";
    cout << std::setw(9) << "Exit";
    cout << std::setw(9) << "Wait";
    cout << endl;
    while (this->processHistory->getLength())
    {
        // Get the process from history queue
        Process* const process = dynamic_cast<Process*>(this->processHistory->dequeue());

        // Print the stats of the process
        process->printProcessInfo();

        // Release memory of the process
        delete process;
    }
}
