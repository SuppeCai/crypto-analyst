package me.bl0ckchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class ApplicationTest {

    public static String m8674a(Long l, String str, Map<String, Object> map) {
        String str2 = "@`";
        Object stringBuilder = new StringBuilder(String.valueOf(str)).append(str2).append("addMimaxinxi_V21").append(str2).append(l).append(str2).append("android").append(str2).append("SensitivewordVersion").append(str2).append("0").toString();
        for (int i = 0; i < map.size(); i++) {
            stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).append(str2).append(map.get("arg" + i).toString()).toString();
        }
        return Base64.getEncoder().encodeToString(new StringBuilder(String.valueOf(stringBuilder)).append(str2).append("-1").toString().getBytes());
    }

    public static String m8678b(Long l, String str, Map<String, Object> map) {
        String str2 = "@`";
        Object obj = "anzhuo" + str + str2 + "SensitivewordTime" + str2 + (((l).hashCode() & 4095) * 77) + str2 + "android" + str2 + "SensitivewordVersion" + str2 + "0";
        for (int i = 0; i < map.size(); i++) {
            Object obj2 = map.get("arg" + i);
            if (obj2 == null) {
                obj2 = "";
            }
            obj = new StringBuilder(String.valueOf(obj)).append(str2).append(obj2.toString()).toString();
        }
        return m8675a(new StringBuilder(String.valueOf(obj)).append(str2).append("-1").toString());
    }

    public static String m8678b1( String str, Map<String, Object> map) {
        String str2 = "@`";
        Object obj = "anzhuo" + str + str2 + "SensitivewordTime" + str2  + str2 + "android" + str2 + "SensitivewordVersion" + str2 + "0";
        for (int i = 0; i < map.size(); i++) {
            Object obj2 = map.get("arg" + i);
            if (obj2 == null) {
                obj2 = "";
            }
            obj = new StringBuilder(String.valueOf(obj)).append(str2).append(obj2.toString()).toString();
        }
        return m8675a(new StringBuilder(String.valueOf(obj)).append(str2).append("-1").toString());
    }

    public static String m8675a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] bArr = null;
            try {
                bArr = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

//    public static String m8677a(List<Sensitiveword> list, String str) {
//        for (int i = 0; i < list.size(); i++) {
//            if (((Sensitiveword) list.get(i)).getType().equals("1")) {
//                str = str.replaceAll(((Sensitiveword) list.get(i)).getKeywords(), "***");
//            }
//        }
//        return str;
//    }

//    public static String m8680b(List<Sensitiveword> list, String str) {
//        String str2;
//        String str3;
//        String str4 = null;
//        String str5 = null;
//        for (int i = 0; i < list.size(); i++) {
//            Sensitiveword sensitiveword = (Sensitiveword) list.get(i);
//            if (str.indexOf(sensitiveword.getKeywords()) > -1) {
//                if (sensitiveword.getType().equals("1")) {
//                    str5 = sensitiveword.getType();
//                } else {
//                    str4 = sensitiveword.getType();
//                }
//            }
//            if (str5 != null && str4 != null) {
//                str2 = str4;
//                str3 = str5;
//                break;
//            }
//        }
//        str2 = str4;
//        str3 = str5;
//        if (str3 != null) {
//            if (str2 != null) {
//                return new StringBuilder(String.valueOf(str3)).append(",").append(str2).toString();
//            }
//            return str3;
//        } else if (str2 != null) {
//            return str2;
//        } else {
//            return null;
//        }
//    }

    public static String m8676a(String str, String str2, String str3) {
        if (str == null) {
            return "?sid=" + str3;
        }
        if (!str.equals("1")) {
            return "?sid=" + str3;
        }
        return "?id=" + str2 + "&c=" + str3 + "&t=" + m8681c(str3);
    }

    public static String m8682d(String str) {
        return "&c=" + str + "&t=" + m8681c(str);
    }

    public static String m8679b(String str) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i2 = 0;
            while (i < length) {
                byte b = digest[i];
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
                i++;
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m8681c(String str) {
        char[] toCharArray = str.toCharArray();
        String str2 = "";
        int length = toCharArray.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String stringBuilder = new StringBuilder(String.valueOf(str2)).append(m8679b(String.valueOf(toCharArray[i])).toCharArray()[i2]).toString();
            int i3 = i2 + 1;
            if (i3 > 31) {
                i3 = 0;
            }
            i++;
            i2 = i3;
            str2 = stringBuilder;
        }
        return m8679b(str2);
    }

    public static void main(String[] args) {
        System.out.println(m8681c("woaiyanyuan520"));
        Map<String, Object> map = new HashMap<>();
        map.put("school_id", 52000);

        System.out.println(m8681c("school_id=52000"));
        System.out.println(m8678b1("yonghuLogin_V21", map));
    }
}
