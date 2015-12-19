package org.summarine.demo;

import org.summarine.core.annotation.Inject;
import org.summarine.core.annotation.MyComponent;

@MyComponent
public class BigMachine implements ITool {
    @Inject
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
