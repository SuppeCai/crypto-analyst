package me.bl0ckchain.analyst.core.monitoring.processor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.monitoring.indicator.MAResult;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 07/07/2018
 */
@Component
public class ExpressionParser {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Map<String, Object> generateContext(List<Result> results) {

        if (CollectionUtils.isEmpty(results)) {
            return Collections.EMPTY_MAP;
        }

        Map<String, Object> context = new HashMap<>();
        for (Result result : results) {
            Indication indication = result.getIndication();
            String key = result.getName();
            if (StringUtils.isBlank(key)) {
                key = indication.getCode();
            }

            if (result instanceof MAResult) {
                key += ((MAResult) result).getInterval();
            }

            if (StringUtils.isNotBlank(key)) {
                context.put(key, result);
            }
        }
        return context;
    }

    public Boolean execute(Strategy strategy, Map<String, Object> context) {
        Boolean isMatch = false;
        try {
            isMatch = (Boolean) AviatorEvaluator.execute(strategy.getExpression(), context);
        } catch (Exception e) {
            //logger.debug("Expression error:", e);
        }
        return isMatch;
    }

}
