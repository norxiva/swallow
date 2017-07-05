package my.norxiva.swallow.merchant;

import my.norxiva.swallow.core.PaymentError;
import my.norxiva.swallow.core.PaymentException;
import my.norxiva.swallow.merchant.query.Merchant;
import my.norxiva.swallow.merchant.query.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MerchantManager {

    private MerchantRepository merchantRepository;

    @Autowired
    public MerchantManager(MerchantRepository merchantRepository){
        this.merchantRepository = merchantRepository;
    }

    public Merchant check(Long id){
        Merchant merchant = find(id);

        if (Objects.isNull(merchant)){
            throw new PaymentException(PaymentError.INCORRECT_MERCHANT.toString());
        }

        return merchant;
    }

    public Merchant find(Long id){
        return merchantRepository.findOne(id);
    }


}
