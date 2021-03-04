#pragma once

#include "ListItem.h"

/*
Duy Anh Nguyen 7892957
March 1, 2021
Simulation.h
class Simulation
This class will simulate the process events.
It will have representation handle to the CPU and IO to process.

Private static member:
processCount - Track count of the number of process created.

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

Private data type:
class Event - Represents an event to be simulate.
class ArrivalEvent - Represents an arrival of a process event of a process.
class StartCPUEvent - Represents a start CPU processing event of a process.
class CompleteCPUEvent - Represents a complete CPU event of a process.
class ExitProcessEvent - Represents an exit process event. Process done processing.
class StartIOEvent - Represents a start IO processing event of a process.
class CompleteIOEvent - Represents a complete IO event of a process.

Private method:
arrival() - Get the next event to process and get the next 
        event from input file.
processExit() - Process is done processing, remove it from the 
        processing queue. The process will get push into history 
        queue for final statistics.
startCPU() - Process execute on CPU.
        Calculate the simulation and create the even accordingly.
        The first event is process finish the CPU burst required.
                An event CompleteCPU is created as a result.
        The second even is process timeout because it required 
                more time that what the process allow to give.
                The TimeoutCPU event is created to put the process 
                back to the queue for more CPU burst time.
completeCPU() - Process complete CPU time and ready for next event.
        If the process is done, no further actions required.
                The process can call exit.
        If the process required IO processing, this method will 
                schedule IO event.
TimeoutCPU() - When the process is timeout because it required more 
        processing time that the CPU can give. The process will get push back into the CPU queue for more processing time.
startIO() - Process execute on IO.
        Calculate the simulation and create the even accordingly.
        This process is not time share so it does not get timeout.
        This will schedule a completeIO event right away.
completeIO() - Process complete IO time and ready for next event.
        If the process is done, no further actions required.
                The process can call exit.
        If the process required CPU processing, this method will 
                schedule CPU event.

Public:
Simulation() - Constructor to create an instance of the simulation.
runSimulation() - Activate the simulation. This is the kick start 
        switch to run the simulation. It will take in a file name 
        parameter for simulation input.
summary() - Print the summary of the simulation to standard output.
*/

// Forward declaration
class Process;

// Data type

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
enum class EventType
{
    EMPTY = 0,
    PROCESS_ARRIVAL,
    PROCESS_EXIST,
    PROCESS_START_CPU,
    PROCESS_COMPLETE_CPU,
    PROCESS_TIMEOUT_CPU,
    PROCESS_START_IO,
    PROCESS_COMPLETE_IO
};

class Simulation
{
private:

    // Private data type

    /* class Event
    This represents an event waiting to be execute.

    Private member:
    eventTime - The time for the event to be execute.
    eventTag - The type of event.
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
    private:
        int eventTime = -1;                    // The time for event to be execute
        EventType eventTag = EventType::EMPTY; // The type of event
        Process* process = nullptr;            // Pointer to the process

    public:
        // Public method
        Event(const int EVENT_TIME, const EventType EVENT_TAG,
            Process* const process); // Constructor to create an instance of Event

        // Public override method
        int getValue() override; // Get the value to be able to compare

        // Public pure virtual method
        virtual void handleEvent() = 0; // Handle the evemt execute
    };



    /* class ArrivalEvent
    This represents the arrival event of a process.
    The next event is the process enter CPU or IO for processing.

    Public override method:
    handleEvent() - Handle the arrival event.
            The next event is start processing CPU or IO.
    */
    class ArrivalEvent : public Event
    {
    public:
        void handleEvent() override; // Handle the arrival event
    };



    /* class StartCPUEvent
    This represents the start CPU event of a process.
    The next event is the process complete CPU processing or timeout.

    Public override method:
    handleEvent() - Handle the start CPU event.
            The next event is complete CPU process or timeout.
    */
    class StartCPUEvent : public Event
    {
    public:
        void handleEvent() override; // Handle  the start CPU event
    };



    /* class CompleteCPUEvent
    This represents the complete processing CPU event of a process.
    The next event is process exits if there are no more process or request 
            the next process from either CPU or IO.

    Public override method:
    handleEvent() - Handle the complete CPU event.
            The next event is process exits or start the next CPU or IO.
    */
    class CompleteCPUEvent : public Event
    {
    public:
        void handleEvent() override; // Handle the complete CPU event
    };



    /* class ExitProcessEvent
    This represents the exit process event.
    This is when the process is done all processing.
    The exist event will pop the process out of the processing and store it 
            to history for stats.

    Public override method:
    handleEvent() - Handle the process exits event.
    */
    class ExitProcessEvent : public Event
    {
    public:
        void handleEvent() override; // Handle the process exits event
    };



    /* class StartIOEvent
    This represents the start IO event of a process.
    The next event is the process complete CPU processing.

    Public override method:
    handleEvent() - Handle the start IO event.
            The next event is compelte IO process.
    */
    class StartIOEvent : public Event
    {
    public:
        void handleEvent() override; // Handle the start IO event
    };



    /* class CompleteIOEvent
    This represents the complete processing IO event of a process.
    The next event is process exits if there is no more process or request 
            the next process of either CPU or IO.
    */
    class CompleteIOEvent : public Event
    {
    public:
        void handleEvent() override; // Handle the complete IO event
    };








   
    
    // Private member
    const ProcessingUnit CPU_UNIT; // Representation handle to the CPU
    const ProcessingUnit IO_UNIT;   // Representation handle to the IO
    PriorityQueue* processHistory; // History of processes got processed
    PriorityQueue* eventsQueue; // Queue holds comming events of the simulation

    // Private method

    /* arrival()
    Get the next event to process and get the next event from input file.
    */

    /* departure()
    Process is done processing.
    Remove it from the processing queue.
    */




public:
    // Public method
	Simulation();  // Default constructor to create an instance
    ~Simulation(); // Default destructor to delete the instance
	void runSimulation(char *fileName); // Activate the simulation
	void summary(); // Print the summary of the simulation
};
