package me.bl0ckchain.analyst.core.service;

import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.repository.StrategyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 2018/11/19
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class StrategyService {

    @Autowired
    private StrategyRepo strategyRepo;

    public List<Strategy> listStrategy() {

        List<Long> ids = strategyRepo.listIds();
        return strategyRepo.list(ids);
    }
}
