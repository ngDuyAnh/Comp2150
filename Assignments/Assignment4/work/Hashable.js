
/*
Duy Anh Nguyen 7892957
April 13, 2021
Hashable.js
class Hashable
Abstract class to wrap around a value to calculate the hash code.

Private member:
#key - The key that got wrap.

Public method:
key() - Getter method to get the value of the key.
hashVal() - Getter method to get value to be use for hash.
equals() - Compare the wrap key if they are the same.
*/

class Hashable
{
    // Private member
    #key = undefined; // The key that got wrap


    // Public method

    /* constructor()
    Constructor does not exist in abstract class.
    Initialize the key wrap value.

    Parameter:
    KEY - The key that is getting wrap.

    Throws:
    "Cannot create an instance of abstract class" - Failed to create an
            instance of abstract class.
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    */
    constructor(KEY)
    {
        if (new.target === Hashable)
        {
            throw new Error("Cannot create an instance of abstract class");
        }
        else if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else
        {
            this.#key = KEY;
        }
    }

    /* key()
    Getter method to get the value of the key.

    Return:
    The key.
    */
    get key()
    {
        return this.#key;
    }

    /* hashVal()
    The value to be use for hash.

    Throws:
    "Missing implementation of hashVal() in concrete class" - Abstract class cannot be
            call.
    */
    get hashVal()
    {
        throw new Error("Missing implementation of hashVal() in concrete class");
    }

    /* equals()
    Compare the wrap key if they are the same.

    Parameter:
    OTHER - The other instance to compare to.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.

    Return:
    A boolean flag if the key of the two wrap instance are the same.
    */
    equals(OTHER)
    {
        // local variable dictionary
        let flag = false; // Boolean flag if the two keys are the same

        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else
        {
            if (this.#key === OTHER.key)
            {
                flag = true;
            }
        }

        // Return the flag if the two instance are equal
        return flag;
    }

}

// Export
module.exports = Hashable;
