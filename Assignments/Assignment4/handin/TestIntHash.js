let IntHash = require("./IntHash.js");

// Global variable dictionary
let test = null;
let same = null;
let different = null;


// Test function

/* testIntHashConstructor()
Test IntHash constructor to create an instance of IntHash.
*/
function testIntHashConstructor()
{
    // Parameter is not a number
    try
    {
        test = new IntHash("not number");
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not be able to initialize when the parameter is not a number.");

    // Too many arguments
    try
    {
        test = new IntHash(0, null);
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not be able to create an instance with too many arguments.");

    // Too few arguments
    try
    {
        test = new IntHash();
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not be able to create an instance with too few arguments.");

    // Initialize with number
    test = new IntHash(0);
    same = new IntHash(0);
    different = new IntHash(1);
}

/* testIntHashValue()
Test the hashVal() method.
*/
function testIntHashValue()
{
    console.assert(test.hashVal() === 0, "Hash value of test is incorrect.");
    console.assert(same.hashVal() === 0, "Hash value of same is incorrect.");
    console.assert(different.hashVal() === 1, "Hash value of different is incorrect.");
}

/* testIntHashEquals()
Test the equals() method.
*/
function testIntHashEquals()
{
    console.assert(test.equals(same), "test and same should be the same.");
    console.assert(test.equals(different) === false, "test and different should be different.");
}


// Main

function main()
{
    // Constructor
    testIntHashConstructor();

    // hashVal()
    testIntHashValue();

    // equals()
    testIntHashEquals();
}
main();
