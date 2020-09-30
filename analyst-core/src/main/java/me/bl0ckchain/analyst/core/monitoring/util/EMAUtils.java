package me.bl0ckchain.analyst.core.monitoring.util;

import me.bl0ckchain.analyst.core.monitoring.indicator.Data;
import me.bl0ckchain.analyst.core.monitoring.indicator.MetaData;
import me.bl0ckchain.analyst.core.util.DoubleUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 19/07/2018
 */
public class EMAUtils {

    public static double getAlpha(int n) {
        return 2.0 / (n + 1);
    }

    public static double EMA(double alpha, double price, double prevEMA) {
        return DoubleUtils.add(DoubleUtils.mul(alpha, DoubleUtils.sub(price, prevEMA)), prevEMA);
    }

    public static <T extends MetaData> List<Data> calculate(List<T> srcData, int interval) {

        if (srcData.size() < interval) {
            return Collections.EMPTY_LIST;
        }

        List<Data> destData = new ArrayList<>();
        double alpha = getAlpha(interval);
        int max = srcData.size() - interval;

        for (int i = max; i >= 0; i--) {
            MetaData emaData = srcData.get(i);
            double ema;
            if (i == max) {
                List<T> sub = srcData.subList(max, srcData.size());
                ema = 0;
                for (int j = 0; j < sub.size(); j++) {
                    ema += sub.get(j).getValue();
                }
                ema /= sub.size();
            } else {
                double price = emaData.getValue();
                Data prevEMA = destData.get(destData.size() - 1);
                ema = EMA(alpha, price, prevEMA.getValue());
            }
            Data data = new Data();
            data.setValue(ema);
            data.setStartAt(emaData.getStartAt());
            data.setEndAt(emaData.getEndAt());
            destData.add(data);
        }

        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

    public static <T extends MetaData> List<Data> calculate(List<T> srcData, int interval, double initialValue) {

        if (srcData.size() < interval) {
            return Collections.EMPTY_LIST;
        }

        int max = srcData.size() - 1;
        double alpha = getAlpha(interval);
        List<Data> destData = new ArrayList<>();

        for (int i = max; i >= 0; i--) {
            MetaData emaData = srcData.get(i);
            double ema;
            if (i == max) {
                ema = initialValue;
            } else {
                double price = emaData.getValue();
                Data prevEMA = destData.get(destData.size() - 1);
                ema = EMA(alpha, price, prevEMA.getValue());
            }
            Data data = new Data();
            data.setValue(ema);
            data.setStartAt(emaData.getStartAt());
            data.setEndAt(emaData.getEndAt());
            destData.add(data);
        }

        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

}
