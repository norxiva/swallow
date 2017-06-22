package my.norxiva.swallow.channel.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.swallow.core.ChannelStatus;
import my.norxiva.swallow.core.ChannelType;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "channel")
public class Channel {

    @Id
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private ChannelType type;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private ChannelStatus status;


}
