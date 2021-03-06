package my.norxiva.swallow.merchant.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.MerchantStatus;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    private Long id;

    @Version
    private Long version;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(length = 32, nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private MerchantStatus status;


}
