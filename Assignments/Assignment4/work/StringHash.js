let Hashable = require("./Hashable.js");

/*
Duy Anh Nguyen 7892957
April 13, 2021
StringHash.js
class StringHash

Wrap class of string key.

Public method:
constructor() - Constructor to create and initialize an instance.

Public override method:
hashVal() - Getter method to get the value to be use for hash.
equals() - Compare the wra[ leu if they are the same.
*/

// Constant variable
const PRIME_NUMBER = 3; // The prime number use to create the hash value.

class StringHash extends Hashable
{
    // Public method

    /* constructor()
    Constructor to create and initialize an instance.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.

    Parameter:
    KEY - The key that is getting wrap.
    */
    constructor(KEY)
    {
        // Determine if the value given as key is an integer
        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else if (typeof(KEY) === "string" || KEY instanceof String)
        {
            super(KEY);
        }
        else
        {
            throw new Error("Invalid type");
        }
    }


    // Public override method

    /* hashVal()
    Getter method to get value to be use for hash.

    Return:
    Hash value that is the key.
    */
    hashVal()
    {
        // Local variable dictionary
        let hashValResult = 0;

        // Generate the hash value
        let length = super.key.length;
        for (let counter = 0; counter < length; counter++)
        {
            hashValResult +=
                    super.key.charCodeAt(counter) * Math.pow(PRIME_NUMBER, length - 1 - counter);
        }

        // Return the hash value
        return hashValResult;
    }

    /* equals()
    Compare the wrap key if they are the same.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.

    Return:
    A boolean flag if the key of the two wrap instance are the same.
    */
    equals(OTHER)
    {
        // Local variable dictionary
        let flag = false; // Boolean flag if the two keys are the same

        // Determine if the two keys are the same
        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else if (!(OTHER instanceof StringHash))
        {
            throw new Error("Invalid type");
        }
        else
        {
            flag = super.equals(OTHER);
        }

        // Return
        return flag;
    }

}

// Export
module.exports = StringHash;
