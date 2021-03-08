#pragma once

#include "PriorityQueue.h"
#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
March 1, 2021
Simulation.h
class Simulation
This class will simulate the process events.
It will have representation handle to the CPU and IO to process.

Private data type:
class Event - Represents an event to be simulate.
class ArrivalEvent - Represents an arrival of a process event of a process.
class StartCPUEvent - Represents a start CPU processing event of a process.
class CompleteCPUEvent - Represents a complete CPU event of a process.
class CPUTimeoutEvent - Represents a timeout CPU event of a process.
class ExitProcessEvent - Represents an exit process event. Process done processing.
class StartIOEvent - Represents a start IO processing event of a process.
class CompleteIOEvent - Represents a complete IO event of a process.

Private member:
CPU_UNIT - Representation handle to the CPU.
        This will help us calculate the amount of time it will
        take to processing a given process.
IO_UNIT - Representation handle to the IO.
        This will help us calculate the amount of time it will
        take to processing a given process.
processHistory - Track of all the processes got processed.
        This priority queue will organize the history with
        arrival time.
eventsQueue - Track of simulation events.
        This priority queue will organize the events with
        the time of when the event can be execute.

Public:
~Simulation() - Deestructor to release memory of an instance.
runSimulation() - Activate the simulation. This is the kick start 
        switch to run the simulation. It will take in a file name 
        parameter for simulation input.
summary() - Print the summary of the simulation to standard output.
*/

// Forward declaration
class string;
class Process;
class PriorityQueue;
class CPU;
class IO;

class Simulation
{
private:

    // Private data type

    /* class Event
    This represents an event waiting to be execute.

    Private member:
    eventTime - The time for the event to be execute.
    eventProcess - Pointer to the process.

    Public method:
    Event() - constructor to create an instance of Event.

    Public override method:
    getValue() - The value use in compareTo() method.
            For event, this value will be the eventTime member.

    Public pure virtual method:
    handleEvent() - Handle the current event request.
    */
    class Event : public ListItem
    {
    protected:
        Simulation* simulation = nullptr; // Pointer to the simulation
        int eventTime = -1;               // The time for event to be execute
        Process* process = nullptr;       // Pointer to the process

    public:
        // Public method
        Event(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create an instance of Event

        // Public override method
        int getValue() override; // Get the value to be able to compare

        // Public pure virtual method
        virtual void handleEvent() = 0; // Handle the evemt execute
    };



    /* class ArrivalEvent
    This represents the arrival event of a process.
    The next event is the process enter CPU or IO for processing.
    
    Public static method:
    newArrivalEvent() - Create new event and enqueue to the the event queue.

    Public method:
    ArrivalEvent() - Constructor to create an instance.

    Public override method:
    handleEvent() - Handle the arrival event.
            The next event is start processing CPU or IO.
    */
    class ArrivalEvent : public Event
    {
    public:
        // Public static method
        static void newArrivalEvent(Simulation* const simulation, string processLine); // Create new event and enqueue it to the event queue

        // Public method
        ArrivalEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle the arrival event
    };



    /* class StartCPUEvent
    This represents the start CPU event of a process.
    The next event is the process complete CPU processing or timeout.

    Public static method:
    newStartCPUEvent() - Create new event and enqueue to the event queue.

    Public method:
    StartCPUEvent() - Constructor to create instance.

    Public override method:
    handleEvent() - Handle the start CPU event.
            The next event is complete CPU process or timeout.
    */
    class StartCPUEvent : public Event
    {
    public:
        // Public static method
        static void newStartCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Create new event and enqueue it to the event queue

        // Public method
        StartCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle  the start CPU event
    };



    /* class CompleteCPUEvent
    This represents the complete processing CPU event of a process.
    The next event is process exits if there are no more process or request 
            the next process from either CPU or IO.

    Public static method:
    newCompleteCPUEvent() - Create new event and enqueue to the event queue.

    Public method:
    CompleteCPUEvent() - Constructor to create instance.

    Public override method:
    handleEvent() - Handle the complete CPU event.
            The next event is process exits or start the next CPU or IO.
    */
    class CompleteCPUEvent : public Event
    {
    public:
        // Public static method
        static void newCompleteCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Create new event and enqueue to the event queue.

        // Public method
        CompleteCPUEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle the complete CPU event
    };



    /* class CPUTimeoutEvent
    Represents a timeout CPU event of a process.
    The next event is start CPU again to get more time to complete process.

    Public static method:
    newCPUTimeoutEvent() - Create new event and enqueue to the event queue.

    Public method:
    CPUTimeoutEvent() - Constructor to create instance.

    Public override method:
    handleEvent() - Handle the complete CPU event.
            The next event is process exits or start the next CPU or IO.
    */
    class CPUTimeoutEvent : public Event
    {
    public:
        // Public static method
        static void newCPUTimeoutEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Create new event and enqueue to the event queue.

        // Public method
        CPUTimeoutEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle the complete CPU event
    };



    /* class ExitProcessEvent
    This represents the exit process event.
    This is when the process is done all processing.
    The exist event will pop the process out of the processing and store it 
            to history for stats.

    Public static method:
    newExitProcessEvent() - Create new event and enqueue to the event queue.

    Public method:
    ExitProcessEvent() - Constructor to create instance.

    Public override method:
    handleEvent() - Handle the process exits event.
    */
    class ExitProcessEvent : public Event
    {
    public:
        // Public static method
        static void newExitProcessEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Create new event and enqueue to the event queue.

        // Public method
        ExitProcessEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create instance

        // Public override method
        void handleEvent() override; // Handle the process exits event
    };



    /* class StartIOEvent
    This represents the start IO event of a process.
    The next event is the process complete CPU processing.

    Public static method:
    newStartIOEvent() - Create new event and enqueue it to the event queue.

    Public method:
    StartIOEvent() - Constructor to create an instance.

    Public override method:
    handleEvent() - Handle the start IO event.
            The next event is compelte IO process.
    */
    class StartIOEvent : public Event
    {
    public:
        // Public static method
        static void newStartIOEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Create new event and enqueue it to the event queue

        // Public method
        StartIOEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle the start IO event
    };



    /* class CompleteIOEvent
    This represents the complete processing IO event of a process.
    The next event is process exits if there is no more process or request 
            the next process of either CPU or IO.

    Public method:
    CompleteIOEvent() - Constructor to create an instnace.

    Public override method:
    handleEvent() - Handle the complete IO event.
            The next event is start CPU if have or exit if no more 
            processing is require.
    */
    class CompleteIOEvent : public Event
    {
    public:
        // Public method
        CompleteIOEvent(Simulation* const simulation, const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle the complete IO event
    };


    
    // Private member
    CPU* cpuUnit = nullptr; // Representation handle to the CPU
    IO* ioUnit = nullptr;   // Representation handle to the IO
    PriorityQueue* processHistory = nullptr; // History of processes got processed
    PriorityQueue* eventsQueue = nullptr;    // Queue holds comming events of the simulation

public:
    // Public method
    ~Simulation(); // Default destructor to delete the instance
	void runSimulation(const char * INPUT_FILE_NAME); // Activate the simulation
	void summary(); // Print the summary of the simulation
};
