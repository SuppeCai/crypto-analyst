package me.bl0ckchain.analyst.core.monitoring.indicator;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Indicator;
import me.bl0ckchain.analyst.core.monitoring.util.KDJUtils;
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
@Indicator("KDJ")
public class KDJ extends AbstractIndicator {

    @Override
    public List<Result> calculate(List<Kline> srcData) {

        if (!check(srcData)) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> intervals = indication.getIntervals();
        int period = 0;
        int signal = 0;

        if (CollectionUtils.isNotEmpty(intervals) && intervals.size() >= 2) {
            period = intervals.get(0);
            signal = intervals.get(1);
        }

        if (period <= 0 || signal <= 0 || srcData.size() < period) {
            return Collections.EMPTY_LIST;
        }

        List<Data> kData = KDJUtils.calculateK(srcData, period, signal);
        List<Data> dData = KDJUtils.calculateD(kData, signal);
        List<Data> jData = KDJUtils.calculateJ(kData, dData);


        List<Result> results = new ArrayList<>();
        CrossResult result = new CrossResult();
        result.setIndication(this.indication);
        result.setSrcData(srcData);
        if (CollectionUtils.isNotEmpty(kData)) {
            result.setStatusEnum(ResultStatusEnum.SUCCESS);
            result.setDestData(kData);
            result.setFastDestData(jData);
            result.setSlowDestData(dData);
            result.setTop(50);
            result.setBottom(50);
        } else {
            result.setStatusEnum(ResultStatusEnum.NO_RESULT);
        }
        results.add(result);
        return results;
    }
}
