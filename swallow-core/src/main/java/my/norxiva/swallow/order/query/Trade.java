package my.norxiva.swallow.order.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Setter
@Getter
@ToString
//@Entity
//@Table(name = "payment_trade", uniqueConstraints = @UniqueConstraint(columnNames = {"no", "merchant_id"}))
public class Trade {

    private Long id;

    private Long version;




}
