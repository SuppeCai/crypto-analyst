package me.bl0ckchain.analyst.message;

import com.alibaba.fastjson.JSON;
import me.bl0ckchain.analyst.core.converter.KlineConverter;
import me.bl0ckchain.analyst.core.model.KlineDTO;
import me.bl0ckchain.analyst.core.monitoring.MonitorEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Receive message form direct exchange.
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 21/05/2018
 */
@Component
@RabbitListener(queues = "kline")
public class DirectListener {

    private final static Logger logger = LoggerFactory.getLogger(DirectListener.class);

    @Autowired
    private MonitorEngine monitorEngine;
    @Autowired
    private KlineConverter klineConverter;

    @RabbitHandler
    public void process(String message) {

        KlineDTO dto = JSON.parseObject(message, KlineDTO.class);
        monitorEngine.handle(klineConverter.to(dto));
    }
}
