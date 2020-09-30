package me.bl0ckchain.analyst.core.monitoring.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.googlecode.aviator.AviatorEvaluator;
import me.bl0ckchain.analyst.core.cache.CommonCache;
import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.monitoring.MonitorMetadata;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.repository.StrategyRepo;
import me.bl0ckchain.analyst.core.util.NoticeUtils;
import me.bl0ckchain.analyst.core.util.PeriodUtils;
import me.bl0ckchain.sdk.utils.CacheUtils;
import me.bl0ckchain.sdk.utils.PlaceholderUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/06/2018
 */
@Component
public class StrategyProcessor {

    @Autowired
    private StrategyRepo strategyRepo;

    public void replaceExpressionPlaceholders(List<Strategy> strategies) {

        List<Strategy> targetStrategies = new ArrayList<>();

        Set<String> placeholders = new HashSet<>();
        for (Strategy strategy : strategies) {
            String expression = strategy.getExpression();
            List<String> list = PlaceholderUtils.findAllPlaceholders(expression);
            if (CollectionUtils.isNotEmpty(list)) {
                placeholders.addAll(list);
                targetStrategies.add(strategy);
            }
        }

        if (CollectionUtils.isEmpty(placeholders)) {
            return;
        }

        Properties properties = new Properties();
        for (String placeholder : placeholders) {
            try {
                Long strategyId = Long.valueOf(placeholder);
                Strategy strategy = strategyRepo.find(strategyId);
                if (strategy != null) {
                    properties.setProperty(placeholder, strategy.getExpression());
                }
            } catch (Exception e) {
            }
        }

        for (Strategy strategy : targetStrategies) {
            String expression = PlaceholderUtils.replacePlaceholders(strategy.getExpression(), properties);
            strategy.setExpression(expression);
        }
    }

    public static void replaceDescriptionPlaceholders(Strategy strategy, Map<String, Object> context) {

        String description = strategy.getDescription();
        List<String> placeholders = PlaceholderUtils.findAllPlaceholders(description);

        if (CollectionUtils.isEmpty(placeholders)) {
            return;
        }

        Properties properties = new Properties();
        for (String placeholder : placeholders) {
            try {
                Object obj = AviatorEvaluator.execute(placeholder, context);
                if (obj != null) {
                    if (obj instanceof Double) {
                        BigDecimal bd = new BigDecimal((Double) obj);
                        obj = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    properties.setProperty(placeholder, obj.toString());
                }
            } catch (Exception e) {
            }
        }

        description = PlaceholderUtils.replacePlaceholders(description, properties);
        strategy.setDescription(description);
    }
}
