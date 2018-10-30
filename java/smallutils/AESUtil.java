package com.gaoyuan.hot.util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    /**
     * AES加密
     * 
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(content.getBytes("UTF-8"));
    }

    /**
     * AES加密为base 64 code
     * 
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return Base64.getEncoder().encodeToString(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * 
     * @param encryptBytes
     *            待解密的byte[]
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);
        SecretKey secretKey = new SecretKeySpec(kgen.generateKey().getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     * 
     * @param encryptStr
     *            待解密的base 64 code
     * @param decryptKey
     *            解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(Base64.getDecoder().decode(encryptStr), decryptKey);
    }
}