package me.bl0ckchain.analyst.core.monitoring;

import me.bl0ckchain.analyst.core.entity.Notification;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 05/06/2018
 */
public interface Notifier extends IComponent {

    void notice(Notice notice);

    /**
     * Get
     *
     * @return
     */
    Notification getNotification();

    /**
     * Set
     *
     * @param notification
     */
    void setNotification(Notification notification);
}
