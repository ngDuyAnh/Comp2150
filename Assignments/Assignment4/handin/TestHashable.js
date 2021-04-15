let Hashable = require("./Hashable.js");

// Global variable dictionary
let test = null;


// Main

function main()
{
    // Try and initialize an instance of Hashable
    try
    {
        test = new Hashable(null);
    }
    catch (e)
    {
    }
    console.assert(test === null, "Should not be able to create an instance of Hashable.");
}
main();
