package me.bl0ckchain.analyst.core.monitoring.indicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Indicator;
import me.bl0ckchain.analyst.core.monitoring.util.MFIUtils;
import org.apache.commons.collections4.CollectionUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 20/09/2018
 */
@Indicator("MFI")
public class MFI extends AbstractIndicator {

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

        List<Data> destData = MFIUtils.calculate(srcData, interval);

        List<Result> results = new ArrayList<>();
        Result result = new Result();
        result.setStatusEnum(ResultStatusEnum.SUCCESS);
        result.setIndication(indication);
        result.setSrcData(srcData);
        result.setDestData(destData);
        results.add(result);
        return results;
    }
}
