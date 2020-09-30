package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.AssetPairConfiguration;
import me.bl0ckchain.analyst.core.entity.Configuration;
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
 * @date 29/06/2018
 */
@Assembler
public class AssetPairConfigurationAssembler extends ManyToManyAssembler<Long, AssetPair, Long, Configuration, AssetPairConfiguration> {

    @Override
    protected void setValue(AssetPair bean, List<Configuration> value) {
        Iterator<Configuration> iterator = value.iterator();
        while (iterator.hasNext()) {
            Configuration configuration = iterator.next();
            if (configuration.getStatus() != StatusEnum.ENABLED) {
                iterator.remove();
            }
        }
        bean.setConfigurations(value);
    }
}
