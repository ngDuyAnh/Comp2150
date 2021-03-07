#include <fstream>
#include <sstream>
#include <string>
#include "Simulation.h"
#include "PriorityQueue.h"
#include "CPU.h"
#include "IO.h"
#include "Process.h"
using namespace std;

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
    bool simulation = true; // Simulation in process flag
    ifstream readFile(INPUT_FILE_NAME); // Read file input
    istringstream readLine; // Read line input
    string line; // The file line read

    // Process input file
    if (readFile.is_open())
    {
        // Read the CPU restriction
        getline(readFile, line);
        int cpuRestriction = 0;
        readLine.str(line);
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
                int processArrivalTime = -1;

                // Get the line and setup for process
                getline(readFile, line);
                readLine.str(line);

                // Get the arrival time
                readLine >> processArrivalTime;

                // Create the process
                Process* process = new Process(processArrivalTime);

                // Enqueue process requests
                int processRequest = 0;
                while (readLine >> processRequest)
                {
                    process->addToProcessingQueue(processRequest);
                }

                // Create an arrival event for this process
                ArrivalEvent* processArrivalEvent = new ArrivalEvent(processArrivalTime, process);

                // Put the event into queue for process
                this->eventsQueue->enqueue(processArrivalEvent);
            }



        }
    }

}



/* summary()
Print the summary of the simulation.
*/
void Simulation::summary()
{

}
