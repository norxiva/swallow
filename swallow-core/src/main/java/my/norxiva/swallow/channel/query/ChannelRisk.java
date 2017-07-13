package my.norxiva.swallow.channel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
public class ChannelRisk {

    @Id
    private Long channelId;

    private Set<ChannelRiskTransaction> transactions = new HashSet<>();

}
