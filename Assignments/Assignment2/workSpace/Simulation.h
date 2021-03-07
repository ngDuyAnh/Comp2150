#pragma once

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
~Simulation() - Deestructor to release memory of an instance.
runSimulation() - Activate the simulation. This is the kick start 
        switch to run the simulation. It will take in a file name 
        parameter for simulation input.
summary() - Print the summary of the simulation to standard output.
*/

// Forward declaration
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
    private:
        int eventTime = -1;                    // The time for event to be execute
        Process* process = nullptr;            // Pointer to the process

    public:
        // Public method
        Event(const int EVENT_TIME, Process* const process); // Constructor to create an instance of Event

        // Public override method
        int getValue() override; // Get the value to be able to compare

        // Public pure virtual method
        virtual void handleEvent() = 0; // Handle the evemt execute
    };



    /* class ArrivalEvent
    This represents the arrival event of a process.
    The next event is the process enter CPU or IO for processing.

    Public method:
    ArrivalEvent() - Constructor to create an instance.

    Public override method:
    handleEvent() - Handle the arrival event.
            The next event is start processing CPU or IO.
    */
    class ArrivalEvent : public Event
    {
    public:
        // Public method
        ArrivalEvent(const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle the arrival event
    };



    /* class StartCPUEvent
    This represents the start CPU event of a process.
    The next event is the process complete CPU processing or timeout.

    Public method:
    StartCPUEvent() - Constructor to create instance.

    Public override method:
    handleEvent() - Handle the start CPU event.
            The next event is complete CPU process or timeout.
    */
    class StartCPUEvent : public Event
    {
    public:
        // Public method
        StartCPUEvent(const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle  the start CPU event
    };



    /* class CompleteCPUEvent
    This represents the complete processing CPU event of a process.
    The next event is process exits if there are no more process or request 
            the next process from either CPU or IO.

    Public method:
    CompleteCPUEvent() - Constructor to create instance.

    Public override method:
    handleEvent() - Handle the complete CPU event.
            The next event is process exits or start the next CPU or IO.
    */
    class CompleteCPUEvent : public Event
    {
    public:
        // Public method
        CompleteCPUEvent(const int EVENT_TIME, Process* const process); // Constructor to create an instance

        // Public override method
        void handleEvent() override; // Handle the complete CPU event
    };



    /* class ExitProcessEvent
    This represents the exit process event.
    This is when the process is done all processing.
    The exist event will pop the process out of the processing and store it 
            to history for stats.

    Public method:
    ExitProcessEvent() - Constructor to create instance.

    Public override method:
    handleEvent() - Handle the process exits event.
    */
    class ExitProcessEvent : public Event
    {
    public:
        // Public method
        ExitProcessEvent(const int EVENT_TIME, Process* const process); // Constructor to create instance

        // Public override method
        void handleEvent() override; // Handle the process exits event
    };



    /* class StartIOEvent
    This represents the start IO event of a process.
    The next event is the process complete CPU processing.

    Public method:
    StartIOEvent() - Constructor to create an instance.

    Public override method:
    handleEvent() - Handle the start IO event.
            The next event is compelte IO process.
    */
    class StartIOEvent : public Event
    {
    public:
        // Public method
        StartIOEvent(const int EVENT_TIME, Process* const process); // Constructor to create an instance

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
        CompleteIOEvent(const int EVENT_TIME, Process* const process); // Constructor to create an instance

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
