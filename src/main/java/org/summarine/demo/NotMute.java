package org.summarine.demo;

public class NotMute extends IVolume {
    private int volume;

    public NotMute() {
        this.volume = 25;
    }

    @Override
    public String play() {
        return "volume is " +this.volume;
    }
}
