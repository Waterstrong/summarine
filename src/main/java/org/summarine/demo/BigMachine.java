package org.summarine.demo;

import org.summarine.core.annotation.Autowired;
import org.summarine.core.annotation.Component;

@Component
public class BigMachine implements ITool {
    @Autowired
    private IVolume notMuteVolume;

    @Override
    public int weed() {
        return 10;
    }

    @Override
    public String using() {
        return "big machine " + notMuteVolume.play();
    }
}
