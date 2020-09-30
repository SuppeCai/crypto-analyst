package me.bl0ckchain.analyst.core.monitoring.notifier;

import me.bl0ckchain.analyst.core.entity.Notification;
import me.bl0ckchain.analyst.core.monitoring.Notifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
public abstract class AbstractNotifier implements Notifier {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected Notification notification;

    @Override
    public Notification getNotification() {
        return notification;
    }

    @Override
    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @Override
    public String getName() {
        return notification.getName();
    }

    @Override
    public String getCode() {
        return notification.getCode();
    }

    @Override
    public String getDescription() {
        return notification.getDescription();
    }
}
