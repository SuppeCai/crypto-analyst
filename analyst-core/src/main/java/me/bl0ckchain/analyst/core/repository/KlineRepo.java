package me.bl0ckchain.analyst.core.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.bl0ckchain.analyst.core.cache.KlinePageCache;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.mapper.KlineMapper;
import me.bl0ckchain.analyst.core.query.KlineQuery;
import me.bl0ckchain.analyst.core.util.KlineUtils;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AbstractRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Repository
public class KlineRepo extends AbstractRepository<Long, Kline, KlineMapper> {

    @Autowired
    private KlinePageCache cache;

    public Kline findByQuery(KlineQuery query) {
        try {
            return mapper.selectByQuery(query);
        } catch (Exception e) {
            logger.error("findByQuery error:", e);
            throw e;
        }
    }

    public PageInfo<Kline> findByPage(KlineQuery query) {

        String key = KlineUtils.genPageKey(query);
        PageInfo<Kline> info = cache.get(key);
        if (info == null) {
            try {
                PageHelper.startPage(query.getPage(), query.getSize());
                Page<Kline> page = mapper.selectByPage(query);
                info = page.toPageInfo();
                if (info.getSize() > 0) {
                    Kline kline = info.getList().get(0);
                    cache.put(key, info, KlineUtils.getInterval(kline));
                }
            } catch (Exception e) {
                logger.error("findByPage error:", e);
                throw e;
            }
        }
        return info;
    }

    public int save(List<Kline> list) {
        try {
            if (CollectionUtils.isEmpty(list)) {
                return 0;
            }
            Kline kline = list.get(0);
            for (Kline k : list) {
                if (k.isNew()) {
                    k.init();
                    k.onInsert();
                }
            }
            return mapper.batchInsert(list, kline.getUnit().getValue(), kline.getUnitNum().getValue());
        } catch (Exception e) {
            logger.error("saveIfAbsent error:", e);
            throw e;
        }
    }
}
