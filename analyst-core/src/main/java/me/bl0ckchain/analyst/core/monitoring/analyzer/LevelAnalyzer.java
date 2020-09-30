package me.bl0ckchain.analyst.core.monitoring.analyzer;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Analyzer;
import me.bl0ckchain.analyst.core.monitoring.indicator.Data;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.analyst.core.monitoring.util.CrossUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Support & Resistance level.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 07/06/2018
 */
@Analyzer
public class LevelAnalyzer extends AbstractAnalyzer {

    public static final int MINIMUM_SIZE = 2;

    @Override
    public void analyze(Result result) {

        List<Kline> srcData = result.getSrcData();
        List<Data> destData = result.getDestData();

        boolean isEmpty = CollectionUtils.isEmpty(srcData) || CollectionUtils.isEmpty(destData);
        boolean isValid = srcData.size() >= MINIMUM_SIZE && destData.size() >= MINIMUM_SIZE;
        if (isEmpty || !isValid) {
            return;
        }

        double latestSrcData = srcData.get(0).getClose().doubleValue();
        double latestDestData = destData.get(0).getValue();

        double prevSrcData = srcData.get(1).getClose().doubleValue();
        double prevDestData = destData.get(1).getValue();

        double latestDiff = latestSrcData - latestDestData;
        double latestRatio = latestDiff / latestDestData;
        double prevDiff = prevSrcData - prevDestData;
        CrossEnum crossType = CrossUtils.getCrossType(latestDiff, prevDiff);

        Level level = new Level(latestDestData, latestDiff, latestRatio, crossType);
        if (latestDiff > 0) {
            level.setLevelEnum(LevelEnum.SUPPORT);
        } else {
            level.setLevelEnum(LevelEnum.RESISTANCE);
        }
        result.setLevel(level);
    }

    @Override
    public String explain(Result result) {
        return null;
    }
}
