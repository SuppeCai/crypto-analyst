package me.bl0ckchain.analyst.core.monitoring.processor;

import me.bl0ckchain.sdk.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 07/06/2018
 */
public abstract class AbstractProcessor<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<? extends T> tClass;

    public AbstractProcessor() {
        this.tClass = (Class<? extends T>) ClassUtils.getGenericClass(this.getClass());
    }

    public String guessName(Class<? extends T> clazz) {
        String simple = clazz.getSimpleName();
        String extend = this.tClass.getSimpleName();
        return simple.replace(extend, "").toLowerCase();
    }
}
