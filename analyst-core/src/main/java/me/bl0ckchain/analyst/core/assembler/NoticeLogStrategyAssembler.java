package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.entity.Strategy;
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
public class NoticeLogStrategyAssembler extends OneToOneAssembler<NoticeLog, Long, Strategy> {

    @Override
    protected Long getKey(NoticeLog bean) {
        return bean.getStrategyId();
    }

    @Override
    protected void setValue(NoticeLog bean, Strategy value) {
        bean.setStrategy(value);
    }
}
