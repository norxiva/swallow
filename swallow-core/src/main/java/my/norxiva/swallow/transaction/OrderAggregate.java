package my.norxiva.swallow.transaction;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.swallow.core.*;
import my.norxiva.swallow.transaction.api.CreateOrderCommand;
import my.norxiva.swallow.transaction.api.OrderCreatedEvent;
import my.norxiva.swallow.util.SnowFlake;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class OrderAggregate {

    @AggregateIdentifier
    private Long id;
    private String no;
    private Long merchantId;
    private ChannelType channelType;
    private TransactionType transactionType;
    private PaymentType paymentType;
    private BigDecimal amount;
    private String accountNo;
    private AccountType accountType;
    private String identityNo;
    private String bankAccountNo;
    private String bankAccountName;
    private String reservedPhone;
    private String idNo;
    private IdType idType;
    private CurrencyType currencyType;
    private LocalDateTime orderTime;
    private String callbackUrl;
    private String returnUrl;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command){
        apply(OrderCreatedEvent.builder()
                .id(command.getId())
                .no(command.getNo())
                .merchantId(command.getMerchantId())
                .channelType(command.getChannelType())
                .transactionType(command.getTransactionType())
                .paymentType(command.getPaymentType())
                .amount(command.getAmount())
                .accountNo(command.getAccountNo())
                .accountType(command.getAccountType())
                .identityNo(command.getIdentityNo())
                .bankAccountNo(command.getBankAccountNo())
                .bankAccountName(command.getBankAccountName())
                .reservedPhone(command.getReservedPhone())
                .idNo(command.getIdNo())
                .idType(command.getIdType())
                .currencyType(command.getCurrencyType())
                .orderTime(command.getOrderTime())
                .callbackUrl(command.getCallbackUrl())
                .build()
        );
    }

    @EventHandler
    public void on(OrderCreatedEvent event){
        log.info("order created: {}", event);
    }

}
