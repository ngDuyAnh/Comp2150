#include "Event.h"
#include "Process.h"

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
EVENT_TIME - The time of the event to be execute.
EVENT_TAG - The type of event to handle.
PROCESS - The process under the event.
*/
Event::Event(const int EVENT_TIME, const EventType EVENT_TAG, Process* const process)
{
    this->eventTime = EVENT_TIME;
    this->eventTag = EVENT_TAG;
    this->process = process;
}



// Public override method

/* getValue()
Get the value to compare.
For event, it is the event time.

Return:
Return the event execute time.
*/
int Event::getValue()
{
    return this->eventTime;
}
