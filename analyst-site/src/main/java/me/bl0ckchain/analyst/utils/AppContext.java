package me.bl0ckchain.analyst.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 25/07/2018
 */
@Component
public class AppContext implements InitializingBean {

    public static final String PROFILES_ACTIVE = "spring.profiles.active";
    public static final String DEV = "dev";

    @Autowired
    private Environment env;

    private static String mode;

    public static boolean isDev() {
        return DEV.equals(mode);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mode = env.getProperty(PROFILES_ACTIVE);
    }
}
