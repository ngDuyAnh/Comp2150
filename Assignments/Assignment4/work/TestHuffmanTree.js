let HuffmanTree = require("./HuffmanTree.js");
let StringHash = require("./StringHash.js");

// Global test variable
let tree0 = null;
let tree1 = null;
let tree2 = null;


// Main
function main()
{
    // Constructor
    tree1 = new HuffmanTree("a", 0.1);
    tree2 = new HuffmanTree("b", 0.5);

    // compareTo()
    console.assert(tree1.compareTo(tree2) === -1, "Tree1 compare to tree2 should be -1.");
    console.assert(tree2.compareTo(tree1) === 1, "Tree 2 compare to tree 1 should be 1.");

    // Compare the same weight but different character value
    let sameLeft = new HuffmanTree("a", 0.5);
    let sameRight = new HuffmanTree("c", 0.5);
    console.assert(tree2.compareTo(sameLeft) === 1, "Tree2 compare should return 1.");
    console.assert(tree2.compareTo(sameRight) === -1, "Tree2 compare should return -1.");

    // Same value and character
    let same = new HuffmanTree("b", 0.5);
    console.assert(tree2.compareTo(same) === 0, "Tree2 compare should return 0.");

    // Static method combineTree()
    tree0 = HuffmanTree.combineTree(tree1, tree2);
    console.assert(tree0.compareTo(tree1) === 1, "Tree0 must be greater than tree1.");
    console.assert(tree0.compareTo(tree2) === 1, "Tree0 must be greater than tree2.");

    // Test the combined tree using getCode()
    console.assert(tree0.getCode(new StringHash("a")) === "0", "a should be to the left.");
    console.assert(tree0.getCode(new StringHash("b")) === "1", "b should be to the right.");

    // getCode() character that does not exist
    console.assert(tree0.getCode(new StringHash("c")) === null, "c should not exist.");

    // Test ends successfully
    console.log("Test ends successfully.");
}
main();
