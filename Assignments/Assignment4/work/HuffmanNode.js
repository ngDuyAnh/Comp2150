
/*
Duy Anh Nguyen 7892957
April 13, 2021
HuffmanNode.js
class HuffmanNode
Node in HuffmanTree.

Private member:
#datum - The datum the node holds.
#weight - The weight of the node.
#left - Pointer to the left node.
#right - Pointer to the right node.

Public method:
constructor() - Constructor to create and initialize an instance.
datum() - Getter method to get the datum of the node.
weight() - Getter method to get the weight of the node.
left() - Getter method to get the left node.
right() - Getter method to get the right now.
compareTo() - Compare the weight between two nodes.
isLeaf() - Determine if the current node is a leaf node.
*/

class HuffmanNode
{
    // Private member
    #datum = null; // The datum the node holds
    #weight = 0.0; // The weight of the node.
    #left = null;  // Pointer to the left node.
    #right= null;  // Pointer to the right node.


    // Public method

    /* constructor()
    Constructor to create and initialize an instance.

    Parameter:
    DATUM - The datum the node holds.
    WEIGHT - The weight of the node.
    LEFT - The left node.
    RIGHT - The right node.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Too few arguments" - Given fewer arguments than the method need.
    "Invalid type" - The parameter type is incorrect.
    */
    constructor(DATUM, WEIGHT, LEFT, RIGHT)
    {
        if (arguments.length > 4)
        {
            throw new Error("Too many arguments");
        }
        else if (arguments.length < 1)
        {
            throw new Error("Too few arguments");
        }
        else if (DATUM !== null && !(typeof(DATUM) === "string" || DATUM instanceof String))
        {
            throw new Error("Invalid type");
        }
        else
        {
            this.#datum = DATUM;
            this.#weight = (WEIGHT !== undefined) ? WEIGHT : 0.0;
            this.#left = (LEFT !== undefined) ? LEFT : null;
            this.#right = (RIGHT !== undefined) ? RIGHT : null;
        }
    }

    /* datum()
    Getter method to get the datum of the node.

    Return:
    Datum the node holds.
    */
    datum()
    {
        return this.#datum;
    }

    /* weight()
    Getter method to get the weight of the node.

    Return:
    Weight of the node.
    */
    weight()
    {
        return this.#weight;
    }

    /* left()
    Getter method to get the left node.

    Return:
    Left node.
    */
    left()
    {
        return this.#left;
    }

    /* right()
    Getter method to get the right now.

    Return:
    Right node.
    */
    right()
    {
        return this.#right;
    }

    /* compareTo()
    Compare the weight between two nodes.

    Parameter:
    OTHER - The other node to compare to.

    Throws:
    "Too many arguments" - Given more arguments than the method can take.
    "Invalid type" - The parameter type is incorrect.

    Return:
    Integer if the current node weight is...
            -1 less than given node weight.
            0 equal to given node weight.
            1 same as the given node weight.
    */
    compareTo(OTHER)
    {
        // Local variable dictionary
        let compareResult = undefined;

        // Compare
        if (arguments.length > 1)
        {
            throw new Error("Too many arguments");
        }
        else if (!(OTHER instanceof HuffmanNode))
        {
            throw new Error("Invalid type");
        }
        else
        {
            if (this.#weight < OTHER.#weight)
            {
                compareResult = -1;
            }
            else if (this.#weight === OTHER.#weight)
            {
                // Compare using the datum or child node datum
                if (this.isLeaf() && OTHER.isLeaf())
                {
                    if (this.#datum < OTHER.#datum)
                    {
                        compareResult = -1;
                    }
                    else if (this.#datum > OTHER.#datum)
                    {
                        compareResult = 1;
                    }
                    else if (this.#datum === OTHER.#datum)
                    {
                        compareResult = 0;
                    }
                }
                else if (this.isLeaf() && !OTHER.isLeaf())
                {
                    // Keep the weight but move to the branch
                    const WEIGHT = OTHER.#weight;
                    let leftBranch = null;
                    let rightBranch = null;

                    // Compare again the left branch
                    leftBranch = OTHER.#left.#left;
                    rightBranch = OTHER.#left.#right;
                    compareResult = this.compareTo(
                        new HuffmanNode(OTHER.#left.#datum, WEIGHT, leftBranch, rightBranch));

                    // Compare the right branch
                    if (compareResult === 0)
                    {
                        leftBranch = OTHER.#right.#left;
                        rightBranch = OTHER.#right.#right;
                        compareResult = this.compareTo(
                            new HuffmanNode(OTHER.#left.#datum, WEIGHT, leftBranch, rightBranch));
                    }
                }
                // Traversal down one side first then the other automatically using the first condition
                else if (!this.isLeaf())
                {
                    // Keep the weight but move to the branch
                    const WEIGHT = OTHER.#weight;
                    let leftBranch = null;
                    let rightBranch = null;

                    // Compare the left branch
                    leftBranch = this.#left.#left;
                    rightBranch = this.#left.#right;
                    let leftNode = new HuffmanNode(this.#left.#datum, WEIGHT, leftBranch, rightBranch);
                    compareResult = leftNode.compareTo(OTHER);

                    // Compare the right branch
                    if (compareResult === 0)
                    {
                        leftBranch = this.#right.#left;
                        rightBranch = this.#right.#right;
                        let leftNode = new HuffmanNode(this.#right.#datum, WEIGHT, leftBranch, rightBranch);
                        compareResult = leftNode.compareTo(OTHER);
                    }
                }
            }
            else if (this.#weight > OTHER.#weight)
            {
                compareResult = 1;
            }
        }

        // Return the compare result
        return compareResult;
    }


    /* isLeaf()
    Determine if the current node is a leaf node.

    Return:
    A boolean flag if the node is a lead node.
    */
    isLeaf()
    {
        return (this.left() === null && this.right() === null);
    }

}

// Export
module.exports = HuffmanNode;
