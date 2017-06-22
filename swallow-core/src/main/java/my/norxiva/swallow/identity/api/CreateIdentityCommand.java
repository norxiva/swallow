package my.norxiva.swallow.identity.api;

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
