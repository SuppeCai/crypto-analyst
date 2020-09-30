package me.bl0ckchain.analyst.core.monitoring.notifier;

/**
 * Notifier type enum.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 05/06/2018
 */
public enum NoticeType {
    /**
     * Notifier in log file.
     */
    LOG,
    /**
     * Save notifier with data base.
     */
    PERSISTENCE,
    /**
     * Notifier by push to app.
     */
    PUSH,
    /**
     * Notifier by sms.
     */
    SMS,
    /**
     * Notifier by email.
     */
    EMAIL
}
