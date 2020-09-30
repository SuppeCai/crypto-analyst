package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Configuration;
import me.bl0ckchain.analyst.core.entity.ConfigurationNotification;
import me.bl0ckchain.analyst.core.entity.Notification;
import me.bl0ckchain.sdk.mybatis.annotation.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.ManyToManyAssembler;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 29/06/2018
 */
@Assembler
public class ConfigurationNotificationAssembler extends ManyToManyAssembler<Long, Configuration, Long, Notification, ConfigurationNotification> {

    @Override
    protected void setValue(Configuration bean, List<Notification> value) {
        bean.setNotifications(value);
    }
}
