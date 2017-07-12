package my.norxiva.swallow.merchant.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.*;

@Setter
@Getter
@ToString
public class MerchantRisk {

    @Id
    private Long merchantId;

    private Map<MerchantRiskTransaction, Set<MerchantRiskQuota>> risks = new HashMap<>();

}
