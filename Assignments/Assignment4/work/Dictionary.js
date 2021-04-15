let LinkedList = require("./LinkedList.js");
let HashEntry = require("./HashEntry.js");
let Hashable = require("./Hashable.js");

/*
Duy Anh Nguyen 7892957
April 12, 2021
Dictionary.js
class Dictionary
Dictionary abstract data type.

Private member:
table - The hash table to track data.

Public method:
constructor() - Constructor to create and initialize an instance.
keys() - Get all the keys in dictionary.
put() - Add the key and value pair to the dictionary.
get() - Get the value with given key.
contains() - Return a boolean flag if the given key exists in the dictionary.
isEmpty() - Return a boolean flag if the dictionary is empty.
*/

class Dictionary
{
    // Private member
    #table = null; // The hash table to track data


    // Public method

    /* constructor()
    Constructor to create and initialize an instance.

    Parameter:
    SIZE - The given hash table size of the dictionary.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    */
    constructor(SIZE)
    {
        if (arguments.length > 1)
        {
            throw "Too many arguments";
        }
        else if (arguments.length === 0)
        {
            throw "Too few arguments";
        }
        else if (!Number.isInteger(SIZE))
        {
            throw "Invalid type";
        }
        else
        {
            // Initialize the hash table
            this.#table = new LinkedList();
            for (let counter = 0; counter < SIZE; counter++)
            {
                this.#table.append(new LinkedList());
            }
        }
    }

    /* keys()
    Get all the keys in dictionary.

    Return:
    Linked list of all the keys in dictionary.
    */
    keys()
    {
        // Local variable dictionary
        let keys = new LinkedList();

        // Get the keys
        for (let counter = 0; counter < this.#table.getLength(); counter++)
        {
            // Get the index linked list
            let listIndex = this.#table.peekIndex(counter);

            // Get the keys in the list index
            for (let count = 0; count < listIndex.getLength(); count++)
            {
                // Get the key and value pair
                let hashEntry = listIndex.peekIndex(count);
                keys.append(hashEntry.key);
            }
        }

        // Return the keys
        return keys;
    }

    /* put()
    Add the key and value pair to the dictionary.

    Parameter:
    KEY - The key.
    VALUE - The value.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    "Key already exist" - The given key already exist in the dictionary.
    */
    put(KEY, VALUE)
    {
        if (arguments.length > 2)
        {
            throw "Too many arguments";
        }
        else if (arguments.length < 2)
        {
            throw "Too few arguments";
        }
        else if (!(KEY instanceof Hashable))
        {
            throw new Error("Invalid type");
        }
        else
        {
            // Check if the key already exist in the dictionary
            if (this.contains(KEY))
            {
                throw new Error("Key already exist");
            }
            else
            {
                // Local variable dictionary
                let hashEntry = new HashEntry(KEY, VALUE); // The hash pair
                let hashIndex = KEY.hashVal % this.#table.getLength();

                // Insert the entry into the dictionary
                this.#table.peekIndex(hashIndex).append(hashEntry);
            }
        }
    }

    /* get()
    Get the value with given key.

    Parameter:
    KEY - The key to find the value.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.

    Return:
    The value correlate to the given key.
    */
    get(KEY)
    {
        // Local variable dictionary
        let value = undefined; // The value pair of the key

        // Find the key and value pair
        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else if (!(KEY instanceof Hashable))
        {
            throw new Error("Invalid type");
        }
        else
        {
            // Local variable dictionary
            let hashIndex = KEY.hashVal % this.#table.getLength();

            // Find if the key exists in the table and get the value
            let listIndex = this.#table.peekIndex(hashIndex);
            for (let counter = 0; counter < listIndex.getLength() && value === undefined; counter++)
            {
                // Get the hash entry
                let hashEntry = listIndex.peekIndex(counter);

                // Check if the has entry is the key to get the value
                if (hashEntry.key.equals(KEY))
                {
                    value = hashEntry.value;
                }
            }
        }

        // Return the value pair of the key
        return value;
    }

    /* contains()
    Return a boolean flag if the given key exists in the dictionary.

    Parameter:
    KEY - The key to find the value.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.

    Return:
    A boolean flag if the given key exists in the dictionary.
    */
    contains(KEY)
    {
        // Local variable dictionary
        let keyFound = false; // Flag if found the key in dictionary

        // Find the key
        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else if (!(KEY instanceof Hashable))
        {
            throw new Error("Invalid type");
        }
        else
        {
            // Local variable dictionary
            let hashIndex = KEY.hashVal % this.#table.getLength();
            console.log(hashIndex);

            // Find if the key exists in the table and get the value
            let listIndex = this.#table.peekIndex(hashIndex);
            for (let counter = 0; counter < listIndex.getLength() && keyFound === false; counter++)
            {
                // Get the hash entry
                let hashEntry = listIndex.peekIndex(counter);
            }
        }

        // Return flag if the key found
        return keyFound;
    }

    /* isEmpty()
    Return a boolean flag if the dictionary is empty.

    Return:
    Flag if the dictionary is empty.
    */
    isEmpty()
    {
        // Local variable dictionary
        let empty = true;

        // See if the dictionary has data
        for (let counter = 0; counter < this.#table.lenght && empty; counter++)
        {
            if (!this.#table[counter].isEmpty())
            {
                empty = false;
            }
        }

        // Return
        return empty;
    }

}

// Export
module.exports = Dictionary;
