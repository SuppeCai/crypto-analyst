package me.bl0ckchain.analyst.core.monitoring.processor;

import me.bl0ckchain.analyst.core.entity.Analysis;
import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.monitoring.Analyzer;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Component
public class AnalyzerProcessor extends AbstractProcessor<Analyzer> {

    public Map<String, Analyzer> analyzerMap = new HashMap<>();

    /**
     * Analyzer single result.
     *
     * @param result
     */
    public void analyze(Result result) {

        Indication indication = result.getIndication();
        if (indication == null) {
            throw new IllegalArgumentException("Indication is required");
        }

        List<Analysis> analyses = indication.getAnalyses();
        if (CollectionUtils.isEmpty(analyses)) {
            return;
        }

        for (Analysis analysis : analyses) {
            Analyzer analyzer = getAnalyzer(analysis.getCode());
            if (analyzer.support(result)) {
                analyzer.analyze(result);
            }
        }
    }

    /**
     * Analyzer multi results.
     *
     * @param results
     */
    public void analyze(List<Result> results) {

        for (Result result : results) {
            analyze(result);
        }
    }

    public Analyzer getAnalyzer(String name) {
        return this.analyzerMap.get(name);
    }

    public void putAnalyzer(String name, Analyzer Analyzer) {
        this.analyzerMap.put(name, Analyzer);
    }

}
