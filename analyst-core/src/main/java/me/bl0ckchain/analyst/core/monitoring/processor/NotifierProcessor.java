package me.bl0ckchain.analyst.core.monitoring.processor;

import me.bl0ckchain.analyst.core.entity.Notification;
import me.bl0ckchain.analyst.core.monitoring.Notifier;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 07/06/2018
 */
@Component
public class NotifierProcessor extends AbstractProcessor<Notifier> {

    private Map<String, Notifier> notifierMap = new HashMap<>();

    /**
     * Notify notices.
     *
     * @param notices
     * @param notifications
     */
    public void notify(List<Notification> notifications, List<Notice> notices) {

        for (Notification notification : notifications) {
            Notifier notifier = getNotifier(notification);
            for (Notice notice : notices) {
                notifier.notice(notice);
            }
        }
    }

    public Notifier getNotifier(String name) {
        return this.notifierMap.get(name);
    }

    public void putNotifier(String name, Notifier Notifier) {
        this.notifierMap.put(name, Notifier);
    }

    public Notifier getNotifier(Notification notification) {
        return this.notifierMap.get(notification.getCode());
    }

    public List<Notifier> getNotifiers(List<Notification> notifications) {
        List<Notifier> notifiers = new ArrayList<>();
        for (Notification notification : notifications) {
            notifiers.add(this.notifierMap.get(notification.getCode()));
        }
        return notifiers;
    }
}
