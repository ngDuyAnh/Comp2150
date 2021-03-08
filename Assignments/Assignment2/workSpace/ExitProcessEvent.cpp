#include <iostream>
#include <iomanip>
#include "Simulation.h"
#include "Process.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
ExitProcessEvent.cpp
class ExitProcessEvent
*/

// Public static method

/* newExitProcessEvent()
Create new event and enqueue to the event queue.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
void Simulation::ExitProcessEvent::newExitProcessEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process)
{
    // Create the event
    ExitProcessEvent* const exitProcessEvent = new Simulation::ExitProcessEvent(simulation, EVENT_TIME, process);

    // Put the event into  event queue in simulation
    simulation->eventsQueue->enqueue(exitProcessEvent);
}



// Public method

/* ExitProcessEvent()
Constructor to create an instance.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
Simulation::ExitProcessEvent::ExitProcessEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process) : Simulation::Event::Event(simulation, EVENT_TIME, process)
{
    std::cout << "Time " << std::setw(3) << this->eventTime << ": ";
    std::cout << "Process " << std::setw(3) << this->process->getValue() << " ";
    std::cout << "Process done, exit.";
}



// Public override method

/* handleEvent()
Handle the complete CPU event.
*/
void Simulation::ExitProcessEvent::handleEvent()
{
    // Put the process into the process history queue to save stats
    this->simulation->processHistory->enqueue(this->process);
}
