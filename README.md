# cs-318-coursework-2

This coursework involves programming and Merkle’s puzzles. There is a total of 25 marks
available for this coursework and it is worth 10% of the module. On Canvas as part of the
assignment you will find a zip file containing Java code (some of which has been obfuscated) to
be used when completing the coursework. Please also note that this coursework will be partially
accessed through automated testing (details below). Please do not place your code in a package
(it must use the default package). Please note that I have created all the code and tests using
Java 8. I recommend you use this version of Java for you submission.
The coursework is based around the following scheme:


<I>Dear Bob,
  I am going to send you 4096 puzzles. Each puzzle is a cryptogram whose plaintext
  starts out with 128 zero bits (16-bytes), followed by a 16-bit (2-byte) puzzle number
  in the range 1 to 4096, and then a 64-bit (8-byte) key. Each cryptogram has been
  encrypted using DES with a different key specification whose final 48 bits are zeros.
  Please pick one cryptogram at random and break it by brute force, trying all 216 keys
  ending in 48 zeros. You know you have broken it when you find a key that yields
  a plaintext starting with 128 zero bits. After breaking the cryptogram, extract the
  64-bit key and use it as our shared key. As your first message send me back the
  puzzle number in plaintext, so I know which key you are going to use.
  Yours sincerely, Alice </I>
