package me.bl0ckchain.analyst.core.converter;

import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.sdk.bean.Converter;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 09/07/2018
 */
@Component
public class NoticeLogConverter extends Converter<NoticeLog, Notice, Long> {

    @Override
    protected Long getId(Notice notice) {
        return null;
    }

    @Override
    protected NoticeLog convert(Notice notice) {
        NoticeLog log = new NoticeLog();
        log.setExchangeId(notice.getExchange().getId());
        log.setAssetPairId(notice.getAssetPair().getId());
        log.setPeriodId(notice.getPeriod().getId());
        log.setStrategyId(notice.getStrategy().getId());
        log.setDescription(notice.getStrategy().getDescription());
        log.setNotifyAt(notice.getNotifyAt());
        return log;
    }

}
