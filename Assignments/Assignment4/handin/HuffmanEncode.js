let fs = require("fs");
let HuffmanTree = require("./HuffmanTree.js");
let LinkedList = require("./LinkedList.js");
let Dictionary = require("./Dictionary.js");
let StringHash = require("./StringHash.js");

/*
Duy Anh Nguyen 7892957
April 13, 2021
HuffmanEncode.js
class HuffmanEncode
Encode to compress a text file.
Implementation of Huffman Encoding using HuffmanTree and
        dictionary.

Public static method:
encodeFile() - Encode a given file name.

Private static method:
#buildTree() - Build the Huffman tree with character input file.
*/

// Constant variable
const DICTIONARY_SIZE = 26; // The size of the dictionary use in Huffman encode

class HuffmanEncode
{
    // Public static method

    /* encodeFile()
    Encode a given file name.

    Parameter:
    FILE_INPUT_NAME - The name of the input file to encode.
    FILE_OUTPUT_NAME - The name of the output file.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    */
    static encodeFile(FILE_INPUT_NAME, FILE_OUTPUT_NAME)
    {
        if (arguments.length > 2)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 2)
        {
            throw new Error("Too few arguments");
        }
        else if (!((typeof(FILE_INPUT_NAME) === "string" || FILE_INPUT_NAME instanceof String)
                && (typeof(FILE_OUTPUT_NAME) === "string") || FILE_OUTPUT_NAME instanceof String))
        {
            throw new Error("Invalid type");
        }
        else
        {
            // Local variable dictionary
            let dictionary = new Dictionary(DICTIONARY_SIZE); // Track of the characters in input file

            // Read the input file
            const INPUT_DATA = fs.readFileSync(FILE_INPUT_NAME, "utf-8");

            // Character frequency count
            for (let counter = 0; counter < INPUT_DATA.length; counter++)
            {
                // Get the character
                let characterHash = new StringHash(INPUT_DATA.charAt(counter));

                // Record the count
                if (dictionary.contains(characterHash))
                {
                    dictionary.get(characterHash).value = dictionary.get(characterHash).value + 1;
                }
                else
                {
                    dictionary.put(characterHash, 1);
                }
            }

            // Build the Huffman tree
            let huffmanTree = HuffmanEncode.#buildTree(dictionary, INPUT_DATA.length);

            // Use the tree to output the encode of the input file
            for (let counter = 0; counter < INPUT_DATA.length; counter++)
            {
                // Get the character to encode
                let characterHash = new StringHash(INPUT_DATA.charAt(counter));

                // Get the encode for the character
                let encode = huffmanTree.getCode(characterHash);

                // Write the encode to the output file
                fs.appendFileSync(FILE_OUTPUT_NAME, encode);
            }
        }
    }


    // Private static method

    /* buildTree()
    Build a tree with given character frequency.

    Parameter:
    DICTIONARY - The dictionary of the character frequency.
    STRING_LENGTH - The length of the string.
    */
    static #buildTree(DICTIONARY, STRING_LENGTH)
    {
        // Local variable dictionary
        let treeList = new LinkedList(); // Tracking the frequency from low to high

        // Get all the unique keys and values to build the tree
        let keys = DICTIONARY.keys();
        let values = new LinkedList();
        for (let counter = 0; counter < keys.getLength(); counter++)
        {
            values.append(DICTIONARY.get(new StringHash(keys.peekIndex(counter))).value);
        }

        // Put the keys and values to Huffman Tree list for combine into 1 tree
        console.assert(keys.getLength() === values.getLength(), "HuffmanEncode. buildTree(). Number of keys and values are not the same.");
        for (let counter = 0; counter < keys.getLength(); counter++)
        {
            // Get the information
            const DATUM = keys.peekIndex(counter);
            const WEIGHT = (values.peekIndex(counter) * 1.0) / STRING_LENGTH;

            // Create tree and put it into the track list
            treeList.append(new HuffmanTree(DATUM, WEIGHT));
        }

        // Build the tree
        while (treeList.getLength() > 1)
        {
            // Sort the tree list from low to high weight
            for (let index = 0; index < treeList.getLength(); index++)
            {
                let minIndex = index;
                let minTree = treeList.peekIndex(index);
                for (let counter = index + 1; counter < treeList.getLength(); counter++)
                {
                    // Smaller tree found
                    if (minTree.compareTo(treeList.peekIndex(counter)) === 1)
                    {
                        minIndex = counter;
                        minTree = treeList.peekIndex(counter);
                    }
                }

                // Swap
                if (treeList.peekIndex(index) !== minTree)
                {
                    let temp = treeList.removeIndex(index);
                    treeList.insertIndex(minTree, index);
                    treeList.removeIndex(minIndex);
                    treeList.insertIndex(temp, minIndex);
                }
            }

            // Take the lowest two tree and combine them together
            let tree0 = treeList.removeHead();
            let tree1 = treeList.removeHead();
            tree0.compareTo(tree1);
            let combineTree = HuffmanTree.combineTree(tree0, tree1);

            // Insert the new tree into the track list
            treeList.append(combineTree);
        }

        // Assert
        console.assert(treeList.getLength() === 1, "The last tree should ");

        // Return the final combined tree
        return treeList.removeHead();
    }

}

// Export
module.exports = HuffmanEncode;
