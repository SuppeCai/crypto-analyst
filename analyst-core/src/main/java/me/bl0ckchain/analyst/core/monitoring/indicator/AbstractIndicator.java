package me.bl0ckchain.analyst.core.monitoring.indicator;

import java.util.List;

import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.Indicator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 02/06/2018
 */
public abstract class AbstractIndicator implements Indicator {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Indication indication;

    /**
     * Check whether the kline data is empty.
     *
     * @param srcData
     * @return
     */
    @Override
    public boolean check(List<Kline> srcData) {

        boolean isValid = false;

        if (CollectionUtils.isNotEmpty(srcData) && srcData.size() > 1) {
            isValid = true;
        }

        return isValid;
    }

    @Override
    public String getName() {
        return indication.getName();
    }

    @Override
    public String getCode() {
        return indication.getCode();
    }

    @Override
    public String getDescription() {
        return indication.getDescription();
    }

    @Override
    public Indication getIndication() {
        return indication;
    }

    @Override
    public void setIndication(Indication indication) {
        this.indication = indication;
    }
}
