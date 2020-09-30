package me.bl0ckchain.analyst.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import me.bl0ckchain.analyst.core.notification.Alert;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 24/07/2018
 */
public class APNsUtils {

    private final static Logger logger = LoggerFactory.getLogger(APNsUtils.class);

    public static final String DEFAULT_DEVICE_TOKEN = "14D235AB046A1512772BA009FF9E43CAA43D580A3ED33BED265D32E522F6FE8F";

    public static final String DEFAULT_SOUND = "default";

    public static final int DEFAULT_BADGE = 0;

    public static final String DEBUG_CERTIFICATE_FILE = "/Users/caisupeng/Documents/apns/Certificates.p12";
    public static final String DEFAULT_CERTIFICATE_FILE = "/home/Certificates.p12";

    public static final String DEFAULT_CERTIFICATE_PWD = "bl0ckchain.me";

    public static List<PushedNotification> sendNotification(Alert alert) {

        List<String> tokens = new ArrayList<>();
        tokens.add(DEFAULT_DEVICE_TOKEN);

        boolean sendCount = true;
        List<PushedNotification> notifications = new ArrayList<>();
        try {
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addCustomAlertTitle(alert.getTitle());
            if (StringUtils.isNotEmpty(alert.getSubtitle())) {
                payLoad.addCustomAlertSubtitle(alert.getSubtitle());
            }
            if (StringUtils.isNotEmpty(alert.getBody())) {
                payLoad.addCustomAlertBody(alert.getBody());
            }
            payLoad.addBadge(DEFAULT_BADGE);
            payLoad.addSound(DEFAULT_SOUND);

            PushNotificationManager pushManager = new PushNotificationManager();


            File file;
            if (AppContext.isDev()) {
                file = ResourceUtils.getFile(DEBUG_CERTIFICATE_FILE);
            } else {
                file = ResourceUtils.getFile(DEFAULT_CERTIFICATE_FILE);
            }
            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(file, DEFAULT_CERTIFICATE_PWD, false));

            if (sendCount) {
                Device device = new BasicDevice();
                device.setToken(tokens.get(0));
                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
                notifications.add(notification);
            } else {
                List<Device> device = new ArrayList<>();
                for (String token : tokens) {
                    device.add(new BasicDevice(token));
                }
                notifications = pushManager.sendNotifications(payLoad, device);
            }
            pushManager.stopConnection();
        } catch (Exception e) {
            logger.error("send notification error:", e);
        }
        return notifications;
    }
}
