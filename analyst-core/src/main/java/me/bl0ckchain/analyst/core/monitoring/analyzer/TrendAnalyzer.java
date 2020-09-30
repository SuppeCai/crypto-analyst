package me.bl0ckchain.analyst.core.monitoring.analyzer;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Analyzer;
import me.bl0ckchain.analyst.core.monitoring.indicator.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/06/2018
 */
@Analyzer
public class TrendAnalyzer extends AbstractAnalyzer {

    @Override
    public void analyze(Result result) {

        if (result instanceof CrossResult) {
            CrossResult crossResult = (CrossResult) result;
            List<Data> fastData = crossResult.getFastDestData();
            List<Data> slowData = crossResult.getSlowDestData();
            crossResult.setFastTrend(analyzeTrend(fastData));
            crossResult.setSlowTrend(analyzeTrend(slowData));
        } else if (result instanceof BaseResult) {
            List<Kline> srcData = result.getSrcData();
            result.setTrend(analyzeTrend(srcData));
        } else {
            List<Data> destData = result.getDestData();
            result.setTrend(analyzeTrend(destData));
        }
    }

    private Trend analyzeTrend(List<? extends MetaData> data) {

        if (CollectionUtils.isEmpty(data)) {
            return null;
        }

        TrendEnum trendEnum = null;
        List<Data> slopes = new ArrayList<>();
        Integer duration = 0;
        boolean isOver = false;
        for (int i = 0; i < data.size() - 1; i++) {
            MetaData now = data.get(i);
            MetaData next = data.get(i + 1);
            Data slope = new Data();
            slope.setValue(now.getValue() - next.getValue());
            slope.setStartAt(now.getStartAt());
            slope.setEndAt(now.getEndAt());
            slopes.add(slope);

            TrendEnum temp = toTrendEnum(slope.getValue());
            if (i == 0) {
                trendEnum = temp;
            }

            if (!isOver && trendEnum == temp) {
                duration++;
            }

            if (!isOver && trendEnum != temp) {
                isOver = true;
            }

        }

        Trend trend = new Trend();
        trend.setDuration(duration);
        trend.setSlopes(slopes);
        trend.setTrendEnum(trendEnum);
        return trend;
    }

    @Override
    public String explain(Result result) {
        return null;
    }

    private TrendEnum toTrendEnum(Double data) {

        TrendEnum trendEnum;
        if (data > 0) {
            trendEnum = TrendEnum.RISE;
        } else if (data < 0) {
            trendEnum = TrendEnum.FALL;
        } else {
            trendEnum = TrendEnum.TRANSVERSE;
        }
        return trendEnum;
    }

}
