package my.norxiva.swallow.merchant.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.PaymentType;
import my.norxiva.swallow.core.TransactionType;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MerchantRiskTransaction {

    private TransactionType transactionType;

    private PaymentType paymentType;

    private Set<MerchantRiskQuota> quotas = new HashSet<>();

}
