#include <iostream>
#include <iomanip>
#include "Process.h"
using namespace std;

/*
Duy Anh Nguyen 7892957
March 2, 2021
PriorityQueue.cpp
class PriorityQueue
*/

// Public method

/* Process()
Constructor to crate an instance of process.

Parameter:
ID_NUMBER - Process ID number.
ARRIVAL_TIME - The time the process arrival and request 
        process.
*/
Process::Process(const int ID_NUMBER, const int ARRIVAL_TIME)
{
    this->PROCESS_NUMBER = ID_NUMBER;
    this->PROCESS_ARRIVAL = ARRIVAL_TIME;
    this->processExit = -1;
    this->processWait = 0;
}



/* setProcessExit()
Set the time the process exit processing.

Parameter:
EXIT_TIME - The time the process done processing.
*/
void Process::setProcessExit(const int EXIT_TIME)
{
    this->processExit = EXIT_TIME;
}



/* addProcessWait()
Add an amount of time the process is waiting to get process.

Parameter:
AMOUNT_TIME - The amount of time the process is waiting to get process.
*/
void Process::addProcessWait(const int AMOUNT_TIME)
{
    this->processWait += AMOUNT_TIME;
}



/* printProcessInfo()
Print the process information to std output.
<Process ID> <Time arrival> <Exit time> <Wait time>
*/
void Process::printProcessInfo()
{
    cout << setw(10) << this->PROCESS_NUMBER;
    cout << setw(10) << this->PROCESS_ARRIVAL;
    cout << setw(10) << this->processExit;
    cout << setw(10) << this->processWait;
}



// Public override method

/* getValue()
Get hte value for compareTo() method.
For process, it is the process ID number.
*/
int Process::getValue()
{
    return this->PROCESS_NUMBER;
}
