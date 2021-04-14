let Hashable = require("./Hashable.js");

/*
Duy Anh Nguyen 7892957
April 13, 2021
HashEntry.js
class HashEntry is a container to hold the key and value
        pair for hashing.

Private member:
#key - The key of the hash entry.
#value - The value of the hash entry.

Public method:
constructor() - Constructor to create and initialize an instance.
key() - Getter method to get the key.
value() - Getter method to get the value.
hashValue() - Getter method to get the value use for hashing.
*/

class HashEntry
{
    // Private member
    #key = null;   // Key
    #value = null; // Value


    // Public method

    /* constructor()
    Constructor to create and initialize an instance.

    Parameter:
    KEY - The key of the hash entry.
    VALUE - The value of the hash entry.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    */
    constructor(KEY, VALUE)
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
            this.#key = KEY;
            this.#value = VALUE;
        }
    }

    /* key()
    Getter method to get the key.

    Return:
    The key.
    */
    get key()
    {
        return this.#key.key;
    }

    /* value()
    Getter method to get the value.

    Return:
    The value.
    */
    get value()
    {
        return this.#value;
    }

    /* hashValue()
    Getter method to get the value use for hashing.

    Return:
    The hashing value.
    */
    get hashVal()
    {
        return this.#key.hashVal();
    }

}

// Export
module.exports = HashEntry;
