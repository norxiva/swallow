package my.norxiva.swallow.merchant;

import my.norxiva.swallow.core.MerchantStatus;
import my.norxiva.swallow.core.PaymentError;
import my.norxiva.swallow.core.PaymentException;
import my.norxiva.swallow.merchant.query.Merchant;
import my.norxiva.swallow.merchant.query.MerchantRepository;
import my.norxiva.swallow.merchant.query.MerchantSecret;
import my.norxiva.swallow.merchant.query.MerchantSecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MerchantManager {

    private MerchantRepository merchantRepository;

    private MerchantSecretRepository merchantSecretRepository;

    @Autowired
    public MerchantManager(MerchantRepository merchantRepository,
                           MerchantSecretRepository merchantSecretRepository) {
        this.merchantRepository = merchantRepository;
        this.merchantSecretRepository = merchantSecretRepository;
    }

    public Merchant check(Long id) {
        Merchant merchant = find(id);

        if (Objects.isNull(merchant)) {
            throw new PaymentException(PaymentError.INCORRECT_MERCHANT_ID.name());
        }

        if (!MerchantStatus.ACTIVE.equals(merchant.getStatus())) {
            throw new PaymentException(PaymentError.INACTIVE_MERCHANT.name());
        }

        return merchant;
    }

    public Merchant find(Long id) {
        return merchantRepository.findOne(id);
    }

    public MerchantSecret findSecret(Long id) {
        return merchantSecretRepository.findOne(id);
    }


}
