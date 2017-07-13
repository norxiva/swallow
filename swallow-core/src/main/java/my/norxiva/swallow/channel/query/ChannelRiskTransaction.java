package my.norxiva.swallow.channel.query;

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
public class ChannelRiskTransaction {

    private TransactionType transactionType;

    private PaymentType paymentType;

    private Set<ChannelRiskQuota> quotas = new HashSet<>();

}
