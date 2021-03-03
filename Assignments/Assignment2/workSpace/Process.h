#pragma once

/*
Duy Anh Nguyen 7892957
March 3, 2021
Process.h
class Process
This class represents a process waiting to be process 
        by processing units.

Private member:
PROCESS_NUMBER - The process ID number.
PROCESS_ARRIVAL - The process arrival time.
processExit - The process exit time.
processWait - The amount of time the process has to wait 
        to get process.

Public method:
Process() - Constructor to create an instance of process.
        It will take the process ID number and arrival time.
setProcessExit() - Set the time the process exit.
addProcessWait() - Add process waiting time to be process.
printProcessInfo() - Print the process information to the 
        standard output.
*/

class Process
{
private:
    // Private member
    int PROCESS_NUMBER;   // The process number
    int PROCESS_ARRIVAL;  // The process arrival time
    int processExit = -1; // The process exit time
    int processWait = 0;  // The precess wait time to be process

public:
    // Public method
    Process(const int ID_NUMBER, const int ARRIVAL_TIME); // Constructor to create an instance
    void setProcessExit(const int EXIT_TIME);   // Set the time the process exit processing
    void addProcessWait(const int AMOUNT_TIME); // Add an amount of time the process wait
    void printProcessInfo();                    // Print the process information to std output
}
