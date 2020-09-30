package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Asset;
import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.sdk.mybatis.annotation.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.OneToOneAssembler;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
@Assembler
public class AssetPairQuoteAssembler extends OneToOneAssembler<AssetPair, Long, Asset> {

    @Override
    protected Long getKey(AssetPair bean) {
        return bean.getQuoteId();
    }

    @Override
    protected void setValue(AssetPair bean, Asset value) {
        bean.setQuote(value);
    }

}
