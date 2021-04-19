package basement.foundation.classloader;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @program: leetcode
 * @Date: 2021-04-15 16:15
 * @Author: Lab
 * @Description: 加解密工具类
 */
public class DESInstance {
    private static String ALGORITHM = "DESede";

    /**
     * 加密
     *
     * @param key
     * @param src
     * @return
     */
    public static byte[] enCode(byte[] key, byte[] src) {

        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            value = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    /**
     * 解密
     *
     * @param key
     * @param src
     * @return
     */
    public static byte[] deCode(byte[] key, byte[] src) {
        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key, ALGORITHM);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            value = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public static void main(String[] args) {
        String classFile = "G:/tmp/Car.class";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(classFile);
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();

            data = DESInstance.enCode("1234567890qwertyuiopasdf".getBytes(), data);

            String outFile = "G:/tmp/Car.class";
            FileOutputStream fos = new FileOutputStream(outFile);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}