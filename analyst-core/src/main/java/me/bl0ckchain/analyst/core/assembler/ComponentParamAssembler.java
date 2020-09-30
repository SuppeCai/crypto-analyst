package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Component;
import me.bl0ckchain.analyst.core.entity.ComponentParam;
import me.bl0ckchain.sdk.mybatis.annotation.Assembler;
import me.bl0ckchain.sdk.mybatis.assembler.OneToManyAssembler;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/08/2018
 */
@Assembler
public class ComponentParamAssembler extends OneToManyAssembler<Long, Component, Long, ComponentParam> {

    @Override
    protected void setValue(Component bean, List<ComponentParam> value) {
        bean.setParams(value);
    }
}
