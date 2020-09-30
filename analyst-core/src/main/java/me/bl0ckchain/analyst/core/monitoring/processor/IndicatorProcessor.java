package me.bl0ckchain.analyst.core.monitoring.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.Indicator;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.analyst.core.monitoring.indicator.ResultStatusEnum;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 02/06/2018
 */
@Component
public class IndicatorProcessor extends AbstractProcessor<Indicator> {

    private Map<String, Indicator> indicatorMap = new HashMap<>();

    /**
     * Calculate indicators and return result list.
     *
     * @param indications
     * @param srcData
     * @return
     */
    public List<Result> calculate(List<Indication> indications, List<Kline> srcData) {

        List<Result> results = new ArrayList<>();
        for (Indication indication : indications) {
            Indicator indicator = getIndicator(indication);
            List<Result> list = indicator.calculate(srcData);
            for (Result result : list) {
                if (ResultStatusEnum.SUCCESS.equals(result.getStatusEnum())) {
                    result.setIndication(indication);
                    results.add(result);
                }
            }
        }

        return results;
    }

    public Indicator getIndicator(String name) {
        return this.indicatorMap.get(name);
    }

    public void putIndicator(String name, Indicator indicator) {
        this.indicatorMap.put(name, indicator);
    }

    public Indicator getIndicator(Indication indication) {
        return this.indicatorMap.get(indication.getCode());
    }

    public List<Indicator> listIndicators(List<Indication> indications) {
        List<Indicator> indicators = new ArrayList<>();
        for (Indication indication : indications) {
            Indicator indicator = this.indicatorMap.get(indication.getCode());
            indicators.add(indicator);
        }
        return indicators;
    }
}
