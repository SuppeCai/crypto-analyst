package me.bl0ckchain.analyst.controller;

import com.alibaba.fastjson.JSON;
import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.model.NoticeDTO;
import me.bl0ckchain.analyst.core.query.NoticeLogQuery;
import me.bl0ckchain.analyst.core.service.NoticeService;
import me.bl0ckchain.sdk.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 15/07/2018
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String findNotice() {

        NoticeLogQuery query = new NoticeLogQuery();
        query.setStartAt(DateUtils.getToday());
        query.setEndAt(System.currentTimeMillis());
        List<NoticeLog> logs = noticeService.listLogs(query);
        return JSON.toJSONString(logs);
    }

    @RequestMapping(value = "/{assetPairId}", method = RequestMethod.GET)
    public String findNoticeByAssetPairId(@PathVariable("assetPairId") Long assetPairId) {

        NoticeLogQuery query = new NoticeLogQuery();
        query.setAssetPairId(assetPairId);
        query.setStartAt(DateUtils.getToday());
        query.setEndAt(System.currentTimeMillis());
        List<NoticeDTO> notices = noticeService.findLogs(query);
        return JSON.toJSONString(notices);
    }

}
