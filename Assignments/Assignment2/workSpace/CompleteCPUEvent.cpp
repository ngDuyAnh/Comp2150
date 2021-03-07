#include "Simulation.h"
#include "CPU.h"
#include "IO.h"
#include "Process.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
CompleteCPUEvent.cpp
class CompleteCPUEvent
*/

// Public static method

/*
*/
void Simulation::CompleteCPUEvent::newCompleteCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process)
{
    // Create the event
    CompleteCPUEvent* const completeCPUEvent = new Simulation::CompleteCPUEvent(simulation, EVENT_TIME, process);

    // Put the event into event queue in simulation
    simulation->eventsQueue->enqueue(completeCPUEvent);
}



// Public method

/* CompleteCPUEvent()
Constructor to create an instance.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
Simulation::CompleteCPUEvent::CompleteCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process) : Simulation::Event::Event(simulation, EVENT_TIME, process)
{

}



// Public override method

/* handleEvent()
Handle the complete CPU event.
*/
void Simulation::CompleteCPUEvent::handleEvent()
{
    // Local variable dictionary
    int eventTime = -1; // The time to execute the next event

    // Determine if the process is done or need to go to IO and process
    if (this->process->doneProcessing()) // Process exit
    {
        // The process is done
        eventTime = this->getValue(); // Current time

        // Schedule the process for exit
        Simulation::CompleteCPUEvent::newCompleteCPUEvent(this->simulation, eventTime, this->process);
    }
    else // Process IO
    {
        // Get the next processing request
        this->process->getNextProcessingLength();

        // Get the time to start process IO
        eventTime = this->simulation->ioUnit->scheduleProcessingTime(this->process);

        // Create start IO event and enqueue it to the simulation
        Simulation::StartIOEvent::newStartIOEvent(this->simulation, eventTime, this->process);
    }
}
