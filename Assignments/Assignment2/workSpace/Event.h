#pragma once

#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
March 2, 2021
Event.h
class Event
This represents an event waiting to be execute.

Private member:
eventTime - The time for the event to be execute.
eventTag - The type of event.
eventProcess - Pointer to the process.


Public method:
Event() - constructor to create an instance of Event.
getValue() - The the value to compare in compareTo().
        For class Event, it is the event time.
*/

// Forward declaration
class Process;

class Event: public ListItem {
private:
    int eventTime = -1;           // The time for event to be execute
    EventType eventTag = nullptr; // The type of event
    Process *process = nullptr;   // Pointer to the process

public:

    // Public data type

    /* enum EventType
    Tag for the type of events.

    Enumerator:
    PROCESS_ARRIVAL - The arrival of a process waiting to be process.
    PROCESS_EXIST - Done processing the process.
    PROCESS_START_CPU - Process enter CPU processing.
    PROCESS_COMPLETE_CPU - Process done CPU processing.
    PROCESS_TIMEOUT_CPU - Process reaches the limit of CPU resource.
    PROCESS_START_IO - Process enter IO processing.
    PROCESS_COMPLETE_IO - Process done IO processing.
    */
    enum EventType
    {
        PROCESS_ARRIVAL = 0, 
        PROCESS_EXIST, 
        PROCESS_START_CPU, 
        PROCESS_COMPLETE_CPU, 
        PROCESS_TIMEOUT_CPU, 
        PROCESS_START_IO, 
        PROCESS_COMPLETE_IO
    };

    // Public method
    Event(const int EVENT_TIME, const EventType EVENT_TAG, const Process* const PROCESS); // Constructor
            // to create an instance of Event

    // Public override method
    int getValue(const ListItem* const OTHER) override; // Get the value 
            // to be able to compare
};
