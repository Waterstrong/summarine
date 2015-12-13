package org.summarine.demo;

import org.summarine.core.annotation.Component;

@Component
public class Mute extends IVolume {

    @Override
    public String play() {
        return "volume is 0";
    }
}
