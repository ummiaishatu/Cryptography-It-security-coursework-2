import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * this class creates a puzzle by using a puzzle number and secret key. it also contains the all the get methods of each parameter
 * it also contains a method that converts puzzles to a byte.
 * @author ummiaishaibrahim
 * @version 1.0
 */
public class Puzzle {

    public int puzzleNumber ;
    public SecretKey secretkey;

    //constructor
    public Puzzle(int puzzleNumber, SecretKey secretKey) {
        this.puzzleNumber = puzzleNumber;
        this.secretkey = secretKey;
    }

    /**
     * get method to return the puzzle number in the constructor
     * @return puzzlenumber
     */
    public int getPuzzleNumber() {
        return puzzleNumber;
    }

    /**
     * get method to return the secret key in the constructor
     * @return secretkey
     */
    public SecretKey getKey() {
        return secretkey;
    }

    /**
     * get method that represents puzzle and returns it in a byte array.
     * @return puzzles as bytes
     * @throws IOException
     */
    public  byte[] getPuzzleAsBytes() throws IOException {
        // created three bytes for key, plaintext and number according to Alice's message to Bob
        // key models correctly to the secret key in the puzzle constructor by connecting it with the byte key array
        byte [] key = this.secretkey.getEncoded();
        // number models correctly to the puzzle number from the puzzle constructor by using the method smallIntToByteArray
        // this method changes the number to a byte array.
        byte [] number = CryptoLib.smallIntToByteArray(getPuzzleNumber());
        // the plaintext byte array consists of the 16 bits from Alice's message to Bob
        byte [] plaintext = new byte[16];

        //the ByteArrayOutputStream object allows all the bytes to be connected and allows them to be captured and written
        // in the stream of the byte array.
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(plaintext);
        stream.write(number);
        stream.write(key);

        return stream.toByteArray();
    }

    // test after completion using test 1 - 3.
    // tested and worked with no error.
}

