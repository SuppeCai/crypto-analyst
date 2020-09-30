package me.bl0ckchain.analyst.notifier;

import me.bl0ckchain.analyst.core.monitoring.annotation.Notifier;
import me.bl0ckchain.analyst.core.monitoring.notifier.AbstractNotifier;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.util.NoticeUtils;
import me.bl0ckchain.analyst.utils.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 05/06/2018
 */
@Notifier
public class LogNotifier extends AbstractNotifier {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void notice(Notice notice) {

        String info = NoticeUtils.toString(notice);
        if (AppContext.isDev()) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            System.out.println(format.format(new Date()) + NoticeUtils.BLANK + info);
        } else {
            logger.info(info);
        }
    }
}
