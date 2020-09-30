package me.bl0ckchain.analyst.core.monitoring.analyzer;

import me.bl0ckchain.analyst.core.entity.Analysis;
import me.bl0ckchain.analyst.core.entity.Component;
import me.bl0ckchain.analyst.core.entity.ComponentParam;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.monitoring.Analyzer;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
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
public abstract class AbstractAnalyzer implements Analyzer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected Analysis analysis;

    @Override
    public boolean support(Result result) {
        return result != null;
    }

    @Override
    public Analysis getAnalysis() {
        return analysis;
    }

    @Override
    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    @Override
    public String getName() {
        return analysis.getName();
    }

    @Override
    public String getCode() {
        return analysis.getCode();
    }

    @Override
    public String getDescription() {
        return analysis.getDescription();
    }
}
