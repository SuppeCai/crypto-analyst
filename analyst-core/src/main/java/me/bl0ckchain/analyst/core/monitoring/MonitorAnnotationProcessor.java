package me.bl0ckchain.analyst.core.monitoring;

import me.bl0ckchain.analyst.core.entity.Analysis;
import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.Notification;
import me.bl0ckchain.analyst.core.monitoring.processor.AnalyzerProcessor;
import me.bl0ckchain.analyst.core.monitoring.processor.IndicatorProcessor;
import me.bl0ckchain.analyst.core.monitoring.processor.NotifierProcessor;
import me.bl0ckchain.analyst.core.monitoring.util.ComponentUtils;
import me.bl0ckchain.analyst.core.service.MonitorService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 28/06/2018
 */
@Component
public class MonitorAnnotationProcessor implements ApplicationListener<ContextRefreshedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IndicatorProcessor indicatorProcessor;
    @Autowired
    private AnalyzerProcessor analyzerProcessor;
    @Autowired
    private NotifierProcessor notifierProcessor;
    @Autowired
    private MonitorService monitorService;

    private Map<String, Indication> indicationMap;

    private Map<String, Analysis> analyseMap;

    private Map<String, Notification> notificationMap;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        init();

        ApplicationContext context = event.getApplicationContext();
        Map<String, Indicator> indicatorMap = context.getBeansOfType(Indicator.class);
        Map<String, Analyzer> analyzerMap = context.getBeansOfType(Analyzer.class);
        Map<String, Notifier> notifierMap = context.getBeansOfType(Notifier.class);

        if (MapUtils.isNotEmpty(indicatorMap)) {
            for (Indicator indicator : indicatorMap.values()) {
                me.bl0ckchain.analyst.core.monitoring.annotation.Indicator _indicator = AnnotationUtils.findAnnotation(indicator.getClass(), me.bl0ckchain.analyst.core.monitoring.annotation.Indicator.class);
                String name = _indicator.value();
                if (StringUtils.isEmpty(name)) {
                    name = indicatorProcessor.guessName(indicator.getClass());
                }
                indicator.setIndication(indicationMap.get(name));
                indicatorProcessor.putIndicator(name, indicator);
            }
        }

        if (MapUtils.isNotEmpty(analyzerMap)) {
            for (Analyzer analyzer : analyzerMap.values()) {
                me.bl0ckchain.analyst.core.monitoring.annotation.Analyzer _analyzer = AnnotationUtils.findAnnotation(analyzer.getClass(), me.bl0ckchain.analyst.core.monitoring.annotation.Analyzer.class);
                String name = _analyzer.value();
                if (StringUtils.isEmpty(name)) {
                    name = analyzerProcessor.guessName(analyzer.getClass());
                }
                analyzer.setAnalysis(analyseMap.get(name));
                analyzerProcessor.putAnalyzer(name, analyzer);
            }
        }

        if (MapUtils.isNotEmpty(notifierMap)) {
            for (Notifier notifier : notifierMap.values()) {
                me.bl0ckchain.analyst.core.monitoring.annotation.Notifier _notifier = AnnotationUtils.findAnnotation(notifier.getClass(), me.bl0ckchain.analyst.core.monitoring.annotation.Notifier.class);
                String name = _notifier.value();
                if (StringUtils.isEmpty(name)) {
                    name = notifierProcessor.guessName(notifier.getClass());
                }
                notifier.setNotification(notificationMap.get(name));
                notifierProcessor.putNotifier(name, notifier);
            }
        }
    }

    private void init() {
        indicationMap = ComponentUtils.toCodeKeyMap(monitorService.listAllIndications());
        analyseMap = ComponentUtils.toCodeKeyMap(monitorService.listAllAnalyses());
        notificationMap = ComponentUtils.toCodeKeyMap(monitorService.listAllNotification());
    }

}
