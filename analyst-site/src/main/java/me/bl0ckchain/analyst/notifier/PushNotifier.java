package me.bl0ckchain.analyst.notifier;

import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.monitoring.annotation.Notifier;
import me.bl0ckchain.analyst.core.monitoring.notifier.AbstractNotifier;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.util.NoticeUtils;
import me.bl0ckchain.analyst.utils.APNsUtils;
import me.bl0ckchain.analyst.utils.AppContext;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Notifier
public class PushNotifier extends AbstractNotifier {

    public static final int MINIMAL_WEIGHT = 50;

    @Override
    public void notice(Notice notice) {

        if (AppContext.isDev()) {
            return;
        }

        Strategy strategy = notice.getStrategy();
        Integer weight = strategy.getWeight();
        if (weight != null && weight >= MINIMAL_WEIGHT) {
            APNsUtils.sendNotification(NoticeUtils.toAlert(notice));
        }
    }
}
