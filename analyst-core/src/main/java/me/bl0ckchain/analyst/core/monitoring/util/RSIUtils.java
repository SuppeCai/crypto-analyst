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
 * @date 21/09/2018
 */
public class RSIUtils {

    public static <T extends MetaData> List<Data> calculate(List<T> srcData, int interval) {
        if (srcData.size() <= interval) {
            return Collections.EMPTY_LIST;
        }

        double averageGain = 0;
        double averageLoss = 0;
        int max = srcData.size() - 1;
        for (int i = max - 1; i >= max - interval; i--) {

            T prev = srcData.get(i + 1);
            T data = srcData.get(i);

            double diff = DoubleUtils.sub(data.getValue(), prev.getValue());
            if (diff >= 0) {
                averageGain = DoubleUtils.add(averageGain, diff);
            } else {
                averageLoss = DoubleUtils.add(averageLoss, Math.abs(diff));
            }
        }

        averageGain = DoubleUtils.div(averageGain, interval);
        averageLoss = DoubleUtils.div(averageLoss, interval);

        MetaData firstSrcData = srcData.get(max - interval);

        Data firstDestData = new Data();
        firstDestData.setValue(calculateRSI(averageGain, averageLoss));
        firstDestData.setStartAt(firstSrcData.getStartAt());
        firstDestData.setEndAt(firstSrcData.getEndAt());

        List<Data> destData = new ArrayList<>();
        destData.add(firstDestData);

        for (int i = max - interval - 1; i >= 0; i--) {
            T prev = srcData.get(i + 1);
            T d = srcData.get(i);
            double diff = DoubleUtils.sub(d.getValue(), prev.getValue());
            if (diff >= 0) {
                averageGain = DoubleUtils.div(DoubleUtils.add(DoubleUtils.mul(averageGain, (interval - 1)), diff), interval);
                averageLoss = DoubleUtils.div(DoubleUtils.mul(averageLoss, (interval - 1)), interval);
            } else {
                averageLoss = DoubleUtils.div(DoubleUtils.add(DoubleUtils.mul(averageLoss, (interval - 1)), Math.abs(diff)), interval);
                averageGain = DoubleUtils.div(DoubleUtils.mul(averageGain, (interval - 1)), interval);
            }

            Data data = new Data();
            data.setValue(calculateRSI(averageGain, averageLoss));
            data.setStartAt(d.getStartAt());
            data.setEndAt(d.getEndAt());
            destData.add(data);
        }

        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

    public static double calculateRSI(double ag, double al) {

        if (ag == 0) {
            return 100;
        }

        if (al == 0) {
            return 0;
        }

        double rs = DoubleUtils.div(ag, al);
        return DoubleUtils.sub(100, DoubleUtils.div(100, DoubleUtils.add(1, rs)));
    }

    public static <T extends MetaData> List<Data> calculateStochRSI(List<T> srcData, int interval) {
        if (srcData.size() <= interval) {
            return Collections.EMPTY_LIST;
        }

        double lowest = Double.MAX_VALUE;
        double highest = 0;

        int max = srcData.size() - 1;
        int startIndex = max - interval;
        List<Data> destData = new ArrayList<>();
        for (int i = max; i >= 0; i--) {
            T d = srcData.get(i);
            double rsi = d.getValue();
            if (rsi > highest) {
                highest = rsi;
            } else if (rsi < lowest) {
                lowest = rsi;
            }

            if (i <= startIndex) {
                Data data = new Data();
                data.setValue(DoubleUtils.mul(calculateSRSI(rsi, lowest, highest), 100));
                data.setStartAt(d.getStartAt());
                data.setEndAt(d.getEndAt());
                destData.add(data);
            }
        }
        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

    private static Double calculateSRSI(double rsi, double lowest, double highest) {
        return DoubleUtils.div(DoubleUtils.sub(rsi, lowest), DoubleUtils.sub(highest, lowest));
    }
}
