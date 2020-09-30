package me.bl0ckchain.analyst.core.service;

import me.bl0ckchain.analyst.core.assembler.AssetPairBaseAssembler;
import me.bl0ckchain.analyst.core.assembler.AssetPairQuoteAssembler;
import me.bl0ckchain.analyst.core.entity.*;
import me.bl0ckchain.analyst.core.repository.AssetPairRepo;
import me.bl0ckchain.analyst.core.repository.AssetRepo;
import me.bl0ckchain.analyst.core.repository.ExchangeRepo;
import me.bl0ckchain.analyst.core.repository.PeriodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 25/05/2018
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ExchangeService {

    @Autowired
    private ExchangeRepo exchangeRepo;
    @Autowired
    private PeriodRepo periodRepo;
    @Autowired
    private AssetRepo assetRepo;
    @Autowired
    private AssetPairRepo assetPairRepo;
    @Autowired
    private AssetPairBaseAssembler assetPairBaseAssembler;
    @Autowired
    private AssetPairQuoteAssembler assetPairQuoteAssembler;

    public List<Exchange> listExchanges() {

        List<Long> ids = exchangeRepo.listIds();
        return exchangeRepo.list(ids);
    }

    public List<Exchange> listSimpleExchanges() {
        List<Long> ids = exchangeRepo.listIds();
        return exchangeRepo.simpleQuery().list(ids);
    }

    public Exchange getSimpleExchange(Long id) {
        return (Exchange) exchangeRepo.simpleQuery().find(id);
    }

    public List<Period> listPeriod() {
        List<Long> ids = periodRepo.listIds();
        return periodRepo.list(ids);
    }

    public AssetPair getAssetPair(Long assetPairId) {
        return assetPairRepo.find(assetPairId);
    }

    public AssetPair getAssetPair(Kline kline) {
        return assetPairRepo.findByBaseIdAndQuoteId(kline.getBaseId(), kline.getQuoteId());
    }

    public List<AssetPair> listSimpleAssetPair() {
        List<Long> ids = assetPairRepo.listIds();
        return assetPairRepo.simpleQuery().list(ids);
    }

    public List<Asset> listAsset() {
        List<Long> ids = assetRepo.listIds();
        return assetRepo.list(ids);
    }
}
