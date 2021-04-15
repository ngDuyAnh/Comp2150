let IntHash = require("../IntHash.js");
let HashEntry = require("../HashEntry.js");

// Global variable dictionary
let test = null;


// Test function

/* testHashEntryConstructor()
Test constructor.
*/
function testHashEntryConstructor()
{
    // Too many arguments
    try
    {
        test = new HashEntry(new IntHash(0), 1, null);
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not create an instance with too many arguments.");

    // Too few arguments
    try
    {
        test = new HashEntry(new IntHash(0));
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not create an instance with too few arguments.");

    // Invalid type
    try
    {
        test = new HashEntry(0, 1);
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not create an instance with invalid key.");

    // Initialize with valid parameter
    test = new HashEntry(new IntHash(0), 1);
}


// Main

function main()
{
    // Constructor
    testHashEntryConstructor();

    // key
    console.assert(test.key.key === 0, "The key should be 0.");

    // value
    console.assert(test.value === 1, "The value should be 1.");

    // hashValue()
    console.assert(test.hashVal() === 0, "The hash value should be 0.");
	
	// Test ends successfully
	console.log("Test ends successfully.");
}
main();
