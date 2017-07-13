package my.norxiva.swallow.channel;

import my.norxiva.swallow.channel.query.Channel;
import my.norxiva.swallow.channel.query.ChannelRisk;
import my.norxiva.swallow.channel.query.ChannelRiskRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

import static my.norxiva.swallow.CacheConstant.CACHE_NAME_CHANNEL_RISK;
import static my.norxiva.swallow.CacheConstant.CACHE_NAME_CHANNEL_RISKS;

@Component
public class ChannelRiskManager {

    private ChannelRiskRepository channelRiskRepository;

    public ChannelRiskManager(ChannelRiskRepository channelRiskRepository){
        this.channelRiskRepository = channelRiskRepository;
    }

    @Cacheable(value = CACHE_NAME_CHANNEL_RISK, key = "#id")
    public ChannelRisk find(Long id){
        return channelRiskRepository.findOne(id);
    }

    @Cacheable(value = CACHE_NAME_CHANNEL_RISKS)
    public List<ChannelRisk> findAll(){
        return channelRiskRepository.findAll();
    }

    @CacheEvict(value = CACHE_NAME_CHANNEL_RISK, key = "#channelRisk.channelId")
    public void save(ChannelRisk channelRisk){
        channelRiskRepository.save(channelRisk);
    }


}
