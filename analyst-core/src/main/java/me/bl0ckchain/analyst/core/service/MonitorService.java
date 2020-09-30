package me.bl0ckchain.analyst.core.service;

import me.bl0ckchain.analyst.core.entity.*;
import me.bl0ckchain.analyst.core.repository.AnalysisRepo;
import me.bl0ckchain.analyst.core.repository.IndicationRepo;
import me.bl0ckchain.analyst.core.repository.NotificationRepo;
import me.bl0ckchain.analyst.core.util.PeriodUtils;
import me.bl0ckchain.sdk.mybatis.StatusEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 03/06/2018
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MonitorService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IndicationRepo indicationRepo;
    @Autowired
    private AnalysisRepo analysisRepo;
    @Autowired
    private NotificationRepo notificationRepo;

    public List<Configuration> matchConfigurations(Kline kline, List<Configuration> configurations) {

        if (CollectionUtils.isEmpty(configurations)) {
            return Collections.EMPTY_LIST;
        }

        List<Configuration> match = new ArrayList<>();
        for (Configuration configuration : configurations) {
            Long configurationId = configuration.getId();
            List<Period> periods = configuration.getPeriods();
            if (CollectionUtils.isEmpty(configuration.getIndications())) {
                logger.error("Indications in Configuration can not be null. configurationId:" + configurationId);
                continue;
            }
            if (CollectionUtils.isEmpty(periods)) {
                logger.error("Periods in Configuration can not be null. configurationId:" + configurationId);
                continue;
            }
            if (CollectionUtils.isEmpty(configuration.getStrategies())) {
                logger.error("Strategies in Configuration can not be null. configurationId:" + configurationId);
                continue;
            }
            if (CollectionUtils.isEmpty(configuration.getNotifications())) {
                logger.error("Notifications in Configuration can not be null. configurationId:" + configurationId);
                continue;
            }
            for (Period period : periods) {
                if (PeriodUtils.match(kline, period)) {
                    match.add(configuration);
                    break;
                }
            }
        }
        return match;
    }

    public List<Indication> listAllIndications() {
        List<Long> ids = indicationRepo.listIds();
        return indicationRepo.list(ids);
    }

    public List<Analysis> listAllAnalyses() {
        List<Long> ids = analysisRepo.listIds();
        return analysisRepo.list(ids);
    }

    public List<Notification> listAllNotification() {
        List<Long> ids = notificationRepo.listIds();
        return notificationRepo.list(ids);
    }

}
