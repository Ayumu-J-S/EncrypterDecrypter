import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


/**
 * A program to encrypt or decrypt a textfile. The encryption scheme used is a
 * straightforward "substitution cypher". That is, each line of the encoded
 * message contains exactly the same number of characters as the corresponding
 * line in the original plain text, and once you have decoded each character of
 * the encoded message you immediately have the original plain text. This also
 * implies that blank lines in the plain text are preserved in the encrypted
 * text.
 * 
 * @author Ayumu Saito
 * @version 1.0
 */
public class EncrypterDecrypter
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            //Opening screen
            OpeningScreen openingScreen = new OpeningScreen
            (
                "Ayumu Saito",
                "EncrypterDecrypter.java",
                "Encrypting and Decrypting Textfiles"
            );
            openingScreen.display();
            TextItems textItems = new TextItems
            (
                EncrypterDecrypter.class.getResourceAsStream
                (
                    "EncrypterDecrypter.txt"
                )
            );
            textItems.displayItem("ProgramDescription");
            System.out.println("");
            System.exit(0);
        }

        if (args.length != 4)
        {
            System.out.println("\nError: Incorrect number of parameters.");
            System.out.println("Program now terminating.");
            Utils.pause();
            System.out.println();
            System.exit(0);
        }

        String encodeOrDecode = args[0];
        String inputFileName = args[1];
        String outputFileName = args[2];
        String keyAsString = args[3];

        //Encrypt of decrypt
        if (encodeOrDecode.equals("e"))
        {
            //Encode the file and store it in output file
            encode(inputFileName, keyAsString, outputFileName);

            //Inform the user of the result
            System.out.println
            (
                "\nThe file " + inputFileName
                + " has been encoded and output to the file "
                + outputFileName + ".\n"
            );
            Utils.pause();
            System.out.println();
        }
        else if (encodeOrDecode.equals("d"))
        {
            //Decode the file and store it in output file
            decode(inputFileName, keyAsString, outputFileName);

            //Inform the user of the result
            System.out.println
            (
                "\nThe file " + inputFileName
                + " has been decoded and output to the file "
                + outputFileName + ".\n"
            );
            Utils.pause();
            System.out.println();
        }
        else
        {
            //Inform the user of the error
            System.out.println("\nError: Bad first parameter.");
            System.out.println("Program now terminating.");
            Utils.pause();
            System.out.println();
        }
    }//end of main

    /**
     * Encrypt the given file with a given key and store the encryption in the given
     * output file.
     * 
     * @param inputFileName  name of the input file
     * @param keyAsString    the key that will be used for encryption
     * @param outputFileName name of the output file that the encryption will be
     *                       saved in
     */
    private static void encode
    (
        String inputFileName, String keyAsString, String outputFileName
    )
    {
        Scanner inputFile = null;

        try
        {
            inputFile = new Scanner(new File(inputFileName));
            PrintWriter out = null;
            try
            {
                out = createOutputObject(outputFileName);
                ArrayList<Character> key = createKey(keyAsString);
                String line;
                            
                //Do encryption with the key line by line
                while (inputFile.hasNextLine())
                {
                    line = inputFile.nextLine();
                    for (int i = 0; i < line.length(); i++)
                    {
                        out.print(key.get(line.charAt(i) - 32));
                    }
                    out.println();
                }
            }
            catch (FileNotFoundException ex)
            {
                System.out.println
                (
                    "\nError: Cannot open "
                    + outputFileName + " for output."
                );
                System.out.println("Program now terminating.");
                Utils.pause();
                System.out.println();
                System.exit(0);
            }
            finally
            {
                out.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println
            (
                "\nError: Cannot open " + inputFileName + " for input."
            );
            System.out.println("Program now terminating.");
            Utils.pause();
            System.out.println();
            System.exit(0);
        }
        finally
        {
            inputFile.close();
        }
    }

    /**
     * Decrypt the given input file with a given key and store it in the output
     * file.
     * 
     * @param inputFileName  the name of the input file that has encryption
     * @param keyAsString    key to decrypt the document
     * @param outputFileName the name of the output file that the result of the
     *                       decryption is saved on.
     */
    private static void decode
    (
        String inputFileName, String keyAsString, String outputFileName
    )
    {
        Scanner inputFile = null;
        try
        {
            inputFile = new Scanner(new File(inputFileName));
            PrintWriter out = null;
            try
            {
                out = createOutputObject(outputFileName);
                ArrayList<Character> key = createKey(keyAsString);
                String line;

                //Do the decryption line by line
                while (inputFile.hasNextLine())
                {
                    line = inputFile.nextLine();
                    for (int i = 0; i < line.length(); i++)
                    {
                        out.print((char) (key.indexOf(line.charAt(i)) + 32));
                    }
                    out.println();
                }
            }
            catch (FileNotFoundException ex)
            {
                System.out.println
                (
                    "\nError: Cannot open "
                    + outputFileName + " for output."
                );
                System.out.println("Program now terminating.");
                Utils.pause();
                System.out.println();
                System.exit(0);
            }
            finally
            {
                out.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println
            (
                "\nError: Cannot open "
                + inputFileName + " for input."
            );
            System.out.println("Program now terminating.");
            Utils.pause();
            System.out.println();
            System.exit(0);
        }
        finally
        {
            inputFile.close();
        }
    }

    /**
     * Create IO object to to write out the result to a file.
     */
    private static PrintWriter createOutputObject(String outputFileName)
    throws FileNotFoundException
    {
        PrintWriter out = null;
        out = new PrintWriter(new FileOutputStream(outputFileName));
        return out;
    }

    /**
     * Create key with a given key stored in String and store it in ArrayList and
     * treat it as the key.
     * 
     * @param keyAsString key that is stored in a String, the length of this String
     *                    has to be greater than or equal to 10
     * @return the complete key that can be used to encrypt or decrypt a document
     */
    private static ArrayList<Character> createKey(String keyAsString)
    {
        if (keyAsString.length() > 10)
        {
            //Store the String in HashSet to eliminate the duplicates
            HashSet<Character> keyHash = new HashSet<>();
            for (int i = 0; i < keyAsString.length() - 1; i++)
            {
                keyHash.add(keyAsString.charAt(i));
            }

            //Store the HashSet in the key that is ArrayList
            ArrayList<Character> key = new ArrayList<>(keyHash);

            //Sort key with elements added so far in reverse order
            key.sort((a, b) -> (int) b - (int) a);

            //Add all the missing pritntable chracters to the key
            for (int i = 32; i <= 126; i++)
            {
                if (!key.contains((char) i))
                {
                    key.add((char) i);
                }
            }

            //Reverse the key according the current order
            key.sort((a, b) -> key.indexOf(b) - key.indexOf(a));

            return key;
        }
        else
        {
            //Inform the user of the error
            System.out.println
            (
                "\nError: Key phrase must not be shorter than 10 " 
                + "characters.\n"
                + "Program now terminating."
            );
            Utils.pause();
            System.out.println();
            System.exit(0);

            //Create an empty arrayList to give something to return
            ArrayList<Character> key = null;
            return key;
        }
    }
}
