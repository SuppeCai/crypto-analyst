package me.bl0ckchain.analyst.core.service;

import com.github.pagehelper.PageInfo;
import me.bl0ckchain.analyst.core.converter.KlineQueryConverter;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.query.KlineQuery;
import me.bl0ckchain.analyst.core.repository.KlineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 24/05/2018
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class KlineService {

    public static final long DEFAULT_START_AT = 1530028800000L;

    public static final int DEFAULT_PAGE_SIZE = 4;

    @Autowired
    private KlineRepo klineRepo;
    @Autowired
    private KlineQueryConverter klineQueryConverter;

    @Transactional(rollbackFor = Exception.class)
    public boolean saveIfAbsent(Kline kline) {
        boolean isSaved = false;
        Kline old = klineRepo.findByQuery(klineQueryConverter.to(kline));
        if (old == null) {
            klineRepo.save(kline);
            isSaved = true;
        }
        return isSaved;
    }

    @Transactional(rollbackFor = Exception.class)
    public int save(List<Kline> klineList) {
        return klineRepo.save(klineList);
    }

    public List<Kline> getLatestData(Kline kline) {

        KlineQuery query = klineQueryConverter.to(kline);
        query.setSize(300);
        long now = System.currentTimeMillis();
        query.setEndAt(now);
        query.setStartAt(DEFAULT_START_AT);

        int count = 0;
        PageInfo<Kline> page = null;
        List<Kline> klineList = new ArrayList<>();
        while (page == null || (page.isHasNextPage() && count < DEFAULT_PAGE_SIZE)) {
            query.setPage(page == null ? 1 : page.getNextPage());
            page = klineRepo.findByPage(query);
            klineList.addAll(page.getList());
            count++;
        }
        klineList.add(0, kline);
        return klineList;
    }
}
