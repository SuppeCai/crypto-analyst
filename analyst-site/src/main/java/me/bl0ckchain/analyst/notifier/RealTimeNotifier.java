package me.bl0ckchain.analyst.notifier;

import me.bl0ckchain.analyst.core.model.Message;
import me.bl0ckchain.analyst.core.model.NoticeDTO;
import me.bl0ckchain.analyst.core.monitoring.annotation.Notifier;
import me.bl0ckchain.analyst.core.monitoring.notifier.AbstractNotifier;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.util.NoticeUtils;
import me.bl0ckchain.analyst.endpoint.AnalysisEndPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 09/07/2018
 */
@Notifier
public class RealTimeNotifier extends AbstractNotifier {

    @Autowired
    private AnalysisEndPoint analysisEndPoint;

    @Override
    public void notice(Notice notice) {
        NoticeDTO dto = new NoticeDTO();
        dto.setAssetPairId(notice.getAssetPair().getId());
        dto.setNotice(NoticeUtils.toString(notice));
        dto.setNotifyAt(notice.getNotifyAt());
        Message message = new Message();
        message.setType(Message.Type.NOTICE);
        message.setData(dto);
        analysisEndPoint.broadcast(message);
    }
}
