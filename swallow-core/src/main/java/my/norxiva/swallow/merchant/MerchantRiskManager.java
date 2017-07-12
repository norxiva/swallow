package my.norxiva.swallow.merchant;

import my.norxiva.swallow.CacheConstant;
import my.norxiva.swallow.merchant.query.MerchantRisk;
import my.norxiva.swallow.merchant.query.MerchantRiskRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerchantRiskManager {

    private MerchantRiskRepository merchantRiskRepository;

    public MerchantRiskManager(MerchantRiskRepository merchantRiskRepository){
        this.merchantRiskRepository = merchantRiskRepository;
    }

    @Cacheable(value = CacheConstant.CACHE_NAME_MERCHANT_RISK, key = "#id")
    public MerchantRisk find(Long id){
        return merchantRiskRepository.findOne(id);
    }

    @Cacheable(value = CacheConstant.CACHE_NAME_MERCHANT_RISKS)
    public List<MerchantRisk> findAll(){
        return merchantRiskRepository.findAll();
    }
}
