package me.bl0ckchain.analyst.core.monitoring;

import me.bl0ckchain.analyst.core.entity.Analysis;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
public interface Analyzer extends IComponent {

    /**
     * Decide support Result or not.
     *
     * @param result
     * @return
     */
    boolean support(Result result);

    /**
     * Analyze calculate result.
     *
     * @param result
     * @return
     */
    void analyze(Result result);

    /**
     * Explain the result of analysis.
     *
     * @param result
     * @return
     */
    String explain(Result result);

    /**
     * Get
     *
     * @return
     */
    Analysis getAnalysis();

    /**
     * Set
     *
     * @param analysis
     */
    void setAnalysis(Analysis analysis);

}
