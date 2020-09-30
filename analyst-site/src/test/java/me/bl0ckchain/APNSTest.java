package me.bl0ckchain;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 24/07/2018
 */
public class APNSTest {

    public static void main(String[] args) {
        String deviceToken = "14D235AB046A1512772BA009FF9E43CAA43D580A3ED33BED265D32E522F6FE8F";
        String alert = "给你发信息了";//push的内容
        int badge = 0;//图标小红圈的数值
        String sound = "default";//铃音

        List<String> tokens = new ArrayList<String>();
        tokens.add(deviceToken);
        String certificatePath = "/Users/caisupeng/Documents/Certificates.p12";
        String certificatePassword = "bl0ckchain.me";//此处注意导出的证书密码不能为空因为空密码会报错
        boolean sendCount = true;

        try
        {
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(alert); // 消息内容
            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值

            if (!StringUtils.isBlank(sound))
            {
                payLoad.addSound(sound);//铃音
            }
            PushNotificationManager pushManager = new PushNotificationManager();
            //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
            // 发送push消息
            if (sendCount)
            {
                Device device = new BasicDevice();
                device.setToken(tokens.get(0));
                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
                notifications.add(notification);
            }
            else
            {
                List<Device> device = new ArrayList<Device>();
                for (String token : tokens)
                {
                    device.add(new BasicDevice(token));
                }
                notifications = pushManager.sendNotifications(payLoad, device);
            }
            pushManager.stopConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        }
}
