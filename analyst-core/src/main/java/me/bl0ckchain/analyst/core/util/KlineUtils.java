package me.bl0ckchain.analyst.core.util;

import me.bl0ckchain.analyst.core.entity.AssetPair;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.model.KlineDTO;
import me.bl0ckchain.analyst.core.query.KlineQuery;
import me.bl0ckchain.sdk.utils.CacheUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 24/05/2018
 */
public class KlineUtils {

    public static final String MONITORING_PREFIX = "m";

    public static String genKey(KlineDTO kline) {
        StringBuilder builder = new StringBuilder();
        builder.append(kline.getExchangeId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnit());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnitNum());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getStartAt());
        return builder.toString();
    }

    public static String genMonitoringKey(Kline kline) {
        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(MONITORING_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getExchangeId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnit().getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnitNum().getValue());
        return builder.toString();
    }

    public static String genKeyPrefix(KlineDTO kline) {
        StringBuilder builder = new StringBuilder();
        builder.append(kline.getExchangeId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnit());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnitNum());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.ASTERISK);
        return builder.toString();
    }

    public static String genKeyPrefix(Exchange exchange, AssetPair assetPair, UnitEnum unit, UnitNumEnum unitNum) {
        StringBuilder builder = new StringBuilder();
        builder.append(exchange.getId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(assetPair.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(assetPair.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(unit.getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(unitNum.getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.ASTERISK);
        return builder.toString();
    }

    public static String genPageKey(KlineQuery query) {

        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.DEFAULT_ENTITY_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(Kline.class.getSimpleName());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(query.getExchangeId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(query.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(query.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(query.getUnit().getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(query.getUnitNum().getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(query.getSize());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(query.getPage());
        return builder.toString();
    }

    public static String genPagePrefix(Kline kline) {

        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.DEFAULT_ENTITY_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getClass().getSimpleName());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getExchangeId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnit().getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnitNum().getValue());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.ASTERISK);
        return builder.toString();
    }

    public static String genResultKey(Kline kline) {

        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.DEFAULT_RESULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getExchangeId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getUnitNum().getValue());
        builder.append(kline.getUnit().name());
        return builder.toString();
    }

    public static String genResultPrefix(Kline kline) {

        StringBuilder builder = new StringBuilder();
        builder.append(CacheUtils.DEFAULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.DEFAULT_RESULT_PREFIX);
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getExchangeId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getBaseId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(kline.getQuoteId());
        builder.append(CacheUtils.SEPARATOR);
        builder.append(CacheUtils.ASTERISK);
        return builder.toString();
    }

    public static int getInterval(Kline kline) {
        if (kline == null) {
            return 0;
        }
        return kline.getUnit().getSecond() * kline.getUnitNum().getValue();
    }

}
