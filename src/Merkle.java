import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * this is the main method that follows the message that Alice sent to Bob
 * @author ummiaishaibrahim
 * @version 1.0
 */

public class Merkle {
    private static String file;
    public static void main(String [] args ) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        file = "AlicePuzzle";
        PuzzleCreator creator = new PuzzleCreator();
        creator.createPuzzles();
        creator.encryptPuzzlesToFile(file);
        PuzzleCracker crack = new PuzzleCracker(file);
        Random random = new Random();
        int range = random.nextInt(4096 + 1 ) + 1;
        Puzzle bob_puzzle = crack.crack(random.nextInt(range));
        SecretKey secretKey = creator.findKey(bob_puzzle.getPuzzleNumber());

        String  plaintext = "Testing Merkles Puzzles!";
        byte [] plaintextByte = plaintext.getBytes();

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted_message = cipher.doFinal(plaintextByte);

        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encrypted_message);

        System.out.println(encryptedText);

        crack.decryptMessage(encrypted_message);



    }
}
