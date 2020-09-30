package me.bl0ckchain.analyst.core.type;

import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.monitoring.ComponentType;
import me.bl0ckchain.sdk.mybatis.StatusEnum;
import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;
import me.bl0ckchain.sdk.mybatis.type.BaseValueTypeHandler;
import org.apache.ibatis.type.MappedTypes;

/**
 * 继承BaseValueTypeHandler以实现mybatis对ValueEnum类型枚举的解析.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/05/2018
 */
@MappedTypes(value = {StatusEnum.class, UnitEnum.class, UnitNumEnum.class, ComponentType.class})
public class ValueEnumTypeHandler<E extends Enum<?> & ValueEnum> extends BaseValueTypeHandler<E> {

    public ValueEnumTypeHandler(Class<E> type) {
        super(type);
    }
}
