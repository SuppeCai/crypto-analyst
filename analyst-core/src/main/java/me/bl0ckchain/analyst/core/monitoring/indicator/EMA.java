package me.bl0ckchain.analyst.core.monitoring.indicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Indicator;
import me.bl0ckchain.analyst.core.monitoring.util.EMAUtils;
import org.apache.commons.collections4.CollectionUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 19/07/2018
 */
@Indicator("EMA")
public class EMA extends AbstractIndicator {

    @Override
    public List<Result> calculate(List<Kline> srcData) {

        if (!check(srcData)) {
            return Collections.EMPTY_LIST;
        }

        List<Result> results = new ArrayList<>();
        List<Integer> intervals = indication.getIntervals();
        for (Integer interval : intervals) {
            if (interval > srcData.size()) {
                continue;
            }

            List<Data> destData = EMAUtils.calculate(srcData, interval);
            MAResult result = new MAResult();
            result.setIndication(this.indication);
            result.setSrcData(srcData);
            result.setInterval(interval);
            if (CollectionUtils.isNotEmpty(destData)) {
                result.setStatusEnum(ResultStatusEnum.SUCCESS);
                result.setDestData(destData);
            } else {
                result.setStatusEnum(ResultStatusEnum.NO_RESULT);
            }
            results.add(result);
        }
        return results;
    }

}
