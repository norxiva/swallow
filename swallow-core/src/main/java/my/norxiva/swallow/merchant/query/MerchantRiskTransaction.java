package my.norxiva.swallow.merchant.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.PaymentType;
import my.norxiva.swallow.core.TransactionType;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = {"transactionType", "paymentType"})
public class MerchantRiskTransaction {

    private TransactionType transactionType;

    private PaymentType paymentType;

}
