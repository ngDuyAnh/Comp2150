#pragma once

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
cpuQueue - Process queue waiting to be process by the CPU.
ioQueue - Process queue waiting to be process by the IO.
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
Simulation() - Constructor to create an instance of the simulation.
runSimulation() - Activate the simulation. This is the kick start 
        switch to run the simulation. It will take in a file name 
        parameter for simulation input.
summary() - Print the summary of the simulation to standard output.
*/

// Forward dec;aration

class Simulation
{
private:
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
