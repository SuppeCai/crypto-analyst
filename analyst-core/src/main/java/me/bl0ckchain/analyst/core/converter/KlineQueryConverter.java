package me.bl0ckchain.analyst.core.converter;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.query.KlineQuery;
import me.bl0ckchain.sdk.bean.Converter;
import org.springframework.stereotype.Component;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 29/05/2018
 */
@Component
public class KlineQueryConverter extends Converter<KlineQuery, Kline, Long> {

    @Override
    protected Long getId(Kline kline) {
        return kline.getId();
    }

    @Override
    protected KlineQuery convert(Kline kline) {
        KlineQuery query = new KlineQuery();
        query.setExchangeId(kline.getExchangeId());
        query.setBaseId(kline.getBaseId());
        query.setQuoteId(kline.getQuoteId());
        query.setUnit(kline.getUnit());
        query.setUnitNum(kline.getUnitNum());
        query.setStartAt(kline.getStartAt());
        query.setEndAt(kline.getEndAt());
        return query;
    }
}
