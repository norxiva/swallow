package my.norxiva.swallow.merchant.query;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.BankAcronym;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MerchantRiskQuota {

    private BankAcronym bankAcronym;

    private BigDecimal dailyMaxQuota;

    private BigDecimal monthlyMaxQuota;

}
