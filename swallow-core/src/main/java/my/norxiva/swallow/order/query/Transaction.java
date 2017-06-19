package my.norxiva.swallow.order.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.AccountType;
import my.norxiva.swallow.core.BankAcronym;
import my.norxiva.swallow.core.CurrencyType;
import my.norxiva.swallow.core.TransactionStatus;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "payment_transaction")
public class Transaction {

    @Id
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

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
    @Column(name = "account_type")
    private AccountType accountType;

    @Column
    private BigDecimal amount;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "currency_type")
    private CurrencyType currencyType;

    @Column
    private LocalDateTime transactionTime;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private TransactionStatus status;


}
