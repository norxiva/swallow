package my.norxiva.swallow.transaction.query;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.BatchStatus;
import my.norxiva.swallow.core.BatchType;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "payment_batch", uniqueConstraints = @UniqueConstraint(columnNames = {"no", "merchant_id"}))
public class Batch {

    @Id
    private Long id;

    @Version
    private Long version;

    @Column(length = 64, nullable = false)
    private String no;

    @Column(name = "merchant_id", nullable = false)
    private Long merchantId;

    @Column(name = "batch_type", nullable = false)
    private BatchType batchType;

    private BatchStatus status;
}
