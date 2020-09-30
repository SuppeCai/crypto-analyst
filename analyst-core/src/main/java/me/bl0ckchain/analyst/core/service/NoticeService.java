package me.bl0ckchain.analyst.core.service;

import me.bl0ckchain.analyst.core.assembler.AssetPairBaseAssembler;
import me.bl0ckchain.analyst.core.assembler.AssetPairQuoteAssembler;
import me.bl0ckchain.analyst.core.converter.NoticeDTOConverter;
import me.bl0ckchain.analyst.core.converter.NoticeLogConverter;
import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.model.NoticeDTO;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.query.NoticeLogQuery;
import me.bl0ckchain.analyst.core.repository.NoticeLogRepo;
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
public class NoticeService {

    @Autowired
    private NoticeLogRepo noticeLogRepo;
    @Autowired
    private NoticeDTOConverter noticeDTOConverter;
    @Autowired
    private NoticeLogConverter noticeLogConverter;
    @Autowired
    private AssetPairBaseAssembler assetPairBaseAssembler;
    @Autowired
    private AssetPairQuoteAssembler assetPairQuoteAssembler;

    @Transactional(rollbackFor = Exception.class)
    public void log(Notice notice) {
        noticeLogRepo.save(noticeLogConverter.to(notice));
    }

    @Transactional(rollbackFor = Exception.class)
    public void log(NoticeLog log) {
        noticeLogRepo.save(log);
    }

    public List<NoticeLog> listLogs(NoticeLogQuery query) {
        noticeLogRepo.simpleQuery();
        return noticeLogRepo.findLatestByQuery(query);
    }

    public List<NoticeDTO> findLogs(NoticeLogQuery query) {

        List<NoticeLog> logs = noticeLogRepo.findLatestByQuery(query);
        for (NoticeLog log : logs) {
            AssetPair assetPair = log.getAssetPair();
            assetPairBaseAssembler.assemble(assetPair);
            assetPairQuoteAssembler.assemble(assetPair);
        }
        return noticeDTOConverter.to(logs);
    }
}
