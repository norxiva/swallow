package my.norxiva.swallow.payment.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "payment_transaction",
        uniqueConstraints = @UniqueConstraint(columnNames = {"channel_type", "channel_serial_no"}))
public class Transaction {

    @Id
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "channel_type")
    private ChannelType channelType;

    @Column(name = "channel_serial_no")
    private String channelSerialNo;

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

    @Column(name = "identity_no", length = 64)
    private String identityNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_acronym", length = 8)
    private BankAcronym bankAcronym;

    @Column(name = "bank_account_no", length = 30)
    private String bankAccountNo;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "reserved_phone")
    private String reservedPhone;

    @Column(name = "id_no")
    private String idNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "currency_type")
    private CurrencyType currencyType;

    @Column(name = "transaction_time", nullable = false)
    private LocalDateTime transactionTime;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private TransactionStatus status;

    @Column(length = 64)
    private String code;

    @Column
    private String message;


}
