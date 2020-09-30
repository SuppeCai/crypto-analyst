package me.bl0ckchain.sdk.mybatis.repository;


import me.bl0ckchain.sdk.mybatis.Constants;
import me.bl0ckchain.sdk.mybatis.assembler.Assembler;
import me.bl0ckchain.sdk.mybatis.entity.BaseEntity;
import me.bl0ckchain.sdk.mybatis.mapper.BaseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 16/9/19
 */
public abstract class AbstractRepository<ID, T extends BaseEntity<ID>, Mapper extends BaseMapper<ID, T>> implements BaseRepository<ID, T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final ThreadLocal<Integer> SIMPLE_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * Base mapper match the repository.
     */
    @Autowired
    protected Mapper mapper;
    /**
     * Assembler list.
     */
    protected List<Assembler<T>> assemblers;

    @Override
    public T find(ID id) {
        try {
            return mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("find error:", e);
            throw e;
        }
    }

    @Override
    public void save(T t) {
        if (t == null) {
            return;
        }
        save(t, t.isNew());
    }

    /**
     * Save entity.
     *
     * @param t
     * @param isNew
     * @return
     */
    private void save(T t, boolean isNew) {
        try {
            if (isNew) {
                t.init();
                t.onInsert();
                mapper.insert(t);
            } else {
                t.onUpdate();
                mapper.updateByPrimaryKeySelective(t);
            }
        } catch (Exception e) {
            logger.error("save error:", e);
            throw e;
        }
    }

    @Override
    public boolean delete(ID id) {
        try {
            return mapper.deleteByPrimaryKey(id) > 0;
        } catch (Exception e) {
            logger.error("delete error:", e);
            throw e;
        }
    }

    @Override
    public List<T> list(List<ID> ids) {
        try {
            if (CollectionUtils.isEmpty(ids)) {
                return Collections.EMPTY_LIST;
            }

            return mapper.selectByPrimaryKeys(ids);
        } catch (Exception e) {
            logger.error("selectByPrimaryKeys error:", e);
            throw e;
        }
    }

    @Override
    public Map<ID, T> mget(List<ID> ids) {
        try {
            if (CollectionUtils.isEmpty(ids)) {
                return Collections.EMPTY_MAP;
            }

            List<T> ts = list(ids);
            if (CollectionUtils.isEmpty(ts)) {
                return Collections.EMPTY_MAP;
            }

            Map<ID, T> map = new HashMap<>();
            for (T t : ts) {
                if (t != null) {
                    map.put(t.getId(), t);
                }
            }

            return map;
        } catch (Exception e) {
            logger.error("mget error:", e);
            throw e;
        }
    }

    @Override
    public BaseRepository simpleQuery() {
        SIMPLE_THREAD_LOCAL.set(Constants.SIMPLE_QUERY);
        return this;
    }

    /**
     * Assemble entities if there are some associated with, depend on Assemblers.
     *
     * @param t
     */
    public void assemble(T t) {
        if (t == null || CollectionUtils.isEmpty(assemblers)) {
            return;
        }

        for (Assembler assembler : assemblers) {
            assembler.assemble(t);
        }
    }

    public void assemble(Collection<T> ts) {
        if (CollectionUtils.isEmpty(ts) || CollectionUtils.isEmpty(assemblers)) {
            return;
        }

        for (T t : ts) {
            assemble(t);
        }
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public void setAssemblers(List<Assembler<T>> assemblers) {
        this.assemblers = assemblers;
    }

    public boolean autoAssemble() {
        Integer signal = SIMPLE_THREAD_LOCAL.get();
        return signal == null ? true : false;
    }

    public void cleanThreadLocal() {
        SIMPLE_THREAD_LOCAL.remove();
    }
}
