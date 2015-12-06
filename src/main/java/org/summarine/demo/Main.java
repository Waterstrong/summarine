package org.summarine.demo;

import org.summarine.core.annotation.Component;
import org.summarine.core.container.DefaultContainer;

@Component
public class Main {

    public static void main(String[] args) {
        Worker worker = (Worker) new DefaultContainer().getBean("worker");
        System.out.println(worker.weed());
    }
}
