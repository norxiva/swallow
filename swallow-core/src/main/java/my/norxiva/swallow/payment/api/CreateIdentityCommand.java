package my.norxiva.swallow.payment.api;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateIdentityCommand {
    private Long id;
    private String identityNo;
}
