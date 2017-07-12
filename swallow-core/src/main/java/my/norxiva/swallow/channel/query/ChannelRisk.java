package my.norxiva.swallow.channel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
@ToString
public class ChannelRisk {

    @Id
    private Long channelId;

    private Map<ChannelRiskTransaction, Set<ChannelRiskQuota>> risks = new HashMap<>();

}
