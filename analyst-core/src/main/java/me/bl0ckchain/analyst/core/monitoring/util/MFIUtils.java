package me.bl0ckchain.analyst.core.monitoring.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.indicator.Data;
import me.bl0ckchain.analyst.core.monitoring.indicator.MetaData;
import me.bl0ckchain.analyst.core.util.DoubleUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/09/2018
 */
public class MFIUtils {

    public static final double THREE = 3.0;

    public static <T extends MetaData> List<Data> calculate(List<T> srcData, int interval) {
        if (srcData.size() <= interval) {
            return Collections.EMPTY_LIST;
        }

        boolean isKline = srcData.get(0) instanceof Kline;
        if (!isKline) {
            return Collections.EMPTY_LIST;
        }

        List<Data> destData = new ArrayList<>();

        int max = srcData.size() - 1;
        for (int i = max - interval; i >= 0; i--) {
            T d = srcData.get(i);
            Kline prevData = (Kline) srcData.get(i + interval);
            double prevTP = calculateTypicalPrice(
                    prevData.getHigh().doubleValue(),
                    prevData.getLow().doubleValue(),
                    prevData.getClose().doubleValue());
            double PMF = 0;
            double NMF = 0;
            for (int j = i + interval - 1; j >= i; j--) {
                Kline data = (Kline) srcData.get(j);
                double high = data.getHigh().doubleValue();
                double low = data.getLow().doubleValue();
                double close = data.getClose().doubleValue();
                double volume = data.getVolume().doubleValue();

                double TP = calculateTypicalPrice(high, low, close);
                double MF = calculateRawMoneyFlow(TP, volume);
                if (TP > prevTP) {
                    PMF = DoubleUtils.add(PMF, MF);
                } else {
                    NMF = DoubleUtils.add(NMF, MF);
                }
                prevTP = TP;
            }
            double MR = DoubleUtils.div(PMF, NMF);
            double MFI = calculateMoneyFlowIndex(MR);
            Data data = new Data();
            data.setValue(MFI);
            data.setStartAt(d.getStartAt());
            data.setEndAt(d.getEndAt());
            destData.add(data);
        }

        Collections.sort(destData, (o1, o2) -> o2.getStartAt().compareTo(o1.getStartAt()));
        return destData;
    }

    private static Double calculateTypicalPrice(double high, double low, double close) {
        return DoubleUtils.div(DoubleUtils.add(DoubleUtils.add(high, low), close), THREE);
    }

    private static Double calculateRawMoneyFlow(double typicalPrice, double volume) {
        return DoubleUtils.mul(typicalPrice, volume);
    }

    private static Double calculateMoneyFlowIndex(double moneyFlowRatio) {
        return DoubleUtils.sub(100, DoubleUtils.div(100, DoubleUtils.add(1, moneyFlowRatio)));
    }
}
