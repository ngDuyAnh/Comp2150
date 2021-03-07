#include <sstream>
#include <string>
#include "Simulation.h"
#include "CPU.h"
#include "IO.h"
#include "Process.h"
using std::istringstream;
using std::string;

/*
Duy Anh Nguyen 7892957
March 7, 2021
ArrivalEvent.cpp
class ArrivalEvent
*/

// Public static method

/* newArrivalEvent()
Create new event and enqueue it to the event queue.

Parameter:
simulation - The simulation the event occurs.
processLine - The line to be process to make an arrival event.
*/
void Simulation::ArrivalEvent::newArrivalEvent(Simulation* const simulation, string processLine)
{
    // Local variable dictionary
    istringstream readLine(processLine); // Read line input

    // Get the arrival time
    int processArrivalTime = -1;
    readLine >> processArrivalTime;

    // Create the process
    Process* process = new Process(processArrivalTime);

    // Enqueue process requests
    int processRequest = 0;
    while (readLine >> processRequest)
    {
        process->addToProcessingQueue(processRequest);
    }

    // Put the event into queue for process
    ArrivalEvent* processArrivalEvent = new Simulation::ArrivalEvent(simulation, processArrivalTime, process);
    simulation->eventsQueue->enqueue(processArrivalEvent);
}



// Public method

/* ArrivalEvent()
Constructor to create an instance.

Paramter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
Simulation::ArrivalEvent::ArrivalEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process) : Simulation::Event::Event(simulation, EVENT_TIME, process)
{
    
}



// Public pure virtual method

/* handleEvent()
Handle the arrival event.
*/
void Simulation::ArrivalEvent::handleEvent()
{
    // Local variable dictionary
    int eventTime = -1; // The event execute time
    int processProcessingLength = this->process->getCurrentProcessingLength(); // The processing information

    // Determine if the process needs to put into CPU or IO
    if (processProcessingLength > 0) // CPU
    {
        // Get the event start time
        eventTime = this->simulation->cpuUnit->scheduleProcessingTime(this->process);

        // Create start CPU event and enqueue it to the simulation
        Simulation::StartCPUEvent::newStartCPUEvent(this->simulation, eventTime, this->process);
    }
    else // IO
    {
        // Get the event start time
        eventTime = this->simulation->ioUnit->scheduleProcessingTime(this->process);

        // Create start CPU event and enqueue it to the simulation
        Simulation::StartIOEvent::newStartIOEvent(this->simulation, eventTime, this->process);
    }
}
