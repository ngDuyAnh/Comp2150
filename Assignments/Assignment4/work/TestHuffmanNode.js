let HuffmanNode = require("./HuffmanNode.js");

// Main

function main()
{
    // Constructor
    let left = new HuffmanNode("b", 1.0);
    let right = new HuffmanNode("c");
    let testNode = new HuffmanNode(null, 0.5, left, right);

    // datum()
    console.assert(left.datum === "b", "Left datum should be null.");
    console.assert(right.datum === "c", "Right datum should be null.");
    console.assert(testNode.datum === null, "TestNode datum should be null.");

    // weight()
    console.assert(left.weight === 1.0, "Left datum should be 1.0");
    console.assert(right.weight === 0.0, "Right datum should be 0.0");
    console.assert(testNode.weight === 0.5, "TestNode datum should be 0.5");

    // left()
    console.assert(left.left === null, "Left datum should be null.");
    console.assert(right.left === null, "Right datum should be null.");
    console.assert(testNode.left === left, "TestNode datum should be left.");

    // right()
    console.assert(left.right === null, "Left datum should be null.");
    console.assert(right.right === null, "Right datum should be null.");
    console.assert(testNode.right === right, "TestNode datum should be right.");

    // compareTo()
    console.assert(testNode.compareTo(left) === -1, "TestNode should be less than left node.");
    console.assert(left.compareTo(testNode) === 1, "Left should be less than TestNode.");

    // isLeaf()
    console.assert(testNode.isLeaf() === false, "Test node should not be a leaf node.");
    console.assert(left.isLeaf() === true, "Left node should be a leaf node.");
    console.assert(right.isLeaf() === true, "Right node should be a leaf node.");
}
main();
