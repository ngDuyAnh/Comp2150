let HuffmanNode = require("./HuffmanNode.js");

/*
Duy Anh Nguyen 7892957
April 14, 2021
HuffmanTree.js
class HuffmanTree
Huffman binary tree.

Private member:
#rootNode - The root node of the Huffman binary tree.

Public static method:
combineTree() - Combine two Huffman trees into one.
        New tree will have a new root node that is the sum weight of the sub-trees.

Public method:
constructor() - Constructor to create and initialize an instance.
compareTo() - Compare the two trees weight.
        If the weight of two trees are equal, compare the letter to determine
        which of the two is lower.
*/

class HuffmanTree
{
    // Private member
    #rootNode = null; // The root node of the Huffman binary tree.


    // Public static method

    /* combineTree()
    Combine two Huffman trees into one.
    New tree will have a new root node that is the sum weight of the sub-trees.

    Parameter:
    TREE0 - The first tree.
    TREE1 - The second tree.
    */
    static combineTree(TREE0, TREE1)
    {
        return null;
    }


    // Public method

    /* constructor()
    Constructor to create and initialize an instance.

    Parameter:
    DATUM - The datum the root node holds.
    WEIGHT - The weight of the root node.
    LEFT - Left branch of the root node.
    RIGHT - Right branch of the root node.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    */
    constructor(DATUM, WEIGHT, LEFT, RIGHT)
    {
        if (arguments.length > 4)
        {
            throw new Error("Too many arguments");
        }
        else
        {
            this.#rootNode = new HuffmanNode(DATUM, WEIGHT, LEFT, RIGHT);
        }
    }

    /* compareTo()
    Compare the two trees weight.
    If the weight of two trees are equal, compare the letter to determine
            which of the two is lower.

    Parameter:
    OTHER - The other tree to compare to.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.

    Return:
    A boolean flag that is if the two trees weight and letter and the game.
    */
    compareTo(OTHER)
    {
        // Local variable dictionary
        let compareResult = undefined;

        // Determine the compare value
        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else if (!(OTHER instanceof HuffmanTree))
        {
            throw new Error("Invalid type");
        }
        else
        {
            // Return -1
            if (this.#rootNode.compareTo(OTHER.#rootNode) === -1)
            {
                compareResult = -1;
            }
            // Return 1
            else if (this.#rootNode.compareTo(OTHER.#rootNode) === 1)
            {
                compareResult = 1;
            }
            // The two nodes weight are the same
            else if (this.#rootNode.compareTo(OTHER.#rootNode) === 0)
            {
                // Use the character to determine the compare value
                if (this.#rootNode.datum < OTHER.#rootNode.datum)
                {
                    compareResult = -1;
                }
                else if (this.#rootNode.datum > OTHER.#rootNode.datum)
                {
                    compareResult = 1;
                }
                else if (this.#rootNode.datum === OTHER.#rootNode.datum)
                {
                    compareResult = 0;
                }
            }
        }

        // Return the compare result
        return compareResult;
    }
}

// Export
module.exports = HuffmanTree;
