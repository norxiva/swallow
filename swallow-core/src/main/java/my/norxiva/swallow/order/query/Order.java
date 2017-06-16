package my.norxiva.swallow.order.query;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.ChannelType;
import my.norxiva.swallow.core.TransactionType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String no;

    private Long merchantId;

    private ChannelType channelType;

    private TransactionType transactionType;




    private Set<Transaction> transactions = Sets.newHashSet();



}
