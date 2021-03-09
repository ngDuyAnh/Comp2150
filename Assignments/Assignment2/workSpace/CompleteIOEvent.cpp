#include <iostream>
#include <iomanip>
#include "Simulation.h"
#include "CPU.h"
#include "IO.h"
#include "Process.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
CompleteIOEvent.cpp
class CompleteIOEvent
*/

// Public static method

/* newCompleteIOEvent()
Create new event and enqueue to the event queue.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
void Simulation::CompleteIOEvent::newCompleteIOEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process)
{
    // Create the event
    CompleteIOEvent* const completeIOEvent = new Simulation::CompleteIOEvent(simulation, EVENT_TIME, process);

    // Put the event into event queue in simulation
    simulation->ioUnit->pQueue->enqueue(completeIOEvent);
}



// Public method

/* CompleteIOEvent()
Constructor to create an instance.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
Simulation::CompleteIOEvent::CompleteIOEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process) : Simulation::Event::Event(simulation, EVENT_TIME, process)
{

}



// Public override method

/* handleEvent()
Handle the complete CPU event.
*/
void Simulation::CompleteIOEvent::handleEvent()
{
    // Local variable dictionary
    int eventTime = -1; // The time to execute the next event

    // Print message
    std::cout << "Time " << std::setw(3) << this->eventTime << ": ";
    std::cout << "Process " << std::setw(3) << this->process->getValue() << " ";
    std::cout << "completes IO burst.";
    std::cout << std::endl;
    
    // Set the time if the CPU and IO are behind in time, it means that they are free
    if (this->simulation->cpuUnit->getTimeAvailable() < this->getValue())
    {
        this->simulation->cpuUnit->setTime(this->getValue());
    }
    
    // Process is done or have CPU burst
    if (this->process->getQueueLength() == 1) // THe last one is done
    {
        // The process is done
        eventTime = this->getValue(); // Current time

        // Schedule the process for exit
        Simulation::ExitProcessEvent::newExitProcessEvent(this->simulation, eventTime, this->process);
    }
    else // Process CPU
    {
        // Get the next processing request
        this->process->getNextProcessingLength();

        // Get the time to start process IO
        eventTime = this->simulation->cpuUnit->scheduleProcessingTime(this->process);

        // Create start IO event and enqueue it to the simulation
        Simulation::StartCPUEvent::newStartCPUEvent(this->simulation, eventTime, this->process);

        // Update the process wait time
        const int PROCESS_WAIT = eventTime - this->getValue(); // Wait is processing time - current time
        this->process->addProcessWaitTime(PROCESS_WAIT);
    }
}
