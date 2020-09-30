package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.sdk.mybatis.annotation.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.OneToOneAssembler;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 19/07/2018
 */
@Assembler
public class NoticeLogAssetPairAssembler extends OneToOneAssembler<NoticeLog, Long, AssetPair> {

    @Override
    protected Long getKey(NoticeLog bean) {
        return bean.getAssetPairId();
    }

    @Override
    protected void setValue(NoticeLog bean, AssetPair value) {
        bean.setAssetPair(value);
    }

    @Override
    protected AssetPair getValue(Long key) {
        return (AssetPair) valueRepo.simpleQuery().find(key);
    }
}
