let LinkedList = require("./LinkedList.js")

// Global variable dictionary
let linkedList = null;


// Test function

/* testLinkedListConstructor()
Test linked list constructor to create an instance of linked list.
*/
function testLinkedListConstructor()
{
    // Create the linked list
    linkedList = new LinkedList();

    // Assert
    console.assert(linkedList != null, "Failed to create a linked list.");
}

/* testLinkedListAddElement()
Test adding elements to the linked list.
*/
function testLinkedListAddElement()
{
    // The linked list is empty
    console.assert(linkedList.isEmpty(), "The linked list is not empty.");

    // Add an element at index less than 0
    try
    {
        linkedList.insertIndex(null, -1);
    }
    catch (e)
    {
    }
    console.assert(linkedList.isEmpty(), "Datum given index less than 0 got added.");

    // Add am element at index out of bounds
    try
    {
        linkedList.insertIndex(null, 1);
    }
    catch (e)
    {
    }
    console.assert(linkedList.isEmpty(), "Datum given index out of bounds got added.");

    // Given too few arguments
    try
    {
        linkedList.insertIndex(null);
    }
    catch (e)
    {
    }
    console.assert(linkedList.isEmpty(), "Few arguments got added.");

    // Given too many arguments
    try
    {
        linkedList.insertIndex(null, 0, null);
    }
    catch (e)
    {
    }
    console.assert(linkedList.isEmpty(), "Too many arguments got added.");

    // Insert the right index, index 0
    linkedList.insertIndex(3, 0);
    console.assert(!linkedList.isEmpty() && linkedList.getLength() === 1,
        "Failed to add datum at correct index 0.");

    // Prepend one element
    linkedList.prepend(2);
    console.assert(linkedList.getLength() === 2);

    // Prepend multiple elements
    linkedList.prepend(1, 0);
    console.assert(linkedList.getLength() === 4);

    // Append one element
    linkedList.append(4);
    console.assert(linkedList.getLength() === 5);

    // Append multiple element
    linkedList.append(5, 6);
    console.assert(linkedList.getLength() === 7)
}

/* testLinkedListPeekElement()
Test peeking elements in the linked list.
*/
function testLinkedListPeekElement()
{
    // Peek head
    console.assert(linkedList.peekHead() === 0, "Datun at index 0 is incorrect.");

    // Peek tail
    console.assert(linkedList.peekTail() === 6, "Datum at tail is incorrect.");

    // Peek index
    for (let counter = 0; counter < linkedList.getLength(); counter++)
    {
        console.assert(linkedList.peekIndex(counter) === counter,
            "Value not equal: counter=" + counter + " value=" + linkedList.peekIndex(counter));
    }
}

/* testLinkedListRemoveElement()
Test remove elements in the linked list.
*/
function testLinkedListRemoveElement()
{
    // Remove head
    console.assert(linkedList.removeHead() === 0, "Datum at head is not 0.");

    // Remove tail
    console.assert(linkedList.removeTail() === 6, "Datum at tail is not 6.");

    // Remove index
    let count = 1;
    while (!linkedList.isEmpty())
    {
        // Get the datum
        let datum = linkedList.removeIndex(0);

        // Assert
        console.assert(datum === count, "Expected datum=" + datum + " count=" + count);

        // Update count
        count++;
    }
}


// Main

function main()
{
    // Constructor
    testLinkedListConstructor();

    // Test adding datum to the linked list
    testLinkedListAddElement();

    // Test peek datum from the linked list
    testLinkedListPeekElement()

    // Test remove datum from the linked list
    testLinkedListRemoveElement();
}
main();
