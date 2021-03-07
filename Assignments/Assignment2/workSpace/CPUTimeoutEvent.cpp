#include "Simulation.h"
#include "CPU.h"
#include "Process.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
CompleteCPUEvent.cpp
class CompleteCPUEvent
*/

// Public static method

/* newCPUTimeoutEvent()
Create new event and enqueue to the event queue.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
void Simulation::CPUTimeoutEvent::newCPUTimeoutEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process)
{
    // Create the event
    CPUTimeoutEvent* const cpuTimeoutEvent = new Simulation::CPUTimeoutEvent(simulation, EVENT_TIME, process);

    // Put the event into  event queue in simulation
    simulation->eventsQueue->enqueue(cpuTimeoutEvent);
}



// Public method

/* CPUTimeoutEvent()
Constructor to create an instance.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
Simulation::CPUTimeoutEvent::CPUTimeoutEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process) : Simulation::Event::Event(simulation, EVENT_TIME, process)
{

}



// Public override method

/* handleEvent()
Handle the complete CPU event.
*/
void Simulation::CPUTimeoutEvent::handleEvent()
{
    // Local variable dictionary
    int eventTime = -1; // The time to execute the next event

    // Calculate the new processing time need
    const int CURRENT_REQUEST_TIME = this->process->getCurrentProcessingLength();
    const int NEW_REQUEST_TIME = CURRENT_REQUEST_TIME - this->simulation->cpuUnit->getRestrictiveTime();

    // Get the new processing time need
    this->process->setCurrentProcessingLenght(NEW_REQUEST_TIME);

    // Enqueue to the event queue to start CPU
    Simulation::StartCPUEvent::newStartCPUEvent(this->simulation, this->getValue(), this->process);
}
