package my.norxiva.swallow.transaction.query;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "payment_order", uniqueConstraints = @UniqueConstraint(columnNames = {"no", "merchant_id"}))
public class Order {

    @Id
    private Long id;

    @Version
    private Long version;

    @Column(length = 64, nullable = false)
    private String no;

    @Column(name = "merchant_id", nullable = false)
    private Long merchantId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "channel_type")
    private ChannelType channelType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @Column
    private BigDecimal amount;

    @Column(name = "account_no", length = 64)
    private String accountNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "account_type")
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_acronym", length = 8)
    private BankAcronym bankAcronym;

    @Column(name = "bank_account_no", length = 32)
    private String bankAccountNo;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "reserved_phone", length = 32)
    private String reservedPhone;

    @Column(name = "id_no")
    private String idNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "id_type")
    private IdType idType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "currency_type")
    private CurrencyType currencyType;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "tradeId")
    private Long tradeId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "callback_url")
    private String callbackUrl;

    @Column(length = 64)
    private String code;

    @Column
    private String message;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<Transaction> transactions = Sets.newHashSet();



}
