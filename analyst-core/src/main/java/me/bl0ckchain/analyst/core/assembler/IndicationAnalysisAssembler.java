package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Analysis;
import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.entity.IndicationAnalysis;
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
public class IndicationAnalysisAssembler extends ManyToManyAssembler<Long, Indication, Long, Analysis, IndicationAnalysis> {

    @Override
    protected void setValue(Indication bean, List<Analysis> value) {
        bean.setAnalyses(value);
    }
}
