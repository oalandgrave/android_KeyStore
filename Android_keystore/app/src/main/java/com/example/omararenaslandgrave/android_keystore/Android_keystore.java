package com.example.omararenaslandgrave.android_keystore;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.widget.Toast;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;

public class Android_keystore {

    Context context;
    KeyStore ks;
    Android_keystore(Context context) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {

        this.context = context;
        this.ks = KeyStore.getInstance("AndroidKeyStore");
        this.ks.load(null);
    }
    public void createKeys(String alias) {

        try{
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore");
            kpg.initialize(new KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_SIGN | KeyProperties.PURPOSE_VERIFY)
                    .setDigests(KeyProperties.DIGEST_SHA256,
                            KeyProperties.DIGEST_SHA512)
                    .build());

            KeyPair kp = kpg.generateKeyPair();
        } catch (Exception e) {
            Toast.makeText(this.context, "Exception " + e.getMessage() + " occured", Toast.LENGTH_LONG).show();

        }
    }

    public static String stringScriptions(byte[] ToBeencrypted, String key ) throws Exception
    {
        MessageDigest messageDigest =  MessageDigest.getInstance("SHA-256");;
        byte[] digestOfPassword = messageDigest.digest(key.getBytes("UTF-16LE"));
        SecretKeySpec skeySpec = new SecretKeySpec(digestOfPassword, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(ToBeencrypted);
        return new String(decrypted, "UTF-16LE");
    }

    public static String stringsDecritions( byte[] ToDecrypted , byte[] key ) throws Exception
    {
        MessageDigest messageDigest =  MessageDigest.getInstance("SHA-256");;
        byte[] digestOfPassword = messageDigest.digest(key);
        SecretKeySpec skeySpec = new SecretKeySpec(digestOfPassword, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(ToDecrypted);
        return Base64.encodeToString(encrypted,Base64.DEFAULT);
    }

}
