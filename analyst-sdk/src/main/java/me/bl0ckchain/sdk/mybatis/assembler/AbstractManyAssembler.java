package me.bl0ckchain.sdk.mybatis.assembler;

import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;
import me.bl0ckchain.sdk.utils.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 27/06/2018
 */
public abstract class AbstractManyAssembler<ID, T extends BaseEntity<ID>, K, V> extends AbstractAssembler<T> {

    public AbstractManyAssembler() {
        this.tClass = ClassUtils.getGenericClass(this.getClass(), 1);
    }

    protected ID getId(T bean) {
        return bean.getId();
    }

    protected abstract List<K> getKeys(ID id);

    protected List<V> getValue(List<K> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.EMPTY_LIST;
        }
        return valueRepo.list(keys);
    }

    protected abstract void setValue(T bean, List<V> value);
}
