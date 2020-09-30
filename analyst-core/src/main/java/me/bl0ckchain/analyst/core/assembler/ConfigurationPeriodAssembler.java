package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Configuration;
import me.bl0ckchain.analyst.core.entity.ConfigurationPeriod;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.sdk.mybatis.annotation.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.ManyToManyAssembler;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/07/2018
 */
@Assembler
public class ConfigurationPeriodAssembler extends ManyToManyAssembler<Long, Configuration, Long, Period, ConfigurationPeriod> {

    @Override
    protected void setValue(Configuration bean, List<Period> value) {
        bean.setPeriods(value);
    }
}
