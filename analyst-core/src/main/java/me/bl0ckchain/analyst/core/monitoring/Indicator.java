package me.bl0ckchain.analyst.core.monitoring;

import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 02/06/2018
 */
public interface Indicator extends IComponent {

    String TOP_PARAM = "top";
    String BOTTOM_PARAM = "bottom";

    /**
     * Check whether the argument is legal.
     *
     * @param srcData
     * @return
     */
    boolean check(List<Kline> srcData);

    /**
     * Calculate monitoring
     *
     * @param srcData
     * @return
     */
    List<Result> calculate(List<Kline> srcData);

    /**
     * Get monitoring for metadata.
     *
     * @return
     */
    Indication getIndication();

    /**
     * Set monitoring as config.
     *
     * @param indication
     */
    void setIndication(Indication indication);
}
