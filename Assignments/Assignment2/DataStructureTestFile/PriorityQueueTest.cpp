#include <iostream>
#include <cassert>
#include "PriorityQueue.h"
#include "IntegerItem.h"
using namespace std;

// Macro
#define NUM_INSERT 6
#define INSERT 5, 2, 1, 0, 3, 4

int main()
{
	// Local variable dictionary
	const int INSERT_ARRAY[] = { INSERT };
	PriorityQueue* testPriorityQueue = new PriorityQueue();

	// Print
	std::cout << "Testing the priority queue." << std::endl;

	// Insert into the priority queue normal conditions
	for (int counter = 0; counter < NUM_INSERT; counter++)
	{
		// The number of data in the queue much match with count insert
		assert(testPriorityQueue->getLength() == counter);

		// Create process to insert
		IntegerItem* insertProcess = new IntegerItem(INSERT_ARRAY[counter]);

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
	IntegerItem* insert0 = new IntegerItem(0);
	IntegerItem* insert1 = new IntegerItem(0);
	IntegerItem* insert2 = new IntegerItem(0);
	testPriorityQueue->enqueue(insert0);
	testPriorityQueue->enqueue(insert1);
	testPriorityQueue->enqueue(insert2);

	// Release memory
	delete testPriorityQueue;

	// Return
	cout << "Program ends sucessfully." << endl;
	return 0;
}
