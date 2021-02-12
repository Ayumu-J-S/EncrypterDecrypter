# EncrypterDecrypter
A java program that either encrypts or decrypts a file with a key.


This program allows the user to encrypt the contents of a textfile based on an
easily remembered phrase with which the user is familiar, and which that user
is unlikely to forget. The program can also decrypt such a file, but the phrase
used to encrypt the file must be supplied at the time of decrypting as well.

The program takes its input from the command line, and there must be exactly
these four input values, in the order shown:
1. Either the single (lowercase) letter e (for encoding) or d (for decoding)
2. The name of the input file (which will contain plain text if encoding,
   or encoded text if decoding)
3. The name of the output file (which will contain encoded text if encoding,
   or plain text if decoding)
4. The phrase to be used for encoding or decoding, which must contain at least
   10 characters, and we assume that all characters are printable

The program must pause and report each of the following errors, if it occurs,
along with the message, "Program now terminating.", and then terminate.
1. Error: Incorrect number of parameters.
2. Error: Bad first parameter.
3. Error: Cannot open file.ext for input.
4. Error: Cannot open file.ext for output.
5. Error: Key phrase must not be shorter than 10 characters.

<br />
__-Test directory has shell script that allows you to test this program and sample test files.__
<br />                                                                
__-Java 8 or above is required to run the jar file included.__
