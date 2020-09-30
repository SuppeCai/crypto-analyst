package me.bl0ckchain.analyst.core.monitoring.analyzer;

import me.bl0ckchain.analyst.core.entity.Component;
import me.bl0ckchain.analyst.core.entity.ComponentParam;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.Indicator;
import me.bl0ckchain.analyst.core.monitoring.annotation.Analyzer;
import me.bl0ckchain.analyst.core.monitoring.indicator.Data;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.analyst.core.util.DoubleUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 28/09/2018
 */
@Analyzer
public class DivergenceAnalyzer extends AbstractAnalyzer {

    public static final int DEFAULT_INTERVAL = 30;

    @Override
    public void analyze(Result result) {

        List<Data> destData = result.getDestData();
        if (CollectionUtils.isEmpty(destData) || destData.size() < DEFAULT_INTERVAL) {
            return;
        }

        double latestClose = result.getLatestSrcData().getClose().doubleValue();
        double latestValue = result.getLatestDestData().getValue();
        double prevLowClose = 0;
        double prevHighClose = 0;
        double prevLowValue = Double.MAX_VALUE;
        double prevHighValue = -Double.MAX_VALUE;
        int prevLowValueIndex = 0;
        int prevHighValueIndex = 0;

        List<Kline> srcData = result.getSrcData();
        for (int i = 0; i < DEFAULT_INTERVAL; i++) {
            Data data = destData.get(i);
            double value = data.getValue();
            if (value < prevLowValue) {
                prevLowValue = value;
                prevLowClose = srcData.get(i).getClose().doubleValue();
                prevLowValueIndex = i;
            }

            if (value > prevHighValue) {
                prevHighValue = value;
                prevHighClose = srcData.get(i).getClose().doubleValue();
                prevHighValueIndex = i;
            }
        }

        double top = Double.MIN_VALUE;
        double bottom = Double.MIN_VALUE;
        List<Component> components = result.getIndication().getComponents();
        if (CollectionUtils.isNotEmpty(components)) {
            Component component = components.get(0);
            List<ComponentParam> params = component.getParams();
            for (ComponentParam param : params) {
                if (param.getKey().equals(Indicator.TOP_PARAM)) {
                    top = Double.valueOf(param.getValue());
                }
                if (param.getKey().equals(Indicator.BOTTOM_PARAM)) {
                    bottom = Double.valueOf(param.getValue());
                }
            }
        }

        boolean isAbove = true;
        boolean isBelow = true;

        if (top != Double.MIN_VALUE) {
            isAbove = prevHighValue > top;
        }

        if (bottom != Double.MIN_VALUE) {
            isBelow = prevLowValue < bottom;
        }

        boolean isPositive = latestClose < prevLowClose && latestValue > prevLowValue && isBelow;
        boolean isNegative = latestClose > prevHighClose && latestValue < prevHighValue && isAbove;

        DivergenceEnum divergenceEnum = DivergenceEnum.NONE;
        Double scale = Double.MIN_VALUE;
        Integer interval = 0;
        if (isPositive) {
            divergenceEnum = DivergenceEnum.POSITIVE;
            scale = DoubleUtils.div(DoubleUtils.sub(prevLowClose, latestClose), prevLowClose, DoubleUtils.FOUR_PRECISION);
            interval = prevLowValueIndex;
        } else if (isNegative) {
            divergenceEnum = DivergenceEnum.NEGATIVE;
            scale = DoubleUtils.div(DoubleUtils.sub(latestClose, prevHighClose), prevHighClose, DoubleUtils.FOUR_PRECISION);
            interval = prevHighValueIndex;
        }

        Divergence divergence = new Divergence();
        divergence.setDivergenceEnum(divergenceEnum);
        divergence.setScale(scale);
        divergence.setInterval(interval);
        result.setDivergence(divergence);
    }

    @Override
    public String explain(Result result) {
        return null;
    }
}
