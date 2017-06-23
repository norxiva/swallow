package my.norxiva.swallow.payment.api;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IdentityCreatedEvent {
    private Long id;
    private String identityNo;
}
