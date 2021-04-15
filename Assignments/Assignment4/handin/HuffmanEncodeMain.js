let readline = require("readline");
let HuffmanEncode = require("./HuffmanEncode.js");

/*
Duy Anh Nguyen 7892957
April 14, 2021
HuffmanEncodeMain.js
The main entry to the Huffman encode program.
*/

function main()
{
    // Get the input and output file name
    let INPUT_FILE_NAME = "hamlet.txt";
    let OUTPUT_FILE_NAME = INPUT_FILE_NAME + ".huff";

    // Encode the input file to output file
    try
    {
        HuffmanEncode.encodeFile(INPUT_FILE_NAME, OUTPUT_FILE_NAME);

        // Finished encoding the input file
        console.log("Encode file " + INPUT_FILE_NAME + "success.");
        console.log("The output file is " + OUTPUT_FILE_NAME);
    }
    catch (e)
    {
        console.error(e);
    }
}
main();
