let StringHash = require("./StringHash.js")

// Global variable dictionary
let test = null;
let same = null;
let different = null;


// Test function

/* testStringHashConstructor()
Test constructor.
*/
function testStringHashConstructor()
{
    // Too many arguments
    try
    {
        test = new StringHash("string0", null);
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not be able to create an instance with too many arguments.");

    // Too few arguments
    try
    {
        test = new StringHash();
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not be able to create an instance with too few arguments.");

    // Parameter is not a string
    try
    {
        test = new StringHash(0);
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not be able to create an instance with parameter is not a string.");

    // Initialize with string
    test = new StringHash("string0");
    same = new StringHash("string0");
    different = new StringHash("string1");
}

/* testStringHashValue()
Test the hashVal() method.
*/
function testStringHashValue()
{
    // Get the hash values
    let testHash = test.hashVal();
    let sameHash = same.hashVal();
    let differentHash = different.hashVal();

    // Assert
    console.assert(testHash === sameHash, "test and same hash should be the same.");
    console.assert(testHash !== differentHash, "test and different has should be different.");
}

/* testStringHashEquals()
Test the equals() method.
*/
function testStringHashEquals()
{
    console.assert(test.equals(same), "test and same should be the same.");
    console.assert(test.equals(different) === false, "test and different should be different.");
}


// Main

function main()
{
    // Constructor
    testStringHashConstructor();

    // hashVal()
    testStringHashValue();

    // equals()
    testStringHashEquals();
}
main();
