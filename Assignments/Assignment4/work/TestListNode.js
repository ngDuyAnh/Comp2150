let assert = require("assert");
let ListNode = require("./ListNode.js");

// Global test variable
let datum = 1;
let failNode;
let node0;
let node1;
let node2


// Test function

/* testListNodeConstructor()
Initialize the test variables.
*/
function testListNodeConstructor()
{
    // Can not call constructor with more arguments than can handle
    try
    {
        failNode = new ListNode(null, null, null, null);
    }
    catch (e)
    {
    }
    console.assert(failNode == undefined, "Constructor with 4 arguments should be work.");

    // Initialize test nodes
    try
    {
        node0 = new ListNode();
        node1 = new ListNode(datum, null, node0);
        node2 = new ListNode(null, null, node1)
    }
    catch (e)
    {
        console.assert(false, "Could not initialize test nodes.")
    }
}

/* testListNodeInitializeTestVar()
Ensure that the test variables are what expected.
*/
function testListNodeInitializeTestVar()
{
    // Assert
    console.assert(node0 != null, "node0 is null.")
    console.assert(node1 != null, "node1 is null.")
    console.assert(node2 != null, "node2 is null.")

    // Assert node0
    console.assert(node0.datum == null, "node0 datum is not null.");
    console.assert(node0.nextNode == null, "node0 nextNode is not null.");
    console.assert(node0.prevNode == null, "node0 prevNode is not null.");

    // Assert node1
    console.assert(node1.datum == datum, "node0 datum is not datum.");
    console.assert(node1.nextNode == null, "node0 nextNode is not null.");
    console.assert(node1.prevNode == node0, "node0 prevNode is not node0.");

    // Assert node1
    console.assert(node1.datum == datum, "node0 datum is not datum.");
    console.assert(node1.nextNode == null, "node0 nextNode is not null.");
    console.assert(node1.prevNode == node0, "node0 prevNode is not node0.");

    // Initialize the test variables
    node0.setNextNode(node1);
    node1.setNextNode(node2);
    node2.setPrevNode(node1);

    // Assert
    console.assert(node0.nextNode == node1, "node0 next is not node1.");
    console.assert(node1.nextNode == node2, "node1 next is not node2.");
    console.assert(node2.prevNode == node1, "node2 previous is not node1.");
}


// Main test

function main()
{
    // Initialize the test variables
    testListNodeConstructor();

    // Ensure the test variables are what expected
    testListNodeInitializeTestVar();
}
main();
