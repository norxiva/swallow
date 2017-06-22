package my.norxiva.swallow.merchant;

import my.norxiva.swallow.merchant.query.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MerchantManager {

    private MerchantRepository merchantRepository;

    @Autowired
    public MerchantManager(MerchantRepository merchantRepository){
        this.merchantRepository = merchantRepository;
    }

}
