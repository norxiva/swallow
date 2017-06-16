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

    @Enumerated(EnumType.STRING)
    @Column(name = "channel_type", length = 32)
    private ChannelType channelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", length = 32, nullable = false)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", length = 32, nullable = false)
    private PaymentType paymentType;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column
    private boolean batching;

    @Column(name = "batch_no", length = 64)
    private String batchNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 32, nullable = false)
    private OrderStatus status;



//    private Set<Transaction> transactions = Sets.newHashSet();



}
