package org.summarine.main;

import org.summarine.demo.Worker;
import org.summarine.core.container.DefaultContainer;

public class Main {
    public static void main(String[] args) {
        DefaultContainer defaultContainer = new DefaultContainer();
        Worker worker = (Worker) new DefaultContainer().getBean("worker");
        System.out.println(worker.weed());
    }
}
