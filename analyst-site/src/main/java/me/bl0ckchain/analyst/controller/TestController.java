package me.bl0ckchain.analyst.controller;

import me.bl0ckchain.analyst.core.entity.Component;
import me.bl0ckchain.analyst.core.entity.ComponentParam;
import me.bl0ckchain.analyst.core.entity.Kline;
import me.bl0ckchain.analyst.core.kline.UnitEnum;
import me.bl0ckchain.analyst.core.kline.UnitNumEnum;
import me.bl0ckchain.analyst.core.monitoring.ComponentType;
import me.bl0ckchain.analyst.core.monitoring.indicator.CrossResult;
import me.bl0ckchain.analyst.core.monitoring.indicator.Result;
import me.bl0ckchain.analyst.core.monitoring.indicator.ResultManager;
import me.bl0ckchain.analyst.core.query.ComponentQuery;
import me.bl0ckchain.analyst.core.repository.ComponentParamRepo;
import me.bl0ckchain.analyst.core.repository.ComponentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/08/2018
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ComponentRepo componentRepo;
    @Autowired
    private ComponentParamRepo componentParamRepo;
    @Autowired
    private ResultManager resultManager;


    @RequestMapping("")
    public String index() {
        Component component = componentRepo.find(1L);
        ComponentParam param = componentParamRepo.find(1L);
        ComponentQuery query = new ComponentQuery();
        query.setComponentType(ComponentType.INDICATION);
        query.setComponentId(2L);
        List<Long> ids1 = componentRepo.findIdByQuery(query);
        for (Long id : ids1) {
            List<Long> ids=componentParamRepo.listAssociatedIds(id);
            List<ComponentParam> params = componentParamRepo.list(ids);
        }
        return "";
    }

    @RequestMapping("/result")
    public String test() {

        Kline kline = new Kline();
        kline.setExchangeId(1L);
        kline.setBaseId(1L);
        kline.setQuoteId(24L);
        kline.setUnit(UnitEnum.HOUR);
        kline.setUnitNum(UnitNumEnum.FOUR);

        Result result = new Result();
        result.setName("asdad");
        List<Result> results = new ArrayList<>();
        results.add(result);

        CrossResult crossResult = new CrossResult();
        crossResult.setTop(22);
        crossResult.setBottom(2);
        results.add(crossResult);


        resultManager.put(kline, results);

        Map<String, List<Result>> map = resultManager.getByPrefix(kline);
        return "";
    }
}
