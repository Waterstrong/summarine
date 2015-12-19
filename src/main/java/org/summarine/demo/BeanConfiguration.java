package org.summarine.demo;

import org.summarine.core.annotation.MyBean;
import org.summarine.core.annotation.MyConfiguration;

@MyConfiguration
public class BeanConfiguration {

//    @Bean
//    public ITool smallShovel() {return new SmallShovel(); }

    @MyBean(name = "bigMachine")
    public ITool bigMachine() {
        return new BigMachine();
    }

    @MyBean
    public IVolume muteVolume() {
        return new Mute();
    }

    @MyBean
    public IVolume notMuteVolume() {
        return new Sound(5);
    }

}
