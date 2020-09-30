package me.bl0ckchain.analyst.core.monitoring;

import java.util.concurrent.Phaser;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;
import me.bl0ckchain.sdk.utils.EnumUtils;

/**
 * @author caisupeng
 * @version $Id: MonitorPhaser.java, v 0.1 2019-01-09 4:58 PM caisupeng Exp $$
 */
public class MonitorPhaser extends Phaser {

    @Override
    public boolean onAdvance(int phase, int registeredParties) {
        switch (EnumUtils.valueOf(MonitorPhaseEnum.class, phase)) {
            case INIT:
                return init();
            case CALCULATED:
                return calculated();
            case ANALYZED:
                return analyzed();
            case GENERATED:
                return matched();
            case MATCHED:
                return matched();
            case NOTIFIED:
                return notified();
            default:
                return true;
        }
    }

    private boolean init() {
        return false;
    }

    private boolean calculated() {
        return false;
    }

    private boolean analyzed() {
        return false;
    }

    private boolean matched() {
        return false;
    }

    private boolean notified() {
        return true;
    }

    public enum MonitorPhaseEnum implements ValueEnum {
        INIT(0),
        CALCULATED(1),
        ANALYZED(2),
        GENERATED(3),
        MATCHED(4),
        NOTIFIED(5);

        private final int value;

        MonitorPhaseEnum(int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }
}