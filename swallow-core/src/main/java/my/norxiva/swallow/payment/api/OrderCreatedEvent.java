package my.norxiva.swallow.payment.api;

import lombok.*;
import my.norxiva.swallow.core.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class OrderCreatedEvent {
    private Long id;
    private String no;
    private Long merchantId;
    private ChannelType channelType;
    private TransactionType transactionType;
    private PaymentType paymentType;
    private BigDecimal amount;
    private String accountNo;
    private AccountType accountType;
    private String identityNo;
    private String bankAccountNo;
    private String bankAccountName;
    private String reservedPhone;
    private String idNo;
    private IdType idType;
    private CurrencyType currencyType;
    private LocalDateTime orderTime;
    private String callbackUrl;
}
