package me.bl0ckchain.analyst.core.util;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.NoticeLog;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.monitoring.notifier.Notice;
import me.bl0ckchain.analyst.core.notification.Alert;
import me.bl0ckchain.sdk.utils.CacheUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 08/07/2018
 */
public class NoticeUtils {

    public static final String NOTICE = "n";
    public static final String SLASH = "/";
    public static final String BLANK = " ";

    public static String genCacheKey(Long exchangeId, Long assetPairId, Period period, Long strategyId) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(NOTICE);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(exchangeId);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(assetPairId);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(period.getUnit().getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(period.getUnitNum().getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(strategyId);
        return builder.toString();
    }

    public static String toString(Notice notice) {
        StringBuilder builder = new StringBuilder();
        AssetPair assetPair = notice.getAssetPair();
        Period period = notice.getPeriod();
        builder.append(notice.getExchange().getName());
        builder.append(BLANK);
        builder.append(assetPair.getBase().getCode().toUpperCase());
        builder.append(SLASH);
        builder.append(assetPair.getQuote().getCode().toUpperCase());
        builder.append(BLANK);
        builder.append(period.getUnitNum().getValue());
        builder.append(period.getUnit());
        builder.append(BLANK);
        builder.append(notice.getStrategy().getDescription());
        return builder.toString();
    }

    public static String toString(NoticeLog noticeLog) {
        StringBuilder builder = new StringBuilder();
        AssetPair assetPair = noticeLog.getAssetPair();
        Period period = noticeLog.getPeriod();
        builder.append(noticeLog.getExchange().getName());
        builder.append(BLANK);
        builder.append(assetPair.getBase().getCode());
        builder.append(SLASH);
        builder.append(assetPair.getQuote().getCode());
        builder.append(BLANK);
        builder.append(period.getUnitNum().getValue());
        builder.append(period.getUnit());
        builder.append(BLANK);
        builder.append(noticeLog.getDescription());
        return builder.toString();
    }

    public static Alert toAlert(Notice notice) {
        Alert alert = new Alert();
        StringBuilder builder = new StringBuilder();
        AssetPair assetPair = notice.getAssetPair();
        Period period = notice.getPeriod();
        builder.append(notice.getExchange().getName());
        builder.append(BLANK);
        builder.append(assetPair.getBase().getCode().toUpperCase());
        builder.append(SLASH);
        builder.append(assetPair.getQuote().getCode().toUpperCase());
        builder.append(BLANK);
        builder.append(period.getUnitNum().getValue());
        builder.append(period.getUnit());
        alert.setTitle(builder.toString());
        alert.setBody(notice.getStrategy().getDescription());
        return alert;
    }
}
