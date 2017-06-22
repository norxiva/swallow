package my.norxiva.swallow.identity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.swallow.identity.api.CreateIdentityCommand;
import my.norxiva.swallow.identity.api.IdentityCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@NoArgsConstructor
@Getter
@ToString
public class IdentityAggregate {

    @AggregateIdentifier
    private Long id;
    private String identityNo;

    @CommandHandler
    public IdentityAggregate(CreateIdentityCommand command) {
        apply(new IdentityCreatedEvent(command.getId(), command.getIdentityNo()));
    }

    @EventHandler
    public void on(IdentityCreatedEvent event) {
        this.id = event.getId();
        this.identityNo = event.getIdentityNo();
        log.info("identity creating: {}", event);
    }


}
