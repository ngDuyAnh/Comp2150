#include <iostream>
#include <iomanip>
#include "Simulation.h"
#include "IO.h"
#include "Process.h"

/*
Duy Anh Nguyen 7892957
March 7, 2021
StartIOEvent.cpp
class StartIOEvent
*/


// Public static method

/* newStartIOEvent()
Create new event and enqueue to the event queue.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
void Simulation::StartIOEvent::newStartIOEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process)
{
    // Create the event
    StartIOEvent* const startIOEvent = new Simulation::StartIOEvent(simulation, EVENT_TIME, process);

    // Put the event into  event queue in simulation
    simulation->eventsQueue->enqueue(startIOEvent);
}



// Public method

/* StartIOEvent()
Constructor to create an instance.

Parameter:
simulation - The simulation the event is under.
EVENT_TIME- The time the event gets execute.
process - The process the event is handle.
*/
Simulation::StartIOEvent::StartIOEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process) : Simulation::Event::Event(simulation, EVENT_TIME, process)
{

}



// Public override method

/* handleEvent()
Handle the complete CPU event.
*/
void Simulation::StartIOEvent::handleEvent()
{
    // Local variable dictionary
    int eventTime = -1; // The time to execute the next event

    // Print message
    std::cout << "Time " << std::setw(3) << this->eventTime << ": ";
    std::cout << "Process " << std::setw(3) << this->process->getValue() << " ";
    std::cout << "begins IO burst.";
    std::cout << std::endl;

    // Calculate the time done processing
    eventTime = this->getValue() + this->process->getCurrentProcessingLength();

    // Schedule complete IO event
    Simulation::CompleteIOEvent::newCompleteIOEvent(this->simulation, eventTime, this->process);
}
