package me.bl0ckchain.analyst.core.monitoring.indicator;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Indicator;
import me.bl0ckchain.analyst.core.monitoring.util.KDJUtils;
import me.bl0ckchain.analyst.core.monitoring.util.RSIUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 20/09/2018
 */
@Indicator("RSI")
public class RSI extends AbstractIndicator {

    public static final String STOCH_RSI = "SRSI";

    @Override
    public List<Result> calculate(List<Kline> srcData) {
        if (!check(srcData)) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> intervals = indication.getIntervals();
        int interval = 0;
        if (CollectionUtils.isNotEmpty(intervals)) {
            interval = intervals.get(0);
        }

        if (interval <= 0 || srcData.size() < interval) {
            return Collections.EMPTY_LIST;
        }

        List<Data> destData = RSIUtils.calculate(srcData, interval);

        List<Result> results = new ArrayList<>();
        Result result = new Result();
        result.setStatusEnum(ResultStatusEnum.SUCCESS);
        result.setIndication(indication);
        result.setSrcData(srcData);
        result.setDestData(destData);
        results.add(result);

        if (destData.size() > interval) {
            List<Data> kData = KDJUtils.calculateK(destData, interval, 3);
            List<Data> dData = KDJUtils.calculateD(kData, 3);

            CrossResult stochResult = new CrossResult();
            stochResult.setStatusEnum(ResultStatusEnum.SUCCESS);
            stochResult.setName(STOCH_RSI);
            stochResult.setIndication(indication);
            stochResult.setSrcData(srcData);
            stochResult.setDestData(kData);
            stochResult.setFastDestData(kData);
            stochResult.setSlowDestData(dData);
            stochResult.setTop(80);
            stochResult.setBottom(20);
            results.add(stochResult);
        }

        return results;
    }
}
