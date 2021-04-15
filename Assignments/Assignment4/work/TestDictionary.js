let assert = require("assert");
let Dictionary = require("./Dictionary.js");
let StringHash = require("./StringHash.js");

// Global variable dictionary
const HASH_TABLE_SIZE = 5;
let dictionary = null;
let key = new StringHash("string");
let value = 1;


// Test function

/* testDictionaryConstructor()
Test dictionary constructor to create an instance of dictionary.
*/
function testDictionaryConstructor()
{
    // Create dictionary without parameter
    try
    {
        dictionary = new Dictionary();
    }
    catch (e)
    {
    }
    assert(dictionary == null, "Dictionary constructor without parameter should not work.");

    // Create dictionary with too many parameter
    try
    {
        dictionary = new Dictionary(HASH_TABLE_SIZE, null)
    }
    catch (e)
    {
    }
    assert(dictionary == null, "Dictionary constructor with too many parameter should not work.");

    // Create dictionary with invalid type parameter
    try
    {
        dictionary = new Dictionary(null);
    }
    catch (e)
    {
    }
    assert(dictionary == null, "Dictionary constructor with invalid type parameter should not work.");

    // Create a dictionary
    dictionary = new Dictionary(HASH_TABLE_SIZE);
    assert(dictionary != null, "Dictionary with correct parameter did not work.");
}

/* testDictionaryPut()
Test put() method.
*/
function testDictionaryPut()
{
    // Too many arguments
    try
    {
        dictionary.put(key, value, null);
    }
    catch (e)
    {
    }
    assert(dictionary.isEmpty(), "Should not be able to create an instance with too many parameter.");

    // Too few arguments
    try
    {
        dictionary.put(key);
    }
    catch (e)
    {
    }
    assert(dictionary.isEmpty(), "Should not be able to create an instance with too few parameter.");

    // Invalid type parameter
    try
    {
        dictionary.put(value, key);
    }
    catch (e)
    {
    }
    assert(dictionary.isEmpty(), "Should not be able to create an instance with invalid type parameter.");

    // Valid insert
    dictionary.put(key, value);

    // Check if the key is in dictionary
    assert(dictionary.contains(key), "Key should already exist in dictionary.");

    // Key already exist in the dictionary
    let pass = false;
    try
    {
        dictionary.put(key, 0);
    }
    catch (e)
    {
        pass = true;
    }
    assert(pass, "Should not be able to create an instance with too many parameter.");
}

/* testDictionaryGet()
Testing get() method.
*/
function testDictionaryGet()
{
    // Get value that does exist
    assert(dictionary.get(key).value === value, "The key should match the value.");

    // Get value that does not exist
    let invalidHash = new StringHash("Does not exist.");
    console.assert(dictionary.get(invalidHash) === null, "Key does not exist should return null.");
}

/* testDictionaryContains()
Testing contains() method.
*/
function testDictionaryContains()
{
    // Given valid key
    assert(dictionary.contains(key) === true, "The dictionary should contain the key.");

    // Given key that does not exist in the dictionary
    let invalidHash = new StringHash("Does not exist.");
    assert(dictionary.contains(invalidHash) === false, "The dictionary should not contain the key.");
}

/* testDictionaryIsEmpty()
Test isEmpty() method.
*/
function testDictionaryIsEmpty()
{
    assert(dictionary.isEmpty() === false, "The dictionary should not be empty.");
}

// Main

function main()
{
    // Constructor
    testDictionaryConstructor();
    assert(dictionary.isEmpty(), "The dictionary should be empty.");

    // put()
    testDictionaryPut();

    // get()
    testDictionaryGet();

    // contains()
    testDictionaryContains();

    // isEmpty()
    testDictionaryIsEmpty();
}
main();
