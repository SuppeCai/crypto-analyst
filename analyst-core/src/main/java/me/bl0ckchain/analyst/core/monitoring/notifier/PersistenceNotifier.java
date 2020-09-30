package me.bl0ckchain.analyst.core.monitoring.notifier;

import me.bl0ckchain.analyst.core.monitoring.annotation.Notifier;
import me.bl0ckchain.analyst.core.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Notifier
public class PersistenceNotifier extends AbstractNotifier {

    @Autowired
    NoticeService noticeService;

    @Override
    public void notice(Notice notice) {
        noticeService.log(notice);
    }
}
