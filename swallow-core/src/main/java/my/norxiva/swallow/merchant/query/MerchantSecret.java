package my.norxiva.swallow.merchant.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MerchantSecret {

    private Long id;

    private Long merchantId;

    private String base64PrivateKey;

    private String base64PublicKey;

    private String base64SecretKey;

    private String cipherAlgorithm;

    private String signatureAlgorithm;

}
