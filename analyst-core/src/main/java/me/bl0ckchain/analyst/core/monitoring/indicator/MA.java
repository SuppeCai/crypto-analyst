package me.bl0ckchain.analyst.core.monitoring.indicator;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Indicator;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MA monitoring, simple average value of the close data.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 02/06/2018
 */
@Indicator("MA")
public class MA extends AbstractIndicator {

    @Override
    public List<Result> calculate(List<Kline> srcData) {

        if (!check(srcData)) {
            return Collections.EMPTY_LIST;
        }

        List<Result> results = new ArrayList<>();
        List<Integer> intervals = indication.getIntervals();
        int size = srcData.size();
        for (Integer interval : intervals) {
            if (interval > srcData.size()) {
                continue;
            }
            List<Data> destData = new ArrayList<>();
            for (int i = interval; i < size; i++) {
                List<Kline> sub = srcData.subList(i - interval, i);
                BigDecimal sum = new BigDecimal(0);
                for (Kline k : sub) {
                    sum = sum.add(k.getClose());
                }
                BigDecimal avg = sum.divide(new BigDecimal(interval), 8, RoundingMode.HALF_UP);
                Kline last = sub.get(0);
                Data data = new Data();
                data.setValue(avg.doubleValue());
                data.setStartAt(last.getStartAt());
                data.setEndAt(last.getEndAt());
                destData.add(data);
            }

            MAResult maResult = new MAResult();
            maResult.setIndication(this.indication);
            maResult.setSrcData(srcData);
            maResult.setInterval(interval);
            if (CollectionUtils.isNotEmpty(destData)) {
                maResult.setStatusEnum(ResultStatusEnum.SUCCESS);
                maResult.setDestData(destData);
            } else {
                maResult.setStatusEnum(ResultStatusEnum.NO_RESULT);
            }
            results.add(maResult);
        }

        return results;
    }

}
