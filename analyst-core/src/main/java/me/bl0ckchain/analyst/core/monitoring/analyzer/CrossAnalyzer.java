package me.bl0ckchain.analyst.core.monitoring.analyzer;

import java.util.ArrayList;
import java.util.List;

import me.bl0ckchain.analyst.core.monitoring.annotation.Analyzer;
import me.bl0ckchain.analyst.core.monitoring.indicator.CrossResult;
import me.bl0ckchain.analyst.core.monitoring.indicator.Data;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.analyst.core.monitoring.util.CrossUtils;
import org.apache.commons.collections4.CollectionUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 31/08/2018
 */
@Analyzer
public class CrossAnalyzer extends AbstractAnalyzer {

    public static final int MINIMAL_SIZE = 2;

    @Override
    public boolean support(Result result) {
        return result != null && CrossResult.class == result.getClass();
    }

    @Override
    public void analyze(Result result) {
        if (!(result instanceof CrossResult)) {
            return;
        }

        CrossResult crossResult = (CrossResult) result;
        List<Data> fastData = crossResult.getFastDestData();
        List<Data> slowData = crossResult.getSlowDestData();

        boolean isEmpty = CollectionUtils.isEmpty(fastData) || CollectionUtils.isEmpty(slowData);
        boolean isSmall = fastData.size() < MINIMAL_SIZE || slowData.size() < MINIMAL_SIZE;
        if (isEmpty || isSmall) {
            return;
        }

        double latestFastData = fastData.get(0).getValue();
        double prevFastData = fastData.get(1).getValue();
        double latestSlowData = slowData.get(0).getValue();
        double prevSlowData = slowData.get(1).getValue();

        double latestDiff = latestFastData - latestSlowData;
        double prevDiff = prevFastData - prevSlowData;
        CrossEnum crossEnum = CrossUtils.getCrossType(latestDiff, prevDiff);
        crossResult.setCrossEnum(crossEnum);

        if (crossEnum != CrossEnum.NONE) {
            double top = crossResult.getTop();
            double bottom = crossResult.getBottom();

            CrossRangeEnum crossRangeEnum = CrossRangeEnum.MIDDLE;
            if (prevFastData >= top) {
                crossRangeEnum = CrossRangeEnum.TOP;
            } else if (prevFastData < bottom) {
                crossRangeEnum = CrossRangeEnum.BOTTOM;
            }
            crossResult.setCrossRangeEnum(crossRangeEnum);

            List<Data> crossData = new ArrayList<>();
            crossData.add(fastData.get(0));
            for (int i = 2; i < slowData.size() - 1; i++) {
                latestFastData = prevFastData;
                latestSlowData = prevSlowData;
                prevFastData = fastData.get(i).getValue();
                prevSlowData = slowData.get(i).getValue();

                boolean isUpper = prevFastData >= top && latestFastData >= top;
                boolean isLower = prevFastData <= bottom && latestFastData <= bottom;
                boolean isBetween = prevFastData > bottom && prevFastData < top && latestFastData > bottom && latestFastData < top;
                if (isUpper || isLower || isBetween) {
                    latestDiff = latestFastData - latestSlowData;
                    prevDiff = prevFastData - prevSlowData;
                    CrossEnum prevCrossEnum = CrossUtils.getCrossType(latestDiff, prevDiff);
                    if (crossEnum == prevCrossEnum) {
                        crossData.add(fastData.get(i));
                    }
                } else {
                    break;
                }
            }

            crossResult.setRangePeriod(calculateRangePeriod(slowData, top, bottom, crossRangeEnum));
            crossResult.setCrossTimes(crossData.size());
            crossResult.setCrossData(crossData);
        }
    }

    private int calculateRangePeriod(List<Data> slowData, double top, double bottom, CrossRangeEnum crossRangeEnum) {
        int rangePeriod = 0;
        if (crossRangeEnum == CrossRangeEnum.MIDDLE || CollectionUtils.isEmpty(slowData)) {
            return rangePeriod;
        }

        for (int i = 1; i < slowData.size() - 1; i++) {
            double value = slowData.get(i).getValue();
            switch (crossRangeEnum) {
                case TOP:
                    if (value >= top) {
                        rangePeriod++;
                    } else {
                        return rangePeriod;
                    }
                    break;
                case BOTTOM:
                    if (value <= bottom) {
                        rangePeriod++;
                    } else {
                        return rangePeriod;
                    }
                    break;
                default:
                    return rangePeriod;
            }
        }
        return rangePeriod;
    }

    @Override
    public String explain(Result result) {
        return null;
    }
}
