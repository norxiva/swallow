package my.norxiva.swallow.util;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class DSA {


    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        SecureRandom random = SecureRandom.getInstance("SHA2PRNG");
        System.out.println("");
    }
}
