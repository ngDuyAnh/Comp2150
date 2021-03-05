#include <iostream>
#include <cassert>
#include "Process.h"
using namespace std;

// Macro
#define PROCESS_ID 1
#define PROCESS_ARRIVE 2
#define PROCESS_EXIT 4
#define PROCESS_WAIT 1

int main()
{
	// Local variable dictionary
	Process* testProcess = new Process(PROCESS_ID, PROCESS_ARRIVE);

	// Check process ID is getValue()
	assert(testProcess->getValue() == PROCESS_ID);

	// Print stats
	cout << "Should output: " << PROCESS_ID << " " << PROCESS_ARRIVE << " " << -1 << " " << 0 << endl;
	testProcess->printProcessInfo();
	cout << endl;

	// Change stats
	testProcess->setProcessExit(PROCESS_EXIT);
	testProcess->addProcessWait(PROCESS_WAIT);

	// Print stats
	cout << "Should output: " << PROCESS_ID << " " << PROCESS_ARRIVE << " " << PROCESS_EXIT << " " << PROCESS_WAIT << endl;
	testProcess->printProcessInfo();
	cout << endl;

	// Release memory of process
	delete testProcess;

	// Return
	cout << "Program ends sucessfully." << endl;
	return 0;
}
