package me.bl0ckchain.sdk.mybatis.assembler;

import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;

import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
public abstract class OneToManyAssembler<ID, T extends BaseEntity<ID>, K, V> extends AbstractManyAssembler<ID, T, K, V> {

    @Override
    public void assemble(T bean) {
        if (bean == null) {
            return;
        }

        List<V> value = getValue(getKeys(getId(bean)));
        if (value == null) {
            value = Collections.EMPTY_LIST;
        }
        setValue(bean, value);
    }

    @Override
    protected List<K> getKeys(ID id) {
        return ((AssociationRepository) valueRepo).listAssociatedIds(id);
    }

}
