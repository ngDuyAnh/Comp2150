#include <iostream>
#include <cassert>
#include "Queue.h"
using namespace std;

// Macro
#define MAX_INSERT 100

int main()
{
	// Local variable dictionary
	Queue* const testQueue = new Queue(); // Queue use for testing

	// Insert from 0 to constant MAX_INSERT
	for (int counter = 0; counter < MAX_INSERT; counter++)
	{
		// The number of data in the queue much match with count insert
		assert(testQueue->getLength() == counter);

		// Insert input queue
		testQueue->enqueue((ListItem*)(counter));
	}

	// Pop the data from queue and check
	for (int counter = 0; counter < MAX_INSERT; counter++)
	{
		// The number of data in the queue much match with the count insert
		assert(testQueue->getLength() == MAX_INSERT - counter);

		// Get the datum
		const int DATUM = (int)testQueue->dequeue();

		// Check
		assert(DATUM == counter);
	}

	// Return
	cout << "Program ends sucessfully." << endl;
	return 0;
}
