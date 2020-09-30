package me.bl0ckchain.sdk.mybatis.assembler;

import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;
import me.bl0ckchain.sdk.mybatis.repository.AssociationRepository;
import me.bl0ckchain.sdk.mybatis.repository.BaseRepository;
import me.bl0ckchain.sdk.utils.ClassUtils;

import java.util.Collections;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 26/06/2018
 */
public abstract class ManyToManyAssembler<ID, T extends BaseEntity<ID>, K, V, A> extends AbstractManyAssembler<ID, T, K, V> {

    public static final int ASSOCIATION_GENERIC_SIZE = 4;

    /**
     * Class of Generic.
     */
    protected Class<?> associatedClasses;

    protected BaseRepository associationRepo;

    public ManyToManyAssembler() {
        associatedClasses = ClassUtils.getGenericClass(this.getClass(), ASSOCIATION_GENERIC_SIZE);
    }

    @Override
    public void assemble(T bean) {
        if (bean == null) {
            return;
        }

        List<V> value = getValue(getKeys(getId(bean)));
        setValue(bean, value);
    }

    @Override
    protected List<K> getKeys(ID id) {
        return ((AssociationRepository) associationRepo).listAssociatedIds(id);
    }

    public Class<?> getAssociatedClasses() {
        return associatedClasses;
    }

    public void setAssociationRepo(BaseRepository associationRepo) {
        this.associationRepo = associationRepo;
    }
}
