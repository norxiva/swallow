package my.norxiva.swallow.channel.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.PaymentType;
import my.norxiva.swallow.core.TransactionType;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ChannelRiskTransaction {

    private TransactionType transactionType;

    private PaymentType paymentType;
}
