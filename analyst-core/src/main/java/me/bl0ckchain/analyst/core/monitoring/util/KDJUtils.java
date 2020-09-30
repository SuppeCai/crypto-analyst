package me.bl0ckchain.analyst.core.monitoring.util;

import me.bl0ckchain.analyst.core.monitoring.indicator.Data;
import me.bl0ckchain.analyst.core.monitoring.indicator.MetaData;
import me.bl0ckchain.analyst.core.util.DoubleUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 01/10/2018
 */
public class KDJUtils {

    public static final double INIT = 50;

    public static <T extends MetaData> List<Data> calculateK(List<T> srcData, int period, int signal) {
        if (srcData.size() <= period) {
            return Collections.EMPTY_LIST;
        }

        int max = srcData.size() - 1;
        List<Data> destData = new ArrayList<>();
        for (int i = max - period + 1; i >= 0; i--) {

            double highest = 0;
            double lowest = Double.MAX_VALUE;
            for (int j = period - 1; j > 0; j--) {
                double value = srcData.get(i + j).getValue();
                if (value < lowest) {
                    lowest = value;
                }
                if (value > highest) {
                    highest = value;
                }
            }

            T s = srcData.get(i);
            double value = s.getValue();

            if (value < lowest) {
                lowest = value;
            }
            if (value > highest) {
                highest = value;
            }

            double rsv = calculateRSV(value, highest, lowest);
            double preK;
            if (i == max - period + 1) {
                preK = INIT;
            } else {
                preK = destData.get(destData.size() - 1).getValue();
            }
            double k = calculateK0(preK, rsv, signal);
            Data d = new Data();
            d.setValue(k);
            d.setStartAt(s.getStartAt());
            d.setEndAt(s.getEndAt());
            destData.add(d);
        }

        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

    public static <T extends MetaData> List<Data> calculateD(List<T> srcData, int signal) {
        if (CollectionUtils.isEmpty(srcData)) {
            return Collections.EMPTY_LIST;
        }

        List<Data> destData = new ArrayList<>();

        int max = srcData.size() - 1;
        for (int i = max; i >= 0; i--) {
            T s = srcData.get(i);
            double value = s.getValue();

            double preD;
            if (i == max) {
                preD = INIT;
            } else {
                preD = destData.get(destData.size() - 1).getValue();
            }

            double d = calculateD0(preD, value, signal);
            Data data = new Data();
            data.setValue(d);
            data.setStartAt(s.getStartAt());
            data.setEndAt(s.getEndAt());
            destData.add(data);
        }

        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

    public static <T extends MetaData> List<Data> calculateJ(List<T> kList, List<T> dList) {
        if (CollectionUtils.isEmpty(kList) || kList.size() != dList.size()) {
            return Collections.EMPTY_LIST;
        }

        List<Data> destData = new ArrayList<>();
        int max = kList.size() - 1;
        for (int i = max; i >= 0; i--) {
            T kData = kList.get(i);
            double k = kData.getValue();
            double d = dList.get(i).getValue();
            double j = calculateJ0(k, d);
            Data data = new Data();
            data.setValue(j);
            data.setStartAt(kData.getStartAt());
            data.setEndAt(kData.getEndAt());
            destData.add(data);
        }

        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

    private static double calculateRSV(double close, double highest, double lowest) {
        return DoubleUtils.mul(100, DoubleUtils.div(DoubleUtils.sub(close, lowest), DoubleUtils.sub(highest, lowest)));
    }

    private static double calculateK0(double prevK, double rsv, double signal) {
        return DoubleUtils.add(DoubleUtils.mul(1 - DoubleUtils.div(1, signal), prevK), DoubleUtils.mul(DoubleUtils.div(1, signal), rsv));
    }

    private static double calculateD0(double prevD, double k, double signal) {
        return DoubleUtils.add(DoubleUtils.mul(1 - DoubleUtils.div(1, signal), prevD), DoubleUtils.mul(DoubleUtils.div(1, signal), k));
    }

    private static double calculateJ0(double k, double d) {
        return DoubleUtils.sub(DoubleUtils.mul(3, k), DoubleUtils.mul(2, d));
    }

}
