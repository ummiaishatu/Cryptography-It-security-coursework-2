import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class helps to create puzzles with different methods on how to retrieve their parameters and
 * it also encrypts puzzles and write them to a file.
 * @author ummiaishaibrahim
 * @version 1.0
 */
public class PuzzleCreator implements Serializable {
    ArrayList<Puzzle> puzzlearray = new ArrayList<>();
    Cipher cipher;

    public PuzzleCreator(){}

    /**
     * this method creates an array of puzzles and stores them in an arraylist
     * @return puzzlearray
     */
    public ArrayList<Puzzle> createPuzzles() {
        byte [] key;
        int i;
        for(i = 1; i < 4097; i++) {
            int puzzleNumber = i;
            key = createRandomKey();
            SecretKey secretKey = null;
            try {
                secretKey = CryptoLib.createKey(key);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }catch(InvalidKeySpecException e){
                e.printStackTrace();
            }
            puzzlearray.add(new Puzzle(puzzleNumber, secretKey));
        }

        return puzzlearray;
    }

    /**
     * this method creates a random keys but the final 48 bits zre zero
     * @return b_key
     */
   public byte[] createRandomKey() {

       byte [] b_key = new byte [8];
       new Random().nextBytes(b_key);
       b_key[2] = 0;
       b_key[3] = 0;
       b_key[4] = 0;
       b_key[5] = 0;
       b_key[6] = 0;
       b_key[7] = 0;
       return b_key;
   }

    /**
     * this method encrypts a puzzle using a byte array of keys and a puzzle object
     * @param b
     * @param p
     * @return
     */
    public byte[] encryptPuzzle(byte[] b, Puzzle p) {
        byte[] encrypted_puzzle = null;
        try {
            SecretKey key = CryptoLib.createKey(b);
            byte[] puzzle = p.getPuzzleAsBytes();

            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            encrypted_puzzle = cipher.doFinal(puzzle);

            return encrypted_puzzle;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return encrypted_puzzle;

    }

    /**
     * this method encrypts an array of puzzles and writes them to a file with a given name
     * @param filename
     */
    public void encryptPuzzlesToFile(String filename){
        File file_input = new File(filename);
        FileOutputStream file_output = null;
        try {
            file_output = new FileOutputStream(file_input);
            byte[] puzzle;
            for(int i = 0; i < puzzlearray.size(); i++) {
                byte[] key = createRandomKey();
                puzzle = encryptPuzzle(key, puzzlearray.get(i));
                file_output.write(puzzle);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method finds the key that has been assigned to the specified puzzle number.
     * @param puzzleNumber
     * @return key
     */
    public SecretKey findKey(int puzzleNumber) {
        SecretKey key = null;
        for(int i = 0; i < puzzlearray.size(); i++){
            Puzzle p  = puzzlearray.get(i);
            if(p.getPuzzleNumber() == puzzleNumber){
                key = p.getKey();
            }
        }
        return key ;

    }

    //test 5-18 after completion
    //tested and worked with no error
}