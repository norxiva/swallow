package my.norxiva.swallow.order.query;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.ChannelType;
import my.norxiva.swallow.core.OrderStatus;
import my.norxiva.swallow.core.PaymentType;
import my.norxiva.swallow.core.TransactionType;

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

    @Column(name = "bank_account_no", length = 30)
    private String bankAccountNo;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column
    private BigDecimal amount;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "tradeId")
    private Long tradeId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<Transaction> transactions = Sets.newHashSet();



}
