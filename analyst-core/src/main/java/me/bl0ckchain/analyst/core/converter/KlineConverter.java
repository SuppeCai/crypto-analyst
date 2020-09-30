package me.bl0ckchain.analyst.core.converter;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.model.KlineDTO;
import me.bl0ckchain.sdk.bean.Converter;
import me.bl0ckchain.sdk.utils.EnumUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 25/05/2018
 */
@Component
public class KlineConverter extends Converter<Kline, KlineDTO, Long> {

    @Override
    protected Long getId(KlineDTO dto) {
        return null;
    }

    @Override
    protected Kline convert(KlineDTO dto) {
        Kline kline = new Kline();
        kline.setExchangeId(dto.getExchangeId());
        kline.setBaseId(dto.getBaseId());
        kline.setQuoteId(dto.getQuoteId());
        kline.setOpen(dto.getOpen());
        kline.setClose(dto.getClose());
        kline.setHigh(dto.getHigh());
        kline.setLow(dto.getLow());
        kline.setStartAt(dto.getStartAt() * 1000);
        kline.setEndAt(dto.getEndAt() * 1000);
        kline.setAmount(dto.getAmount() == null ? new BigDecimal(0) : dto.getAmount());
        kline.setVolume(dto.getVolume() == null ? new BigDecimal(0) : dto.getVolume());
        kline.setVolamount(dto.getVolamount() == null ? 0 : dto.getVolamount());
        kline.setUnit(EnumUtils.valueOf(UnitEnum.class, dto.getUnit()));
        kline.setUnitNum(EnumUtils.valueOf(UnitNumEnum.class, dto.getUnitNum()));
        return kline;
    }
}
