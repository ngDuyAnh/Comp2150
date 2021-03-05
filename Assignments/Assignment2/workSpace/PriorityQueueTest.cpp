#include <iostream>
#include <cassert>
#include "PriorityQueue.h"
#include "Process.h"
using namespace std;

// Macro
#define NUM_INSERT 6
#define INSERT 5, 2, 1, 0, 3, 4

int main()
{
	// Local variable dictionary
	const int INSERT_ARRAY[] = { INSERT };
	PriorityQueue* testPriorityQueue = new PriorityQueue();

	// Insert into the priority queue
	for (int counter = 0; counter < NUM_INSERT; counter++)
	{
		// The number of data in the queue much match with count insert
		assert(testPriorityQueue->getLength() == counter);

		// Create process to insert
		Process* insertProcess = new Process(INSERT_ARRAY[counter], 0);

		// Insert input queue
		testPriorityQueue->enqueue(insertProcess);
	}

	// Pop the data from queue and check
	for (int counter = 0; counter < NUM_INSERT; counter++)
	{
		// The number of data in the queue much match with the count insert
		assert(testPriorityQueue->getLength() == NUM_INSERT - counter);

		// Get the datum
		ListItem* datum = testPriorityQueue->dequeue();

		// Check
		assert(datum->getValue() == counter);

		// Release memory
		delete datum;
	}

	// Debug mode check if 2 same value insert, the second should go to the back
	Process* insert0 = new Process(0, 0);
	Process* insert1 = new Process(0, 0);
	Process* insert2 = new Process(0, 0);
	testPriorityQueue->enqueue(insert0);
	testPriorityQueue->enqueue(insert1);
	testPriorityQueue->enqueue(insert2);

	// Release memory
	delete testPriorityQueue;

	// Return
	cout << "Program ends sucessfully." << endl;
	return 0;
}
