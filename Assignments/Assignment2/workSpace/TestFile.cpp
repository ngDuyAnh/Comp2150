// Test setup
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

// Include
#include "PriorityQueue.h"
#include "ListItem.h"
#include "IntegerItem.h"

// Priority queue test
TEST_CASE("Priority Queue test enqueue.")
{
    // Local variable dictionary
    const int NUM_INSERT = 1000; // Number of datum insert
    PriorityQueue* const testPriorityQueue = new PriorityQueue(); // Priority queue instance use for testing

    // Enqueue data into the queue
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // Create integer container to insert into the queue
        IntegerItem* insertDatum = new IntegerItem(counter);

        // Insert to priority queue
        testPriorityQueue->enqueue(insertDatum);
    }

    // Release memory
    delete testPriorityQueue;
}

TEST_CASE("Priority Queue test dequeue.")
{
    // Local variable dictionary
    const int NUM_INSERT = 1000; // Number of datum insert
    PriorityQueue* const testPriorityQueue = new PriorityQueue(); // Priority queue instance use for testing

    // Enqueue data into the queue
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // Create integer container to insert into the queue
        IntegerItem* insertDatum = new IntegerItem(counter);

        // Insert to priority queue
        testPriorityQueue->enqueue(insertDatum);
    }

    // Dequeue data from the queue
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // Get the dequeue datum
        ListItem* const datum = testPriorityQueue->dequeue();

        // Release memory of datum
        delete datum;
    }

    // Release memory
    delete testPriorityQueue;
}

TEST_CASE("Priority Queue test getLength().")
{
    // Local variable dictionary
    const int NUM_INSERT = 1000; // Number of datum insert
    PriorityQueue* const testPriorityQueue = new PriorityQueue(); // Priority queue instance use for testing

    // Initial condition
    REQUIRE(testPriorityQueue->getLength() == 0);

    // Add 1000 elements to the priority queue
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // The number of data in the queue must match with count
        REQUIRE(testPriorityQueue->getLength() == counter);

        // Create integer container to insert into the queue
        IntegerItem* insertDatum = new IntegerItem(counter);

        // Insert to priority queue
        testPriorityQueue->enqueue(insertDatum);
    }

    // Expected number of data in the priority queue
    REQUIRE(testPriorityQueue->getLength() == NUM_INSERT);

    // Pop the data from queue and check
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // The number of data in the priority queue must match with the count insert
        REQUIRE(testPriorityQueue->getLength() == (NUM_INSERT - counter));

        // Get the dequeue datum
        ListItem* const datum = testPriorityQueue->dequeue();

        // Release memory of datum
        delete datum;
    }

    // Expected number of data in the priority queue
    REQUIRE(testPriorityQueue->getLength() == 0);

    // Release memory of the queue
    delete testPriorityQueue;
}

TEST_CASE("Priority Queue test sorting datum correctly. Simple insert.")
{
    // Local variable dictionary
    const int NUM_INSERT = 1000; // Number of datum insert
    PriorityQueue* const testPriorityQueue = new PriorityQueue(); // Priority queue instance use for testing

    // Initial condition
    REQUIRE(testPriorityQueue->getLength() >= 0);

    // Add 1000 elements to the priority queue
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // The number of data in the queue must match with count
        REQUIRE(testPriorityQueue->getLength() == counter);

        // Create integer container to insert into the queue
        IntegerItem* insertDatum = new IntegerItem(counter);

        // Insert to priority queue
        testPriorityQueue->enqueue(insertDatum);
    }

    // Expected number of data in the priority queue
    REQUIRE(testPriorityQueue->getLength() == NUM_INSERT);

    // Pop the data from queue and check
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // The number of data in the priority queue must match with the count insert
        REQUIRE(testPriorityQueue->getLength() == (NUM_INSERT - counter));

        // Get the dequeue datum
        ListItem* const datum = testPriorityQueue->dequeue();

        // Check if the datum is expected
        REQUIRE(datum->getValue() == counter);

        // Release memory of datum
        delete datum;
    }

    // Expected number of data in the priority queue
    REQUIRE(testPriorityQueue->getLength() == 0);

    // Release memory of the queue
    delete testPriorityQueue;
}

TEST_CASE("Priority Queue test sorting datum correctly. Complex insert.")
{
    // Local variable dictionary
    const int NUM_INSERT = 10; // Number of datum insert
    const int UNSORTED_INSERT[] = {5,6,8,9,4,1,5,0,2,5};
    const int SORTED_INSERT[] = {0,1,2,4,5,5,5,6,8,9};
    PriorityQueue* const testPriorityQueue = new PriorityQueue(); // Priority queue instance use for testing

    // Initial condition
    REQUIRE(testPriorityQueue->getLength() >= 0);

    // Add 1000 elements to the priority queue
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // The number of data in the queue must match with count
        REQUIRE(testPriorityQueue->getLength() == counter);

        // Create integer container to insert into the queue
        IntegerItem* insertDatum = new IntegerItem(UNSORTED_INSERT[counter]);

        // Insert to priority queue
        testPriorityQueue->enqueue(insertDatum);
    }

    // Expected number of data in the priority queue
    REQUIRE(testPriorityQueue->getLength() == NUM_INSERT);

    // Pop the data from queue and check
    for (int counter = 0; counter < NUM_INSERT; counter++)
    {
        // The number of data in the priority queue must match with the count insert
        REQUIRE(testPriorityQueue->getLength() == (NUM_INSERT - counter));

        // Get the dequeue datum
        ListItem* const datum = testPriorityQueue->dequeue();

        // Check if the datum is expected
        REQUIRE(datum->getValue() == SORTED_INSERT[counter]);

        // Release memory of datum
        delete datum;
    }

    // Expected number of data in the priority queue
    REQUIRE(testPriorityQueue->getLength() == 0);

    // Release memory of the queue
    delete testPriorityQueue;
}
