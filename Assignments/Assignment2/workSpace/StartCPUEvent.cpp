#include "Simulation.h"
#include "CPU.h"
#include "Process.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
StartCPUEvent.cpp
class StartCPUEvent
*/

// Public static method

/* newStartCPUEvent()
Create new event and enqueue to the event queue.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
void Simulation::StartCPUEvent::newStartCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process)
{
    // Create the event
    StartCPUEvent* const startCPUEvent = new Simulation::StartCPUEvent(simulation, EVENT_TIME, process);

    // Put the event into event queue in simulation
    simulation->eventsQueue->enqueue(startCPUEvent);
}



// Public method

/* StartCPUEvent()
Constructor to create an instance.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
Simulation::StartCPUEvent::StartCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process) : Simulation::Event::Event(simulation, EVENT_TIME, process)
{

}



// Public override method

/* handleEvent()
Handle the start CPU event.
*/
void Simulation::StartCPUEvent::handleEvent()
{
    // Local variable dictionary
    int eventEndTime = -1; // The time done processing this event

    // Determine the time done processing
    if (this->process->getCurrentProcessingLength() < this->simulation->cpuUnit->getRestrictiveTime())
    {
        // Time done processing
        eventEndTime = this->getValue() + this->process->getCurrentProcessingLength();

        // Schedule done processing event and enqueue it to the event queue
        Simulation::CompleteCPUEvent::newCompleteCPUEvent(this->simulation, eventTime, this->process);
    }
    else // Timeout
    {
        // Time till timeout
        eventEndTime = this->getValue() + this->simulation->cpuUnit->getRestrictiveTime();

        // Schedule done processing event and enqueue it to the event queue
        Simulation::CPUTimeoutEvent::newCPUTimeoutEvent(this->simulation, eventTime, this->process);
    }
}
