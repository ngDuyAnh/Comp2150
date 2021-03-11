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

/* Simulation()
Constructor to create an instance.
*/
Simulation::Simulation()
{
    this->processHistory = new PriorityQueue();
    this->eventsQueue = new PriorityQueue();
}



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
        ArrivalEvent* nextEvent = nullptr; // The comming arrival event
        while (this->eventsQueue->getLength() != 0
            || this->cpuUnit->pQueue->getLength() != 0 || this->ioUnit->pQueue->getLength() 
            || !readFile.eof())
        {
            // Local variable dictionary
            Event* processingEvent = nullptr; // The event to be process

            // Read file and create a process
            if (!readFile.eof() && nextEvent == nullptr)
            {
                // Local variable dictionary
                string line; // The file line read
                int processArrivalTime = -1;

                // Get the line and setup for process
                getline(readFile, line);

                // Create new arrival event for simulation
                if (!line.empty())
                {
                    nextEvent = ArrivalEvent::newArrivalEvent(this, line);
                }
            }
            else if (nextEvent && (this->eventsQueue->getLength() == 0 || nextEvent->getValue() <= this->eventsQueue->peekHead()->getValue()))
            {
                this->eventsQueue->enqueue(nextEvent);

                // Set the next event pointer to null
                nextEvent = nullptr;
            }

            // Process the coming event
            else if (this->eventsQueue->getLength() && this->cpuUnit->pQueue->getLength() == 0 && this->ioUnit->pQueue->getLength() == 0) // Kick start the simulation
            {
                processingEvent = dynamic_cast<Event*>(this->eventsQueue->dequeue());
            }
            else if ((this->eventsQueue->getLength() && this->cpuUnit->pQueue->getLength() && this->ioUnit->pQueue->getLength() == 0)
                && this->eventsQueue->peekHead()->getValue() <= this->cpuUnit->pQueue->peekHead()->getValue()) // Only Event and CPU
            {
                processingEvent = dynamic_cast<Event*>(this->eventsQueue->dequeue());
            }
            else if ((this->eventsQueue->getLength() && this->cpuUnit->pQueue->getLength() == 0 && this->ioUnit->pQueue->getLength())
                && this->eventsQueue->peekHead()->getValue() <= this->ioUnit->pQueue->peekHead()->getValue()) // Only Event and IO
            {
                processingEvent = dynamic_cast<Event*>(this->eventsQueue->dequeue());
            }
            else if ((this->eventsQueue->getLength() && this->cpuUnit->pQueue->getLength() && this->ioUnit->pQueue->getLength())
                && this->eventsQueue->peekHead()->getValue() <= this->cpuUnit->pQueue->peekHead()->getValue()
                && this->eventsQueue->peekHead()->getValue() <= this->ioUnit->pQueue->peekHead()->getValue()) // Event, CPU, and IO
            {
                processingEvent = dynamic_cast<Event*>(this->eventsQueue->dequeue());
            }
            else if (this->cpuUnit->pQueue->getLength() && this->ioUnit->pQueue->getLength() == 0) // CPU only
            {
                processingEvent = dynamic_cast<Event*>(this->cpuUnit->pQueue->dequeue());
            }
            else if ((this->cpuUnit->pQueue->getLength() && this->ioUnit->pQueue->getLength())
                && this->cpuUnit->pQueue->peekHead()->getValue() <= this->ioUnit->pQueue->peekHead()->getValue()) // CPU and IO
            {
                processingEvent = dynamic_cast<Event*>(this->cpuUnit->pQueue->dequeue());
            }
            else if (this->ioUnit->pQueue->getLength())
            {
                processingEvent = dynamic_cast<Event*>(this->ioUnit->pQueue->dequeue());
            }

            // Process the event
            if (processingEvent != nullptr)
            {
                processingEvent->handleEvent();

                // Release the handled event memory
                delete processingEvent;
            }
        }
    }

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
        cout << endl;

        // Release memory of the process
        delete process;
    }
}
