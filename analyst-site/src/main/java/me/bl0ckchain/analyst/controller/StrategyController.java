package me.bl0ckchain.analyst.controller;

import com.alibaba.fastjson.JSON;
import me.bl0ckchain.analyst.core.entity.Strategy;
import me.bl0ckchain.analyst.core.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 2018/11/19
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll() {

        List<Strategy> strategies = strategyService.listStrategy();
        return JSON.toJSONString(strategies);
    }
}
