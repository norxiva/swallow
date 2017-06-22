package my.norxiva.swallow.identity.api;

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
