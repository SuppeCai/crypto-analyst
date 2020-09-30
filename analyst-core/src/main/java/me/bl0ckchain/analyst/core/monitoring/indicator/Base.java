package me.bl0ckchain.analyst.core.monitoring.indicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.annotation.Indicator;
import me.bl0ckchain.analyst.core.util.DoubleUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 03/09/2018
 */
@Indicator("BASE")
public class Base extends AbstractIndicator {

    public static final int AVG_DAYS = 7;

    @Override
    public List<Result> calculate(List<Kline> srcData) {

        if (!check(srcData) || srcData.size() < AVG_DAYS + 2) {
            return Collections.EMPTY_LIST;
        }

        List<Result> results = new ArrayList<>();
        BaseResult baseResult = new BaseResult();
        baseResult.setIndication(this.indication);
        baseResult.setSrcData(srcData);

        Kline latestSrcData = srcData.get(0);
        double open = latestSrcData.getOpen().doubleValue();
        double close = latestSrcData.getClose().doubleValue();
        double change = DoubleUtils.div(DoubleUtils.sub(close, open), open);
        baseResult.setChange(change);

        Kline secondSrcData = srcData.get(1);
        double secondOpen = secondSrcData.getOpen().doubleValue();
        double secondClose = secondSrcData.getClose().doubleValue();
        double secondChange = DoubleUtils.div(DoubleUtils.sub(secondClose, secondOpen), secondOpen);
        baseResult.setSecondChange(secondChange);

        double volumeAVG = 0;
        for (int i = 1; i <= AVG_DAYS; i++) {
            volumeAVG = DoubleUtils.add(volumeAVG, srcData.get(i).getVolume().doubleValue());
        }
        volumeAVG = DoubleUtils.div(volumeAVG, 7);
        baseResult.setVolumeAVG(volumeAVG);

        double secondVolumeAVG = 0;
        for (int i = 2; i <= AVG_DAYS + 1; i++) {
            secondVolumeAVG = DoubleUtils.add(secondVolumeAVG, srcData.get(i).getVolume().doubleValue());
        }
        secondVolumeAVG = DoubleUtils.div(secondVolumeAVG, 7);
        baseResult.setSecondVolumeAVG(secondVolumeAVG);

        baseResult.setStatusEnum(ResultStatusEnum.SUCCESS);
        results.add(baseResult);
        return results;
    }
}
