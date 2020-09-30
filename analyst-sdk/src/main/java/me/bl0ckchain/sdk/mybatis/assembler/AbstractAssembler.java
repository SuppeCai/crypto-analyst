package me.bl0ckchain.sdk.mybatis.assembler;

import me.bl0ckchain.sdk.mybatis.repository.BaseRepository;
import me.bl0ckchain.sdk.utils.ClassUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
public abstract class AbstractAssembler<T> implements Assembler<T> {

    /**
     * Class of Generic.
     */
    protected Class<?> tClass;

    protected BaseRepository valueRepo;

    public AbstractAssembler() {
        this.tClass = ClassUtils.getGenericClass(this.getClass());
    }

    @Override
    public boolean match(Class<?> target) {
        return target == this.tClass;
    }

    public Class<?> getTClass() {
        return tClass;
    }

    public void setValueRepo(BaseRepository valueRepo) {
        this.valueRepo = valueRepo;
    }
}
