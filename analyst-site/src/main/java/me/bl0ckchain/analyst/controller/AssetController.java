package me.bl0ckchain.analyst.controller;

import com.alibaba.fastjson.JSON;
import me.bl0ckchain.analyst.core.entity.Asset;
import me.bl0ckchain.analyst.core.entity.AssetPair;
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
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAllAsset() {

        List<Asset> assets = exchangeService.listAsset();
        return JSON.toJSONString(assets);
    }

    @RequestMapping(value = "/pair", method = RequestMethod.GET)
    public String listAllAssetPair() {

        List<AssetPair> assetPairs = exchangeService.listSimpleAssetPair();
        return JSON.toJSONString(assetPairs);
    }

    @RequestMapping(value = "/pair/{assetPairId}", method = RequestMethod.GET)
    public String findAssetPairById(@PathVariable("assetPairId") Long assetPairId) {

        AssetPair assetPair = exchangeService.getAssetPair(assetPairId);
        return JSON.toJSONString(assetPair);
    }
}
