package me.bl0ckchain.analyst.core.assembler;

import me.bl0ckchain.analyst.core.entity.Component;
import me.bl0ckchain.analyst.core.entity.Indication;
import me.bl0ckchain.analyst.core.monitoring.ComponentType;
import me.bl0ckchain.analyst.core.query.ComponentQuery;
import me.bl0ckchain.analyst.core.repository.ComponentRepo;
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
public class IndicationComponentAssembler extends OneToManyAssembler<Long, Indication, Long, Component> {

    @Override
    protected List<Long> getKeys(Long id) {
        ComponentQuery query = new ComponentQuery();
        query.setComponentType(ComponentType.INDICATION);
        query.setComponentId(id);
        return ((ComponentRepo) valueRepo).findIdByQuery(query);
    }

    @Override
    protected void setValue(Indication bean, List<Component> value) {
        bean.setComponents(value);
    }
}
