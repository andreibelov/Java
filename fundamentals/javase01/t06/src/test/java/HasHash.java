import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.security.DigestException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 6/3/2016.
 *
 */

public class HasHash {
    private static MessageDigest md = null;
    private static Map<Integer, String> hashTable;
    private static AtomicInteger ai;
    public static void main(String[] args) {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        ai = new AtomicInteger(-1);
        hashTable = new Hashtable<>();
        put("some string");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите текст: ");
        try {
            put(in.nextLine());
        } catch (NoSuchElementException ex){
            //do nothing
        }
        for (Map.Entry hash:hashTable.entrySet()) {
            System.out.println(hash);
        }

    }

    private static boolean put(String text){
        hashTable.put(ai.incrementAndGet(),calculateHash(text));
        return true;
    }

    @NotNull private static String calculateHash(String s) {
        assert md != null;
        md.update(s.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : md.digest()) {
            String hex = Integer.toHexString(0xff & aByteData);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }
}