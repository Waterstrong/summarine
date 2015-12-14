package org.summarine.demo;

import org.summarine.core.annotation.Bean;
import org.summarine.core.annotation.Configuration;

@Configuration
public class BeanConfiguration {

//    @Bean
//    public ITool smallShovel() {return new SmallShovel(); }

    @Bean(name = "bigMachine")
    public ITool bigMachine() {
        return new BigMachine();
    }

    @Bean
    public IVolume muteVolume() {
        return new Mute();
    }

    @Bean
    public IVolume notMuteVolume() {
        return new Sound(5);
    }

}
