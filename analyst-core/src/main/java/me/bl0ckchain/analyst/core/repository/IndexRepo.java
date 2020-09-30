package me.bl0ckchain.analyst.core.repository;

import java.util.List;

import me.bl0ckchain.analyst.core.entity.Index;
import me.bl0ckchain.analyst.core.mapper.IndexMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.CacheRepository;

/**
 * @author caisupeng
 * @version $Id: IndexRepo.java, v 0.1 2019-04-08 5:10 PM caisupeng Exp $$
 */
@Repository
public class IndexRepo extends CacheRepository<Long, Index, IndexMapper> {
    public List<Long> listIds() {
        return mapper.selectIds();
    }
}