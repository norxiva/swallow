package my.norxiva.swallow.merchant.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
public class MerchantRisk {

    @Id
    private Long merchantId;

    private Set<MerchantRiskTransaction> risks = new HashSet<>();

}
