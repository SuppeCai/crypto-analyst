package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.entity.Period;
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
public class NoticeLogPeriodAssembler extends OneToOneAssembler<NoticeLog, Long, Period> {

    @Override
    protected Long getKey(NoticeLog bean) {
        return bean.getPeriodId();
    }

    @Override
    protected void setValue(NoticeLog bean, Period value) {
        bean.setPeriod(value);
    }
}
