package me.bl0ckchain.analyst.core.monitoring.indicator;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/07/2018
 */
public interface MetaData {

    /**
     * Get EMA data.
     *
     * @return
     */
    Double getValue();

    /**
     * @return
     */
    Long getStartAt();

    /**
     * @return
     */
    Long getEndAt();
}
