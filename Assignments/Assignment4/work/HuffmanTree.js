let LinkedList = require("./LinkedList.js");
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

Private static method:
nodeTraversal() - Recursive method to traversal to find the path to a given character.

Public method:
constructor() - Constructor to create and initialize an instance.
compareTo() - Compare the two trees weight.
        If the weight of two trees are equal, compare the letter to determine
        which of the two is lower.
getCode() - Get the code of given character from the tree.
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
        // Local variable dictionary
        let tree = null; // The combine tree
        let rootWeight = TREE0.#rootNode.weight() + TREE1.#rootNode.weight();

        // Combine the tree
        if (TREE0.compareTo(TREE1) === -1 || TREE0.compareTo(TREE1) === 0)
        {
            tree = new HuffmanTree(null, rootWeight, TREE0.#rootNode, TREE1.#rootNode);
        }
        else if (TREE0.compareTo(TREE1) === 1)
        {
            tree = new HuffmanTree(null, rootWeight, TREE1.#rootNode, TREE0.#rootNode);
        }

        // Return the combined tree
        return tree;
    }


    // Private static method

    /* nodeTraversal()
    Recursive method to traversal to find the path to a given character.

    Parameter:
    CHARACTER - The character searching for.
    PARENT - The parent node to traverse from.
    pathList - The path encode.

    Return:
    A boolean flag if the path is encode for the character is found.
    */
    static #nodeTraversal(CHARACTER, PARENT, pathList)
    {
        // Local variable dictionary
        let charFound = false; // Found the character

        // The leaf node contain compare character
        if (PARENT.isLeaf())
        {
            if (PARENT.datum() === CHARACTER)
            {
                charFound = true;
            }
        }
        // Traversal to reach the leaf node
        else
        {
            // Traversal to the left
            pathList.append("0");
            charFound = HuffmanTree.#nodeTraversal(CHARACTER, PARENT.left(), pathList);

            // Traversal to the right
            if (charFound === false)
            {
                // Remove and replace path to the right
                pathList.removeTail();
                pathList.append("1");
                charFound = HuffmanTree.#nodeTraversal(CHARACTER, PARENT.right(), pathList);
            }
        }

        // Return if the character path found
        return charFound;
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
                if (this.#rootNode.datum() < OTHER.#rootNode.datum())
                {
                    compareResult = -1;
                }
                else if (this.#rootNode.datum() > OTHER.#rootNode.datum())
                {
                    compareResult = 1;
                }
                else if (this.#rootNode.datum() === OTHER.#rootNode.datum())
                {
                    compareResult = 0;
                }
            }
        }

        // Return the compare result
        return compareResult;
    }

    /* getCode()
    Get the code of given character from the tree.

    Parameter:
    CHARACTER - The given character to find the path.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    */
    getCode(CHARACTER)
    {
        // Local variable dictionary
        let path = null; // The path encode of the character

        // Get the character encode
        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else if (!(typeof(CHARACTER) === "string" || CHARACTER instanceof String))
        {
            throw new Error("Invalid type");
        }
        else
        {
            // Local variable dictionary
            let pathList = new LinkedList(); // List to keep track of the path

            // Traversal to find the path
            let pathFound = HuffmanTree.#nodeTraversal(CHARACTER, this.#rootNode, pathList);

            // Get the path
            if (pathFound === false)
            {
                path = null;
            }
            else
            {
                // Initialize the path
                path = "";

                // Get the path from the list
                while (!pathList.isEmpty())
                {
                    path += pathList.removeHead();
                }
            }
        }

        // Return the path for the character
        return path;
    }

}

// Export
module.exports = HuffmanTree;
