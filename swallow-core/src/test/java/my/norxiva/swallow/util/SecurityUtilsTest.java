package my.norxiva.swallow.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Slf4j
public class SecurityUtilsTest {

    @Test
    public void testDSA() {
        KeyPair keyPair = SecurityUtils.generateKeyPair(SecurityUtils.DSA);

        final String message = getFixedMessage(512, 'a');
        log.info("testing dsa case, original message: {}", message);

        byte[] signature = SecurityUtils.sign(SecurityUtils.DSA, keyPair.getPrivate(),
                StringUtils.getBytesUtf8(message));
        log.info("testing dsa case, signature: {}", Base64.getEncoder().encodeToString(signature));

        boolean verify = SecurityUtils.verify(SecurityUtils.DSA, keyPair.getPublic(),
                StringUtils.getBytesUtf8(message), signature);

        assertThat(verify, is(true));
    }

    @Test
    public void testRSA() {
        KeyPair keyPair = SecurityUtils.generateKeyPair(SecurityUtils.RSA);
        final String message = getFixedMessage(12, 'a');
        log.info("testing rsa case, original message: {}", message);

        byte[] encryptBytes = SecurityUtils.encrypt(SecurityUtils.RSA, keyPair.getPublic(), StringUtils.getBytesUtf8(message));
        log.info("testing rsa case, encrypt message: {}", Base64.getEncoder().encodeToString(encryptBytes));

        byte[] decryptBytes = SecurityUtils.decrypt(SecurityUtils.RSA, keyPair.getPrivate(), encryptBytes);
        assertThat(message, is(StringUtils.newStringUtf8(decryptBytes)));

        byte[] encryptBytes1 = SecurityUtils.encrypt(SecurityUtils.RSA, keyPair.getPrivate(), StringUtils.getBytesUtf8(message));
        log.info("testing rsa case, encrypt message: {}", Base64.getEncoder().encodeToString(encryptBytes1));

        byte[] decryptBytes1 = SecurityUtils.decrypt(SecurityUtils.RSA, keyPair.getPublic(), encryptBytes1);
        assertThat(message, is(StringUtils.newStringUtf8(decryptBytes1)));

        String base64Encrypt1 = Base64.getEncoder().encodeToString(encryptBytes1);
        byte[] signature =  SecurityUtils.sign(SecurityUtils.RSA, keyPair.getPrivate(), StringUtils.getBytesUtf8(base64Encrypt1));
        log.info("testing rsa case, signature: {}", Base64.getEncoder().encodeToString(signature));

        boolean verify = SecurityUtils.verify(SecurityUtils.RSA, keyPair.getPublic(), StringUtils.getBytesUtf8(base64Encrypt1), signature);
        assertThat(verify,is(true));
    }

    private static void printSet(
            String setName,
            Set algorithms) {
        System.out.println(setName + ":");

        if (algorithms.isEmpty()) {
            System.out.println("            None available.");
        } else {

            for (Object algorithm : algorithms) {
                String name = (String) algorithm;

                System.out.println("            " + name);
            }
        }
    }

    @Test
    public void testListAlgorithms() {
        Provider[] providers = Security.getProviders();
        Set<String> ciphers = new HashSet<>();
        Set<String> keyAgreements = new HashSet<>();
        Set<String> macs = new HashSet<>();
        Set<String> messageDigests = new HashSet<>();
        Set<String> signatures = new HashSet<>();

        for (int i = 0; i != providers.length; i++) {

            for (Object o : providers[i].keySet()) {
                String entry = (String) o;

                if (entry.startsWith("Alg.Alias.")) {
                    entry = entry.substring("Alg.Alias.".length());
                }

                if (entry.startsWith("Cipher.")) {
                    ciphers.add(entry.substring("Cipher.".length()));
                } else if (entry.startsWith("KeyAgreement.")) {
                    keyAgreements.add(entry.substring("KeyAgreement.".length()));
                } else if (entry.startsWith("Mac.")) {
                    macs.add(entry.substring("Mac.".length()));
                } else if (entry.startsWith("MessageDigest.")) {
                    messageDigests.add(entry.substring("MessageDigest.".length()));
                } else if (entry.startsWith("Signature.")) {
                    signatures.add(entry.substring("Signature.".length()));
                }
            }
        }

        printSet("Ciphers", ciphers);
        printSet("KeyAgreeents", keyAgreements);
        printSet("Macs", macs);
        printSet("MessageDigests", messageDigests);
        printSet("Signatures", signatures);
    }

    @Test
    public void testListBCCapabilities() {
        Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);

        for (Object o : provider.keySet()) {
            String entry = (String) o;

            // this indicates the entry refers to another entry

            if (entry.startsWith("Alg.Alias.")) {
                entry = entry.substring("Alg.Alias.".length());
            }

            String factoryClass = entry.substring(0, entry.indexOf('.'));
            String name = entry.substring(factoryClass.length() + 1);

            System.out.println(factoryClass + ": " + name);
        }
    }

    @Test
    public void testListProviders() {
        Provider[] providers = Security.getProviders();

        for (int i = 0; i != providers.length; i++) {
            System.out.println("Name: " + providers[i].getName() + " Version: " + providers[i].getVersion());
        }
    }

    @Test
    public void testPolicy() {
        byte[] data = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};

        SecretKeySpec key64 = new SecretKeySpec(
                new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07},
                "Blowfish");

        try {
            Cipher c = Cipher.getInstance("Blowfish/ECB/NoPadding");

            c.init(Cipher.ENCRYPT_MODE, key64);

            c.doFinal(data);

            System.out.println("64 bit test: passed");
        } catch (SecurityException e) {
            if (Objects.equals(e.getMessage(), "Unsupported keysize or algorithm parameters")) {
                System.out.println("64 bit test failed: unrestricted policy files have not been installed for this runtime.");
            } else {
                System.err.println("64 bit test failed: there are bigger problems than just policy files: " + e);
            }
        } catch (GeneralSecurityException e) {
            System.err.println("64 bit test failed: there are bigger problems than just policy files: " + e);
        }

        SecretKeySpec key192 = new SecretKeySpec(
                new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                        0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
                        0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17},
                "Blowfish");

        try {
            Cipher c = Cipher.getInstance("Blowfish/ECB/NoPadding");

            c.init(Cipher.ENCRYPT_MODE, key192);

            c.doFinal(data);

            System.out.println("192 bit test: passed");
        } catch (SecurityException e) {
            if (Objects.equals(e.getMessage(), "Unsupported keysize or algorithm parameters")) {
                System.out.println("192 bit test failed: unrestricted policy files have not been installed for this runtime.");
            } else {
                System.err.println("192 bit test failed: there are bigger problems than just policy files: " + e);
            }
        } catch (GeneralSecurityException e) {
            System.err.println("192 bit test failed: there are bigger problems than just policy files: " + e);
        }

        System.out.println("Tests completed");
    }

    @Test
    public void testPrecedence() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");

        System.out.println(cipher.getProvider());

        cipher = Cipher.getInstance("Blowfish/ECB/NoPadding", BouncyCastleProvider.PROVIDER_NAME);

        System.out.println(cipher.getProvider());
    }

//    @Test
//    public void testSimplePolicy() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//        byte[] data = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
//
//        // create a 64 bit secret key from raw bytes
//
//        SecretKey key64 = new SecretKeySpec(new byte[]{0x00, 0x01, 0x02,
//                0x03, 0x04, 0x05, 0x06, 0x07}, "Blowfish");
//
//        // create a cipher and attempt to encrypt the data block with our key
//
//        Cipher c = Cipher.getInstance("Blowfish/ECB/NoPadding");
//
//        c.init(Cipher.ENCRYPT_MODE, key64);
//        c.doFinal(data);
//        System.out.println("64 bit test: passed");
//
//        // create a 192 bit secret key from raw bytes
//
//        SecretKey key192 = new SecretKeySpec(new byte[]{0x00, 0x01, 0x02,
//                0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c,
//                0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
//                0x17}, "Blowfish");
//
//        // now try encrypting with the larger key
//
//        c.init(Cipher.ENCRYPT_MODE, key192);
//        c.doFinal(data);
//        System.out.println("192 bit test: passed");
//
//        System.out.println("Tests completed");
//    }

    @Test
    public void testBasicDSA() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "BC");

        keyGen.initialize(512, new SecureRandom());

        KeyPair keyPair = keyGen.generateKeyPair();
        Signature signature = Signature.getInstance("DSA", "BC");

        // generate a signature
        signature.initSign(keyPair.getPrivate(), SecureRandom.getInstance("SHA1PRNG"));

//        byte[] message = new byte[]{(byte) 'a', (byte) 'b', (byte) 'c'};
        byte[] message = StringUtils.getBytesUtf8(getFixedMessage(1024, 'a'));


        signature.update(message);

        byte[] sigBytes = signature.sign();

        System.out.println("signature: " + Base64.getEncoder().encodeToString(sigBytes));

        // verify a signature
        signature.initVerify(keyPair.getPublic());

        signature.update(message);

        if (signature.verify(sigBytes)) {
            System.out.println("signature verification succeeded.");
        } else {
            System.out.println("signature verification failed.");
        }
    }

    private static String getFixedMessage(int length, char c) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(c);
        }
        return builder.toString();
    }

}
