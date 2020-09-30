package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Exchange;
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
public class NoticeLogExchangeAssembler extends OneToOneAssembler<NoticeLog, Long, Exchange> {

    @Override
    protected Long getKey(NoticeLog bean) {
        return bean.getExchangeId();
    }

    @Override
    protected void setValue(NoticeLog bean, Exchange value) {
        bean.setExchange(value);
    }

    @Override
    protected Exchange getValue(Long key) {
        return (Exchange) valueRepo.simpleQuery().find(key);
    }
}
