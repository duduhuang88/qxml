package com.qxml.tools.encrypt;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

/**
 * @author Mr.Zheng
 * @date 2014年8月22日 下午1:44:23
 */
public final class RSAUtils {
    private static String RSA = "RSA";

    private static final int MAX_ENCRYPT_BLOCK_1024 = 117;
    private static final int MAX_DECRYPT_BLOCK_1024 = 128;
    private static final int MAX_ENCRYPT_BLOCK_2048 = 245;
    private static final int MAX_DECRYPT_BLOCK_2048 = 256;

    private static final int CUR_ENCRYPT_BLOCK = MAX_ENCRYPT_BLOCK_1024;
    private static final int CUR_DECRYPT_BLOCK = MAX_DECRYPT_BLOCK_1024;

    private static int KEYBIT = 2048;// 密钥位数

    /**
     * 随机生成RSA密钥对(默认密钥长度为1024)
     *
     * @return
     */
    public static KeyPair generateRSAKeyPair()
    {
        return generateRSAKeyPair(KEYBIT);
    }

    /**
     * 随机生成RSA密钥对
     *
     * @param keyLength
     *            密钥长度，范围：512～2048<br>
     *            一般1024
     * @return
     */
    public static KeyPair generateRSAKeyPair(int keyLength)
    {
        try
        {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用公钥加密 <br>
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data
     *            需加密数据的byte数据
     *            公钥
     * @return 加密后的byte型数据
     */
    public static byte[] encryptData(byte[] data, PublicKey publicKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(RSA);
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 传入编码数据并返回编码结果
            return cipher.doFinal(data);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用私钥解密
     *
     * @param encryptedData
     *            经过encryptedData()加密返回的byte数据
     * @param privateKey
     *            私钥
     * @return
     */
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 用私钥加密 <br>
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data
     *            需加密数据的byte数据
     *            私钥
     * @return 加密后的byte型数据
     */
    public static byte[] encryptDataByPrivateKey(byte[] data, PrivateKey privateKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(RSA);
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            // 传入编码数据并返回编码结果
            return cipher.doFinal(data);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用公钥解密
     *
     * @param encryptedData
     *            经过encryptedData()加密返回的byte数据
     * @param publicKey
     *            公钥
     * @return
     */
    public static byte[] decryptDataByPublicKey(byte[] encryptedData, PublicKey publicKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
     *
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException
    {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 通过私钥byte[]将公钥还原，适用于RSA算法
     *
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException
    {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 使用N、e值还原公钥
     *
     * @param modulus
     * @param publicExponent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String modulus, String publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 使用N、d值还原私钥
     *
     * @param modulus
     * @param privateExponent
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String modulus, String privateExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr
     *            公钥数据字符串
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception
    {
        try
        {
            byte[] buffer = Base64Utils.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e)
        {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e)
        {
            throw new Exception("公钥非法");
        } catch (NullPointerException e)
        {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从字符串中加载私钥<br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception
    {
        try
        {
            byte[] buffer = Base64Utils.decode(privateKeyStr);
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e)
        {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e)
        {
            throw new Exception("私钥非法");
        } catch (NullPointerException e)
        {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in
     *            公钥输入流
     * @throws Exception
     *             加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(InputStream in) throws Exception
    {
        try
        {
            return loadPublicKey(readKey(in));
        } catch (IOException e)
        {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e)
        {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     *            私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(InputStream in) throws Exception
    {
        try
        {
            return loadPrivateKey(readKey(in));
        } catch (IOException e)
        {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e)
        {
            throw new Exception("私钥输入流为空");
        }
    }

    /**
     * 读取密钥信息
     *
     * @param in
     * @return
     * @throws IOException
     */
    private static String readKey(InputStream in) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null)
        {
            if (readLine.charAt(0) == '-')
            {
                continue;
            } else
            {
                sb.append(readLine);
                sb.append('\r');
            }
        }

        return sb.toString();
    }

    /**
     * 使用私钥将数据进行分段加密
     *
     * @param dataStr   要加密的数据
     * @param privateKeyStr 私钥 Base64 字符串，X509 格式
     * @return 加密后的 Base64 编码数据，加密失败返回 null
     */
    public static String encryptByPrivateKey(String dataStr, String privateKeyStr) {
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            PrivateKey privateKey = loadPrivateKey(privateKeyStr);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] bytes = dataStr.getBytes();
            int inputLen = bytes.length;
            int offLen = 0;//偏移量
            int i = 0;
            while(inputLen - offLen > 0){
                byte [] cache;
                if(inputLen - offLen > CUR_ENCRYPT_BLOCK){
                    cache = cipher.doFinal(bytes, offLen, CUR_ENCRYPT_BLOCK);
                }else{
                    cache = cipher.doFinal(bytes, offLen,inputLen - offLen);
                }
                bops.write(cache);
                i++;
                offLen = CUR_ENCRYPT_BLOCK * i;
            }
            byte[] encryptedData = bops.toByteArray();
            return Base64Utils.encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                bops.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用公钥将加密后的 Base64 字符串进行分段解密
     *
     * @param dataStr 加密后的 Base64 字符串
     * @param publicKeyStr            公钥 Base64 字符串，PKCS8 格式
     * @return 解密后的明文，解密失败返回 null
     */
    public static String decryptByPublicKey(String dataStr, String publicKeyStr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            PublicKey publicKey = loadPublicKey(publicKeyStr);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] bytes = Base64Utils.decode(dataStr);
            int inputLen = bytes.length;
            int offLen = 0;
            int i = 0;
            while(inputLen - offLen > 0){
                byte[] cache;
                if(inputLen - offLen > CUR_DECRYPT_BLOCK){
                    cache = cipher.doFinal(bytes,offLen,CUR_DECRYPT_BLOCK);
                }else{
                    cache = cipher.doFinal(bytes,offLen,inputLen - offLen);
                }
                byteArrayOutputStream.write(cache);
                i++;
                offLen = CUR_DECRYPT_BLOCK * i;
            }
            byteArrayOutputStream.close();

            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
