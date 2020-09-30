package me.bl0ckchain.analyst.core.sdk;

import java.security.MessageDigest;

public class Test
{
    private static final char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };

    public static String encode(String paramString)
    {
        String str = paramString;
        if (paramString == null) {
            str = "";
        }
        MessageDigest localMessageDigest =null;
        try
        {
            localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(str.getBytes("utf-8"));
        }
        catch (Exception localException)
        {
                localException.printStackTrace();
        }
        return toHex(localMessageDigest.digest());
    }

    public static String getMD5String(String paramString)
    {
        return getMD5String(paramString.getBytes());
    }

    public static String getMD5String(byte[] paramArrayOfByte)
    {
        Object localObject = null;
        try
        {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localObject = localMessageDigest;
            localMessageDigest.update(paramArrayOfByte);
            localObject = localMessageDigest;
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return toHex(((MessageDigest)localObject).digest());
    }

//    public static String md5_3(String paramString)
//    {
//        String str = paramString;
//        if (paramString == null) {
//            str = "";
//        }
//        paramString = null;
//        try
//        {
//            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
//            localMessageDigest.update(localMessageDigest.digest(localMessageDigest.digest(str.getBytes("utf-8"))));
//        }
//        catch (Exception localException)
//        {
//            for (;;)
//            {
//                localException.printStackTrace();
//            }
//        }
//        return toHex(paramString.digest());
//    }

    private static String toHex(byte[] paramArrayOfByte)
    {
        StringBuffer localStringBuffer = new StringBuffer(32);
        int j = paramArrayOfByte.length;
        int i = 0;
        for (;;)
        {
            if (i >= j) {
                return localStringBuffer.toString();
            }
            int k = paramArrayOfByte[i];
            localStringBuffer.append(hexDigits[((k & 0xF0) >> 4)]);
            localStringBuffer.append(hexDigits[(k & 0xF)]);
            i += 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(Test.encode("school_id="));
        System.out.println(Test.encode("school_id=0"));
    }
}
