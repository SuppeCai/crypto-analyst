package me.bl0ckchain.analyst.core.util;

import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.entity.Period;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.sdk.utils.EnumUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 25/05/2018
 */
public class PeriodUtils {

    public static Long getEndAt(long timestamp, int num, int unit) {

        Long startAt, endAt = 0L, temp;

        UnitEnum unitEnum = EnumUtils.valueOf(UnitEnum.class, unit);
        if (unitEnum == null) {
            return endAt;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        switch (unitEnum) {
            case MIN:
                startAt = calendar.getTimeInMillis();
                temp = startAt;
                calendar.add(Calendar.HOUR, 1);
                endAt = calendar.getTimeInMillis();
                break;
            case HOUR:
            case DAY:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                startAt = calendar.getTimeInMillis();
                temp = startAt;
                calendar.add(Calendar.DATE, 1);
                endAt = calendar.getTimeInMillis();
                break;
            case WEEK:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                if (dayOfWeek == 0) {
                    dayOfWeek = 7;
                }
                calendar.add(Calendar.DATE, -dayOfWeek + 1);
                startAt = calendar.getTimeInMillis();
                temp = startAt;
                calendar.add(Calendar.DATE, 7);
                endAt = calendar.getTimeInMillis();
                break;
            default:
                return endAt;
        }


        int interval = unitEnum.getSecond() * num;

        List<Long> timeList = new ArrayList<>();
        while (temp <= endAt) {
            timeList.add(temp);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(temp);
            c.add(Calendar.SECOND, interval);
            temp = c.getTimeInMillis();
        }

        for (int i = 0; i < timeList.size(); i++) {
            Long item = timeList.get(i);
            Long next = timeList.get(i + 1);
            if (timestamp >= item && timestamp <= next) {
                endAt = next;
                break;
            }
        }

        return endAt / 1000;
    }

    public static boolean match(Kline kline, Period period) {
        return period.getUnit().equals(kline.getUnit()) && period.getUnitNum().equals(kline.getUnitNum());
    }

}

