package me.bl0ckchain.analyst.core.converter;

import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.model.NoticeDTO;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.util.NoticeUtils;
import me.bl0ckchain.sdk.bean.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 12/07/2018
 */
@Component
public class NoticeDTOConverter extends Converter<NoticeDTO, NoticeLog, Long> {

    @Override
    protected Long getId(NoticeLog noticeLog) {
        return noticeLog.getId();
    }

    @Override
    protected NoticeDTO convert(NoticeLog noticeLog) {
        NoticeDTO dto = new NoticeDTO();
        dto.setAssetPairId(noticeLog.getAssetPairId());
        dto.setNotice(NoticeUtils.toString(noticeLog));
        dto.setNotifyAt(noticeLog.getNotifyAt());
        return dto;
    }
}
