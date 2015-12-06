package org.summarine.demo;

import org.summarine.core.annotation.Bean;
import org.summarine.core.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ITool buildSmallShovel() {return new SmallShovel(); }

    @Bean
    public ITool buildBigMachine() {
        return new BigMachine();
    }

    @Bean
    public IVolume buildMuteVolume() {
        return new Mute();
    }

    @Bean
    public IVolume buildNotMuteVolume() {
        return new NotMute();
    }

}
