let Hashable = require("./Hashable.js");

/*
Duy Anh Nguyen 7892957
April 13, 2021
IntHash.js
class IntHash
Wrap class of integer key.

Public method:
constructor() - Constructor to create and initialize an instance.

Public override method:
hashVal() - Getter method to get value to be use for hash.
equals() - Compare the wrap key if they are the same.
*/

class IntHash extends Hashable
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
        else if (Number.isInteger(KEY))
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
        return super.key;
    }

    /* equals()
    Compare the wrap key if they are the same.

    Throws:
    "Invalid type" - The parameter type is incorrect.

    Return:
    A boolean flag if the key of the two wrap instance are the same.
    */
    equals(OTHER)
    {
        // Local variable dictionary
        let flag = false; // Boolean flag if the two keys are the same

        // Determine if the two keys are the same
        if (OTHER instanceof IntHash)
        {
            flag = super.equals(OTHER);
        }
        else
        {
            throw new Error("Invalid type");
        }

        // Return
        return flag;
    }

}

// Export
module.exports = IntHash;
