package my.norxiva.swallow.channel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@ToString
public class ChannelSecret {

    @Id
    private Long channelId;

    private String base64PrivateKey;

    private String base64PublicKey;

    private String base64SecretKey;

    private String cipherAlgorithm;

    private String signatureAlgorithm;
}
