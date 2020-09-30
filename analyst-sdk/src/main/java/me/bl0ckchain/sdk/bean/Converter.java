package me.bl0ckchain.sdk.bean;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

/**
 * bean对象转换 src -> dest.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 16/7/4
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class Converter<D, S, ID> implements InitializingBean {
    protected Logger LOG = LoggerFactory.getLogger(getClass());

    private Map<String, Object> hints = Collections.emptyMap();
    private List<ConverterAssembler> assemblers = new ArrayList<ConverterAssembler>();

    public void setHints(Map<String, Object> hints) {
        this.hints = hints;
    }

    public void setAssemblers(List<ConverterAssembler> assemblers) {
        this.assemblers = assemblers;
    }

    public D to(S s) {
        if (s == null) {
            return null;
        }
        D d = convert(s);
        for (ConverterAssembler assembler : assemblers) {
            if (assembler.accept(hints)) {
                Object value = assembler.getValue(assembler.getKey(s, d));
                if (value != null) {
                    assembler.setValue(s, d, value);
                }
            }
        }
        return d;
    }

    public List<D> to(List<S> ss) {
        if (CollectionUtils.isEmpty(ss)) {
            return Collections.emptyList();
        }
        List<D> ds = convert(ss);
        assembler(ss.size(), ss, ds);
        return ds;
    }

    public Map<ID, D> to(Map<ID, S> sMap) {
        if (MapUtils.isEmpty(sMap)) {
            return Collections.emptyMap();
        }
        final int len = sMap.size();
        List<S> ss = new ArrayList<S>(sMap.values());
        List<D> ds = to(ss);
        Map<ID, D> dMap = new HashMap<ID, D>(ss.size());
        for (int i = 0; i < len; i++) {
            dMap.put(getId(ss.get(i)), ds.get(i));
        }
        return dMap;
    }

    public Map<ID, D> toMap(List<S> ss) {
        if (CollectionUtils.isEmpty(ss)) {
            return Collections.emptyMap();
        }
        final int len = ss.size();
        List<D> ds = to(ss);
        Map<ID, D> dMap = new HashMap<ID, D>(ss.size());
        for (int i = 0; i < len; i++) {
            dMap.put(getId(ss.get(i)), ds.get(i));
        }
        return dMap;
    }

    private void assembler(int len, List<S> ss, List<D> ds) {
        List<Object> keys = new ArrayList<Object>(len);
        for (ConverterAssembler assembler : assemblers) {
            if (assembler.accept(hints)) {
                keys.clear();
                for (int i = 0; i < len; i++) {
                    keys.add(assembler.getKey(ss.get(i), ds.get(i)));
                }
                Map<Object, Object> map = assembler.mgetValue(keys);
                for (int i = 0; i < len; i++) {
                    Object key = keys.get(i);
                    if (key instanceof List) {
                        List list = (List) key;
                        if (list.isEmpty()) {
                            assembler.setValue(ss.get(i), ds.get(i), Collections.EMPTY_LIST);
                        } else {
                            List<Object> values = new ArrayList<Object>(list.size());
                            for (Object k : (List) key) {
                                Object v = map.get(k);
                                if (v != null) {
                                    values.add(v);
                                }
                            }
                            assembler.setValue(ss.get(i), ds.get(i), values);
                        }
                    } else {
                        if (key != null) {
                            Object value = map.get(key);
                            if (value != null) {
                                assembler.setValue(ss.get(i), ds.get(i), value);
                            }
                        }
                    }
                }
            }
        }
    }

    public D get(ID id) {
        return to(internalGet(id));
    }

    public Map<ID, D> mget(Collection<ID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        return to(internalMGet(ids));
    }

    public List<D> mgetList(Collection<ID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        Map<ID, S> map = internalMGet(ids);
        List<S> list = new ArrayList<S>(map.size());
        for (ID id : ids) {
            S s = map.get(id);
            if (s != null) {
                list.add(s);
            }
        }
        return to(list);
    }

    public List<ID> getIds(List<S> ss) {
        if (CollectionUtils.isEmpty(ss)) {
            return Collections.emptyList();
        }
        List<ID> ids = new ArrayList<ID>(ss.size());
        for (S s : ss) {
            ids.add(getId(s));
        }
        return ids;
    }

    public S from(D d) {
        throw new NotImplementedException(this.getClass().getSimpleName());
    }

    protected S internalGet(ID id) {
        throw new NotImplementedException(this.getClass().getSimpleName());
    }

    protected Map<ID, S> internalMGet(Collection<ID> ids) {
        throw new NotImplementedException(this.getClass().getSimpleName());
    }

    protected abstract ID getId(S s);

    protected abstract D convert(S s);

    protected List<D> convert(List<S> ss) {
        List<D> ds = new ArrayList<D>(ss.size());
        for (S s : ss) {
            ds.add(convert(s));
        }
        return ds;
    }

    protected void initAssemblers(List<ConverterAssembler> assemblers) {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initAssemblers(assemblers);
    }
}
