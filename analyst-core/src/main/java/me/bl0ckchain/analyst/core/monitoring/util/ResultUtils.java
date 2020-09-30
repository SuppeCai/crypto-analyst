package me.bl0ckchain.analyst.core.monitoring.util;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.indicator.Data;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/06/2018
 */
public class ResultUtils {

    public static boolean validate(Kline kline, Data data) {

        boolean isValid = false;

        if (kline == null || kline.getStartAt() == null || kline.getEndAt() == null) {
            return isValid;
        }

        if (data == null || data.getStartAt() == null || data.getEndAt() == null) {
            return isValid;
        }

        if (kline.getStartAt().equals(data.getStartAt()) && kline.getEndAt().equals(data.getEndAt())) {
            isValid = true;
        }

        return isValid;
    }
}
