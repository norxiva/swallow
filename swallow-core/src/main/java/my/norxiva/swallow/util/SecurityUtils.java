package my.norxiva.swallow.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class SecurityUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static final String DEFAULT_PROVIDER = BouncyCastleProvider.PROVIDER_NAME;
    public static final int DEFAULT_KEY_SIZE = 1024;
    public static final String DEFAULT_SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    public static final String DSA = "DSA";
    public static final String RSA = "RSA";
    public static final String AES = "AES";
    public static final String DES = "DES";

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";

    public static KeyPair generateKeyPair(String algorithm, int keySize,
                                          String provider) {
        Preconditions.checkNotNull(algorithm);
        try {
            KeyPairGenerator keyGen = Strings.isNullOrEmpty(provider) ?
                    KeyPairGenerator.getInstance(algorithm) :
                    KeyPairGenerator.getInstance(algorithm, DEFAULT_PROVIDER);
            keyGen.initialize(keySize > 0 ? keySize : DEFAULT_KEY_SIZE,
                    SecureRandom.getInstance(DEFAULT_SECURE_RANDOM_ALGORITHM));

            return keyGen.generateKeyPair();
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static KeyPair generateKeyPair(String algorithm) {
        return generateKeyPair(algorithm, DEFAULT_KEY_SIZE, DEFAULT_PROVIDER);
    }

    public static PrivateKey getPrivateKey(String algorithm, String base64Key,
                                           String provider) {
        Preconditions.checkNotNull(algorithm);
        Preconditions.checkNotNull(base64Key);
        try {
            KeyFactory keyFactory = Strings.isNullOrEmpty(provider) ?
                    KeyFactory.getInstance(algorithm) : KeyFactory.getInstance(algorithm, provider);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(base64Key)));
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static PublicKey getPublicKey(String algorithm, String base64Key,
                                         String provider) {
        Preconditions.checkNotNull(algorithm);
        Preconditions.checkNotNull(base64Key);
        try {
            KeyFactory keyFactory = Strings.isNullOrEmpty(provider) ?
                    KeyFactory.getInstance(algorithm) : KeyFactory.getInstance(algorithm, provider);
            return keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(base64Key)));
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static Certificate getCertificate(String type, String base64Key,
                                             String provider) {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(base64Key);
        try {
            CertificateFactory certificateFactory = Strings.isNullOrEmpty(provider)
                    ? CertificateFactory.getInstance(type) : CertificateFactory.getInstance(type, provider);
            return certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(base64Key)));
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static KeyStore getKeyStore(String type, String base64Key, String password,
                                       String provider) {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(base64Key);
        try {
            KeyStore keyStore = Strings.isNullOrEmpty(provider)
                    ? KeyStore.getInstance(type) : KeyStore.getInstance(type, provider);
            char[] passwordChars = Strings.isNullOrEmpty(password) ? null : password.toCharArray();
            keyStore.load(new ByteArrayInputStream(Base64.decodeBase64(base64Key)), passwordChars);
            return keyStore;
        } catch (GeneralSecurityException | IOException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }


    public static byte[] sign(String algorithm, PrivateKey key, byte[] data,
                              String provider) {
        Preconditions.checkNotNull(algorithm);
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(data);
        try {
            Signature signature = Strings.isNullOrEmpty(provider)
                    ? Signature.getInstance(algorithm) : Signature.getInstance(algorithm, provider);
            signature.initSign(key);
            signature.update(data);
            return signature.sign();
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static byte[] sign(String algorithm, PrivateKey key, byte[] data) {
        return sign(algorithm, key, data, DEFAULT_PROVIDER);
    }

    public static boolean verify(String algorithm, PublicKey key, byte[] data,
                                 byte[] signature, String provider) {
        Preconditions.checkNotNull(algorithm);
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(data);

        try {
            Signature verifier = Strings.isNullOrEmpty(provider) ?
                    Signature.getInstance(algorithm) : Signature.getInstance(algorithm, provider);
            verifier.initVerify(key);
            verifier.update(data);
            return verifier.verify(signature);
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static boolean verify(String algorithm, PublicKey key, byte[] data,
                                 byte[] signature) {
        return verify(algorithm, key, data, signature, DEFAULT_PROVIDER);
    }

    public static byte[] encrypt(String algorithm, Key key, byte[] data,
                                 AlgorithmParameterSpec spec, String provider) {
        Preconditions.checkNotNull(algorithm);
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(data);
        try {
            Cipher cipher = Strings.isNullOrEmpty(provider)
                    ? Cipher.getInstance(algorithm) : Cipher.getInstance(algorithm, provider);
            if (Objects.isNull(spec)) cipher.init(Cipher.ENCRYPT_MODE, key);
            else cipher.init(Cipher.ENCRYPT_MODE, key, spec);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static byte[] encrypt(String algorithm, Key key, byte[] data) {
        return encrypt(algorithm, key, data, null, DEFAULT_PROVIDER);
    }

    public static byte[] decrypt(String algorithm, Key key, byte[] data,
                                 AlgorithmParameterSpec spec, String provider) {
        Preconditions.checkNotNull(algorithm);
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(data);
        try {
            Cipher cipher = Strings.isNullOrEmpty(provider)
                    ? Cipher.getInstance(algorithm) : Cipher.getInstance(algorithm, provider);

            if (Objects.isNull(spec)) cipher.init(Cipher.DECRYPT_MODE, key);
            else cipher.init(Cipher.DECRYPT_MODE, key, spec);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }

    public static byte[] decrypt(String algorithm, Key key, byte[] data) {
        return decrypt(algorithm, key, data, null, DEFAULT_PROVIDER);
    }


}
