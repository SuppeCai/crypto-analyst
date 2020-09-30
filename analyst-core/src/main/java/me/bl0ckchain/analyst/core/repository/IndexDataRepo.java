package me.bl0ckchain.analyst.core.repository;

import me.bl0ckchain.analyst.core.entity.IndexData;
import me.bl0ckchain.analyst.core.mapper.IndexDataMapper;
import me.bl0ckchain.sdk.mybatis.annotation.Repository;
import me.bl0ckchain.sdk.mybatis.repository.AbstractRepository;

/**
 * @author caisupeng
 * @version $Id: IndexDataRepo.java, v 0.1 2019-04-08 5:10 PM caisupeng Exp $$
 */
@Repository
public class IndexDataRepo extends AbstractRepository<Long, IndexData, IndexDataMapper> {
}