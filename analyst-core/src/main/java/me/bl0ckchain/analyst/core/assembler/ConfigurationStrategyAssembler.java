package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Configuration;
import me.bl0ckchain.analyst.core.entity.ConfigurationStrategy;
import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.sdk.mybatis.StatusEnum;
import me.bl0ckchain.sdk.mybatis.annotation.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.ManyToManyAssembler;

import java.util.Iterator;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/07/2018
 */
@Assembler
public class ConfigurationStrategyAssembler extends ManyToManyAssembler<Long, Configuration, Long, Strategy, ConfigurationStrategy> {

    @Override
    protected void setValue(Configuration bean, List<Strategy> value) {
        Iterator<Strategy> iterator = value.iterator();
        while (iterator.hasNext()) {
            Strategy strategy = iterator.next();
            if (strategy.getStatus() != StatusEnum.ENABLED) {
                iterator.remove();
            }
        }
        bean.setStrategies(value);
    }
}
