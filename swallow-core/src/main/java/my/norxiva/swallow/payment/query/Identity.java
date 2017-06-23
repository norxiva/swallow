package my.norxiva.swallow.payment.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.BankCardType;
import my.norxiva.swallow.core.IdType;
import my.norxiva.swallow.core.IdentityStatus;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "identity")
public class Identity {
    @Id
    private Long id;

    @Version
    private Long version;

    @Column(name = "no", length = 32, nullable = false)
    private String no;

    @Column(nullable = false)
    private Long merchantId;

    @Column(name = "bank_card_no", length = 32, nullable = false)
    private String bankCardNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bank_card_type", nullable = false)
    private BankCardType bankCardType;

    @Column(name = "bank_card_name", nullable = false)
    private String bankAccountName;

    @Column(name = "reserved_phone", length = 32, nullable = false)
    private String reservedPhone;

    @Column(name = "id_no", length = 32, nullable = false)
    private String idNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "id_type", nullable = false)
    private IdType idType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private IdentityStatus status;



}
