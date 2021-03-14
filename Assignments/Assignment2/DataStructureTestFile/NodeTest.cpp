#include <iostream>
#include <cassert>
#include "Node.h"
using namespace std;

int main()
{
	// Local variable dictionary
	Node* testNode = new Node(nullptr);

	// With default constructor
	assert(testNode->getDatum() == nullptr);
	assert(testNode->getNext() == nullptr);
	assert(testNode->getPrev() == nullptr);

	// Change next and prev node reference
	testNode->setNext((Node*)1);
	testNode->setPrev((Node*)2);
	assert((int)testNode->getNext() == 1);
	assert((int)testNode->getPrev() == 2);

    // Release node memory
	delete testNode;
	
	// Return
	cout << "Program ends sucessfully." << endl;
	return 0;
}
