package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.ExchangeAssetPair;
import me.bl0ckchain.sdk.mybatis.annotation.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.ManyToManyAssembler;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
@Assembler
public class ExchangeAssetPairAssembler extends ManyToManyAssembler<Long, Exchange, Long, AssetPair, ExchangeAssetPair> {

    @Override
    protected List<AssetPair> getValue(List<Long> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.EMPTY_LIST;
        }
        return valueRepo.simpleQuery().list(keys);
    }

    @Override
    protected void setValue(Exchange bean, List<AssetPair> value) {
        bean.setAssetPairs(value);
    }

}
