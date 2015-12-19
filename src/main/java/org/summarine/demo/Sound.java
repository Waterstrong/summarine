package org.summarine.demo;

import org.summarine.core.annotation.MyComponent;

@MyComponent
public class Sound extends IVolume {
    private int volume;

    public Sound(int volume) {
        this.volume = volume;
    }

    @Override
    public String play() {
        return "volume is " + this.volume;
    }
}
