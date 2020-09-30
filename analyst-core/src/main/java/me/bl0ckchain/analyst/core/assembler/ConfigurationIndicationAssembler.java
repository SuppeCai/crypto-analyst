package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Configuration;
import me.bl0ckchain.analyst.core.entity.ConfigurationIndication;
import me.bl0ckchain.analyst.core.entity.Indication;
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
public class ConfigurationIndicationAssembler extends ManyToManyAssembler<Long, Configuration, Long, Indication, ConfigurationIndication> {

    @Override
    protected void setValue(Configuration bean, List<Indication> value) {
        bean.setIndications(value);
    }
}
