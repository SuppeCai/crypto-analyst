package me.bl0ckchain.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 15/07/2018
 */
public class DateUtils {

    public static final String DEFAULT_FORMAT = "HH:mm:ss";

    public static Long getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static String format(Long date) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        return format.format(date);
    }

}
