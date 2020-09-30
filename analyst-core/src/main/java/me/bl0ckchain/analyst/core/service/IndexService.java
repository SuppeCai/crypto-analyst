package me.bl0ckchain.analyst.core.service;

import java.util.List;

import me.bl0ckchain.analyst.core.entity.Index;
import me.bl0ckchain.analyst.core.entity.IndexData;
import me.bl0ckchain.analyst.core.repository.IndexDataRepo;
import me.bl0ckchain.analyst.core.repository.IndexRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author caisupeng
 * @version $Id: IndexService.java, v 0.1 2019-04-09 2:57 PM caisupeng Exp $$
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class IndexService {
    private final static Logger logger = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    private IndexRepo indexRepo;
    @Autowired
    private IndexDataRepo indexDataRepo;

    public List<Index> listIndexes() {
        List<Long> ids = indexRepo.listIds();
        return indexRepo.list(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveData(IndexData data) {
        indexDataRepo.save(data);
    }
}