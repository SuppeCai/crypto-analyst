package me.bl0ckchain.analyst.core.monitoring.indicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Indicator;
import me.bl0ckchain.analyst.core.monitoring.util.EMAUtils;
import me.bl0ckchain.analyst.core.util.DoubleUtils;
import org.apache.commons.collections4.CollectionUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/07/2018
 */
@Indicator("MACD")
public class MACD extends AbstractIndicator {

    @Override
    public List<Result> calculate(List<Kline> srcData) {

        if (!check(srcData)) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> intervals = indication.getIntervals();
        int shortPeriod = intervals.get(0);
        int longPeriod = intervals.get(1);
        int midPeriod = intervals.get(2);

        List<Result> results = new ArrayList<>();
        try {
            List<Data> shortEMA = EMAUtils.calculate(srcData, shortPeriod);
            List<Data> longEMA = EMAUtils.calculate(srcData, longPeriod);
            List<Data> dif = calculate0(shortEMA, longEMA);
            List<Data> dea = EMAUtils.calculate(dif, midPeriod);
            List<Data> macd = calculate0(dif, dea);

            CrossResult crossResult = new CrossResult();
            crossResult.setIndication(this.indication);
            crossResult.setSrcData(srcData);
            if (CollectionUtils.isNotEmpty(macd)) {
                crossResult.setStatusEnum(ResultStatusEnum.SUCCESS);
                crossResult.setTop(0);
                crossResult.setBottom(0);
                crossResult.setDestData(macd);
                crossResult.setFastDestData(dif);
                crossResult.setSlowDestData(dea);
            } else {
                crossResult.setStatusEnum(ResultStatusEnum.NO_RESULT);
            }

            results.add(crossResult);
        } catch (Exception e) {
            logger.error("MACD calculate error:", e);
        }
        return results;
    }

    private List<Data> calculate0(List<Data> shortEMA, List<Data> longEMA) {
        List<Data> result = new ArrayList<>();
        for (int i = 0; i < longEMA.size(); i++) {
            Data shortData = shortEMA.get(i);
            Data d = new Data();
            d.setValue(DoubleUtils.sub(shortData.getValue(), longEMA.get(i).getValue()));
            d.setStartAt(shortData.getStartAt());
            d.setEndAt(shortData.getEndAt());
            result.add(d);
        }
        return result;
    }

}
