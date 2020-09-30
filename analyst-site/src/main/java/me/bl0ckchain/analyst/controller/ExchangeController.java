package me.bl0ckchain.analyst.controller;

import com.alibaba.fastjson.JSON;
import me.bl0ckchain.analyst.core.entity.Exchange;
import me.bl0ckchain.analyst.core.service.ExchangeService;
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
 * @date 2018/11/18
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll() {

        List<Exchange> exchanges = exchangeService.listSimpleExchanges();
        return JSON.toJSONString(exchanges);
    }

    @RequestMapping(value = "/{exchangeId}", method = RequestMethod.GET)
    public String findById(@PathVariable("exchangeId") Long exchangeId) {

        Exchange exchange = exchangeService.getSimpleExchange(exchangeId);
        return JSON.toJSONString(exchange);
    }
}
