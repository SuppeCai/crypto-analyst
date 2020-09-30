package me.bl0ckchain.analyst.core.monitoring.util;

import me.bl0ckchain.analyst.core.monitoring.analyzer.CrossEnum;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/06/2018
 */
public class CrossUtils {

    public static CrossEnum getCrossType(double latestDiff, double prevDiff) {

        CrossEnum crossType = CrossEnum.NONE;
        if (latestDiff > 0 && prevDiff <= 0) {
            crossType = CrossEnum.GOLDEN;
        } else if (latestDiff < 0 && prevDiff >= 0) {
            crossType = CrossEnum.DEATH;
        }
        return crossType;
    }
}
