package my.norxiva.swallow.channel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.BankAcronym;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class ChannelRiskQuota {

    private BankAcronym bankAcronym;

    private BigDecimal dailyMaxQuota;

    private BigDecimal monthlyMaxQuota;
}
