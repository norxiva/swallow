package my.norxiva.swallow.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class CryptMessage {

    @NotNull
    private Long merchantId;

    @NotNull
    private String message;

    @NotNull
    private String signature;
}
