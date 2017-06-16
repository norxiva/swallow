package my.norxiva.swallow.order.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "payment_trade", uniqueConstraints = @UniqueConstraint(columnNames = {"no", "merchant_id"}))
public class Trade {

    @Id
    private Long id;

    @Version
    private Long version;

    @Column(length = 64, nullable = false)
    private String no;

    @Column(name = "merchant_id", nullable = false)
    private Long merchantId;




}
