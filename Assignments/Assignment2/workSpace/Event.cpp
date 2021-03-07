#include "Simulation.h"

/*
Duy Anh Nguyen 7892957
March 2, 2021
Event.cpp
class Event
*/

// Public method

/* Event()
Constructor to create an instance of Event.

Parameter:
simulation - The simulation the event is for.
EVENT_TIME - The time of the event to be execute.
process - The process under the event.
*/
Simulation::Event::Event(Simulation* simulation, const int EVENT_TIME, Process* const process)
{
    this->eventTime = EVENT_TIME;
    this->process = process;
}



// Public override method

/* getValue()
Get the value to compare.
For event, it is the event time.

Return:
Return the event execute time.
*/
int Simulation::Event::getValue()
{
    return this->eventTime;
}
