package club.uglyland.util;

import org.springframework.stereotype.Component;
import java.security.MessageDigest;


/**
 * 加密
 */
@Component

public class MD5Util {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d","e", "f" };

    public static String MD5(String source)
    {
        String result = null;
        try {
            result = source;

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(result.getBytes("UTF-8"));

            result = byteArrayToHexString(messageDigest.digest());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result.toUpperCase();
    }

    private static String byteArrayToHexString(byte[] bytes)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(byte tem: bytes)
        {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }
    private static Object byteToHexString(byte b)
    {
        int n = b;
        if(n < 0)
        {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
