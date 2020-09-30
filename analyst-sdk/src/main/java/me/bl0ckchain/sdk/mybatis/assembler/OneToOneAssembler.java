package me.bl0ckchain.sdk.mybatis.assembler;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
public abstract class OneToOneAssembler<T, K, V> extends AbstractAssembler<T> {

    @Override
    public void assemble(T bean) {
        if (bean == null) {
            return;
        }

        setValue(bean, getValue(getKey(bean)));
    }

    protected V getValue(K key) {
        return (V) valueRepo.find(key);
    }

    protected abstract K getKey(T bean);


    protected abstract void setValue(T bean, V value);
}
